package org.oop.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Tirocinio {

    public JPanel tirociniopanel;
    private JButton quit;
    private JButton submit;

    public void insSubmitButtonListener (ActionListener l) {
        submit.addActionListener(l);
    }
    public void insQuitFormButtonListener (ActionListener l) {
        quit.addActionListener(l);
    }
}
