package org.oop.view.segreteria;

import org.oop.controller.*;
import org.oop.view.AbstractView;

import javax.swing.*;

/**
 * Created by toioski on 20/12/14.
 */
public class Segreteria extends AbstractView<Segreteria> {
    public JPanel segreteriapanel;
    private JTabbedPane maintabpane;

    private Immatricolazione immatricolazione = new Immatricolazione();
    private ImmatricolazioneController immatricolazionecontroller = new ImmatricolazioneController(immatricolazione);
    private Iscrizione iscrizione = new Iscrizione();
    private IscrizioneController iscrizionecontroller = new IscrizioneController(iscrizione);
    private Tirocinio tirocinio = new Tirocinio();
    private TirocinioController tirociniocontroller = new TirocinioController(tirocinio);
    private Tesi tesi = new Tesi();
    private TesiController tesicontroller = new TesiController(tesi);
    private Tasse tasse = new Tasse();
    private TasseController tassecontroller = new TasseController(tasse);


    public Segreteria() {
        super();
        maintabpane.addTab("Immatricolazione", immatricolazione.immatricolazionepanel);
        maintabpane.addTab("Iscrizione", iscrizione.iscrizionepanel);
        maintabpane.addTab("Tirocinio", tirocinio.tirociniopanel);
        maintabpane.addTab("Tesi", tesi.tesipanel);
        maintabpane.addTab("Tasse", tasse.tassepanel);

    }

}
