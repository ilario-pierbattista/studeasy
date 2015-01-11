package org.oop.controller;

import com.lowagie.text.DocumentException;
import org.apache.commons.lang3.StringUtils;
import org.oop.general.Utils;
import org.oop.general.Validator;
import org.oop.services.PdfGenerator;
import org.oop.view.segreteria.FormImmatricolazione;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;


/**
 * Controller per gestire il form per l'immatricolazione
 */
public class ImmatricolazioneController {
    private FormImmatricolazione view;

    /**
     * Imposta i listeners alla vista
     * @param view Vista
     */
    public ImmatricolazioneController(FormImmatricolazione view) {
        this.view = view;

        view.insSubmitFormButtonListener(new SubmitFormAction());
        view.insQuitFormButtonListener(new QuitFormAction());
        view.setInfoUtente(BaseController.getUtenteCorrente());
        view.addFocusListenerMatricola(new FocusMatricola());
    }

    /**
     * Action per convalidare la matricola durante l'immissione
     */
    class FocusMatricola implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {

        }

        @Override
        public void focusLost(FocusEvent e) {
            if (!Validator.controlloCifre(view.getMatricola().getText(), 7)) {
                JOptionPane.showMessageDialog(null, "La matricola deve essere di 7 cifre");
                view.getMatricola().setText("");
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
                int r = c.showSaveDialog(view.immatricolazionepanel);
                if (r == JFileChooser.APPROVE_OPTION) {
                    String path = c.getCurrentDirectory().toString().replace("\\", "\\\\");
                    String fileName = c.getSelectedFile().getName();

                    try {
                        PdfGenerator pdfGeneratorCreate = new PdfGenerator(Utils.getResourceAsInputStream("template/templateImmatricolazionePDF.pdf"), fileName);
                        pdfGeneratorCreate.generatePdfImmatricolazione(view, path);
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
     * Action per gestire l'annullamento del form
     */
    class QuitFormAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //in questo caso l'annullamento del form comporterà la cancellazione di tutti i campi
            view.getMatricola().setText("");
            view.getNome().setText("");
            view.getCognome().setText("");
            view.getDatanascita().setText("");
            view.getLuogonascita().setText("");
            view.getCodicefiscale().setText("");
            view.getDiploma().setText("");
            view.getVoto().setText("");
            view.getAnnoConseguimento1().setText("");
            view.getProvincia().setText("");
        }
    }
}


