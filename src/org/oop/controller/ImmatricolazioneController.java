package org.oop.controller;

import com.lowagie.text.DocumentException;
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

import static org.oop.general.Utils.stringToCapital;

public class ImmatricolazioneController {
    private FormImmatricolazione view;

    public ImmatricolazioneController(FormImmatricolazione view) {
        this.view = view;

        view.insSubmitFormButtonListener(new SubmitFormAction());
        view.insQuitFormButtonListener(new QuitFormAction());
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
                view.getMatricola().setText("");
            }

        }

    }

    class SubmitFormAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Date datanascita = (Date) view.getDatanascita().getValue();

            String codicefiscale = view.getCodicefiscale().getText();
            String matricola = view.getMatricola().getText();
            //il voto delle superiori si assume in centesimi
//            int voto = Integer.parseInt(view.getVoto().getText());

            String anno = view.getAnnoConseguimento1().getText();
            String nome = view.getNome().getText();
            String cognome = view.getCognome().getText();
            String luogonascita = view.getLuogonascita().getText();
            String diploma = view.getDiploma().getText();

            if (view.isValid()) {

                String name = stringToCapital(nome);
                String surname = stringToCapital(cognome);
                //Apre schermata di salvataggio e genera il pdf
                JFileChooser c = new JFileChooser();
                int r = c.showSaveDialog(view.immatricolazionepanel);
                if (r == JFileChooser.APPROVE_OPTION) {
                    String path = c.getCurrentDirectory().toString().replace("\\", "\\\\");
                    String fileName = c.getSelectedFile().getName();

                    try {
                        PdfGenerator pdfGeneratorCreate = new PdfGenerator(System.getProperty("user.dir")
                                .concat(File.separator.concat("template"))
                                .concat(File.separator.concat("templateImmatricolazionePDF.pdf")), fileName);
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

            /*if ( (inputCodiceFiscaleControl(codicefiscale)) && (inputMatricolaControl(matricola) ) && ( (voto<=100) || (voto>=60) ) && (inputNameControl(nome)) && (inputNameControl(cognome)) && inputSentenceControl(luogonascita) && inputSentenceControl(diploma) && view.isValid() ) {
                String name = stringToCapital(nome);
                String surname = stringToCapital(cognome);
                //Apre schermata di salvataggio e genera il pdf
                JFileChooser c = new JFileChooser();
                int r = c.showSaveDialog(view.immatricolazionepanel);
                if (r == JFileChooser.APPROVE_OPTION) {
                    String path= c.getCurrentDirectory().toString().replace("\\","\\\\");
                    String fileName = c.getSelectedFile().getName();

                    try {
                        PdfGenerator pdfGeneratorCreate = new PdfGenerator(System.getProperty("user.dir")
                                .concat(File.separator.concat("template"))
                                .concat(File.separator.concat("templateImmatricolazionePDF.pdf")),fileName);
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
            } else if (!inputSentenceControl(luogonascita)) {
                JOptionPane.showMessageDialog(null,"Luogo Di Nascita Errato!");
            } else if (!inputSentenceControl(diploma)) {
                JOptionPane.showMessageDialog(null,"Diploma Errato!");
            } else {
                /*if (!view.isValid()) {
                    JOptionPane.showMessageDialog(null, "Anno Errato!");
                } else if ( nome.equals("") || cognome.equals("") || luogonascita.equals("") || diploma.equals("") || codicefiscale.equals("") || matricola.equals("")){
                    JOptionPane.showMessageDialog(null,"Uno o Più Campi devono essere riempiti");
                }
            }


        }*/


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
            view.getAnnoConseguimento2().setText("");
            view.getProvincia().setText("");
        }
    }
}


