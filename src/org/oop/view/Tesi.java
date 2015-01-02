package org.oop.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Tesi {
    public JPanel tesipanel;
    private JButton submit;
    private JButton quit;
    private JFormattedTextField nome;
    private JFormattedTextField cognome;
    private JFormattedTextField dataNascita;
    private JFormattedTextField luogoNascita;
    private JFormattedTextField email;
    private JFormattedTextField annoCorso;
    private JFormattedTextField profRelatore;
    private JFormattedTextField titoloTesi;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JLabel dataNascitaLabel;
    private JLabel luogoDiNascitaLabel;
    private JLabel emailLabel;
    private JLabel annoCorsoLabel;
    private JLabel profRelatoreLabel;
    private JLabel titoloTesiLabel;


    public void insSubmitFormButtonListener (ActionListener l) {
        submit.addActionListener(l);
    }

    public void insQuitFormButtonListener (ActionListener l) {
        quit.addActionListener(l);
    }
}
