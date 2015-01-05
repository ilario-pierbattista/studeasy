package org.oop.controller;

import org.oop.view.Mainframe;
import org.oop.view.agenda.Agenda;
import org.oop.view.agenda.AttivitaView;
import org.oop.view.agenda.FormAttivita;

import java.awt.event.ActionEvent;

/**
 * Created by toioski on 24/12/14.
 */
public class AttivitaController {
    private AttivitaView view;
    private FormAttivita formAttivita;
    private String newActivityType;

    public AttivitaController(AttivitaView view,String newActivityType){
        this.view = view;
        this.newActivityType = newActivityType;

        formAttivita = new FormAttivita();
        formAttivita.setType(newActivityType);


        view.addEditButtonListener(new EditButtonAction());
        formAttivita.addSubmitButtonListener(new SubmitFormAction());
        formAttivita.addCancelButtonListener(new CloseFormAction());
    }

    /**
     * Action per modifica un'attività
     */
    class EditButtonAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    /**
     * Action per il submit del form
     */
    class SubmitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (formAttivita.isValid()){
                String docente = formAttivita.getActivityname().getText();
                String aula = formAttivita.getAulafield().getText();
                String date = formAttivita.getDataField().getText();
                String description = formAttivita.getDescriptionfield().getText();

                Agenda.getInstance().addAttivita(new AttivitaView(newActivityType, docente, aula, date, description));
                Mainframe.refreshView();
                FormAttivita.closeFrame();
            }
        }
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
