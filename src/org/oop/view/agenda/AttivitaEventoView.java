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
    private JLabel luogoField;
    private JLabel hourEnd;
    private JLabel hourStart;
    private JLabel dataField;
    private JLabel dayField;
    private JLabel tipoEsameField;


    public AttivitaEventoView(String nomeattivita) {
        activityname.setText(nomeattivita);
        teacher.setText("Docente non definito");
        luogoField.setText("Aula non definita");
        hourEnd.setText("Orario non definito");
    }

    // Costruttore attivitaEvento
    public AttivitaEventoView(String nomeattivita, String nomeprofessore, String place, Date date, LocalTime hstart, LocalTime hend) {
        activityname.setText(nomeattivita);
        teacher.setText(nomeprofessore);
        luogoField.setText(place);
        dataField.setText(Utils.dateToString(date,0));
        hourStart.setText(hstart.toString());
        hourEnd.setText(hend.toString());
    }

    // Costrutto attivitaPeriodica
    public AttivitaEventoView(String nomeattivita, String nomeprofessore, int giorno, LocalTime hstart, LocalTime hend) {
        activityname.setText(nomeattivita);
        teacher.setText(nomeprofessore);
        dayField.setText(String.valueOf(giorno));
        hourEnd.setText(hend.toString());
        hourStart.setText(hstart.toString());
    }

    // Costrutto attivitaEsame
    public AttivitaEventoView(String nomeattivita, String nomeprofessore, Date date, LocalTime hstart, LocalTime hend, String aula, String tipoesame) {
        activityname.setText(nomeattivita);
        teacher.setText(nomeprofessore);
        dataField.setText(Utils.dateToString(date,0));
        hourEnd.setText(hstart.toString());
        hourStart.setText(hend.toString());
        luogoField.setText(aula);
        tipoEsameField.setText(tipoesame);

    }

    public JButton getEditbutton() {
        return editbutton;
    }

    public void addEditButtonListener (ActionListener listener){
        editbutton.addActionListener(listener);
    }
}
