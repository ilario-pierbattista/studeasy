package org.oop.controller;

import org.oop.db.SQLParameters;
import org.oop.model.dao.CorsoDAO;
import org.oop.model.dao.UtenteDAO;
import org.oop.model.entities.Insegnamento;
import org.oop.model.entities.InsegnamentoOfferto;
import org.oop.model.entities.Utente;
import org.oop.view.Mainframe;
import org.oop.view.profilo.FormRegistrazione;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class FormRegistrazioneController {

    private FormRegistrazione view;
    private Utente utente;
    private CorsoDAO corsoDAO;
    private UtenteDAO utenteDAO;
    private boolean primoAvvio;

    public FormRegistrazioneController(FormRegistrazione view) {
        corsoDAO = new CorsoDAO();
        utenteDAO = new UtenteDAO();

        this.view = view;
        view.addSubmitFormButtonListener(new submitFormAction());
        view.addQuitFormButtonListener(new quitFormAction());
        view.addLivelloRadiusButtonsListener(new changeLivelloAction());
        utente = cercaUtente();
        initInfo();

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

    private void aggiornaUtente(Utente nuoviDati) {
        if(!primoAvvio && utente.getMatricola() != nuoviDati.getMatricola()) {
            utenteDAO.remove(utente);
        } else if(!primoAvvio && utente.getLibretto().getCorso().getId() != nuoviDati.getLibretto().getCorso().getId()) {
            utenteDAO.remove(utente);
        }
        utente = nuoviDati;
        if(utente.getLibretto().getInsegnamenti().isEmpty()) {
            // Aggiunta degli insegnamenti obbligatori
            ArrayList<Insegnamento> insegnamenti = new ArrayList<Insegnamento>(10);
            for (InsegnamentoOfferto insegnamentoOfferto : utente.getLibretto().getCorso().getInsegnamentiObbligatori()) {
                insegnamenti.add(new Insegnamento(insegnamentoOfferto));
            }
            utente.getLibretto().setInsegnamenti(insegnamenti);
        }
        if(!utente.getLibretto().getCorso().getInsegnamentiOpzionali().isEmpty()) {
            /* Todo far apparire il form per opzionare gli insegnamenti a scelta */
        }
    }

    private void initInfo() {
        /* @TODO aggiornare le informazioni iniziali del form con i dati giò
           salvati
         */
    }

    class changeLivelloAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            AbstractButton ab = (AbstractButton) e.getSource();

            SQLParameters parameters = new SQLParameters();

            view.getTriennaleRadioButton().setSelected(false);
            view.getMagistraleRadioButton().setSelected(false);
            view.getCicloUnicoRadioButton().setSelected(false);

            if (ab.getText().equals("Triennale")) {
                view.getTriennaleRadioButton().setSelected(true);
                parameters.add("livello", 1);
            } else if (ab.getText().equals("Magistrale")) {
                view.getMagistraleRadioButton().setSelected(true);
                parameters.add("livello", 2);
            } else {
                view.getCicloUnicoRadioButton().setSelected(true);
                parameters.add("livello", 0);
            }
            view.setCorsiList(corsoDAO.findBy(parameters));
        }
    }

    /**
     * Action per annullare l'immissione del form
     */
    class quitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.frame.dispose();
        }
    }

    /**
     * Action per confermare l'immissione del form
     */
    class submitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.isValid()) {
                UtenteDAO utenteDAO = new UtenteDAO();
                Utente nuoviDati = view.getUtente();
                if (primoAvvio) {
                    utenteDAO.persist(nuoviDati);
                    BaseController.setUtenteCorrente(nuoviDati);
                } else {
                    /** @TODO gestire l'aggiornamento dei dati dell'utente */
                    BaseController.startController();
                }
                utenteDAO.flush();
            }
            view.frame.dispose();
            Mainframe.setVisible(true);
        }
    }
}
