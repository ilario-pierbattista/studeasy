package org.oop.controller;

import org.oop.view.Tirocinio;

import java.awt.event.ActionEvent;

public class TirocinioController {
    Tirocinio view;

    public TirocinioController (Tirocinio view) {

        this.view = view;
        view.insQuitFormButtonListener(new SubmitFormAction());
        view.insSubmitButtonListener(new QuitFormAction() );
    }

    class SubmitFormAction extends AbstractAction {
        @Override
        public void actionPerformed (ActionEvent e) {
            //controlla che ci siano 120 crediti
            //butta tutto nel database
        }
    }

    class QuitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            //cancella tutti i campi
        }
    }
}
