package org.oop.view.segreteria;

import javax.swing.*;
import java.awt.event.ActionListener;


public class FormTasse {
    private JFrame frame;

    private JTextField annoAccademicoField;
    private JTextField importoField;
    private JRadioButton pagatoRadioButton;
    private JRadioButton nonPagatoRadioButton;
    private JButton submitButton;
    private JButton cancelButton;
    private JList corsiList;
    private JRadioButton triennaleRadioButton;
    private JRadioButton magistraleRadioButton;
    private JRadioButton cicloUnicoRadioButton;
    private JPanel panel;

    public FormTasse() {
        frame = new JFrame("Aggiungi tassa");
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

    /* Listener setters */
    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void addCancelButtonListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    /* Getters */
    public JTextField getAnnoAccademicoField() {
        return annoAccademicoField;
    }

    public JTextField getImportoField() {
        return importoField;
    }

    public JRadioButton getPagatoRadioButton() {
        return pagatoRadioButton;
    }

    public JRadioButton getNonPagatoRadioButton() {
        return nonPagatoRadioButton;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JList getCorsiList() {
        return corsiList;
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
