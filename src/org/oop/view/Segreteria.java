package org.oop.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by toioski on 20/12/14.
 */
public class Segreteria extends AbstractView<Segreteria> {
    public JFrame frame = new JFrame("Segreteria");
    public JPanel segreteriapanel;
    private JTabbedPane segreteriatabpane;
    public Agenda agenda = new Agenda();


    public Segreteria() {
        super();
        frame.setContentPane(segreteriapanel);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setVisible(true);
    }

}
