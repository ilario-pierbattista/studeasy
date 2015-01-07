package org.oop.services;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import org.oop.controller.BaseController;
import org.oop.model.entities.Utente;
import org.oop.view.segreteria.FormImmatricolazione;
import org.oop.view.segreteria.FormTesi;
import org.oop.view.segreteria.FormTirocinio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by MelvinMancini on 05/01/15.
 */
public class PdfGenerator {
    private String template;
    private String nomeFile;
    private Utente utente;

    public PdfGenerator(String nomeTemplate,String nome)
    {
        //template contiene la path della cartella template
        template=nomeTemplate;
        nomeFile = nome;
        utente= BaseController.getUtenteCorrente();
    }

    public void generatePdfTesi(FormTesi tesiView,String path) throws IOException,DocumentException {

        //the PdfReader will read the template
        PdfReader pdfTemplate = new PdfReader(this.getTemplate());

        FileOutputStream fileOutputStream = new FileOutputStream(path.concat(File.separator.
                                                                      concat(this.nomeFile).
                                                                      concat(".pdf")));

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        //PdfStamper will populate the fields dynamically with real time data.
        PdfStamper stamper = new PdfStamper(pdfTemplate, fileOutputStream);

        stamper.setFormFlattening(true);


        stamper.getAcroFields().setField("nome", utente.getNome());
        stamper.getAcroFields().setField("cognome", utente.getCognome());
        stamper.getAcroFields().setField("matricola", String.valueOf(utente.getMatricola()));
        stamper.getAcroFields().setField("dataNascita", tesiView.getDataNascita().getText());
        stamper.getAcroFields().setField("luogoNascita", tesiView.getLuogoNascita().getText());
        stamper.getAcroFields().setField("eMail", utente.getEmail());
        stamper.getAcroFields().setField("annoCorso", tesiView.getAnnoCorso().getText());
        stamper.getAcroFields().setField("professoreRelatore", tesiView.getProfRelatore().getText());
        stamper.getAcroFields().setField("titoloTesi", tesiView.getTitoloTesi().getText());

        pdfTemplate.close();
        stamper.close();
        pdfTemplate.close();
    }

    public void generatePdfTirocinio(FormTirocinio tirocinioView,String path) throws IOException,DocumentException {

        //the PdfReader will read the template
        PdfReader pdfTemplate = new PdfReader(this.getTemplate());

        FileOutputStream fileOutputStream = new FileOutputStream(path.concat(File.separator
                                                                                        .concat(this.nomeFile)
                                                                                        .concat(".pdf")));
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        //PdfStamper will populate the fields dynamically with real time data.
        PdfStamper stamper = new PdfStamper(pdfTemplate, fileOutputStream);

        stamper.setFormFlattening(true);

        stamper.getAcroFields().setField("nome", utente.getNome());
        stamper.getAcroFields().setField("cognome", utente.getCognome());
        stamper.getAcroFields().setField("matricola", String.valueOf(utente.getMatricola()));
        stamper.getAcroFields().setField("luogoNascita", tirocinioView.getLuogonascita().getText());
        stamper.getAcroFields().setField("dataNascita", tirocinioView.getDatanascita().getText());
        stamper.getAcroFields().setField("residenza", tirocinioView.getResidenza().getText());
        stamper.getAcroFields().setField("provincia", tirocinioView.getProvincia().getText());
        stamper.getAcroFields().setField("cap", tirocinioView.getCap().getText());
        stamper.getAcroFields().setField("via",tirocinioView.getVia().getText());
        stamper.getAcroFields().setField("codiceFiscale",tirocinioView.getCodicefiscale().getText());

        pdfTemplate.close();
        stamper.close();
        pdfTemplate.close();
    }

    public void generatePdfImmatricolazione(FormImmatricolazione immatricolazioneView,String path) throws IOException,DocumentException {

        //the PdfReader will read the template
        PdfReader pdfTemplate = new PdfReader(this.getTemplate());

        FileOutputStream fileOutputStream = new FileOutputStream(path.concat(File.separator
                                                                                        .concat(this.nomeFile)
                                                                                        .concat(".pdf")));
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        //PdfStamper will populate the fields dynamically with real time data.
        PdfStamper stamper = new PdfStamper(pdfTemplate, fileOutputStream);

        stamper.setFormFlattening(true);

        stamper.getAcroFields().setField("nome", utente.getNome());
        stamper.getAcroFields().setField("cognome", utente.getCognome());
        stamper.getAcroFields().setField("matricola", String.valueOf(utente.getMatricola()));
        stamper.getAcroFields().setField("luogoNascita", immatricolazioneView.getLuogonascita().getText());
        stamper.getAcroFields().setField("dataNascita", immatricolazioneView.getDatanascita().getText());
        stamper.getAcroFields().setField("provinciaNascita", immatricolazioneView.getProvincia().getText());
        stamper.getAcroFields().setField("codiceFiscale", immatricolazioneView.getCodicefiscale().getText());
        stamper.getAcroFields().setField("tipologiaScuolaSuperiore", immatricolazioneView.getDiploma().getText());
        stamper.getAcroFields().setField("voto",immatricolazioneView.getVoto().getText());
        stamper.getAcroFields().setField("anno1",immatricolazioneView.getAnnoConseguimento1().getText());
        stamper.getAcroFields().setField("anno2",immatricolazioneView.getAnnoConseguimento2().getText());

        pdfTemplate.close();
        stamper.close();
        pdfTemplate.close();
    }

    public String getTemplate() {
        return template;
    }

    public String getNomeFile() {
        return nomeFile;
    }
}
