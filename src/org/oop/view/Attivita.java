package org.oop.view;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by toioski on 20/12/14.
 */
public class Attivita {
    public JPanel activitypanel;

    private JLabel activityname;
    private JLabel teacher;
    private JButton editbutton;
    private JLabel aula;
    private JLabel description;
    private JLabel hour;


    public Attivita(String nomeattivita) {
        activityname.setText(nomeattivita);
        teacher.setText("Docente non definito");
        aula.setText("Aula non definita");
        description.setText("Descrizione non definita");
        hour.setText("Orario non definito");
    }

    public Attivita(String nomeattivita, String nomeprofessore, String a, String h, String d) {
        activityname.setText(nomeattivita);
        teacher.setText(nomeprofessore);
        aula.setText(a);
        hour.setText(h);
        description.setText(d);
    }

    public JButton getEditbutton() {
        return editbutton;
    }

    public void addEditButtonListener (ActionListener listener){
        editbutton.addActionListener(listener);
    }
}
