package org.oop.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by toioski on 20/12/14.
 */
public class Segreteria extends AbstractView<Segreteria> {
    public JPanel segreteriapanel;
    private JTabbedPane maintabpane;
    private Immatricolazione immatricolazione = new Immatricolazione();

    public Segreteria() {
        super();

        maintabpane.addTab("Immatricolazione", immatricolazione.immatricolazionepanel);
    }

}
