package org.oop.view.agenda;

import org.oop.general.Utils;
import org.oop.model.entities.Ciclo;
import org.oop.view.AbstractView;

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

    /**
     * Metodo che prende i valori dei campi del form e li mette dentro un oggetto Ciclo.
     * Dopodichè ritorna tale oggetto
     * @return
     */
    public Ciclo getNuovoCiclo() {
        boolean flag = false;
        Ciclo ciclo = new Ciclo();

        while(flag){
            if (isFormValid()) {
                ciclo.setLabel(ciclonamefield.getText())
                        .setInizio((Date) cicloStartField.getValue())
                        .setFine((Date) cicloEndField.getValue());
                flag = true;
            }
        }

        return ciclo;

    }

    /**
     * Metodo di appoggio che controlla che i campi del form siano stati compilati
     * correttamente
     * @return
     */
    private boolean isFormValid(){
        boolean flag;
        Date start = (Date) cicloStartField.getValue();
        Date end = (Date) cicloEndField.getValue();

        if (end.after(start) && Utils.isTextFieldFilled(ciclonamefield)){
            flag = true;
        } else if (!end.after(start)){
            JOptionPane.showMessageDialog(null, "La data di fine deve essere successiva a quella di inizio!");
            flag = false;
        } else {
            JOptionPane.showMessageDialog(null, "Devi inserire un nome per il ciclo!");
            flag = false;

        }

        return flag;
    }

    /**
     * Metodo che chiude il form
     */
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
