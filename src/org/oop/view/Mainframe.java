package org.oop.view;

import javax.swing.*;

/**
 * Created by toioski on 20/12/14.
 */
public class Mainframe {
    private JPanel mainpanel;
    private JTabbedPane maintabpane;

    public Mainframe() {
        final JFrame frame = new JFrame("Studeasy");
        frame.setContentPane(mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,650);
        // Posiziona la finestra al centro dello schermo
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        maintabpane.addTab("Agenda", new Agenda().agendapanel);
        maintabpane.addTab("Profilo", new Profilo().profilopanel);
        maintabpane.addTab("Segreteria", new Segreteria().segreteriapanel);

    }


}
