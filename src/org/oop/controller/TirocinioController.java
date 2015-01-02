package org.oop.controller;

import org.oop.view.Tirocinio;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.oop.general.Utils.*;

public class TirocinioController {
    Tirocinio view;

    public TirocinioController (Tirocinio view) {

        this.view = view;
        view.insQuitFormButtonListener(new SubmitFormAction());
        view.insSubmitButtonListener(new QuitFormAction() );
    }

    class SubmitFormAction extends AbstractAction {
        @Override
        public void actionPerformed (ActionEvent e) {
            //controlla che ci siano 120 crediti
            //butta tutto nel database
            JFormattedTextField datanascita = view.getDatanascita();
            JFormattedTextField cap = view.getCap();
            JFormattedTextField cfu = view.getCfu();

            String nome = view.getNome().getText();
            String cognome = view.getCognome().getText();
            String matricola = view.getMatricola().getText();
            String luogonascita = view.getLuogonascita().getText();
            String residenza = view.getResidenza().getText();
            String provincia = view.getProvincia().getText();
            String via = view.getVia().getText();
            String codicefiscale = view.getCodicefiscale().getText();


            if ( inputNameControl(nome) && inputNameControl(cognome) && inputMatricolaControl(matricola) && inputCorsoLaureaControl(luogonascita) && inputCorsoLaureaControl(residenza) && inputProvinciaControl(provincia) && inputCodiceFiscaleControl(codicefiscale) ) {
                String name = stringToCapital(nome);
                String surname = stringToCapital(cognome);
                //butta tutto nel modulo
            } else if (!inputNameControl(nome)) {
                JOptionPane.showMessageDialog(null,"Nome Errato!");
            } else if (!inputNameControl(cognome)) {
                JOptionPane.showMessageDialog(null,"Cognome Errato!");
            } else if (!inputMatricolaControl(matricola)) {
                JOptionPane.showMessageDialog(null,"Matricola Errata!");
            } else if (!inputCorsoLaureaControl(luogonascita)) {
                JOptionPane.showMessageDialog(null,"Luogo Di Nascita Errato!");
            } else if (!inputCorsoLaureaControl(residenza)) {
                JOptionPane.showMessageDialog(null,"Residenza Errata!");
            } else if (!inputProvinciaControl(provincia)) {
                JOptionPane.showMessageDialog(null,"Provincia Errata!");
            } else if (!inputCodiceFiscaleControl(codicefiscale)) {
                JOptionPane.showMessageDialog(null,"Codice Fiscale Errato!");
            }
        }
    }

    class QuitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            //cancella tutti i campi
        }
    }
}
