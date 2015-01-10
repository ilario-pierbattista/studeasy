package org.oop.view.segreteria;

import org.oop.general.Validator;
import org.oop.model.entities.Tassa;
import org.oop.view.AbstractForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


public class FormTasse extends AbstractForm {
    private JFormattedTextField importoField;
    private JFormattedTextField annoField;
    private JFormattedTextField scadenzaField;
    private JRadioButton pagatoRadioButton;
    private JRadioButton nonPagatoRadioButton;
    private JButton submitButton;
    private JButton cancelButton;
    private JPanel panel;
    private int idTassa;

    public FormTasse() {
        frame = new JFrame("Aggiungi tassa");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        SetPagamentoTassaAction pagamentoTassaAction = new SetPagamentoTassaAction();
        pagatoRadioButton.addActionListener(pagamentoTassaAction);
        nonPagatoRadioButton.addActionListener(pagamentoTassaAction);
        idTassa = 0;
    }

    /**
     * Metodo che prende i valori dei campi del form e li mette dentro un oggetto Tassa.
     * Dopodichè ritorna tale oggetto
     *
     * @return Tassa
     */
    public Tassa getNuovaTassa() {
        Tassa tassa = new Tassa();
        Double importo = Double.parseDouble((importoField.getText()).replace(",", "."));

        tassa.setId(idTassa)
                .setAnnoAccademico(Integer.parseInt(annoField.getText()))
                .setImporto(importo)
                .setScadenza((Date) scadenzaField.getValue())
                .setPagata(isTassaPagata());

        return tassa;
    }

    /**
     * Metodo che riempe i campi del form con i valori della Tassa che gli si è passata
     *
     * @param tassa
     */
    public void fillForm(Tassa tassa) {
        importoField.setText(String.valueOf(tassa.getImporto()));
        annoField.setText(String.valueOf(tassa.getAnnoAccademico()));
        scadenzaField.setValue(tassa.getScadenza());
        if (tassa.isPagata()) {
            pagatoRadioButton.setSelected(true);
            nonPagatoRadioButton.setSelected(false);
        } else {
            pagatoRadioButton.setSelected(false);
            nonPagatoRadioButton.setSelected(true);
        }
        idTassa = tassa.getId();
    }

    /**
     * Metodo di appoggio che controlla che i campi del form siano stati compilati
     * correttamente
     *
     * @return
     */
    public boolean isValid() {
        boolean flag = true;

        if (Validator.isFormattedFieldEmpty(annoField, "Anno accademico")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(importoField, "Importo")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(scadenzaField, "Data di scadenza")) {
            flag = false;
        } else if (!pagatoRadioButton.isSelected() && !nonPagatoRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(null, "Devi selezionare lo 'Stato' della tassa");
            flag = false;
        }
        return flag;
    }

    /**
     * Restituisce lo stato della tassa
     *
     * @return
     */
    private boolean isTassaPagata() {
        return pagatoRadioButton.isSelected();
    }

    /**
     * Setta componenti GUI custom (rispetto all'editor visuale)
     */
    private void createUIComponents() {
        annoField = new JFormattedTextField(dateformatYear);
        importoField = new JFormattedTextField(pagamentoformat);
        scadenzaField = new JFormattedTextField(dateformat);
    }

    /* Listener setters */
    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void addCancelButtonListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

    /* Getters */
    public JTextField getImportoField() {
        return importoField;
    }

    public JRadioButton getPagatoRadioButton() {
        return pagatoRadioButton;
    }

    public JRadioButton getNonPagatoRadioButton() {
        return nonPagatoRadioButton;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    /**
     * listener interno
     */
    class SetPagamentoTassaAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AbstractButton button = (AbstractButton) e.getSource();
            pagatoRadioButton.setSelected(false);
            nonPagatoRadioButton.setSelected(false);

            if (button.getText().equals("Pagato")) {
                pagatoRadioButton.setSelected(true);
            } else {
                nonPagatoRadioButton.setSelected(true);
            }
        }
    }
}
