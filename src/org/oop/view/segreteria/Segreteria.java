package org.oop.view.segreteria;

import org.oop.view.View;

import javax.swing.*;


public class Segreteria extends View {
    private static Segreteria instance;
    public JPanel segreteriapanel;
    private JTabbedPane maintabpane;
    private FormImmatricolazione immatricolazione;
    private IscrizioneView iscrizioneView;
    private FormTirocinio tirocinio;
    private FormTesi tesi;
    private TasseView tasseView;

    public Segreteria() {
        super();
        instance = this;
        immatricolazione = new FormImmatricolazione();
        tirocinio = new FormTirocinio();
        tesi = new FormTesi();
        tasseView = new TasseView();
        iscrizioneView = new IscrizioneView();

        maintabpane.addTab("Immatricolazione", immatricolazione.immatricolazionepanel);
        maintabpane.addTab("Iscrizione", iscrizioneView.iscrizionepanel);
        maintabpane.addTab("Tirocinio", tirocinio.tirociniopanel);
        maintabpane.addTab("Tesi", tesi.tesipanel);
        maintabpane.addTab("Tasse", tasseView.tassepanel);
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

    public TasseView getTasseView() {
        return tasseView;
    }

    public IscrizioneView getIscrizioneView() {
        return iscrizioneView;
    }

    public JTabbedPane getMaintabpane() {
        return maintabpane;
    }
}
