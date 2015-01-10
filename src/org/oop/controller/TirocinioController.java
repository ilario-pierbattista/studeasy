package org.oop.controller;

import com.lowagie.text.DocumentException;
import org.apache.commons.lang3.StringUtils;
import org.oop.general.Validator;
import org.oop.services.PdfGenerator;
import org.oop.view.segreteria.FormTirocinio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

public class TirocinioController {
    FormTirocinio view;

    public TirocinioController(FormTirocinio view) {

        this.view = view;
        view.insQuitFormButtonListener(new QuitFormAction());
        view.insSubmitButtonListener(new SubmitFormAction());
        view.setInfoUtente(BaseController.getUtenteCorrente());
        view.addFocusListenerCap(new FocusCap());
        view.addFocusListenerMatricola(new FocusMatricola());
        view.addFocusListenerCfu(new FocusCfu());
    }


    class FocusCfu implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {

        }

        @Override
        public void focusLost(FocusEvent e) {
            if (!Validator.controlloCifre(view.getCfu().getText(), 3)) {
                JOptionPane.showMessageDialog(null, "Il numero dei CFU deve essere almeno 120 per conseguire il Tirocinio.");
                view.getCfu().setText(" ");
            }

        }
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

    class FocusCap implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {

        }

        @Override
        public void focusLost(FocusEvent e) {
            if (!Validator.controlloCifre(view.getCap().getText(), 5)) {
                JOptionPane.showMessageDialog(null, "Il CAP deve essere di 5 cifre");
                view.getCap().setText(" ");
            }
        }
    }

    class SubmitFormAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFormattedTextField datanascita = view.getDatanascita();
            JFormattedTextField cap = view.getCap();
            JFormattedTextField cfu = view.getCfu();
            //int crediti = Integer.parseInt(cfu.getText());

            String nome = view.getNome().getText();
            String cognome = view.getCognome().getText();
            String matricola = view.getMatricola().getText();
            String luogonascita = view.getLuogonascita().getText();
            String residenza = view.getResidenza().getText();
            String provincia = view.getProvincia().getText();
            String via = view.getVia().getText();
            String codicefiscale = view.getCodicefiscale().getText();

            if (view.isValid()) {

                String name = StringUtils.capitalize(nome);
                String surname = StringUtils.capitalize(cognome);
                //Apre schermata di salvataggio e genera il pdf
                JFileChooser c = new JFileChooser();
                int r = c.showSaveDialog(view.tirociniopanel);
                if (r == JFileChooser.APPROVE_OPTION) {
                    String path = c.getCurrentDirectory().toString().replace("\\", "\\\\");
                    String fileName = c.getSelectedFile().getName();
                    try {
                        PdfGenerator pdfGeneratorCreate = new PdfGenerator(System.getProperty("user.dir")
                                .concat(File.separator.concat("template"))
                                .concat(File.separator.concat("templateTirocinioPDF.pdf")), fileName);
                        pdfGeneratorCreate.generatePdfTirocinio(view, path);
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
            view.getDatanascita().setText("");
            view.getCap().setText("");
            view.getCfu().setText("");
            view.getNome().setText("");
            view.getCognome().setText("");
            view.getMatricola().setText("");
            view.getLuogonascita().setText("");
            view.getResidenza().setText("");
            view.getProvincia().setText("");
            view.getVia().setText("");
            view.getCodicefiscale().setText("");
        }
    }
}
