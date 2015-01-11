package org.oop.controller;

import com.lowagie.text.DocumentException;
import org.oop.general.Validator;
import org.oop.services.PdfGenerator;
import org.oop.view.segreteria.FormTesi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

public class TesiController {

    private FormTesi view;

    public TesiController(FormTesi view) {
        this.view = view;
        view.insQuitFormButtonListener(new QuitFormAction());
        view.insSubmitFormButtonListener(new SubmitFormAction());
        view.setInfoUtente(BaseController.getUtenteCorrente());
        view.addFocusListenerMatricola(new FocusMatricola());
    }

    class FocusMatricola implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {

        }

        @Override
        public void focusLost(FocusEvent e) {
            if (!Validator.controlloCifre(view.getMatricola().getText(), 7)) {
                JOptionPane.showMessageDialog(null, "La matricola deve essere di 7 cifre");
                view.getMatricola().setText(" ");
            }
        }
    }

    class SubmitFormAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           /* Date datanascita = (Date) view.getDataNascita().getValue();
            String nome = view.getNome().getText();
            String cognome = view.getCognome().getText();
            String luogo = view.getLuogoNascita().getText();
            String email = view.getEmail().getText();
            String year = view.getAnnoCorso().getText();*/

            /* Se tutti i campi del form sono corretti crea e salva il PDF */
            if (view.isValid()) {

                /*String name = stringToCapital(nome);
                String surname = stringToCapital(cognome);*/

                //Apre schermata di salvataggio e genera il pdf
                JFileChooser c = new JFileChooser();
                int r = c.showSaveDialog(view.tesipanel);
                if (r == JFileChooser.APPROVE_OPTION) {
                    String path = c.getCurrentDirectory().toString().replace("\\", "\\\\");
                    String fileName = c.getSelectedFile().getName();

                    try {
                        PdfGenerator pdfGeneratorCreate = new PdfGenerator(System.getProperty("user.dir")
                                .concat(File.separator.concat("template"))
                                .concat(File.separator.concat("templateTesiPDF.pdf")), fileName);
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
            }
        }
    }

    class QuitFormAction implements ActionListener {
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
