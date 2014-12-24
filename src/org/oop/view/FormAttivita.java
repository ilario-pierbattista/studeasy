package org.oop.view;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by toioski on 23/12/14.
 */
public class FormAttivita {
    public static  JFrame frame = new JFrame("Crea Attività");
    private JPanel panel1;
    private JTextField namefield;
    private JTextField teacherfield;
    private JTextField aulafield;
    private JFormattedTextField formattedTextField1;
    private JTextArea textArea1;
    private JButton submitbutton;
    private JButton cancelbutton;

    public FormAttivita() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    /* Listeners setters */
    public void addSubmitButtonListener (ActionListener listener){
        submitbutton.addActionListener(listener);
    }
    public void addCancelButtonListener (ActionListener listener){
        cancelbutton.addActionListener(listener);
    }

    /* Getters */
    public JButton getSubmitbutton() {
        return submitbutton;
    }

    public JButton getCancelbutton() {
        return cancelbutton;
    }


    public static void closeFrame(){
        frame.dispose();
    }

}
