package org.oop.view;

import javax.swing.*;

/**
 * Created by toioski on 20/12/14.
 */
public class Mainframe {
    final static JFrame frame = new JFrame("Studeasy");
    private JPanel mainpanel;
    private JTabbedPane maintabpane;
    public Agenda agenda = new Agenda();
    public Profilo profilo = new Profilo();
    public Segreteria segreteria = new Segreteria();


    public Mainframe() {
        frame.setContentPane(mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,650);
        // Posiziona la finestra al centro dello schermo
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        maintabpane.addTab("Agenda", agenda.agendapanel);
        maintabpane.addTab("Profilo", profilo.profilopanel);
        maintabpane.addTab("Segreteria", segreteria.segreteriapanel);

    }

    public static void refreshView(){
        frame.repaint();
    }


}
