package org.oop.controller;

import org.oop.view.Tesi;

import java.awt.event.ActionEvent;

public class TesiController {

    private Tesi view;

    public TesiController(Tesi view) {
        this.view = view;
        view.insQuitFormButtonListener(new QuitFormAction());
        view.insSubmitFormButtonListener(new SubmitFormAction());
    }

    class SubmitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            //butta nel database
        }
    }

    class QuitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            //cancella tutti i campi
        }
    }
}
