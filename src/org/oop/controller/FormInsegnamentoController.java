package org.oop.controller;

import org.oop.view.agenda.FormInsegnamento;
import org.oop.view.profilo.Profilo;

import java.awt.event.ActionEvent;

/**
 * Created by MelvinMancini on 02/01/15.
 */
public class FormInsegnamentoController {
    private FormInsegnamento view;
    private Profilo profiloView;

    public FormInsegnamentoController(FormInsegnamento view, Profilo profiloView) {
        this.view = view;
        this.profiloView = profiloView;
        view.addConfermaButtonListener(new ConfermaButtonAction());
        view.addAnnullaButtonListener(new AnnullaButtonAction());

        view.setVisible(true);
    }


    public class ConfermaButtonAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nomeinsegnamento = view.getTextFieldnomeinsegnamento().getText();
            String cfuinsegnamento = view.getFormattedTextFieldCFU().getText();
            String cicloinsegnamento = view.getFormattedTextFieldCiclo().getText();
            String datainsegnamento = view.getFormattedTextFieldData().getText();
            String votoinsegnamento = view.getFormattedTextFieldVoto().getText();
            profiloView.addElementTable(nomeinsegnamento, cicloinsegnamento, cfuinsegnamento, datainsegnamento, votoinsegnamento);
            view.frame.dispose();
            //verificare che i campi siano corretti e inviarli al metodo oppurtuno in modo tale da inserire una riga nella JTable del form profilo
        }
    }

    public class AnnullaButtonAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.frame.dispose();
        }
    }
}

