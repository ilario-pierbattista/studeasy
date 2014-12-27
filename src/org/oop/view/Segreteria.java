package org.oop.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by toioski on 20/12/14.
 */
public class Segreteria extends AbstractView<Segreteria> {
    public JPanel segreteriapanel;

    private JButton aggiungiform;

    public Segreteria() {
        super();
    }



    public void insFormButtonListener(ActionListener l) {
        aggiungiform.addActionListener(l);
    }


    public JButton getAggiungiform() { return aggiungiform;}
}
