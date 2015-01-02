package org.oop.view;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by toioski on 02/01/15.
 */
public class FormCiclo extends AbstractView<FormCiclo> {
    public static  JFrame frame = new JFrame("Crea nuovo ciclo");

    private JPanel panel1;
    private JTextField ciclonamefield;
    private JFormattedTextField ciclostartfield;
    private JFormattedTextField cicloendfield;
    private JButton submitbutton;
    private JButton cancelbutton;

    public FormCiclo() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    /* Getters */
    public JButton getSubmitbutton() {
        return submitbutton;
    }

    public JButton getCancelbutton() {
        return cancelbutton;
    }

    public JFormattedTextField getCicloendfield() {
        return cicloendfield;
    }

    public JFormattedTextField getCiclostartfield() {

        return ciclostartfield;
    }

    public JTextField getCiclonamefield() {

        return ciclonamefield;
    }

    /* Listeners setters */
    public void addSubmitButtonListener(ActionListener listener){
        submitbutton.addActionListener(listener);
    }
    public void addCancelButtonListener(ActionListener listener){
        cancelbutton.addActionListener(listener);
    }


    public static void closeFrame(){
        frame.dispose();
    }

    private void createUIComponents() {
        ciclostartfield = new JFormattedTextField(dateformat);
    }
}
