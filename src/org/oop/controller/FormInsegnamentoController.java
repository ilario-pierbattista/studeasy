package org.oop.controller;

import org.oop.db.SQLParameters;
import org.oop.view.FormInsegnamento;
import org.oop.view.Profilo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by MelvinMancini on 02/01/15.
 */
public class FormInsegnamentoController {
    private FormInsegnamento view;
    private Profilo profiloView;

    public FormInsegnamentoController(FormInsegnamento view, Profilo profiloView) {
        this.view = view;
        this.profiloView=profiloView;
        view.addConfermaButtonListener(new ConfermaButtonAction());
        view.addAnnullaButtonListener(new AnnullaButtonAction());
        view.addRadioListener(new RadioAction());
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
            profiloView.addElementTable(nomeinsegnamento,cicloinsegnamento,cfuinsegnamento,datainsegnamento,votoinsegnamento);
            view.frame.dispose();
            //verificare che i campi siano corretti e inviarli al metodo oppurtuno in modo tale da inserire una riga nella JTable del form profilo
        }
    }

    public class AnnullaButtonAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.frame.dispose();
        }
    }

    public class RadioAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            AbstractButton ab = (AbstractButton) e.getSource();

            //SQLParameters parameters = new SQLParameters();

            view.getTriennaleRadioButton().setSelected(false);
            view.getMagistraleRadioButton().setSelected(false);
            view.getCicloUnicoRadioButton().setSelected(false);

            if(ab.getText().equals("Triennale")) {
                view.getTriennaleRadioButton().setSelected(true);
               // parameters.add("livello", 1);
            } else if (ab.getText().equals("Magistrale")) {
                view.getMagistraleRadioButton().setSelected(true);
                //parameters.add("livello", 2);
            } else {
                view.getCicloUnicoRadioButton().setSelected(true);
                //parameters.add("livello", 0);
            }
            //view.setCorsiList(corsoDAO.findBy(parameters));
        }
        }
    }

