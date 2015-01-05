package org.oop.controller;

import org.oop.view.agenda.Agenda;
import org.oop.view.agenda.AttivitaView;
import org.oop.view.agenda.FormAttivita;
import org.oop.view.Mainframe;


import java.awt.event.ActionEvent;


public class FormAttivitaController {
    private FormAttivita view;
    private String type;

    public FormAttivitaController(FormAttivita view, String type){
        this.view = view;
        this.type = type;
        view.addCancelButtonListener(new CloseFormAction());
        view.addSubmitButtonListener(new SubmitFormAction());
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
            if (view.isValid()){
                String docente = view.getActivityname().getText();
                String aula = view.getAulafield().getText();
                String date = view.getDataField().getText();
                String description = view.getDescriptionfield().getText();

                Agenda.getInstance().addAttivita(new AttivitaView(type, docente, aula, date, description));
                Mainframe.refreshView();
                FormAttivita.closeFrame();
            }
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
