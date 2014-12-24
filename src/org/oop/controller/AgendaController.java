package org.oop.controller;

import org.oop.view.Agenda;
import org.oop.view.Attivita;
import org.oop.view.FormAttivita;
import org.oop.view.Mainframe;

import java.awt.event.ActionEvent;


public class AgendaController{
    private Agenda view;

    public AgendaController(Agenda view) {
        this.view = view;
        view.addLezioneButtonListener(new AddLezioneAction());
        view.addEsameButtonListener(new AddEsameAction());
        view.addLaboratorioButtonListener(new AddLaboratorioAction());

    }

    /**
     * Action per aggiungere un'attività di tipo lezione
     */
    class AddLezioneAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.addLezione(new Attivita("Lezione"));
            Mainframe.refreshView();

            FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita());
        }
    }

    /**
     * Action per aggiungere un'attività di tipo esame
     */
    class AddEsameAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.addLezione(new Attivita("Esame"));
            Mainframe.refreshView();
        }
    }


    /**
     * Action per aggiungere un'attività di tipo laboratorio
     */
    class AddLaboratorioAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.addLezione(new Attivita("Laboratorio"));
            Mainframe.refreshView();
        }
    }


}
