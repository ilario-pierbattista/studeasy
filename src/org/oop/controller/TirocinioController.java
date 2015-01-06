package org.oop.controller;

import com.lowagie.text.DocumentException;
import org.oop.services.PdfGenerator;
import org.oop.view.segreteria.Tirocinio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import static org.oop.general.Utils.*;

public class TirocinioController {
    Tirocinio view;

    public TirocinioController (Tirocinio view) {

        this.view = view;
        view.insQuitFormButtonListener(new QuitFormAction());
        view.insSubmitButtonListener(new SubmitFormAction() );
    }

    class SubmitFormAction extends AbstractAction {
        @Override
        public void actionPerformed (ActionEvent e) {
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

            if(view.isValid()){

                String name = stringToCapital(nome);
                String surname = stringToCapital(cognome);
                //Apre schermata di salvataggio e genera il pdf
                JFileChooser c = new JFileChooser();
                int r = c.showSaveDialog(view.tirociniopanel);
                if (r == JFileChooser.APPROVE_OPTION) {
                    String path= c.getCurrentDirectory().toString().replace("\\","\\\\");
                    String fileName = c.getSelectedFile().getName();
                    try {
                        PdfGenerator pdfGeneratorCreate = new PdfGenerator(System.getProperty("user.dir")
                                .concat(File.separator.concat("template"))
                                .concat(File.separator.concat("templateTirocinioPDF.pdf")),fileName);
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


            /*if ( inputNameControl(nome) && (crediti >=120) && inputNameControl(cognome) && inputMatricolaControl(matricola) && inputSentenceControl(luogonascita) && inputSentenceControl(residenza) && inputSentenceControl(via) && inputProvinciaControl(provincia) && inputCodiceFiscaleControl(codicefiscale) ) {
                String name = stringToCapital(nome);
                String surname = stringToCapital(cognome);
                //Apre schermata di salvataggio e genera il pdf
                JFileChooser c = new JFileChooser();
                int r = c.showSaveDialog(view.tirociniopanel);
                if (r == JFileChooser.APPROVE_OPTION) {
                    String path= c.getCurrentDirectory().toString().replace("\\","\\\\");
                    String fileName = c.getSelectedFile().getName();
                    try {
                        PdfGenerator pdfGeneratorCreate = new PdfGenerator(System.getProperty("user.dir")
                                .concat(File.separator.concat("template"))
                                .concat(File.separator.concat("templateTirocinioPDF.pdf")),fileName);
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
            } else if (!inputNameControl(nome)) {
                JOptionPane.showMessageDialog(null,"Nome Errato!");
            } else if (!inputNameControl(cognome)) {
                JOptionPane.showMessageDialog(null,"Cognome Errato!");
            } else if (!inputMatricolaControl(matricola)) {
                JOptionPane.showMessageDialog(null,"Matricola Errata!");
            } else if (!inputSentenceControl(luogonascita)) {
                JOptionPane.showMessageDialog(null,"Luogo Di Nascita Errato!");
            } else if (!inputSentenceControl(residenza)) {
                JOptionPane.showMessageDialog(null,"Residenza Errata!");
            } else if (!inputProvinciaControl(provincia)) {
                JOptionPane.showMessageDialog(null,"Provincia Errata!");
            } else if (!inputCodiceFiscaleControl(codicefiscale)) {
                JOptionPane.showMessageDialog(null,"Codice Fiscale Errato!");
            } else if (!inputSentenceControl(via)) {
                JOptionPane.showMessageDialog(null,"Via Errata");
            } else if (crediti<120) {
                JOptionPane.showMessageDialog(null,"Non hai abbastanza crediti!");
            }

*/
        }
    }

    class QuitFormAction extends AbstractAction {
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
