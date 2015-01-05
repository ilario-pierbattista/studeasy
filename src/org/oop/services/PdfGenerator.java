package org.oop.services;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import org.oop.view.segreteria.Immatricolazione;
import org.oop.view.segreteria.Tesi;
import org.oop.view.segreteria.Tirocinio;

/**
 * Created by MelvinMancini on 05/01/15.
 */
public class PdfGenerator {
    private String template;
    private String nomeFile;

    public PdfGenerator(String nomeTemplate, String file)
    {
        template=nomeTemplate;
        nomeFile=file;


    }

    public void generatePdfTesi(Tesi tesiView,String path,String fileName) throws IOException,DocumentException {

        //the PdfReader will read the template
        PdfReader pdfTemplate = new PdfReader(this.getTemplate());

        FileOutputStream fileOutputStream = new FileOutputStream(path.concat("\\".concat(fileName).concat(".pdf")));

        //FileOutputStream fileOutputStream = new FileOutputStream(this.getNomeFile());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        //PdfStamper will populate the fields dynamically with real time data.
        PdfStamper stamper = new PdfStamper(pdfTemplate, fileOutputStream);

        stamper.setFormFlattening(true);


        stamper.getAcroFields().setField("nome", tesiView.getNome().getText());
        stamper.getAcroFields().setField("cognome", tesiView.getCognome().getText());
        stamper.getAcroFields().setField("dataNascita", tesiView.getDataNascita().getText());
        stamper.getAcroFields().setField("luogoNascita", tesiView.getLuogoNascita().getText());
        stamper.getAcroFields().setField("eMail", tesiView.getEmail().getText());
        stamper.getAcroFields().setField("annoCorso", tesiView.getAnnoCorso().getText());
        stamper.getAcroFields().setField("professoreRelatore", tesiView.getProfRelatore().getText());
        stamper.getAcroFields().setField("titoloTesi", tesiView.getTitoloTesi().getText());

        pdfTemplate.close();
        stamper.close();
        pdfTemplate.close();
    }

    public void generatePdfTirocinio(Tirocinio tirocinioView) throws IOException,DocumentException {

        //the PdfReader will read the template
        PdfReader pdfTemplate = new PdfReader(this.getTemplate());

        FileOutputStream fileOutputStream = new FileOutputStream(this.getNomeFile());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        //PdfStamper will populate the fields dynamically with real time data.
        PdfStamper stamper = new PdfStamper(pdfTemplate, fileOutputStream);

        stamper.setFormFlattening(true);

        //MODIFICARE I CAMPI IN BASE AI TEXTFIELD
        stamper.getAcroFields().setField("nome", tirocinioView.getNome().getText());
        stamper.getAcroFields().setField("cognome", tirocinioView.getCognome().getText());
        //stamper.getAcroFields().setField("dataNascita", tirocinioView.getDataNascita().getText());
        //stamper.getAcroFields().setField("luogoNascita", tirocinioView.getLuogoNascita().getText());
       // stamper.getAcroFields().setField("eMail", tirocinioView.getEmail().getText());
        //stamper.getAcroFields().setField("annoCorso", tirocinioView.getAnnoCorso().getText());
        //stamper.getAcroFields().setField("professoreRelatore", tirocinioView.getProfRelatore().getText());
       // stamper.getAcroFields().setField("titoloTesi", tirocinioView.getTitoloTesi().getText());

        stamper.close();
        pdfTemplate.close();
    }

    public void generatePdfImmatricolazione(Immatricolazione immatricolazioneView) throws IOException,DocumentException {

        //the PdfReader will read the template
        PdfReader pdfTemplate = new PdfReader(this.getTemplate());

        FileOutputStream fileOutputStream = new FileOutputStream(this.getNomeFile());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        //PdfStamper will populate the fields dynamically with real time data.
        PdfStamper stamper = new PdfStamper(pdfTemplate, fileOutputStream);

        stamper.setFormFlattening(true);

        //MODIFICARE I CAMPI IN BASE AI TEXTFIELD
        stamper.getAcroFields().setField("nome", immatricolazioneView.getNome().getText());
        stamper.getAcroFields().setField("cognome", immatricolazioneView.getCognome().getText());
        //stamper.getAcroFields().setField("dataNascita", tirocinioView.getDataNascita().getText());
        //stamper.getAcroFields().setField("luogoNascita", tirocinioView.getLuogoNascita().getText());
        // stamper.getAcroFields().setField("eMail", tirocinioView.getEmail().getText());
        //stamper.getAcroFields().setField("annoCorso", tirocinioView.getAnnoCorso().getText());
        //stamper.getAcroFields().setField("professoreRelatore", tirocinioView.getProfRelatore().getText());
        // stamper.getAcroFields().setField("titoloTesi", tirocinioView.getTitoloTesi().getText());

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
