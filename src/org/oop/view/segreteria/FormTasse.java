package org.oop.view.segreteria;

import org.oop.general.Validator;
import org.oop.view.AbstractForm;
import org.oop.view.AbstractView;

import javax.swing.*;
import java.awt.event.ActionListener;


public class FormTasse extends AbstractForm {
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
    private JFormattedTextField annoField;
    private JFormattedTextField scadenzaField;

    public FormTasse() {
        frame = new JFrame("Aggiungi tassa");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Metodo di appoggio che controlla che i campi del form siano stati compilati
     * correttamente
     *
     * @return
     */
    public boolean isValid() {
        boolean flag = false;


        return flag;
    }

    /**
     * Setta componenti GUI custom (rispetto all'editor visuale)
    **/

    private void createUIComponents() {
        annoAccademicoField = new JFormattedTextField(dateformatYear);
        importoField = new JFormattedTextField(pagamentoformat);
        scadenzaField = new JFormattedTextField(dateformat);

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
