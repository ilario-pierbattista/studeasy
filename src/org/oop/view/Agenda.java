package org.oop.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by toioski on 20/12/14.
 */
public class Agenda {
    public JPanel agendapanel;

    private JScrollPane sidebarpane;
    private JScrollPane activitypane;
    private JSplitPane splitpane;
    private JButton addinsbutton;
    private JPanel sidebarpanel;
    private JPanel activitypanel;
    private JLabel insegnamentolabel;
    private JLabel durataciclolabel;
    private JPanel activitieslist;
    private JButton lezioneButton;
    private JButton laboratorioButton;
    private JButton esameButton;

    public Agenda() {
        splitpane.setDividerLocation(200 + splitpane.getInsets().left);
    }
}
