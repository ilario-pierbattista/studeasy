package org.oop.controller;

import org.oop.view.segreteria.FormTasse;
import org.oop.view.segreteria.Tasse;
import sun.jvm.hotspot.utilities.soql.JSJavaFactoryImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TasseController {
    private Tasse view;
    private FormTasse form;

    public TasseController(Tasse view) {
        this.view = view;
        view.addAddButtonListener(new addTassaAction());
        view.addRemoveButtonListener(new eliminaTassaAction());
        view.addEditButtonListener(new ConfermaTassaAction());
    }

    /**
     * Action per aprire il form di aggiunta tassa
     */
    class addTassaAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.addTassa(); //Va tolto
            form = new FormTasse();
            form.addSubmitButtonListener(new submitFormAction());
            form.addCancelButtonListener(new closeFormAction());
            form.addPagamentoRadioButtonListener(new SetPagamentoTassaAction());
        }
    }

    /**
     * Action per il submit del form
     */
    class submitFormAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (form.isValid()){

            }
        }
    }

    /**
     * Action per chiudere il form
     */
    class closeFormAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            form.closeFrame();
        }
    }

    /**
     * Action per eliminare una tassa dalla tabella
     */
    class eliminaTassaAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.eliminaTassa();
        }
    }

    /**
     * Action per modificare una tassa
     */
    class ConfermaTassaAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Modifica della tassa
        }
    }

    class SetPagamentoTassaAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AbstractButton button = (AbstractButton) e.getSource();
            manageRadioButton(button);
        }

        private void manageRadioButton(AbstractButton b) {
            form.getPagatoRadioButton().setSelected(false);
            form.getNonPagatoRadioButton().setSelected(false);

            if (b.getText().equals("Pagato")) {
                form.getPagatoRadioButton().setSelected(true);
            } else {
                form.getNonPagatoRadioButton().setSelected(true);
            }
        }
    }
}
