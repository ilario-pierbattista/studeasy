package org.oop.view;

import org.oop.view.agenda.Agenda;
import org.oop.view.profilo.Profilo;
import org.oop.view.segreteria.Segreteria;

import javax.swing.*;


public class Mainframe extends AbstractView<Mainframe> {
    final static JFrame frame = new JFrame("Studeasy");
    private JPanel mainpanel;
    private JTabbedPane maintabpane;
    public Agenda agenda = new Agenda();
    public Profilo profilo = new Profilo();
    public Segreteria segreteria = new Segreteria();


    public Mainframe() {
        frame.setContentPane(mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 650);
        // Posiziona la finestra al centro dello schermo
        frame.setLocationRelativeTo(null);
        maintabpane.addTab("Agenda", agenda.agendapanel);
        maintabpane.addTab("Profilo", profilo.profilopanel);
        maintabpane.addTab("Segreteria", segreteria.segreteriapanel);

    }

    public static void refreshView() {
        frame.repaint();
    }

    public static void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

}
