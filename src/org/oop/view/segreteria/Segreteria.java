package org.oop.view.segreteria;

import org.oop.controller.*;
import org.oop.view.AbstractView;

import javax.swing.*;


public class Segreteria extends AbstractView {
    public JPanel segreteriapanel;
    private JTabbedPane maintabpane;

    private FormImmatricolazione immatricolazione;
    private Iscrizione iscrizione = new Iscrizione();
    private IscrizioneController iscrizionecontroller = new IscrizioneController(iscrizione);
    private FormTirocinio tirocinio;
    private FormTesi tesi;
    private Tasse tasse = new Tasse();
    private TasseController tassecontroller = new TasseController(tasse);

    public Segreteria() {
        super();
        immatricolazione = new FormImmatricolazione();
        tirocinio = new FormTirocinio();
        tesi = new FormTesi();

        maintabpane.addTab("Immatricolazione", immatricolazione.immatricolazionepanel);
        maintabpane.addTab("Iscrizione", iscrizione.iscrizionepanel);
        maintabpane.addTab("Tirocinio", tirocinio.tirociniopanel);
        maintabpane.addTab("Tesi", tesi.tesipanel);
        maintabpane.addTab("Tasse", tasse.tassepanel);
    }

    public FormImmatricolazione getImmatricolazione() {
        return immatricolazione;
    }

    public FormTirocinio getTirocinio() {
        return tirocinio;
    }

    public FormTesi getTesi() {
        return tesi;
    }
}
