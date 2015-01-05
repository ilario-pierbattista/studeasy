package org.oop.controller;

import org.oop.view.segreteria.Iscrizione;

import java.awt.event.ActionEvent;

/**
 * Created by MelvinMancini on 30/12/14.
 */
public class IscrizioneController {
    private Iscrizione view;

    public IscrizioneController(Iscrizione view)
    {
        this.view=view;
        //Passo come argomento gli ascoltatori ai metodi della classe Iscrizione che permettono di settare gli
        // ascoltatori per i bottoni
        view.addAggiungiRigaButtonListener(new AggiungiRigaAction());
        view.addEliminaRigaButtonListener(new EliminaRigaAction());
        view.addConfermaButtonListener(new ConfermaAction());
    }

    public class AggiungiRigaAction extends AbstractAction{
        //Quando primo il bottone Aggiungi richiamo il metodo aggiungiriga() della classe Iscrizione
        @Override
        public void actionPerformed(ActionEvent e) {
            view.addRiga();
        }
    }
    public class EliminaRigaAction extends AbstractAction{
        //Quando primo il bottone Elimina richiamo il metodo eliminariga() della classe Iscrizione
        @Override
        public void actionPerformed(ActionEvent e) {
            view.eliminaRiga();
        }
    }

    public class ConfermaAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            //Inserire il codice che permette di salvare le modifiche fatte sul form Iscrizione
        }
    }
}
