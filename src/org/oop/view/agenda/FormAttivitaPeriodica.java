package org.oop.view.agenda;

import org.oop.general.Validator;
import org.oop.model.ArrayListComboBoxModel;
import org.oop.model.dao.DocenteDAO;
import org.oop.model.entities.Docente;
import org.oop.view.AbstractForm;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class FormAttivitaPeriodica extends AbstractForm {
    private JFrame frame;

    private JPanel panel;
    private JLabel activityname;
    private JButton submitButton;
    private JButton periodicaCancelButton;
    private JComboBox teacherBox;
    private JFormattedTextField hourStartField;
    private JFormattedTextField hourEndField;
    private JTextField aulaField;
    private JComboBox dayBox;

    public FormAttivitaPeriodica() {
        frame = new JFrame("Crea attività");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        setListaDocenti();
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
        } else if (Validator.isComboBoxEmpty(dayBox, "Giorno")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(hourStartField,"Ora inizio") || Validator.isFormattedFieldEmpty(hourEndField, "Ora fine")) {
            flag = false;
        } else if (Validator.isTextFieldEmpty(aulaField, "Aula")) {
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
        hourStartField = new JFormattedTextField(hourformat);
        hourEndField = new JFormattedTextField(hourformat);
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
        periodicaCancelButton.addActionListener(listener);
    }

    /* Getters */
    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getPeriodicaCancelButton() {
        return periodicaCancelButton;
    }

    public JComboBox getTeacherBox() {
        return teacherBox;
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

    public JComboBox getDayBox() {
        return dayBox;
    }

    /* Setters */

    public void setActivityname(String text) {
        activityname.setText(text);
    }
}
