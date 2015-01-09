package org.oop.view.agenda;

import org.apache.commons.lang3.StringUtils;
import org.oop.general.Utils;
import org.oop.model.entities.AttivitaEvento;
import org.oop.model.entities.AttivitaPeriodica;
import org.oop.model.entities.Esame;
import org.oop.view.AbstractView;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Date;


public class Attivita extends AbstractView {
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
    private JLabel ruoloField;
    private JButton deleteButton;

    public Attivita(org.oop.model.entities.Attivita attivita) {
        activityname.setText(StringUtils.capitalize(attivita.getCategoria()));
        teacher.setText(attivita.getDocente().getNomeCognome());
        ruoloField.setText("(".concat(attivita.getRuoloDocente().concat(")")));
        luogoField.setText("Luogo: ".concat(attivita.getLuogo()));
        hourStart.setText("Ora inizio: ".concat(attivita.getOraInizio().toString()));
        hourEnd.setText("Ora fine: ".concat(attivita.getOraFine().toString()));
        if (attivita instanceof AttivitaEvento) {
            dataField.setText(Utils.dateToString(((AttivitaEvento) attivita).getData(), 0));
            dayField.setText("");
            tipoEsameField.setText("");
            if (attivita instanceof Esame) {
                tipoEsameField.setText("Tipologia: ".concat(((Esame) attivita).getTipologiaProva()));
            }
        } else if (attivita instanceof AttivitaPeriodica) {
            dayField.setText(((AttivitaPeriodica) attivita).getNomeGiorno());
            dataField.setText("");
            tipoEsameField.setText("");
        }
        editbutton.setActionCommand(Integer.toString(attivita.getId()));
        deleteButton.setActionCommand(Integer.toString(attivita.getId()));
    }

    public Attivita(String nomeattivita, String nomeprofessore, String ruolodocente, String place, LocalTime hstart, LocalTime hend) {
        activityname.setText(nomeattivita);
        teacher.setText(nomeprofessore);
        ruoloField.setText("(" + ruolodocente + ")");
        luogoField.setText("Luogo: " + place);
        hourStart.setText("Ora inizio: " + hstart.toString());
        hourEnd.setText("Ora fine: " + hend.toString());
    }

    // Costruttore attivitaEvento
    public Attivita(String nomeattivita, String nomeprofessore, String ruolodocente, String place, LocalTime hstart, LocalTime hend, Date date) {
        this(nomeattivita, nomeprofessore, ruolodocente, place, hstart, hend);
        dataField.setText(Utils.dateToString(date, 0));

        //Elimino i campi che non servono
        tipoEsameField.setText("");
        dayField.setText("");
    }

    // Costrutto attivitaPeriodica
    public Attivita(String nomeattivita, String nomeprofessore, String ruolodocente, String place, LocalTime hstart, LocalTime hend, int giorno) {
        this(nomeattivita, nomeprofessore, ruolodocente, place, hstart, hend);
        dayField.setText(Utils.intToStringDate(giorno));

        //Elimino i campi che non servono
        tipoEsameField.setText("");
        dataField.setText("");
    }

    // Costrutto attivitaEsame
    public Attivita(String nomeattivita, String nomeprofessore, String ruolodocente, String place, LocalTime hstart, LocalTime hend, Date date, String tipoesame) {
        this(nomeattivita, nomeprofessore, ruolodocente, place, hstart, hend);
        dataField.setText(Utils.dateToString(date, 0));
        tipoEsameField.setText("Tipologia: " + "\"" + tipoesame + "\"");

        //Elimino i campi che non servono
        dayField.setText("");

    }

    /* Listeners setter */
    public void addEditButtonListener(ActionListener listener) {
        editbutton.addActionListener(listener);
    }

    public void addDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    /* Getters */
    public JButton getEditbutton() {
        return editbutton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

}
