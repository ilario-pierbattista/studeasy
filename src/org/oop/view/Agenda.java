package org.oop.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by toioski on 20/12/14.
 */
public class Agenda {
    public JPanel agendapanel;

    private JScrollPane sidebarpanel;
    private JScrollPane activitypanel;
    private JSplitPane splitpane;

    public Agenda() {
        super();
        splitpane.setDividerLocation(200 + splitpane.getInsets().left);
    }
}
