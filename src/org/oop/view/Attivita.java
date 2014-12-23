package org.oop.view;

import javax.swing.*;
import javax.swing.text.StringContent;

/**
 * Created by toioski on 20/12/14.
 */
public class Attivita {
    public JPanel activitypanel;

    private JLabel activityname;
    private JLabel teachername;
    private JButton editbutton;

    public Attivita(String nomeattivita, String nomeprofessore) {
        activityname.setText(nomeattivita);
        teachername.setText(nomeprofessore);
    }
}
