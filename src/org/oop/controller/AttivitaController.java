package org.oop.controller;

import org.oop.view.Mainframe;
import org.oop.view.agenda.*;

import java.awt.event.ActionEvent;


public class AttivitaController {
    private AttivitaView view;
    private FormAttivitaEvento formAttivitaEvento;
    private FormAttivitaPeriodica formAttivitaPeriodica;
    private FormEsame formEsame;
    private String newActivityType;

    public AttivitaController(AttivitaView view,String newActivityType){
        this.view = view;
        this.newActivityType = newActivityType;

        openForm();

        view.addEditButtonListener(new EditButtonAction());
    }

    private void openForm(){
        if (newActivityType.equals("progetto")|| newActivityType.equals("seminario")) {
            formAttivitaEvento = new FormAttivitaEvento();
            //formAttivitaEvento.addSubmitButtonListener();
            formAttivitaEvento.addCancelButtonListener(new CloseFormAction());
        } else if (newActivityType.equals("lezione") || newActivityType.equals("laboratorio")) {
            formAttivitaPeriodica = new FormAttivitaPeriodica();
            //formAttivitaPeriodica.addSubmitButtonListener();
            formAttivitaPeriodica.addCancelButtonListener(new CloseFormAction());
        } else {
            formEsame = new FormEsame();
            //formEsame.addSubmitButtonListener();
            formEsame.addCancelButtonListener(new CloseFormAction());
        }
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
         //Submit
        }
    }

    /**
     * Action per chiudere il form
     * @TODO: mezzo porchetto: chiude tutti a brutto muso (non può funzionare)
     */
    class CloseFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            formAttivitaPeriodica.closeFrame();
            formAttivitaEvento.closeFrame();
            formEsame.closeFrame();
        }
    }
}
