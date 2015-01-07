package org.oop.view.profilo;

import org.oop.general.Utils;
import org.oop.general.Validator;
import org.oop.model.entities.Insegnamento;
import org.oop.view.AbstractForm;
import org.oop.view.AbstractView;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormInsegnamento extends AbstractForm {
    private JPanel forminsegnamentopanel;
    private JButton annullaButton;
    private JButton confermaButton;
    private JFormattedTextField formattedTextFieldData;
    private JFormattedTextField formattedTextFieldVoto;
    private JPanel dettagliInsegnamentoPanel;
    private Insegnamento insegnamento;

    public FormInsegnamento() {
        frame = new JFrame("Inserimento Insegnamento");
        frame.setContentPane(forminsegnamentopanel);
        frame.pack();
        Utils.centerJFrame(frame);
    }

    /**
     * Metodo di appoggio che controlla che i campi del form siano stati compilati
     * correttamente
     *
     * @return
     */
    public boolean isValid() {
        boolean flag = true;

        if (Validator.isFormattedFieldEmpty(formattedTextFieldData, "Data di superamento")) {
            flag = false;
        } else if (Validator.isTextFieldEmpty(formattedTextFieldVoto, "Voto")) {
            flag = false;
        }

        return flag;
    }

    public void setInsegnamento(Insegnamento insegnamento) {
        this.insegnamento = insegnamento;
        // @TODO sarebbe bello aggiungere il nome dell'insegnamento nel titolo
    }

    /**
     * Setta componenti GUI custom (rispetto all'editor visuale)
     */
    private void createUIComponents() {
        formattedTextFieldData = new JFormattedTextField(dateformat);
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

    public JFormattedTextField getFormattedTextFieldData() {
        return formattedTextFieldData;
    }

    public JFormattedTextField getFormattedTextFieldVoto() {
        return formattedTextFieldVoto;
    }
}
