package org.oop.controller;

import com.lowagie.text.DocumentException;
import org.oop.services.PdfGenerator;
import org.oop.view.segreteria.Tesi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
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
            if ((inputAppelControl(nome)) && ( inputAppelControl(cognome) ) && ( inputSentenceControl(luogo) ) && (inputMailControl(email) ) && inputYearControl(year) ) {
                String name = stringToCapital(nome);
                String surname = stringToCapital(cognome);

                //Apre schermata di salvataggio e genera il pdf
                JFileChooser c = new JFileChooser();
                int r = c.showSaveDialog(view.tesipanel);
                if (r == JFileChooser.APPROVE_OPTION) {
                    String path= c.getCurrentDirectory().toString().replace("\\","\\\\");
                    String fileName = c.getSelectedFile().getName();

                    try {
                        PdfGenerator pdfGeneratorCreate = new PdfGenerator(System.getProperty("user.dir")
                                                                                                .concat(File.separator.concat("template"))
                                                                                                    .concat(File.separator.concat("templateTesiPDF.pdf")),fileName);
                        pdfGeneratorCreate.generatePdfTesi(view, path);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (DocumentException e1) {
                        e1.printStackTrace();
                    }

                }
                if (r == JFileChooser.CANCEL_OPTION) {
                    //chiudi finestra
                }


            } else if (!inputAppelControl(nome)) {
                JOptionPane.showMessageDialog(null,"Nome Non Valido!");
             } else if (!inputAppelControl(cognome)) {
                JOptionPane.showMessageDialog(null,"Cognome Non Valido!");
            } else if (!inputSentenceControl(luogo)) {
                JOptionPane.showMessageDialog(null,"Luogo Non Valido!");
            } else if (!inputMailControl(email)) {
                JOptionPane.showMessageDialog(null,"Email non valida");
            } else if (!inputYearControl(year)) {
                JOptionPane.showMessageDialog(null,"Anno non Valido");
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
            view.getProfRelatore().setText("");
            view.getTitoloTesi().setText("");
            view.getMatricola().setText("");
        }
    }
}
