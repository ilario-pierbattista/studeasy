package org.oop.controller;

import org.oop.view.Immatricolazione;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import static org.oop.general.Utils.*;

public class ImmatricolazioneController {
    private Immatricolazione view;

    public ImmatricolazioneController(Immatricolazione view) {
        this.view = view;

        view.insSubmitFormButtonListener(new SubmitFormAction());
        view.insQuitFormButtonListener(new QuitFormAction());
    }

    class SubmitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) throws NumberFormatException {
            JFormattedTextField datanascita = view.getDatanascita();
            JFormattedTextField anno = view.getAnnoconseguimento();

            //DateFormat dateformat = new SimpleDateFormat("dd-mm-yyyy");
            //datanascita.setValue(dateformat);
            //anno.setValue(dateformat);

            String codicefiscale = view.getCodicefiscale().getText();
            String matricola = view.getMatricola().getText();

            //il voto delle superiori si assume in centesimi
            int voto = Integer.parseInt(view.getVoto().getText());



            String nome = view.getNome().getText();
            String cognome = view.getCognome().getText();
            String luogonascita = view.getLuogonascita().getText();
            String diploma = view.getDiploma().getText();

            if ( (inputCodiceFiscaleControl(codicefiscale)) && (inputMatricolaControl(matricola) ) && ( (voto<=100) || (voto>=60) ) && (inputNameControl(nome)) && (inputNameControl(cognome)) && inputCorsoLaureaControl(luogonascita) && inputCorsoLaureaControl(diploma) && inputYearControl(anno.getText()) ) {
                String name = stringToCapital(nome);
                String surname = stringToCapital(cognome);
                //butta tutto nel modulo
            }
            else if (!inputCodiceFiscaleControl(codicefiscale)) {
                JOptionPane.showMessageDialog(null,"Codice Fiscale Errato!");
            } else if (!inputMatricolaControl(matricola)) {
                JOptionPane.showMessageDialog(null,"Matricola Errata!");
            } else if ( (voto >100) || (voto < 60) ){
                JOptionPane.showMessageDialog(null,"Voto Conseguito Errato");
            } else if (!inputNameControl(nome)) {
                JOptionPane.showMessageDialog(null,"Nome Errato!");
            } else if (!inputNameControl(cognome)) {
                JOptionPane.showMessageDialog(null,"Cognome Errato!");
            } else if (!inputCorsoLaureaControl(luogonascita)) {
                JOptionPane.showMessageDialog(null,"Luogo Di Nascita Errato!");
            } else if (!inputCorsoLaureaControl(diploma)) {
                JOptionPane.showMessageDialog(null,"Diploma Errato!");
            } else {
                if (!inputYearControl(anno.getText())) {
                    JOptionPane.showMessageDialog(null, "Anno Errato!");
                } else if ( nome.equals("") || cognome.equals("") || luogonascita.equals("") || diploma.equals("") || codicefiscale.equals("") || matricola.equals("")){
                    JOptionPane.showMessageDialog(null,"Uno o Più Campi devono essere riempiti");
                }
            }

        }
    }

    class QuitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            //in questo caso l'annullamento del form comporterà la cancellazione di tutti i campi
            view.getMatricola().setValue(null);
            view.getMatricola().setText("");
            //view.getNome().setText("");
            //view.getCognome().setText("");
            //view.getDatanascita().setText("");
            //view.getLuogonascita().setText("");
            //view.getCodicefiscale().setText("");
            //view.getDiploma().setText("");
            //view.getVoto().setText("");
            //view.getAnnoconseguimento().setText("");
        }
    }
}
