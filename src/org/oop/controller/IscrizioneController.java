package org.oop.controller;

import org.oop.view.segreteria.FormIscrizione;
import org.oop.view.segreteria.Iscrizione;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class IscrizioneController {
    private Iscrizione view;
    private FormIscrizione form;

    public IscrizioneController(Iscrizione view) {
        this.view = view;

        view.addAddButtonListener(new addIscrizioneAction());
        view.addDeleteButtonListener(new deleteIscrizioneAction());
        view.addEditButtonListener(new editIscrizioneAction());
    }

    /**
     * Action per far partire il form di aggiunta
     */
    class addIscrizioneAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.addRiga(); //Va tolto
            form = new FormIscrizione();
            form.addSubmitButtonListener(new submitFormAction());
            form.addCancelButtonListener(new closeFormAction());
        }
    }

    /**
     * Action per il submit del form di aggiunta di iscrizione
     */
    class submitFormAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Submit
        }
    }

    /**
     * Action per chiudere il form
     */
    class closeFormAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            form.closeFrame();
        }
    }

    /**
     * Action che elimina una riga dalla tabella delle iscrizioni
     */
    class deleteIscrizioneAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.eliminaRiga();
        }
    }

    /**
     * Action per modificare un'iscrizione
     */
    class editIscrizioneAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
}
