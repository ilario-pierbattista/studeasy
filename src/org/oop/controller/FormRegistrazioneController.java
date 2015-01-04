package org.oop.controller;

import org.oop.db.SQLParameters;
import org.oop.model.dao.CorsoDAO;
import org.oop.model.dao.UtenteDAO;
import org.oop.model.entities.Utente;
import org.oop.view.profilo.FormRegistrazione;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import static org.oop.general.Utils.*;


public class FormRegistrazioneController {

    private FormRegistrazione view;
    private Utente utente;
    private CorsoDAO corsoDAO;
    private boolean primoAvvio;

    public FormRegistrazioneController(FormRegistrazione view) {
        corsoDAO = new CorsoDAO();

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
     * @return Utente trovato oppure utente nuovo
     */
    private Utente cercaUtente() {
        UtenteDAO utenteDAO = new UtenteDAO();
        ArrayList<Utente> utenti = utenteDAO.findAll();
        primoAvvio = utenti.isEmpty();
        if(primoAvvio) {
            utente = new Utente();
        } else {
            utente = utenti.get(0);
        }
        return utente;
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

            if(ab.getText().equals("Triennale")) {
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
            String nome = view.getNome().getText();
            String cognome = view.getCognome().getText();
            String matricola = view.getMatricola().getText();
            String mail = view.getEmail().getText();
            String corso = view.getCorsolaurea().getText();

            if ((inputNameControl(nome)) && (inputNameControl(cognome)) && (inputMatricolaControl(matricola)) && (inputMailControl(mail)) && (inputSentenceControl(corso))) {

                String name = stringToCapital(nome);
                String surname = stringToCapital(cognome);
                //butta tutto  nel model e poi nel database

            } else if (!inputNameControl(nome)) {
                JOptionPane.showMessageDialog(null, "Nome non valido");
            } else if (!inputNameControl(cognome)) {
                JOptionPane.showMessageDialog(null, "Cognome non Valido");
            } else if (!inputMatricolaControl(matricola)) {
                JOptionPane.showMessageDialog(null, "Matricola non valida");
            } else if (!inputMailControl(mail)) {
                JOptionPane.showMessageDialog(null, "Email Non Valida");
            } else if (!inputSentenceControl(corso)) {
                JOptionPane.showMessageDialog(null, "Corso non Valido");
            }
        }
    }
}
