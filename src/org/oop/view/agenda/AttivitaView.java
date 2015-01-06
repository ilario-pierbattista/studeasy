package org.oop.view.agenda;

import org.oop.view.AbstractView;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by toioski on 20/12/14.
 */

public class AttivitaView extends AbstractView {
    public JPanel activitypanel;

    private JLabel activityname;
    private JLabel teacher;
    private JButton editbutton;
    private JLabel luogo;
    private JLabel description;
    private JLabel hour;


    public AttivitaView(String nomeattivita) {
        activityname.setText(nomeattivita);
        teacher.setText("Docente non definito");
        luogo.setText("Aula non definita");
        description.setText("Descrizione non definita");
        hour.setText("Orario non definito");
    }

    public AttivitaView(String nomeattivita, String nomeprofessore, String a, String h, String d) {
        activityname.setText(nomeattivita);
        teacher.setText(nomeprofessore);
        luogo.setText(a);
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
