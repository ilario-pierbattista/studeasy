package org.oop.controller;

import org.oop.view.segreteria.FormTasse;
import org.oop.view.segreteria.Tasse;

import java.awt.event.ActionEvent;


public class TasseController{
    private Tasse view;
    private FormTasse form;

    public TasseController(Tasse view)
    {
        this.view=view;
        view.addAddButtonListener(new addTassaAction());
        view.addRemoveButtonListener(new eliminaTassaAction());
        view.addEditButtonListener(new ConfermaTassaAction());
    }

    /**
     * Action per aprire il form di aggiunta tassa
     */
    class addTassaAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.addTassa(); //Va tolto
            form = new FormTasse();
            form.addSubmitButtonListener(new submitFormAction());
            form.addCancelButtonListener(new closeFormAction());
        }
    }

    /**
     * Action per il submit del form
     */
    class submitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Submit form
        }
    }

    /**
     * Action per chiudere il form
     */
    class closeFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            form.closeFrame();
        }
    }

    /**
     * Action per eliminare una tassa dalla tabella
     */
    class eliminaTassaAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.eliminaTassa();
        }
    }

    /**
     * Action per modificare una tassa
     */
    class ConfermaTassaAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            //Modifica della tassa
        }
    }
}
