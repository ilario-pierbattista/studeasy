package org.oop.view;

import javax.swing.*;
import java.awt.event.ActionListener;

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

    public FormRegistrazione() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
}
