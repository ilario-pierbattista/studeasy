package org.oop.controller;

import org.oop.view.FormRegistrazione;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static javax.xml.bind.DatatypeConverter.parseInt;
import static org.oop.general.Utils.*;


public class FormRegistrazioneController {

    private FormRegistrazione view;

    public FormRegistrazioneController(FormRegistrazione view) {
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
            String nome = view.getNome().getText();
            String cognome = view.getCognome().getText();
            String matricola = view.getMatricola().getText();
            String mail = view.getEmail().getText();
            String corso = view.getCorsolaurea().getText();

            if ( (inputNameControl(nome)) && (inputNameControl(cognome)) &&  (inputMatricolaControl(matricola)) && ( inputMailControl(mail)) && (inputCorsoLaureaControl(corso)) ) {

                String name = stringToCapital(nome);
                String surname = stringToCapital(cognome);
                //butta tutto  nel model e poi nel database

            }else if (!inputNameControl(nome)) {
                JOptionPane.showMessageDialog(null,"Nome non valido");
            }else if (!inputNameControl(cognome)) {
                JOptionPane.showMessageDialog(null,"Cognome non Valido");
            }else if (!inputMatricolaControl(matricola)) {
                JOptionPane.showMessageDialog(null,"Matricola non valida");
            }else if ( !inputMailControl(mail)) {
                JOptionPane.showMessageDialog(null,"Email Non Valida");
            }else if (!inputCorsoLaureaControl(corso)) {
                JOptionPane.showMessageDialog(null,"Corso non Valido");
            }


        }
    }
}
