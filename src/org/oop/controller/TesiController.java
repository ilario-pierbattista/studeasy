package org.oop.controller;

import com.lowagie.text.DocumentException;
import org.oop.view.Pdf;
import org.oop.view.segreteria.Tesi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Date;

import static org.oop.general.Utils.*;

public class TesiController {

    private Tesi view;

    public TesiController(Tesi view) {
        this.view = view;
        view.insQuitFormButtonListener(new QuitFormAction());
        view.insSubmitFormButtonListener(new SubmitFormAction());
    }

    class SubmitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            Date datanascita = (Date) view.getDataNascita().getValue();
            String nome = view.getNome().getText();
            String cognome = view.getCognome().getText();
            String luogo = view.getLuogoNascita().getText();
            String email = view.getEmail().getText();
            String year = view.getAnnoCorso().getText();
            if ((inputNameControl(nome)) && ( inputNameControl(cognome) ) && ( inputSentenceControl(luogo) ) && (inputMailControl(email) ) && inputYearControl(year) ) {
                String name = stringToCapital(nome);
                String surname = stringToCapital(cognome);
                //butta tutto nel modulo
            } else if (!inputNameControl(nome)) {
                JOptionPane.showMessageDialog(null,"Nome Non Valido!");
             } else if (!inputNameControl(cognome)) {
                JOptionPane.showMessageDialog(null,"Cognome Non Valido!");
            } else if (!inputSentenceControl(luogo)) {
                JOptionPane.showMessageDialog(null,"Luogo Non Valido!");
            } else if (!inputMailControl(email)) {
                JOptionPane.showMessageDialog(null,"Email non valida");
            } else if (!inputYearControl(year)) {
                JOptionPane.showMessageDialog(null,"Anno non Valido");
            }

            Pdf pdfCreate = new Pdf("templateTesiPDF.pdf","provaTesi.pdf",view);
            try {
                pdfCreate.generatePdf();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (DocumentException e1) {
                e1.printStackTrace();
            }

        }
    }

    class QuitFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            //cancella tutti i campi
            view.getDataNascita().setText("");
            view.getNome().setText("");
            view.getCognome().setText("");
            view.getLuogoNascita().setText("");
            view.getEmail().setText("");
            view.getAnnoCorso().setText("");
        }
    }
}
