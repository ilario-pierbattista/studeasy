package org.oop.view.agenda;

import org.oop.general.Utils;
import org.oop.general.Validator;
import org.oop.model.Agenda;
import org.oop.model.entities.Attivita;
import org.oop.model.entities.AttivitaEvento;
import org.oop.model.entities.Docente;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Date;

public class FormAttivitaEvento extends AbstractFormAttivita {

    private JPanel panel;
    private JComboBox<Docente> teacherBox;
    private JTextField namefield;
    private JTextField aulafield;
    private JFormattedTextField dataField;
    private JButton submitButton;
    private JButton eventoCancelButton;
    private JLabel activityname;
    private JFormattedTextField hourStartField;
    private JFormattedTextField hourEndField;
    private JTextField luogoField;
    private JComboBox ruoloDocenteBox;

    private String categoria;

    public FormAttivitaEvento() {
        frame = new JFrame("Crea attività");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        setListaDocenti(teacherBox);
        setDocenteSelected(teacherBox, AgendaView.getInstance().getInsegnamentoSelected());
    }

    /**
     * Metodo che prende i valori dei campi del form e li mette dentro un oggetto AttivitaEvento
     *
     * @return
     */
    public AttivitaEvento getNuovaAttivita() {
        AttivitaEvento attivita = new AttivitaEvento();
        Date oraInizio = (Date) hourStartField.getValue();
        Date oraFine = (Date) hourEndField.getValue();
        attivita.setId(idAttivita)
                .setDocente((Docente) teacherBox.getSelectedItem())
                .setLuogo(luogoField.getText())
                .setOraInizio(Utils.dateToLocaltime(oraInizio))
                .setOraFine(Utils.dateToLocaltime(oraFine))
                .setCategoria(categoria)
                .setRuoloDocente(getRuoloDocente());
        attivita.setData((Date) dataField.getValue());

        return attivita;
    }

    /**
     * Ritorna il ruolo del docente in base all'elemento selezionato nella Combobox
     *
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
     *
     * @return
     */
    public boolean isValid() {
        boolean flag = true;

        if (Validator.isComboBoxEmpty(teacherBox, "Docente")) {
            flag = false;
        } else if (Validator.isTextFieldEmpty(luogoField, "Luogo")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(dataField, "Giorno")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(hourStartField, "Ora inizio") || Validator.isFormattedFieldEmpty(hourEndField, "Ora fine")) {
            flag = false;
        } else if (Validator.checkTimeJFormattedText(hourStartField, hourEndField)) {
            flag = false;
        }

        return flag;
    }

    /**
     * Imposta i campi del form con i parametri dell'attivita passata. Serve se si vuole modificare
     * un'attivita
     *
     * @param attivita
     */
    public void fillForm(AttivitaEvento attivita) {
        teacherBox.setSelectedItem(attivita.getDocente());
        ruoloDocenteBox.setSelectedItem(attivita.getRuoloDocente());
        luogoField.setText(attivita.getLuogo());
        dataField.setValue(attivita.getData());
        hourStartField.setValue(Utils.localtimeToDate(attivita.getOraInizio()));
        hourEndField.setValue(Utils.localtimeToDate(attivita.getOraFine()));
        idAttivita = attivita.getId();
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
    public void closeFrame() {
        frame.dispose();
    }

    /* Listeners setters */
    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void addCancelButtonListener(ActionListener listener) {
        eventoCancelButton.addActionListener(listener);
    }

    /* Getters */

    public void setActivityname(String text) {
        activityname.setText(text);
    }

    public JButton getSubmitButton() {
        return submitButton;
    }


    /* Setters */

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
