package org.oop.view;

import org.oop.model.entities.Corso;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormRegistrazione extends AbstractView<Agenda> {
    public JFrame frame = new JFrame("Registrazione");
    private JPanel panel1;
    private JTextField nome;
    private JTextField corsodilaurea;
    private JTextField cognome;
    private JButton Submit;
    private JButton Quit;
    private JTextField matricola;
    private JTextField email;
    private JList corsiList;
    private JRadioButton triennaleRadioButton;
    private JRadioButton magistraleRadioButton;
    private JRadioButton cicloUnicoRadioButton;

    public FormRegistrazione() {
        frame.setContentPane(panel1);
       // frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

   /*listeners adders*/
    public void addSubmitFormButtonListener(ActionListener l) {
        Submit.addActionListener(l);
    }

    public void addQuitFormButtonListener(ActionListener l) {
        Quit.addActionListener(l);
    }

    public void addLivelloRadiusButtonsListener(ActionListener l) {
        triennaleRadioButton.addActionListener(l);
        magistraleRadioButton.addActionListener(l);
        cicloUnicoRadioButton.addActionListener(l);
    }

    /** GETTER @TODO ottimizzarli, togliere quelli che non servono */
    public JTextField getNome() {
        return nome;
    }

    public JTextField getCorsolaurea() {
        return corsodilaurea;
    }

    public JTextField getCognome() {
        return cognome;
    }

    public JButton getSubmit() {
        return Submit;
    }

    public JButton getQuit() {
        return Quit;
    }

    public JTextField getMatricola() {
        return matricola;
    }

    public JTextField getEmail() {
        return email;
    }

    public JRadioButton getTriennaleRadioButton() {
        return triennaleRadioButton;
    }

    public JRadioButton getMagistraleRadioButton() {
        return magistraleRadioButton;
    }

    public JRadioButton getCicloUnicoRadioButton() {
        return cicloUnicoRadioButton;
    }
}
