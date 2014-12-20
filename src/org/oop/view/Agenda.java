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
    Dimension minimumsize = new Dimension(200,-1);

    public Agenda() {
        super();
        sidebarpanel.setMinimumSize(minimumsize);
    }
}
