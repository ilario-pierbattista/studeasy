package org.oop.controller;

import org.oop.view.FormAttivita;

import java.awt.event.ActionEvent;

/**
 * Created by toioski on 24/12/14.
 */
public class FormAttivitaController {
    private FormAttivita view;

    public FormAttivitaController(FormAttivita view){
        this.view = view;
        view.addCancelButtonListener(new CloseFormAction());

    }

    /**
     * Action per chiudere il form
     */
    class CloseFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            FormAttivita.closeFrame();
        }
    }
}
