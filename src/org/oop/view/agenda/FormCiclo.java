package org.oop.view.agenda;

import org.oop.general.Validator;
import org.oop.model.entities.Ciclo;
import org.oop.view.AbstractForm;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Date;


public class FormCiclo extends AbstractForm {

    private JPanel panel1;
    private JTextField ciclonamefield;
    private JFormattedTextField cicloStartField;
    private JFormattedTextField cicloEndField;
    private JButton submitButton;
    private JButton cancelButton;

    public FormCiclo() {
        frame = new JFrame("Crea nuovo ciclo");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Metodo che prende i valori dei campi del form e li mette dentro un oggetto Ciclo. Dopodichè ritorna tale oggetto
     *
     * @return Nuovo oggetto Ciclo
     */
    public Ciclo getNuovoCiclo() {
        Ciclo ciclo = new Ciclo();

        ciclo.setLabel(ciclonamefield.getText())
                .setInizio((Date) cicloStartField.getValue())
                .setFine((Date) cicloEndField.getValue());

        return ciclo;
    }

    @Override
    public boolean isValid() {
        boolean flag = false;
        Date start = (Date) cicloStartField.getValue();
        Date end = (Date) cicloEndField.getValue();

        if (Validator.isTextFieldEmpty(ciclonamefield, "Nome ciclo")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(cicloStartField, "Data inizio") || Validator.isFormattedFieldEmpty(cicloEndField, "Date fine")) {
            flag = false;
        } else if (Validator.isDateGreater(start, end)) {
            flag = true;
        }

        return flag;
    }

    /**
     * Metodo che chiude il form
     */
    public void closeFrame() {
        frame.dispose();
    }


    /**
     * Setta componenti GUI custom (rispetto all'editor visuale)
     */
    private void createUIComponents() {
        cicloStartField = new JFormattedTextField(dateformat);
        cicloEndField = new JFormattedTextField(dateformat);
    }

    /* Getters */
    public JButton getSubmitButton() {
        return submitButton;
    }

    /* Listeners setters */
    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void addCancelButtonListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }
}
