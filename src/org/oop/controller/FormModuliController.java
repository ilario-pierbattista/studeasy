package org.oop.controller;

import org.oop.view.FormModuli;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.oop.general.Utils.inputYearControl;


public class FormModuliController {

    private FormModuli view;

    public FormModuliController(FormModuli view) {
        this.view = view;
        view.addSubmitFormButtonListener(new submitFormAction());
        view.addQuitFormButtonListener(new quitFormAction());
    }



    /**
     * Action per annullare l'immissione del form
     */
    class quitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.frame.dispose();
        }
    }

    /**
     * Action per confermare l'immissione del form
     */
    class submitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

            //la funzione di controllo anno va implementqta diversamente
            String annoacc = view.getAnnoaccademico().getText();
            if (inputYearControl(annoacc)) {
                //buttalo nel model e nel database
                //System.out.println(annoacc);
            } else {
                JOptionPane.showMessageDialog(null,"Anno non Valido");
            }
        }
    }
}
