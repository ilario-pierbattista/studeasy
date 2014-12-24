package org.oop.view;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by toioski on 23/12/14.
 */
public class FormAttivita extends AbstractView<Mainframe> {
    public static  JFrame frame = new JFrame("Crea Attività");
    private JPanel panel1;
    private JTextField namefield;
    private JTextField teacherfield;
    private JTextField aulafield;
    private JFormattedTextField datafield;
    private JTextArea descriptionfield;
    private JButton submitbutton;
    private JButton cancelbutton;
    private JLabel activityname;

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

    public JLabel getActivityname() {
        return activityname;
    }

    public JButton getSubmitbutton() {
        return submitbutton;
    }

    public JButton getCancelbutton() {
        return cancelbutton;
    }

    public JTextField getTeacherfield() {
        return teacherfield;
    }

    public JTextArea getDescriptionfield() {
        return descriptionfield;
    }

    public JFormattedTextField getDatafield() {

        return datafield;
    }

    public JTextField getAulafield() {

        return aulafield;
    }

    public JTextField getNamefield() {

        return namefield;
    }

    public static void closeFrame(){
        frame.dispose();
    }

}
