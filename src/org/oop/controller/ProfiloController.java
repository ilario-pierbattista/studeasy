package org.oop.controller;

import org.oop.view.FormRegistrazione;
import org.oop.view.Mainframe;
import org.oop.view.profilo.Profilo;

import java.awt.event.ActionEvent;


public class ProfiloController {
    private Profilo view;

    public ProfiloController(Profilo view) {
        this.view = view;
        view.addTableListener(new addTableElementAction());
        view.addDeletetableListener(new addDeleteElementAction());
        view.insFormButtonListener(new FormAction());
    }

    class addTableElementAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.addElementTable();
        }
    }
    class addDeleteElementAction extends AbstractAction{
        public void actionPerformed(ActionEvent e){
            view.DeleteElementTable();
        }
    }

    /**
     * action per aprire il form di registrazione
     */
    class FormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

            //apro il form in un'altra finestra
            FormRegistrazione form = new FormRegistrazione();
            FormRegistrazioneController formcontroller = new FormRegistrazioneController(form);

            Mainframe.refreshView();
        }
    }
}
