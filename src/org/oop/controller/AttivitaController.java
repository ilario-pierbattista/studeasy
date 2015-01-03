package org.oop.controller;

import org.oop.view.agenda.AttivitaView;
import org.oop.view.agenda.FormAttivita;

import java.awt.event.ActionEvent;

/**
 * Created by toioski on 24/12/14.
 */
public class AttivitaController {
    private AttivitaView view;

    public AttivitaController(AttivitaView view){
        this.view = view;
        FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita(),"lezione");
        view.addEditButtonListener(new EditButtonAction());
    }

    class EditButtonAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Attenzione, il tipo dell'attivita ovviamente dovrà essere preso dal model, questo è solo un esempio statico
            FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita(),"lezione");
        }
    }
}
