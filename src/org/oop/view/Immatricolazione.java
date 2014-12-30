package org.oop.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Immatricolazione {
    public JPanel immatricolazionepanel;
    private JButton submit;
    private JButton quit;

    public Immatricolazione() {
        super();

    }

    public void insSubmitFormButtonListener (ActionListener l) {
        submit.addActionListener(l);
    }
    public void insQuitFormButtonListener (ActionListener l) {
        quit.addActionListener(l);
    }
}
