package org.oop.view.agenda;

import org.oop.general.Utils;
import org.oop.general.Validator;
import org.oop.model.entities.Attivita;
import org.oop.model.entities.Docente;
import org.oop.model.entities.Esame;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Date;


public class FormEsame extends AbstractFormAttivita {

    private JPanel panel;
    private JComboBox<Docente> teacherBox;
    private JLabel activityname;
    private JButton submitButton;
    private JButton esameCancelButton;
    private JFormattedTextField dataField;
    private JFormattedTextField hourStartField;
    private JFormattedTextField hourEndField;
    private JTextField aulaField;
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

        setListaDocenti(teacherBox);
        setDocenteSelected(teacherBox, AgendaView.getInstance().getInsegnamentoSelected());
    }

    /**
     * Metodo che prende i valori dei campi del form e li mette dentro un oggetto Esame
     *
     * @return
     */
    public Esame getNuovaAttivita() {
        Esame attivita = new Esame();
        Date oraInizio = (Date) hourStartField.getValue();
        Date oraFine = (Date) hourEndField.getValue();
        attivita.setId(idAttivita)
                .setDocente((Docente) teacherBox.getSelectedItem())
                .setOraInizio(Utils.dateToLocaltime(oraInizio))
                .setOraFine(Utils.dateToLocaltime(oraFine))
                .setCategoria(Attivita.ESAME)
                .setLuogo(aulaField.getText())
                .setRuoloDocente(getRuoloDocente());
        attivita.setData((Date) dataField.getValue());
        attivita.setTipologiaProva(findTipologiaEsameFromSelection());
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
        } else if (Validator.isFormattedFieldEmpty(dataField, "Data")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(hourStartField, "Ora inizio") || Validator.isFormattedFieldEmpty(hourEndField, "Ora fine")) {
            flag = false;
        } else if (Validator.isTextFieldEmpty(aulaField, "Aula")) {
            flag = false;
        } else if (!scrittoRadioButton.isSelected() && !oraleRadioButton.isSelected()) {
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
    public void fillForm(Esame attivita) {
        teacherBox.setSelectedItem(attivita.getDocente());
        ruoloDocenteBox.setSelectedItem(attivita.getRuoloDocente());
        dataField.setValue(attivita.getData());
        hourStartField.setValue(Utils.localtimeToDate(attivita.getOraInizio()));
        hourEndField.setValue(Utils.localtimeToDate(attivita.getOraFine()));
        aulaField.setText(attivita.getLuogo());
        if (attivita.getTipologiaProva().equals("scritto")) {
            scrittoRadioButton.setSelected(true);
        } else if (attivita.getTipologiaProva().equals("orale")) {
            oraleRadioButton.setSelected(true);
        } else {
            laboratorioRadioButton.setSelected(true);
        }
        idAttivita = attivita.getId();
    }

    /**
     * Restituisce la tipologia di esame selezionata
     *
     * @return
     */
    private String findTipologiaEsameFromSelection() {
        String tipologia;
        if (scrittoRadioButton.isSelected()) {
            tipologia = Esame.TIPOLOGIA_SCRITTO;
        } else if (oraleRadioButton.isSelected()) {
            tipologia = Esame.TIPOLOGIA_ORALE;
        } else {
            tipologia = Esame.TIPOLOGIA_LABORATORIO;
        }
        return tipologia;
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
    public void closeFrame() {
        frame.dispose();
    }


    /* Listeners setters */

    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void addCancelButtonListener(ActionListener listener) {
        esameCancelButton.addActionListener(listener);
    }

    public void addTipologiaProvaRadioListener(ActionListener listener) {
        oraleRadioButton.addActionListener(listener);
        laboratorioRadioButton.addActionListener(listener);
        scrittoRadioButton.addActionListener(listener);
    }

    /* Getters */

    public JButton getSubmitButton() {
        return submitButton;
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
