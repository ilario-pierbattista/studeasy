package org.oop.view.segreteria;

import org.oop.general.Validator;
import org.oop.model.entities.Utente;
import org.oop.view.AbstractForm;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

public class FormTesi extends AbstractForm {
    public JPanel tesipanel;
    private JButton submit;
    private JButton quit;
    private JFormattedTextField nome;
    private JFormattedTextField cognome;
    private JFormattedTextField dataNascita;
    private JFormattedTextField luogoNascita;
    private JFormattedTextField annoCorso;
    private JFormattedTextField profRelatore;
    private JFormattedTextField titoloTesi;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JLabel matricolaLabel;
    private JLabel emailLabel;
    private JLabel dataNascitaLabel;
    private JLabel luogoDiNascitaLabel;
    private JLabel annoCorsoLabel;
    private JLabel profRelatoreLabel;
    private JLabel titoloTesiLabel;
    private JFormattedTextField matricola;
    private JFormattedTextField email;

    /**
     * Imposta le informazioni dell'utente
     *
     * @param utente Utente corrente
     */
    public void setInfoUtente(Utente utente) {
        nome.setValue(utente.getNome());
        cognome.setValue(utente.getCognome());
        email.setValue(utente.getEmail());
        matricola.setValue(utente.getMatricola());
    }

    @Override
    public boolean isValid() {
        boolean flag;

        if (Validator.isFormattedFieldEmpty(nome, "Nome") || !Validator.inputSentenceControl(nome.getText(), "Nome")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(cognome, "Cognome") || !Validator.inputSentenceControl(cognome.getText(), "Cognome")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(dataNascita, "Data di nascita")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(email, "Email") || !Validator.email(email.getText())) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(matricola, "Matricola")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(luogoNascita, "Luogo di nascita") || !Validator.inputSentenceControl(luogoNascita.getText(), "Luogo di nascita")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(annoCorso, "Anno di corso") || !Validator.inputYearControl(annoCorso.getText())) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(profRelatore, "Professore relatore") || !Validator.inputSentenceControl(profRelatore.getText(), "Professore relatore")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(titoloTesi, "Titolo Tesi") || !Validator.inputSentenceControl(titoloTesi.getText(), "Titolo Tesi")) {
            flag = false;
        } else {
            flag = true;
        }

        return flag;
    }

    public void addFocusListenerMatricola(FocusListener listener) {
        matricola.addFocusListener(listener);
    }

    public void insSubmitFormButtonListener(ActionListener l) {
        submit.addActionListener(l);
    }

    public void insQuitFormButtonListener(ActionListener l) {
        quit.addActionListener(l);
    }

    private void createUIComponents() {
        dataNascita = new JFormattedTextField(dateformat);
        matricola = new JFormattedTextField(dfMatricola);
        annoCorso = new JFormattedTextField(dateformatYear);
    }

    public JFormattedTextField getNome() {
        return nome;
    }

    public JFormattedTextField getCognome() {
        return cognome;
    }

    public JFormattedTextField getDataNascita() {
        return dataNascita;
    }

    public JFormattedTextField getLuogoNascita() {
        return luogoNascita;
    }

    public JFormattedTextField getAnnoCorso() {
        return annoCorso;
    }

    public JFormattedTextField getProfRelatore() {
        return profRelatore;
    }

    public JFormattedTextField getTitoloTesi() {
        return titoloTesi;
    }

    public JFormattedTextField getMatricola() {
        return matricola;
    }

    public JFormattedTextField getEmail() {
        return email;
    }
}
