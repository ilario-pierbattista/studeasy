package org.oop.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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

    }

    public void addLezione(Attivita attivita){
        activitiespanel.add(attivita.activitypanel);
    }

    /* Getters */
    public JButton getAddinsbutton() {
        return addinsbutton;
    }

    public JLabel getInsegnamentolabel() {
        return insegnamentolabel;
    }

    public JLabel getDurataciclolabel() {
        return durataciclolabel;
    }

    public JButton getLezioneButton() {
        return lezioneButton;
    }

    public JButton getLaboratorioButton() {
        return laboratorioButton;
    }

    public JButton getEsameButton() {
        return esameButton;
    }

    /* Listeners setters */
    public void addLezioneButtonListener (ActionListener listener){
        lezioneButton.addActionListener(listener);
    }



}
