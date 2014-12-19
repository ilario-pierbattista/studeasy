package org.oop.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by toioski on 19/12/14.
 */
public class Layout {
    private JTextField helloWorldTextField;
    private JButton mainbutton;
    private JPanel layoutpanel;
    private JButton exitbutton;

    int counter;

    public Layout() {
        final JFrame frame = new JFrame("Layout");
        frame.setContentPane(layoutpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        mainbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                counter++;
                mainbutton.setText("Clicked # " + counter);
            }
        });
        exitbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }
}

