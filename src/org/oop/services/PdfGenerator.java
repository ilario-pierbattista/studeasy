package org.oop.services;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import org.oop.view.segreteria.FormImmatricolazione;
import org.oop.view.segreteria.FormTesi;
import org.oop.view.segreteria.FormTirocinio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * PdfGenerator si occupa di creare i file pdf, estrapolando i dati dalle viste contenenti i form, inserirli nei
 * template dei documenti e salvando il risultato in un file di destinazione.
 */
public class PdfGenerator {
    private InputStream template;
    private String nomeFile;

    /**
     * @param nomeTemplate Nome del template da utilizzare
     * @param nome         Nome del file di destinazione
     */
    public PdfGenerator(InputStream nomeTemplate, String nome) {
        //template contiene la path della cartella template
        template = nomeTemplate;
        nomeFile = nome;
    }

    /**
     * Genera il pdf per la richiesta della tesi
     *
     * @param tesiView Vista con il form per raccogliere i dati
     * @param path     Path del file di destinazione
     * @throws IOException
     * @throws DocumentException
     */
    public void generatePdfTesi(FormTesi tesiView, String path) throws IOException, DocumentException {

        // Lettura del template
        PdfReader pdfTemplate = new PdfReader(template);
        // Apertura del file di destinazione
        FileOutputStream fileOutputStream = createOutputStream(path);
        // Popolazione del template con i dati
        PdfStamper stamper = new PdfStamper(pdfTemplate, fileOutputStream);

        stamper.setFormFlattening(true);

        stamper.getAcroFields().setField("nome", tesiView.getNome().getText());
        stamper.getAcroFields().setField("cognome", tesiView.getCognome().getText());
        stamper.getAcroFields().setField("matricola", tesiView.getMatricola().getText());
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

    /**
     * Genera il pdf per la richiesta di tirocinio
     *
     * @param tirocinioView Vista con il form per raccogliere i dati
     * @param path          Path del file di destinazione
     * @throws IOException
     * @throws DocumentException
     */
    public void generatePdfTirocinio(FormTirocinio tirocinioView, String path) throws IOException, DocumentException {

        // Lettura del template
        PdfReader pdfTemplate = new PdfReader(template);
        // Apertura del file di destinazione
        FileOutputStream fileOutputStream = createOutputStream(path);
        // Popolazione del template con i dati
        PdfStamper stamper = new PdfStamper(pdfTemplate, fileOutputStream);

        stamper.setFormFlattening(true);

        stamper.getAcroFields().setField("nome", tirocinioView.getNome().getText());
        stamper.getAcroFields().setField("cognome", tirocinioView.getCognome().getText());
        stamper.getAcroFields().setField("matricola", tirocinioView.getMatricola().getText());
        stamper.getAcroFields().setField("luogoNascita", tirocinioView.getLuogonascita().getText());
        stamper.getAcroFields().setField("dataNascita", tirocinioView.getDatanascita().getText());
        stamper.getAcroFields().setField("residenza", tirocinioView.getResidenza().getText());
        stamper.getAcroFields().setField("provincia", tirocinioView.getProvincia().getText());
        stamper.getAcroFields().setField("cap", tirocinioView.getCap().getText());
        stamper.getAcroFields().setField("via", tirocinioView.getVia().getText());
        stamper.getAcroFields().setField("codiceFiscale", tirocinioView.getCodicefiscale().getText());

        pdfTemplate.close();
        stamper.close();
        pdfTemplate.close();
    }

    /**
     * Genera il pdf per l'immatricolazione
     *
     * @param immatricolazioneView Vista che contiene il form per raccogliere i dati
     * @param path                 Path del file di destinazione
     * @throws IOException
     * @throws DocumentException
     */
    public void generatePdfImmatricolazione(FormImmatricolazione immatricolazioneView, String path) throws IOException, DocumentException {

        // Lettura del template
        PdfReader pdfTemplate = new PdfReader(template);
        // Apertura del file di destinazione
        FileOutputStream fileOutputStream = createOutputStream(path);
        // Popolazione del template con i dati
        PdfStamper stamper = new PdfStamper(pdfTemplate, fileOutputStream);

        stamper.setFormFlattening(true);

        stamper.getAcroFields().setField("nome", immatricolazioneView.getNome().getText());
        stamper.getAcroFields().setField("cognome", immatricolazioneView.getCognome().getText());
        stamper.getAcroFields().setField("matricola", immatricolazioneView.getMatricola().getText());
        stamper.getAcroFields().setField("luogoNascita", immatricolazioneView.getLuogonascita().getText());
        stamper.getAcroFields().setField("dataNascita", immatricolazioneView.getDatanascita().getText());
        stamper.getAcroFields().setField("provinciaNascita", immatricolazioneView.getProvincia().getText());
        stamper.getAcroFields().setField("codiceFiscale", immatricolazioneView.getCodicefiscale().getText());
        stamper.getAcroFields().setField("tipologiaScuolaSuperiore", immatricolazioneView.getDiploma().getText());
        stamper.getAcroFields().setField("voto", immatricolazioneView.getVoto().getText());
        String primaParteAnnoScolastico = immatricolazioneView.getAnnoConseguimento1().getText();
        stamper.getAcroFields().setField("anno1", primaParteAnnoScolastico);
        stamper.getAcroFields().setField("anno2", Integer.toString(Integer.parseInt(primaParteAnnoScolastico) + 1));

        pdfTemplate.close();
        stamper.close();
        pdfTemplate.close();
    }

    /**
     * Crea uno stream di output
     *
     * @param path Path di output
     * @return Stream del file
     * @throws IOException
     */
    private FileOutputStream createOutputStream(String path) throws IOException {
        String percorso = path.concat(File.separator).concat(nomeFile);
        if (!getEstensione(percorso).equals("pdf")) {
            percorso = percorso.concat(".pdf");
        }
        return new FileOutputStream(percorso);
    }

    /**
     * Ritorna l'estensione di un file dal suo nome
     *
     * @param path Percorso del file
     * @return Estensione
     */
    private String getEstensione(String path) {
        String[] parti = path.split(".");
        String estensione;
        if (parti.length > 0) {
            estensione = parti[parti.length - 1];
        } else {
            estensione = path;
        }
        return estensione;
    }
}
