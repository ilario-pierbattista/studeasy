package org.oop.controller;

import org.oop.view.Immatricolazione;

import java.awt.event.ActionEvent;

public class ImmatricolazioneController {
    private Immatricolazione view;

    public ImmatricolazioneController(Immatricolazione view) {
        this.view = view;

        view.insSubmitFormButtonListener(new SubmitFormAction());
        view.insSubmitFormButtonListener(new QuitFormAction());
    }

    class SubmitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    class QuitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            //in questo caso l'annullamento del form comporterà la cancellazione di tutti i campi
        }
    }
}
