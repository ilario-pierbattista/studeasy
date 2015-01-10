package org.oop.view.agenda;

import org.oop.general.Utils;
import org.oop.general.Validator;
import org.oop.model.entities.Attivita;
import org.oop.model.entities.AttivitaPeriodica;
import org.oop.model.entities.Docente;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class FormAttivitaPeriodica extends AbstractFormAttivita {

    private JPanel panel;
    private JComboBox<Docente> teacherBox;
    private JLabel activityname;
    private JButton submitButton;
    private JButton periodicaCancelButton;
    private JFormattedTextField hourStartField;
    private JFormattedTextField hourEndField;
    private JTextField aulaField;
    private JComboBox dayBox;
    private JComboBox ruoloDocenteBox;
    private String categoria;

    public FormAttivitaPeriodica() {
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
     * Metodo che prende i valori dei campi del form e li mette dentro un oggetto AttivitaPeriodica
     *
     * @return
     */
    public AttivitaPeriodica getNuovaAttivita() {
        AttivitaPeriodica attivita = new AttivitaPeriodica();
        Date oraInizio = (Date) hourStartField.getValue();
        Date oraFine = (Date) hourEndField.getValue();
        attivita.setId(idAttivita)
                .setDocente((Docente) teacherBox.getSelectedItem())
                .setOraInizio(Utils.dateToLocaltime(oraInizio))
                .setOraFine(Utils.dateToLocaltime(oraFine))
                .setCategoria(categoria)
                .setLuogo(aulaField.getText())
                .setRuoloDocente(getRuoloDocente());
        attivita.setGiorno(convertDayToInt(dayBox.getSelectedItem().toString()));

        return attivita;
    }

    /**
     * Converte il giorno scritto a lettere nel formato elaborato da java.util.calendar
     *
     * @param giorno
     * @return
     */
    private int convertDayToInt(String giorno) {
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
        } else if (Validator.isComboBoxEmpty(dayBox, "Giorno")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(hourStartField, "Ora inizio") || Validator.isFormattedFieldEmpty(hourEndField, "Ora fine")) {
            flag = false;
        } else if (Validator.isTextFieldEmpty(aulaField, "Aula")) {
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
    public void fillForm(AttivitaPeriodica attivita) {
        teacherBox.setSelectedItem(attivita.getDocente());
        ruoloDocenteBox.setSelectedItem(attivita.getRuoloDocente());
        dayBox.setSelectedItem(attivita.getNomeGiorno());
        hourStartField.setValue(Utils.localtimeToDate(attivita.getOraInizio()));
        hourEndField.setValue(Utils.localtimeToDate(attivita.getOraFine()));
        aulaField.setText(attivita.getLuogo());
        idAttivita = attivita.getId();
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
    public void closeFrame() {
        frame.dispose();
    }

    /* Listeners setters */
    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void addCancelButtonListener(ActionListener listener) {
        periodicaCancelButton.addActionListener(listener);
    }

    /* Getters */
    public JButton getSubmitButton() {
        return submitButton;
    }

    /* Setters */

    public void setActivityname(String text) {
        activityname.setText(text);
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
