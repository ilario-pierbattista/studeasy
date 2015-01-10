package org.oop.view.segreteria;

import org.oop.view.AbstractView;

import javax.swing.*;


public class Segreteria extends AbstractView {
    private static Segreteria instance;
    public JPanel segreteriapanel;
    private JTabbedPane maintabpane;
    private FormImmatricolazione immatricolazione;
    private IscrizioneView iscrizioneView;
    private FormTirocinio tirocinio;
    private FormTesi tesi;
    private Tasse tasse;

    public Segreteria() {
        super();
        instance = this;
        immatricolazione = new FormImmatricolazione();
        tirocinio = new FormTirocinio();
        tesi = new FormTesi();
        tasse = new Tasse();
        iscrizioneView = new IscrizioneView();

        maintabpane.addTab("Immatricolazione", immatricolazione.immatricolazionepanel);
        maintabpane.addTab("Iscrizione", iscrizioneView.iscrizionepanel);
        maintabpane.addTab("Tirocinio", tirocinio.tirociniopanel);
        maintabpane.addTab("Tesi", tesi.tesipanel);
        maintabpane.addTab("Tasse", tasse.tassepanel);
    }

    public static Segreteria getInstance() {
        return instance;
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

    public IscrizioneView getIscrizioneView() {
        return iscrizioneView;
    }

    public JTabbedPane getMaintabpane() {
        return maintabpane;
    }
}
