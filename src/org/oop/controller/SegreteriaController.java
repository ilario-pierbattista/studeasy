package org.oop.controller;

import org.oop.view.FormModuli;
import org.oop.view.Mainframe;
import org.oop.view.Segreteria;

import java.awt.event.ActionEvent;


public class SegreteriaController {

    private Segreteria view;

    public SegreteriaController(Segreteria view) {
        this.view = view;

        view.insFormButtonListener(new FormAction() );


    }


    /**
     * action per aprire il form di registrazione
     */
    class FormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

            //apro il form in un'altra finestra
            FormModuli form = new FormModuli();
            FormModuliController formcontroller = new FormModuliController(form);

            Mainframe.refreshView();
        }
    }




}
