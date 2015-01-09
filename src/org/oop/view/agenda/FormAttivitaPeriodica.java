package org.oop.view.agenda;

import org.oop.general.Utils;
import org.oop.general.Validator;
import org.oop.model.ArrayListComboBoxModel;
import org.oop.model.dao.DocenteDAO;
import org.oop.model.entities.AttivitaEvento;
import org.oop.model.entities.AttivitaPeriodica;
import org.oop.model.entities.Docente;
import org.oop.view.AbstractForm;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FormAttivitaPeriodica extends AbstractForm {
    private JFrame frame;

    private JPanel panel;
    private JLabel activityname;
    private JButton submitButton;
    private JButton periodicaCancelButton;
    private JComboBox<Docente> teacherBox;
    private JFormattedTextField hourStartField;
    private JFormattedTextField hourEndField;
    private JTextField aulaField;
    private JComboBox dayBox;
    private String categoria;

    public FormAttivitaPeriodica() {
        frame = new JFrame("Crea attività");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        setListaDocenti();
    }

    /**
     * Metodo che prende i valori dei campi del form e li mette dentro un oggetto AttivitaPeriodica
     * @return
     */
    public AttivitaPeriodica getNuovaAttivita() {
        AttivitaPeriodica attivita = new AttivitaPeriodica();
        Date oraInizio = (Date) hourStartField.getValue();
        Date oraFine = (Date) hourEndField.getValue();
        attivita.setDocente((Docente) teacherBox.getSelectedItem())
                .setOraInizio(Utils.dateToLocaltime(oraInizio))
                .setOraFine(Utils.dateToLocaltime(oraFine))
                .setCategoria(categoria);
        attivita.setGiorno(convertDayToInt(dayBox.getSelectedItem().toString()));

        return attivita;

    }

    private int convertDayToInt(String giorno){
        int valore = Calendar.MONDAY;

        if (giorno.equals("Lunedì")) {
            valore = Calendar.MONDAY;
        } else if (giorno.equals("Martedì")) {
            valore = Calendar.TUESDAY;
        } else if (giorno.equals("Mercoledì")) {
            valore = Calendar.WEDNESDAY;
        } else if (giorno.equals("Giovedì")) {
            valore = Calendar.THURSDAY;
        } else if (giorno.equals("Venerdì")) {
            valore = Calendar.FRIDAY;
        }

        return valore;
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

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
