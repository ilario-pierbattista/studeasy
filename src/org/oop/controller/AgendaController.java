package org.oop.controller;

import org.oop.general.Utils;
import org.oop.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class AgendaController {
    private Agenda view;

    public AgendaController(Agenda view) {
        this.view = view;
        view.addLezioneButtonListener(new AddAttivitaAction());
        view.addEsameButtonListener(new AddAttivitaAction());
        view.addLaboratorioButtonListener(new AddAttivitaAction());
        view.addSeminarioButtonListener(new AddAttivitaAction());
        view.addProgettoButtonListener(new AddAttivitaAction());
        view.addCicloButtonListener(new AddCicloButton());
        view.addRemoveCicloButtonListener(new RemoveCicloButton());

        setListaCicliModel();

    }

    /**
     * Metodo che setta il model della lista dei cicli
     */
    public void setListaCicliModel(){
        //Dovra essere preso dal db
        view.setListaCicli(new DefaultListModel());
    }

    /**
     * Metodo che aggiunge un ciclo alla lista dei cicli
     */
    public void addCiclo(Object el){
        // modelListacicli.addElement(el);
    }

    /**
     * Action per aggiungere un'attività di tipo lezione
     */
    class AddAttivitaAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String activitytype = Utils.explodeStringForSpace(actionEvent.getActionCommand(),1);
            AttivitaView attivitaview = new AttivitaView(activitytype);
            AttivitaController attivitacontroller = new AttivitaController(attivitaview);
        }
    }

    /**
     * Action per aggiungere un'attività di tipo esame
     */
    class AddEsameAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            view.addAttivita(new AttivitaView("Esame"));
            Mainframe.refreshView();

            FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita(), "esame");

        }
    }


    /**
     * Action per aggiungere un'attività di tipo laboratorio
     */
    class AddLaboratorioAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.addAttivita(new AttivitaView("Laboratorio"));
            Mainframe.refreshView();

            FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita(), "laboratorio");

        }
    }

    /**
     * Action per aggiungere un'attività di tipo progetto
     */
    class AddProgettoAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.addAttivita(new AttivitaView("Progetto"));
            Mainframe.refreshView();

            FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita(), "progetto");

        }
    }

    /**
     * Action per aggiungere un'attività di tipo seminario
     */
    class AddSeminarioAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.addAttivita(new AttivitaView("Seminario"));
            Mainframe.refreshView();

            FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita(), "seminario");

        }
    }

    /**
     * Action per aprire il form di aggiunta ciclo
     */
    class AddCicloButton extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            FormCiclo cicloview = new FormCiclo();
            FormCicloController controllerciclo = new FormCicloController(cicloview);
        }
    }

    /**
     * Action per rimuovere un ciclo
     */
    class RemoveCicloButton extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}
