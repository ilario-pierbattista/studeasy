package org.oop.controller;

import org.oop.view.Tasse;

import java.awt.event.ActionEvent;

/**
 * Created by MelvinMancini on 30/12/14.
 */
public class TasseController{
    private Tasse view;
    public TasseController(Tasse view)
    {
        this.view=view;
        view.addAggiungiTassaListener(new AddTassaAction());
        view.addEliminaTassaListener(new EliminaTassaAction());
        view.addConfermaTassaListener(new ConfermaTassaAction());
    }

    public class AddTassaAction extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.addTassa();

        }
    }

    public class EliminaTassaAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.eliminaTassa();
        }
    }

    public class ConfermaTassaAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            //Codice che permette di salvare le modifiche della Tasse view sul db
        }
    }
}
