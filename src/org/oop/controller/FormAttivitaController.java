package org.oop.controller;

import org.oop.view.FormAttivita;

import java.awt.event.ActionEvent;

/**
 * Created by toioski on 24/12/14.
 */
public class FormAttivitaController {
    private FormAttivita view;
    private String type;

    public FormAttivitaController(FormAttivita view, String type){
        this.view = view;
        this.type = type;
        view.addCancelButtonListener(new CloseFormAction());
        setAttivitaType();
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


    /**
     * Action per il submit del form
     */
    class SubmitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private void setAttivitaType(){
        if(type.equals("lezione")){
            view.getActivityname().setText("Lezione");
        } else if (type.equals("laboratorio")){
            view.getActivityname().setText("Laboratorio");
        } else if (type.equals("progetto")){
            view.getActivityname().setText("Progetto");
        } else if (type.equals("esame")){
            view.getActivityname().setText("Esame");
        } else if (type.equals("seminario")){
            view.getActivityname().setText("Seminario");
        }
    }
}
