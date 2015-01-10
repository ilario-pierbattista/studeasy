package org.oop.controller;

import org.oop.db.SQLParameters;
import org.oop.general.Validator;
import org.oop.model.dao.CorsoDAO;
import org.oop.model.dao.InsegnamentoDAO;
import org.oop.model.dao.UtenteDAO;
import org.oop.model.entities.Corso;
import org.oop.model.entities.Insegnamento;
import org.oop.model.entities.InsegnamentoOfferto;
import org.oop.model.entities.Utente;
import org.oop.view.Mainframe;
import org.oop.view.profilo.FormRegistrazione;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;


public class FormRegistrazioneController {

    private FormRegistrazione view;
    private Utente utente;
    private CorsoDAO corsoDAO;
    private UtenteDAO utenteDAO;
    private InsegnamentoDAO insegnamentoDAO;
    private boolean primoAvvio;

    public FormRegistrazioneController(FormRegistrazione view) {
        corsoDAO = new CorsoDAO();
        utenteDAO = new UtenteDAO();
        insegnamentoDAO = new InsegnamentoDAO();

        this.view = view;
        view.addSubmitFormButtonListener(new submitFormAction());
        view.addQuitFormButtonListener(new quitFormAction());
        view.addLivelloRadiusButtonsListener(new changeLivelloAction());
        view.addFocusListenerMatricola(new FocusMatricola());
        utente = cercaUtente();
        if (!primoAvvio) {
            view.initInfo(utente);
        }

        view.setVisible(true);
    }

    /**
     * Ricerca nel database se esiste già un utente registrato.
     * Nel caso esistesse già un utente registrato, imposta il form
     * per la modifica dei dati.
     * Nel caso in cui non esistesse alcun utente registrato, imposta
     * il form per una nuova registrazione.
     *
     * @return Utente trovato oppure utente nuovo
     */
    private Utente cercaUtente() {
        ArrayList<Utente> utenti = utenteDAO.findAll();
        primoAvvio = utenti.isEmpty();
        if (primoAvvio) {
            utente = null;
        } else {
            utente = utenti.get(0);
        }
        return utente;
    }

    /**
     * Crea od aggiorna un utente. Vi sono 4 casi:
     * 1) L'utente non esiste. Viene creato.
     * 2) L'utente ha un corso di laurea diverso. Bisogna reinserire anche gli insegnamenti.
     * 3) L'utente esiste e differisce solo per alcune informazioni. Viene aggiornato.
     *
     * @param nuoviDati Nuovi dati dell'utente
     */
    private void aggiornaUtente(Utente nuoviDati) {
        boolean compilazioneLibretto;

        // Il libretto viene compilato se non esiste alcun utente o se l'utente ha cambiato corso
        compilazioneLibretto = (primoAvvio ||
                utente.getLibretto().getCorso().getId() != nuoviDati.getLibretto().getCorso().getId()
        );

        if (!compilazioneLibretto) {
            nuoviDati.setLibretto(utente.getLibretto())
                    .setAgenda(utente.getAgenda());
        }
        utente = nuoviDati;
        // Impostazione del nuovo utente
        BaseController.setUtenteCorrente(utente);
        if (primoAvvio) {
            utenteDAO.persist(utente);
        } else {
            utenteDAO.update(utente);
        }
        if (compilazioneLibretto) {
            for (InsegnamentoOfferto insegnamentoOfferto : utente.getLibretto().getCorso().getInsegnamentiObbligatori()) {
                Insegnamento insegnamento = new Insegnamento(insegnamentoOfferto);
                utente.getLibretto().addInsegnamento(insegnamento);
                insegnamentoDAO.persist(insegnamento);
            }
            utenteDAO.update(utente);
        }
        utenteDAO.flush();
        if (ProfiloController.getInstance() != null) {
            ProfiloController.getInstance().updateView();
        }
    }

    class changeLivelloAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AbstractButton ab = (AbstractButton) e.getSource();
            SQLParameters parameters = new SQLParameters();
            int livello = getLivelloLaureaFromSelection(ab);
            parameters.add("livello", livello);
            view.setCorsiList(corsoDAO.findBy(parameters));
        }

        /**
         * Restituisce il livello del corso di laurea dalla selezione
         *
         * @param ab
         * @return
         */
        private int getLivelloLaureaFromSelection(AbstractButton ab) {
            int livello;
            view.getTriennaleRadioButton().setSelected(false);
            view.getMagistraleRadioButton().setSelected(false);
            view.getCicloUnicoRadioButton().setSelected(false);

            if (ab.getText().equals("Triennale")) {
                view.getTriennaleRadioButton().setSelected(true);
                livello = Corso.TRIENNALE;
            } else if (ab.getText().equals("Magistrale")) {
                view.getMagistraleRadioButton().setSelected(true);
                livello = Corso.MAGISTRALE;
            } else {
                view.getCicloUnicoRadioButton().setSelected(true);
                livello = Corso.CICLO_UNICO;
            }
            return livello;
        }
    }

    /**
     * Action per annullare l'immissione del form
     */
    class quitFormAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.frame.dispose();
        }
    }

    /**
     * Action per confermare l'immissione del form
     */
    class submitFormAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.isValid()) {
                Utente nuoviDati = view.getUtente();
                aggiornaUtente(nuoviDati);
                if (primoAvvio) {
                    BaseController.startController();
                }
                view.frame.dispose();
                Mainframe.setVisible(true);
            }
        }
    }

    class FocusMatricola implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {

        }

        @Override
        public void focusLost(FocusEvent e) {
            if (!Validator.controlloCifre(view.getMatricola().getText(), 7)) {
                JOptionPane.showMessageDialog(null, "La matricola deve essere di 7 cifre");
                view.getMatricola().setText("");
            }
        }
    }
}
