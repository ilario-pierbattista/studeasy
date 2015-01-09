package org.oop.view.agenda;

import org.oop.general.Utils;
import org.oop.general.Validator;
import org.oop.model.ArrayListComboBoxModel;
import org.oop.model.dao.DocenteDAO;
import org.oop.model.entities.Attivita;
import org.oop.model.entities.Docente;
import org.oop.model.entities.Esame;
import org.oop.model.entities.Utente;
import org.oop.view.AbstractForm;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;


public class FormEsame extends AbstractForm {
    private JFrame frame;
    private JPanel panel;
    private JLabel activityname;
    private JButton submitButton;
    private JButton esameCancelButton;
    private JFormattedTextField dataField;
    private JFormattedTextField hourStartField;
    private JFormattedTextField hourEndField;
    private JTextField aulaField;
    private JComboBox teacherBox;
    private JRadioButton scrittoRadioButton;
    private JRadioButton oraleRadioButton;
    private JRadioButton laboratorioRadioButton;
    private JComboBox ruoloDocenteBox;

    public FormEsame() {
        frame = new JFrame("Crea attività");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        setListaDocenti();
    }

    /**
     * Metodo che prende i valori dei campi del form e li mette dentro un oggetto Esame
     * @return
     */
    public Esame getNuovaAttivita() {
        Esame attivita = new Esame();
        Date oraInizio = (Date) hourStartField.getValue();
        Date oraFine = (Date) hourEndField.getValue();
        attivita.setDocente((Docente) teacherBox.getSelectedItem())
                .setOraInizio(Utils.dateToLocaltime(oraInizio))
                .setOraFine(Utils.dateToLocaltime(oraFine))
                .setCategoria(Attivita.CATEGORIA_ESAME)
                .setLuogo(aulaField.getText())
                .setRuoloDocente(getRuoloDocente());
        attivita.setData((Date) dataField.getValue());
        //la tipologia dell'esame si setta nel controller

        return attivita;

    }

    /**
     * Ritorna il ruolo del docente in base all'elemento selezionato nella Combobox
     * @return
     */
    private String getRuoloDocente() {
        String ruolo;
        String e = ruoloDocenteBox.getSelectedItem().toString();

        if (e.equals("Docente")) {
            ruolo = Attivita.DOCENTE;
        } else if (e.equals("Assistente")) {
            ruolo = Attivita.ASSISTENTE;
        } else {
            ruolo = Attivita.TUTOR;
        }

        return ruolo;
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
        } else if (Validator.isFormattedFieldEmpty(dataField, "Luogo")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(hourStartField,"Ora inizio") || Validator.isFormattedFieldEmpty(hourEndField, "Ora fine")) {
            flag = false;
        } else if (Validator.isTextFieldEmpty(aulaField, "Aula")) {
            flag = false;
        } else if (!scrittoRadioButton.isSelected() && !oraleRadioButton.isSelected()) {
            flag = true;
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
     * Imposta i campi del form con i parametri dell'attivita passata. Serve se si vuole modificare
     * un'attivita
     *
     * @param attivita
     */
    public void fillForm(Esame attivita) {
        teacherBox.setSelectedItem(attivita.getDocente());
        ruoloDocenteBox.setSelectedItem(attivita.getRuoloDocente());
        dataField.setText(Utils.dateToString(attivita.getData(),1));
        hourStartField.setText(attivita.getOraInizio().toString());
        hourEndField.setText(attivita.getOraFine().toString());
        aulaField.setText(attivita.getLuogo());
        if (attivita.getTipologiaProva().equals("scritto")) {
            scrittoRadioButton.setSelected(true);
        } else if (attivita.getTipologiaProva().equals("orale")) {
            oraleRadioButton.setSelected(true);
        } else {
            laboratorioRadioButton.setSelected(true);
        }
    }

    /**
     * Setta componenti GUI custom (rispetto all'editor visuale)
     */
    private void createUIComponents() {
        dataField = new JFormattedTextField(dateformat);
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
        esameCancelButton.addActionListener(listener);
    }
    public void addTipologiaProvaRadioListener (ActionListener listener) {
        oraleRadioButton.addActionListener(listener);
        laboratorioRadioButton.addActionListener(listener);
        scrittoRadioButton.addActionListener(listener);
    }

    /* Getters */

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getEsameCancelButton() {
        return esameCancelButton;
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

    public JRadioButton getLaboratorioRadioButton() {
        return laboratorioRadioButton;
    }

    /* Setters */

    public void setActivityname(String text) {
        activityname.setText(text);
    }
}
