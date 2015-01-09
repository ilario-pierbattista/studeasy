package org.oop.view.agenda;

import org.oop.general.Validator;
import org.oop.model.ArrayListComboBoxModel;
import org.oop.model.dao.DocenteDAO;
import org.oop.model.entities.AttivitaEvento;
import org.oop.model.entities.Docente;
import org.oop.view.AbstractForm;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

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
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        setListaDocenti();
    }

    /**
     * Metodo che prende i valori dei campi del form e li mette dentro un oggetto AttivitaEvento
     * @return
     */
    public AttivitaEvento getNuovaAttivita() {
        AttivitaEvento attivita = new AttivitaEvento();
        attivita.setDocente((Docente) teacherBox.getSelectedItem())
                .setLuogo(luogoField.getText())
                .setOraInizio((LocalTime) hourStartField.getValue())
                .setOraFine((LocalTime) hourEndField.getValue());
        attivita.setData((Date) dataField.getValue());

        return attivita;

    }


    /**
     * Metodo di appoggio che controlla che i campi del form siano stati compilati
     * correttamente
     * @return
     */
    public boolean isValid(){
        boolean flag = false;
        Date hstart = (Date) hourStartField.getValue();
        Date hend = (Date) hourEndField.getValue();

        if (Validator.isComboBoxEmpty(teacherBox, "Docente")) {
            flag = false;
        } else if (Validator.isTextFieldEmpty(luogoField, "Luogo")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(dataField, "Giorno")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(hourStartField,"Ora inizio") || Validator.isFormattedFieldEmpty(hourEndField, "Ora fine")) {
            flag = false;
        } else if (Validator.isDateGreater(hstart,hend)) {
            flag = true;
        }

        return flag;
    }


    /**
     * Setta la lista dei docenti
     */
    private void setListaDocenti() {
        ArrayList<Docente> docenti = new DocenteDAO().findAll();
        ArrayList<Docente> listadocenti;

        ArrayListComboBoxModel model = new ArrayListComboBoxModel(docenti);

        teacherBox.setModel(model);
        teacherBox.setSelectedIndex(0);
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

    /* Setters */

    public void setActivityname(String text) {
        activityname.setText(text);
    }
}