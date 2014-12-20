package org.oop.view;

import com.sun.tools.example.debug.gui.ApplicationTool;

import javax.swing.*;
import java.awt.*;

public class Agenda {
    public JPanel agendapanel;

    private JScrollPane sidebarpane;
    private JSplitPane splitpane;
    private JButton addinsbutton;
    private JPanel sidebarpanel;
    private JPanel activitypanel;
    private JLabel insegnamentolabel;
    private JLabel durataciclolabel;
    private JPanel buttonspanel;
    private JButton lezioneButton;
    private JButton laboratorioButton;
    private JButton esameButton;
    private JPanel activitiespanel;

    public Agenda() {
        //Setta la larghezza della sidebar
        splitpane.setDividerLocation(200 + splitpane.getInsets().left);
        //Elimina i bordi
        splitpane.setBorder(null);
        sidebarpane.setBorder(null);
        //Imposta il layout a 2 colonne
        activitiespanel.setLayout(new GridLayout(0, 2, 20, 20));
        activitiespanel.add(new Attivita("Lezione pratica", "Lucio Demeio").activity);
        activitiespanel.add(new Attivita("Lezione pratica", "Lucio Demeio").activity);
        activitiespanel.add(new Attivita("Lezione pratica","Lucio Demeio").activity);
        activitiespanel.add(new Attivita("Lezione pratica","Lucio Demeio").activity);
        activitiespanel.add(new Attivita("Lezione pratica","Lucio Demeio").activity);
        activitiespanel.add(new Attivita("Lezione pratica","Lucio Demeio").activity);
        activitiespanel.add(new Attivita("Lezione pratica","Lucio Demeio").activity);
    }
}
