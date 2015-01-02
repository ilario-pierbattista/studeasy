package org.oop.controller;

import org.oop.model.entities.Ciclo;
import org.oop.view.Agenda;
import org.oop.view.FormCiclo;

import java.awt.event.ActionEvent;
import java.util.Date;


public class FormCicloController {
    private FormCiclo view;

    public FormCicloController(FormCiclo view) {
        this.view = view;
        view.addCancelButtonListener(new CloseFormAction());
        view.addSubmitButtonListener(new SubmitFormAction());
    }

    /**
     * Action per validare il form
     */
    class SubmitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            Ciclo ciclo = new Ciclo();
            ciclo.setLabel(view.getCiclonamefield().getText());
            ciclo.setInizio((Date) view.getCiclostartfield().getValue());
            ciclo.setFine((Date) view.getCicloendfield().getValue());

            FormCiclo.closeFrame();
        }
    }

    /**
     * Action per chiudere il form
     */
    class CloseFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            FormCiclo.closeFrame();
        }
    }
}
