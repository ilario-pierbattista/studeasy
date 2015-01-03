package org.oop.view;

import org.oop.model.entities.Ciclo;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Date;


public class FormCiclo extends AbstractView<FormCiclo> {
    public static JFrame frame = new JFrame("Crea nuovo ciclo");

    private JPanel panel1;
    private JTextField ciclonamefield;
    private JFormattedTextField cicloStartField;
    private JFormattedTextField cicloEndField;
    private JButton submitButton;
    private JButton cancelButton;

    public FormCiclo() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public Ciclo getNuovoCiclo() {
        Ciclo ciclo = new Ciclo();

        ciclo.setLabel(ciclonamefield.getText())
                .setInizio((Date) cicloStartField.getValue())
                .setFine((Date) cicloEndField.getValue());
        return ciclo;
    }

    public void closeFrame() {
        frame.dispose();
    }

    private void createUIComponents() {
        cicloStartField = new JFormattedTextField(dateformat);
        cicloEndField = new JFormattedTextField(dateformat);
    }

    /* Getters */
    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JFormattedTextField getCicloEndField() {
        return cicloEndField;
    }

    public JFormattedTextField getCicloStartField() {
        return cicloStartField;
    }

    public JTextField getCiclonamefield() {
        return ciclonamefield;
    }

    /* Listeners setters */
    public void addSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void addCancelButtonListener(ActionListener listener) {
        cancelButton.addActionListener(listener);
    }

}
