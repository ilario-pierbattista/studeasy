package org.oop.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by toioski on 20/12/14.
 */
public class Profilo extends AbstractView<Mainframe> {
    public JPanel profilopanel;
    private JPanel sidebarpanel;
    private JSplitPane splitpane;
    private JPanel librettopanel;
    private JLabel sidebartitle;

    public Profilo(){
        super();
        //Setta la larghezza della sidebar
        splitpane.setDividerLocation(200 + splitpane.getInsets().left);
        //Elimina i bordi
        splitpane.setBorder(null);
        sidebarpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(126,126,126)));
    }

}
