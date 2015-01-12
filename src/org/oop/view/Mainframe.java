package org.oop.view;

import org.oop.view.agenda.AgendaView;
import org.oop.view.profilo.ProfiloView;
import org.oop.view.segreteria.Segreteria;

import javax.swing.*;
import java.awt.event.WindowListener;


public class Mainframe {
    final static JFrame frame = new JFrame("Studeasy");
    public AgendaView agendaView;
    public ProfiloView profiloView;
    public Segreteria segreteria;
    private JPanel mainpanel;
    private JTabbedPane maintabpane;

    public Mainframe() {
        frame.setContentPane(mainpanel);
        frame.setSize(1000, 650);
        frame.setLocationRelativeTo(null);

        agendaView = new AgendaView();
        profiloView = new ProfiloView();
        segreteria = new Segreteria();
        maintabpane.addTab("Agenda", agendaView.agendapanel);
        maintabpane.addTab("Profilo", profiloView.profiloPanel);
        maintabpane.addTab("Segreteria", segreteria.segreteriapanel);
    }

    public static void refreshView() {
        frame.repaint();
    }

    public static void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public void setSelectedTab(int index) {
        maintabpane.setSelectedIndex(index);
    }

    public void addWindowListener(WindowListener w) {
        frame.addWindowListener(w);
    }
}
