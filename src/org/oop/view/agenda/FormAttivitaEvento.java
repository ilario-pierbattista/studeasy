package org.oop.view.agenda;

import org.oop.general.Validator;
import org.oop.view.AbstractForm;

import javax.swing.*;
import java.awt.event.ActionListener;

public class FormAttivitaEvento extends AbstractForm {
    private JFrame frame;
    private JPanel panel;
    private JTextField namefield;
    private JTextField teacherfield;
    private JTextField aulafield;
    private JFormattedTextField dataField;
    private JTextArea descriptionfield;
    private JButton submitButton;
    private JButton eventoCancelButton;
    private JLabel activityname;
    private JFormattedTextField hourStartField;
    private JFormattedTextField hourEndField;
    private JComboBox teacherBox;
    private JTextField luogoField;

    private String activityType;

    public FormAttivitaEvento() {
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
        boolean flag;

        if (Validator.isTextFieldEmpty(teacherfield,"Docente")){
            flag = false;
        } else if (Validator.isTextFieldEmpty(aulafield,"Aula")){
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(dataField,"Data")){
            flag = false;
        } else {
            return true;
        }

        return flag;
    }

    /**
     * Setta il tipo dell'attività in base al bottone che è stato cliccato
     */
    public void setType(String type){
        activityType = type;

        if(activityType.equals("lezione")){
            activityname.setText("Lezione");
        } else if (activityType.equals("laboratorio")){
            activityname.setText("Laboratorio");
        } else if (activityType.equals("progetto")){
            activityname.setText("Progetto");
        } else if (activityType.equals("esame")){
            activityname.setText("Esame");
        } else if (activityType.equals("seminario")){
            activityname.setText("Seminario");
        }
    }

    /**
     * Setta componenti GUI custom (rispetto all'editor visuale)
     */
    private void createUIComponents() {
        dataField = new JFormattedTextField(dateformat);
        hourEndField = new JFormattedTextField(hourformat);
        hourStartField = new JFormattedTextField(hourformat);
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
        eventoCancelButton.addActionListener(listener);
    }

    /* Getters */

    public JLabel getActivityname() {
        return activityname;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getEventoCancelButton() {
        return eventoCancelButton;
    }

    public JTextField getTeacherfield() {
        return teacherfield;
    }

    public JTextArea getDescriptionfield() {
        return descriptionfield;
    }

    public JFormattedTextField getDataField() {

        return dataField;
    }

    public JTextField getAulafield() {

        return aulafield;
    }

    public JTextField getNamefield() {

        return namefield;
    }

    public JFormattedTextField getHourStartField() {
        return hourStartField;
    }

    public JFormattedTextField getHourEndField() {
        return hourEndField;
    }

    public JComboBox getTeacherBox() {
        return teacherBox;
    }

    public JTextField getLuogoField() {
        return luogoField;
    }
}
