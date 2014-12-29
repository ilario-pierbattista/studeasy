package org.oop.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class FormModuli extends AbstractView<Agenda> {
    public JFrame frame = new JFrame("Completa il form");
    private JPanel panel1;
    private JTextField annocorso;
    private JTextField corsolaurea;
    private JTextField annoaccademico;
    private JButton Submit;
    private JButton Quit;
    private JTextField cfuottenuti;
    private JTextField statoesami;

    public FormModuli () {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();
    }


   /*listeners adders*/
    public void addSubmitFormButtonListener(ActionListener l) {
        Submit.addActionListener(l);
    }


    public void addQuitFormButtonListener(ActionListener l) {
        Quit.addActionListener(l);
    }

    public JTextField getAnnocorso() {
        return annocorso;
    }

    public JTextField getCorsolaurea() {
        return corsolaurea;
    }

    public JTextField getAnnoaccademico() {
        return annoaccademico;
    }

    public JTextField getCfuottenuti() {
        return cfuottenuti;
    }

    public JTextField getStatoesami() {
        return statoesami;
    }
}
