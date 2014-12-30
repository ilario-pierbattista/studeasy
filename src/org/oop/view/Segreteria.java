package org.oop.view;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by toioski on 20/12/14.
 */
public class Segreteria extends AbstractView<Segreteria> {
    public JPanel segreteriapanel;
    private JTabbedPane maintabpane;
    private Immatricolazione immatricolazione = new Immatricolazione();

    private Iscrizione iscrizione = new Iscrizione();

    private Tirocinio tirocinio = new Tirocinio();
    private Tesi tesi = new Tesi();


    public Segreteria() {
        super();
        maintabpane.addTab("Immatricolazione", immatricolazione.immatricolazionepanel);
        maintabpane.addTab("Iscrizione",iscrizione.iscrizionepanel);
        maintabpane.addTab("Tirocinio",tirocinio.tirociniopanel);
        maintabpane.addTab("Tesi", tesi.tesipanel);

    }

}
