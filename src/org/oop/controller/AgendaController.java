package org.oop.controller;

import org.oop.view.Agenda;
import org.oop.view.AttivitaView;
import org.oop.view.FormAttivita;
import org.oop.view.Mainframe;

import java.awt.event.ActionEvent;


public class AgendaController{
    private Agenda view;

    public AgendaController(Agenda view) {
        this.view = view;
        view.addLezioneButtonListener(new AddAttivitaAction());
        view.addEsameButtonListener(new AddEsameAction());
        view.addLaboratorioButtonListener(new AddLaboratorioAction());
        view.addSeminarioButtonListener(new AddSeminarioAction());
        view.addProgettoButtonListener(new AddProgettoAction());

    }

    /**
     * Action per aggiungere un'attività di tipo lezione
     */
    class AddAttivitaAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            AttivitaView attivitaview = new AttivitaView("Lezione");

            AttivitaController attivitacontroller = new AttivitaController(attivitaview);

            view.addAttivita(attivitaview);
            Mainframe.refreshView();
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

            FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita(),"esame");

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

            FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita(),"laboratorio");

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

            FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita(),"progetto");

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

            FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita(),"seminario");

        }
    }


}
