package org.oop.controller;

import org.oop.view.Mainframe;
import org.oop.view.Segreteria;
import java.awt.event.ActionEvent;


public class SegreteriaController {

    private Segreteria view;

    public SegreteriaController(Segreteria view) {
        this.view = view;

        view.insFormButtonListener(new InsertFormAction() );

    }


    /**
     * action per completare il form di registrazione utente
     */
    class InsertFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

            //il comando seguente è una sorta di esempio, i dati da inserire nel form dovranno essere immessi da tastiera
            //poi passati come parametri al costruttore nella view di Segreteria
            //Segreteria formprofilo = new Segreteria(...);

            //per inserire la schermata di riempimento form:
            //formprofilo.addInsForm();

            Mainframe.refreshView();
        }
    }

    /**
     * Action per annullare l'immissione del form
     */
    class quitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    /**
     * Action per confermare l'immissione del form
     */
    class submitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


}
