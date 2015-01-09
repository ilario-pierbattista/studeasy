package org.oop.view.agenda;

import org.oop.general.Utils;
import org.oop.view.AbstractView;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Date;


public class AttivitaEventoView extends AbstractView {
    public JPanel activitypanel;

    private JLabel activityname;
    private JLabel teacher;
    private JButton editbutton;
    private JLabel luogo;
    private JLabel hourEnd;
    private JLabel hourStart;
    private JLabel dataField;

    public AttivitaEventoView() {
    }


    public AttivitaEventoView(String nomeattivita) {
        activityname.setText(nomeattivita);
        teacher.setText("Docente non definito");
        luogo.setText("Aula non definita");
        hourEnd.setText("Orario non definito");
    }

    public AttivitaEventoView(String nomeattivita, String nomeprofessore, String place, Date date, LocalTime hstart, LocalTime hend) {
        activityname.setText(nomeattivita);
        teacher.setText(nomeprofessore);
        luogo.setText(place);
        dataField.setText(Utils.dateToString(date,0));
        hourStart.setText(Utils.timeToString(hstart));
        hourEnd.setText(Utils.timeToString(hend));
    }

    public JButton getEditbutton() {
        return editbutton;
    }

    public void addEditButtonListener (ActionListener listener){
        editbutton.addActionListener(listener);
    }
}
