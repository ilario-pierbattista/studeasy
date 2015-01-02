package org.oop.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Tesi {
    public JPanel tesipanel;
    private JButton submit;
    private JButton quit;


    public void insSubmitFormButtonListener (ActionListener l) {
        submit.addActionListener(l);
    }

    public void insQuitFormButtonListener (ActionListener l) {
        quit.addActionListener(l);
    }
}
