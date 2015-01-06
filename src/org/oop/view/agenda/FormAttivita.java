package org.oop.view.agenda;

import org.oop.general.Validator;
import org.oop.view.AbstractView;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by toioski on 23/12/14.
 */
public class FormAttivita extends AbstractView {
    public static  JFrame frame = new JFrame("Crea Attività");
    private JPanel panel1;
    private JTextField namefield;
    private JTextField teacherfield;
    private JTextField aulafield;
    private JFormattedTextField dataField;
    private JTextArea descriptionfield;
    private JButton submitbutton;
    private JButton cancelbutton;
    private JLabel activityname;

    private String activityType;

    public FormAttivita() {
        frame.setContentPane(panel1);
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
    }

    /**
     * Metodo che chiude il form
     */
    public static void closeFrame(){
        frame.dispose();
    }

    /* Listeners setters */
    public void addSubmitButtonListener (ActionListener listener){
        submitbutton.addActionListener(listener);
    }
    public void addCancelButtonListener (ActionListener listener){
        cancelbutton.addActionListener(listener);
    }

    /* Getters */

    public JLabel getActivityname() {
        return activityname;
    }

    public JButton getSubmitbutton() {
        return submitbutton;
    }

    public JButton getCancelbutton() {
        return cancelbutton;
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
}
