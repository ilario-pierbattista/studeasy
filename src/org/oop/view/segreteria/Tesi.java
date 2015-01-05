package org.oop.view.segreteria;

import org.oop.view.AbstractView;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Tesi extends AbstractView {
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
    private JLabel dataNascitaLabel;
    private JLabel luogoDiNascitaLabel;
    private JLabel annoCorsoLabel;
    private JLabel profRelatoreLabel;
    private JLabel titoloTesiLabel;
    private JFormattedTextField matricola;
    private JLabel matricolaLabel;
    private JFormattedTextField email;
    private JLabel emailLabel;


    public void insSubmitFormButtonListener (ActionListener l) {
        submit.addActionListener(l);
    }

    public void insQuitFormButtonListener (ActionListener l) {
        quit.addActionListener(l);
    }

    private void createUIComponents() {
        dataNascita = new JFormattedTextField(dateformat);
    }
    public JFormattedTextField getNome() {
        return nome;
    }

    public void setNome(JFormattedTextField nome) {
        this.nome = nome;
    }

    public JFormattedTextField getCognome() {
        return cognome;
    }

    public void setCognome(JFormattedTextField cognome) {
        this.cognome = cognome;
    }

    public JFormattedTextField getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(JFormattedTextField dataNascita) {
        this.dataNascita = dataNascita;
    }

    public JFormattedTextField getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(JFormattedTextField luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public JFormattedTextField getAnnoCorso() {
        return annoCorso;
    }

    public void setAnnoCorso(JFormattedTextField annoCorso) {
        this.annoCorso = annoCorso;
    }

    public JFormattedTextField getProfRelatore() {
        return profRelatore;
    }

    public void setProfRelatore(JFormattedTextField profRelatore) {
        this.profRelatore = profRelatore;
    }

    public JFormattedTextField getTitoloTesi() {
        return titoloTesi;
    }

    public JFormattedTextField getMatricola () {
        return matricola;
    }
    public JFormattedTextField getEmail() {
        return email;
    }
}
