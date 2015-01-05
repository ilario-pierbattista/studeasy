package org.oop.view;

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
public class Pdf {
    private String template;
    private String nomeFile;
    private Tesi tesiView;
    private Tirocinio tirocinioView;
    private Immatricolazione immatricolazioneView;

    public  Pdf (String nomeTemplate, String file, Tesi view)
    {
        template=nomeTemplate;
        nomeFile=file;
        tesiView=view;

    }
    public  Pdf (String nomeTemplate, String file, Tirocinio view)
    {
        template=nomeTemplate;
        nomeFile=file;
        tirocinioView=view;

    }
    public  Pdf (String nomeTemplate, String file, Immatricolazione view)
    {
        template=nomeTemplate;
        nomeFile=file;
        immatricolazioneView=view;

    }
    public void generatePdf() throws IOException,DocumentException {

        //the PdfReader will read the template
        PdfReader pdfTemplate = new PdfReader(this.getTemplate());

        FileOutputStream fileOutputStream = new FileOutputStream(this.getNomeFile());
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
