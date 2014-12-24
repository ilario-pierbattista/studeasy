package org.oop.controller;

import org.oop.view.Segreteria;
import java.awt.event.ActionEvent;


public class SegreteriaController {

    private Segreteria view;

    public SegreteriaController(Segreteria view) {
        this.view = view;

        view.insFormButtonListener(new InsertFormAction() );

    }


    /**
     * action per completare il form di registrazione utente
     */
    class InsertFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


}
