package org.oop.view.profilo;

import org.oop.general.Utils;
import org.oop.general.Validator;
import org.oop.model.entities.Insegnamento;
import org.oop.view.AbstractForm;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

public class FormInsegnamento extends AbstractForm {
    private JPanel forminsegnamentopanel;
    private JButton annullaButton;
    private JButton confermaButton;
    private JFormattedTextField dataField;
    private JFormattedTextField votoField;
    private JPanel dettagliInsegnamentoPanel;
    private JCheckBox lodeCheckBox;
    private Insegnamento insegnamento;

    public FormInsegnamento() {
        frame = new JFrame("Inserimento Insegnamento");
        frame.setContentPane(forminsegnamentopanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Metodo di appoggio che controlla che i campi del form siano stati compilati
     * correttamente
     * @return
     */
    public boolean isValid(){
        boolean flag = false;

        if (Validator.isFormattedFieldEmpty(dataField, "Data di superamento")) {
            flag = false;
        } else if (Validator.isTextFieldEmpty(votoField, "Voto")) {
            flag = false;
        } else if (dataField.getValue() == null) {
            flag = false;
            JOptionPane.showMessageDialog(null, "Il formato della data non è valido");
        } else if ((Integer) votoField.getValue() < 18 || (Integer) votoField.getValue() > 30) {
            flag = false;
            JOptionPane.showMessageDialog(null, "Voto non valido");
        } else if ((Integer) votoField.getValue() != 30 && lodeCheckBox.isSelected()) {
            flag = false;
            JOptionPane.showMessageDialog(null, "Non è possibile assegnare la lode con questo voto");
        }

        return flag;
    }

    public void setInsegnamento(Insegnamento insegnamento) {
        this.insegnamento = insegnamento;
        // @TODO sarebbe bello aggiungere il nome dell'insegnamento nel titolo
        votoField.setValue(insegnamento.getVoto());
        dataField.setValue(insegnamento.getData());
        lodeCheckBox.setSelected(insegnamento.isLode());
    }

    public Insegnamento salvaInsegnamento() {
        insegnamento.setVoto((Integer) votoField.getValue());
        insegnamento.setData((Date) dataField.getValue());
        insegnamento.setLode(lodeCheckBox.isSelected());
        return insegnamento;
    }

    /**
     * Setta componenti GUI custom (rispetto all'editor visuale)
     */
    private void createUIComponents() {
        dataField = new JFormattedTextField(dateformat);
    }


    /**
     * Metodo che chiude il form
     */
    public void closeFrame() {
        frame.dispose();
    }


    /* Listener setters */
    public void addConfermaButtonListener(ActionListener listener) {
        confermaButton.addActionListener(listener);
    }

    public void addAnnullaButtonListener(ActionListener listener) {
        annullaButton.addActionListener(listener);
    }


    /* Getters */
    public JFrame getFrame() {
        return frame;
    }


    public JPanel getForminsegnamentopanel() {
        return forminsegnamentopanel;
    }

    public JButton getAnnullaButton() {
        return annullaButton;
    }

    public JButton getConfermaButton() {
        return confermaButton;
    }

    public JFormattedTextField getDataField() {
        return dataField;
    }

    public JFormattedTextField getVotoField() {
        return votoField;
    }
}
