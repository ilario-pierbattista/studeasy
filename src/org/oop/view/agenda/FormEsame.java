package org.oop.view.agenda;

import org.oop.view.AbstractForm;

import javax.swing.*;
import java.awt.event.ActionListener;


public class FormEsame extends AbstractForm {
    private JFrame frame;
    private JPanel panel;
    private JLabel activityname;
    private JButton submitButton;
    private JButton cancelButton;
    private JFormattedTextField dataField;
    private JFormattedTextField hourStartField;
    private JFormattedTextField hourEndField;
    private JTextField aulaField;
    private JComboBox teacherBox;
    private JRadioButton scrittoRadioButton;
    private JRadioButton oraleRadioButton;

    public FormEsame() {
        frame = new JFrame("Crea attività");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Metodo di appoggio che controlla che i campi del form siano stati compilati
     * correttamente
     * @return
     */
    public boolean isValid(){
        return false;
    }

    /**
     * Metodo che chiude il form
     */
    public void closeFrame(){
        frame.dispose();
    }

    /* Listeners setters */
    public void addSubmitButtonListener (ActionListener listener){
        submitButton.addActionListener(listener);
    }
    public void addCancelButtonListener (ActionListener listener){
        cancelButton.addActionListener(listener);
    }

    /* Getters */

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JFormattedTextField getDataField() {
        return dataField;
    }

    public JFormattedTextField getHourStartField() {
        return hourStartField;
    }

    public JFormattedTextField getHourEndField() {
        return hourEndField;
    }

    public JTextField getAulaField() {
        return aulaField;
    }

    public JComboBox getTeacherBox() {
        return teacherBox;
    }

    public JRadioButton getScrittoRadioButton() {
        return scrittoRadioButton;
    }

    public JRadioButton getOraleRadioButton() {
        return oraleRadioButton;
    }
}
