package org.oop.view.segreteria;

import javax.swing.*;
import java.awt.event.ActionListener;


public class FormIscrizione {
    private JFrame frame;

    private JTextField annoField;
    private JTextField annoAccademicoField;
    private JTextField esamiField;
    private JTextField cfuField;
    private JRadioButton triennaleRadioButton;
    private JRadioButton magistraleRadioButton;
    private JRadioButton cicloUnicoRadioButton;
    private JList corsiList;
    private JButton submitButton;
    private JButton cancelButton;
    private JPanel panel;

    public FormIscrizione() {
        frame = new JFrame("Aggiungi anno iscrizione");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Chiude form
     */
    public void closeFrame() {
        frame.dispose();
    }

    /* Listeners setter */
    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void addCancelButtonListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    /* Getters */
    public JTextField getAnnoField() {
        return annoField;
    }

    public JTextField getAnnoAccademicoField() {
        return annoAccademicoField;
    }

    public JTextField getEsamiField() {
        return esamiField;
    }

    public JTextField getCfuField() {
        return cfuField;
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

    public JList getCorsiList() {
        return corsiList;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
