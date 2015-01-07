package org.oop.view;

import org.oop.view.agenda.Agenda;
import org.oop.view.profilo.Profilo;
import org.oop.view.segreteria.Segreteria;

import javax.swing.*;


public class Mainframe {
    final static JFrame frame = new JFrame("Studeasy");
    private JPanel mainpanel;
    private JTabbedPane maintabpane;
    public Agenda agenda;
    public Profilo profilo;
    public Segreteria segreteria;


    public Mainframe() {
        frame.setContentPane(mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 650);
        frame.setLocationRelativeTo(null);

        agenda = new Agenda();
        profilo = new Profilo();
        segreteria = new Segreteria();
        maintabpane.addTab("Agenda", agenda.agendapanel);
        maintabpane.addTab("Profilo", profilo.profiloPanel);
        maintabpane.addTab("Segreteria", segreteria.segreteriapanel);

    }

    public static void refreshView() {
        frame.repaint();
    }

    public static void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

}
