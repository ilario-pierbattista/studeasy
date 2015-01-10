package org.oop.view.segreteria;

import org.oop.controller.IscrizioneController;
import org.oop.controller.TasseController;
import org.oop.view.AbstractView;

import javax.swing.*;


public class Segreteria extends AbstractView {
    public JPanel segreteriapanel;
    private JTabbedPane maintabpane;

    private FormImmatricolazione immatricolazione;
    private Iscrizione iscrizione;
    private FormTirocinio tirocinio;
    private FormTesi tesi;
    private Tasse tasse;

    public Segreteria() {
        super();
        immatricolazione = new FormImmatricolazione();
        tirocinio = new FormTirocinio();
        tesi = new FormTesi();
        tasse = new Tasse();
        iscrizione = new Iscrizione();

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

    public Tasse getTasse() {
        return tasse;
    }

    public Iscrizione getIscrizione() {
        return iscrizione;
    }
}
