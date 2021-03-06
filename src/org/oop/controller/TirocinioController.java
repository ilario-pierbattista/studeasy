package org.oop.controller;

import com.lowagie.text.DocumentException;
import org.oop.general.Utils;
import org.oop.general.Validator;
import org.oop.services.PdfGenerator;
import org.oop.view.segreteria.FormTirocinio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;


/**
 * Controller per gestire il form del tirocinio
 */
public class TirocinioController {
    private FormTirocinio view;

    public TirocinioController(FormTirocinio view) {

        this.view = view;
        view.insQuitFormButtonListener(new QuitFormAction());
        view.insSubmitButtonListener(new SubmitFormAction());
        view.setInfoUtente(BaseController.getUtenteCorrente());
        view.addFocusListenerCap(new FocusCap());
        view.addFocusListenerMatricola(new FocusMatricola());
        view.addFocusListenerCfu(new FocusCfu());
    }

    /**
     * Action per controllare la validità dei cfu durante l'immissione
     */
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

    /**
     * Action per controllare la validità della matricola durante l'immissione
     */
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

    /**
     * Action per controllare la validità del cap durante l'immissione
     */
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

    /**
     * Action per gestire il submit del form
     */
    class SubmitFormAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.isValid()) {
                //Apre schermata di salvataggio e genera il pdf
                JFileChooser c = new JFileChooser();
                int r = c.showSaveDialog(view.tirociniopanel);
                if (r == JFileChooser.APPROVE_OPTION) {
                    String path = c.getCurrentDirectory().toString().replace("\\", "\\\\");
                    String fileName = c.getSelectedFile().getName();
                    try {
                        PdfGenerator pdfGeneratorCreate = new PdfGenerator(Utils.getResourceAsInputStream("template/templateTirocinioPDF.pdf"), fileName);
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

    /**
     * Action per gestire la chiusura del form
     */
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
