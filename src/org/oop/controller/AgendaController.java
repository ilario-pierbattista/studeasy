package org.oop.controller;

import org.oop.view.Agenda;
import org.oop.view.Attivita;
import org.oop.view.Mainframe;

import java.awt.event.ActionEvent;


public class AgendaController {
    private Agenda view;

    public AgendaController(Agenda view) {
        this.view = view;
        view.addLezioneButtonListener(new AddLezioneAction());
    }


    class AddLezioneAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.addLezione(new Attivita("Prova", "docente"));
            Mainframe.refreshView();
        }
    }
}
