package org.oop.controller;

import org.oop.view.FormModuli;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;


public class FormModuliController {

    private FormModuli view;

    public FormModuliController(FormModuli view) {
        this.view = view;
        view.addSubmitFormButtonListener(new submitFormAction());
        view.addQuitFormButtonListener(new quitFormAction());
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

        }
    }
}
