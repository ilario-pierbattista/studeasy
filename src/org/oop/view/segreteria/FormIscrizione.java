package org.oop.view.segreteria;

import org.oop.controller.BaseController;
import org.oop.general.Validator;
import org.oop.model.entities.Iscrizione;
import org.oop.view.AbstractForm;

import javax.swing.*;
import java.awt.event.ActionListener;


public class FormIscrizione extends AbstractForm {
    private JFormattedTextField annoAccademicoField;
    private JButton submitButton;
    private JButton cancelButton;
    private JPanel panel;
    private JFormattedTextField annoCorsoField;
    private int idIscrizione;

    public FormIscrizione() {
        frame = new JFrame("Aggiungi anno iscrizione");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        idIscrizione = 0;
    }

    /**
     * Metodo che prende i valori dei campi del form e li mette dentro un oggetto Iscrizione.
     * Dopodichè ritorna tale oggetto
     *
     * @return Iscrizione
     */
    public Iscrizione getNuovaIscrizione() {
        Iscrizione iscrizione = new Iscrizione();
        iscrizione.setId(idIscrizione)
                .setAnno(Integer.parseInt(annoCorsoField.getText()))
                .setAnnoAccademico(Integer.parseInt(annoAccademicoField.getText()))
                .setUtente(BaseController.getUtenteCorrente());
        return iscrizione;
    }

    @Override
    public boolean isValid() {
        boolean flag = true;

        if (Validator.isFormattedFieldEmpty(annoCorsoField, "Anno di corso")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(annoAccademicoField, "Anno accademico")) {
            flag = false;
        }

        return flag;
    }

    /**
     * Metodo che riempe i campi del form con i valori dell'Iscrizione che gli si è passata
     *
     * @param iscrizione
     */
    public void fillForm(Iscrizione iscrizione) {
        idIscrizione = iscrizione.getId();
        annoAccademicoField.setText(String.valueOf(iscrizione.getAnnoAccademico()));
        annoCorsoField.setText(String.valueOf(iscrizione.getAnno()));
    }

    /**
     * Setta componenti GUI custom (rispetto all'editor visuale)
     */
    private void createUIComponents() {
        annoCorsoField = new JFormattedTextField(dateformatYear);
        annoAccademicoField = new JFormattedTextField(dateformatYear);
    }

    /* Listeners setter */
    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void addCancelButtonListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    /* Getters */
    public JTextField getAnnoAccademicoField() {
        return annoAccademicoField;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
