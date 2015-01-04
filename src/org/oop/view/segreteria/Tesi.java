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
    private JFormattedTextField email;
    private JFormattedTextField annoCorso;
    private JFormattedTextField profRelatore;
    private JFormattedTextField titoloTesi;
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JLabel dataNascitaLabel;
    private JLabel luogoDiNascitaLabel;
    private JLabel emailLabel;
    private JLabel annoCorsoLabel;
    private JLabel profRelatoreLabel;
    private JLabel titoloTesiLabel;


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

    public JFormattedTextField getEmail() {
        return email;
    }

    public void setEmail(JFormattedTextField email) {
        this.email = email;
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

    public void setTitoloTesi(JFormattedTextField titoloTesi) {
        this.titoloTesi = titoloTesi;
    }

    public JLabel getNomeLabel() {
        return nomeLabel;
    }

    public void setNomeLabel(JLabel nomeLabel) {
        this.nomeLabel = nomeLabel;
    }

    public JLabel getCognomeLabel() {
        return cognomeLabel;
    }

    public void setCognomeLabel(JLabel cognomeLabel) {
        this.cognomeLabel = cognomeLabel;
    }

    public JLabel getDataNascitaLabel() {
        return dataNascitaLabel;
    }

    public void setDataNascitaLabel(JLabel dataNascitaLabel) {
        this.dataNascitaLabel = dataNascitaLabel;
    }

    public JLabel getLuogoDiNascitaLabel() {
        return luogoDiNascitaLabel;
    }

    public void setLuogoDiNascitaLabel(JLabel luogoDiNascitaLabel) {
        this.luogoDiNascitaLabel = luogoDiNascitaLabel;
    }

    public JLabel getEmailLabel() {
        return emailLabel;
    }

    public void setEmailLabel(JLabel emailLabel) {
        this.emailLabel = emailLabel;
    }

    public JLabel getAnnoCorsoLabel() {
        return annoCorsoLabel;
    }

    public void setAnnoCorsoLabel(JLabel annoCorsoLabel) {
        this.annoCorsoLabel = annoCorsoLabel;
    }

    public JLabel getProfRelatoreLabel() {
        return profRelatoreLabel;
    }

    public void setProfRelatoreLabel(JLabel profRelatoreLabel) {
        this.profRelatoreLabel = profRelatoreLabel;
    }

    public JLabel getTitoloTesiLabel() {
        return titoloTesiLabel;
    }

    public void setTitoloTesiLabel(JLabel titoloTesiLabel) {
        this.titoloTesiLabel = titoloTesiLabel;
    }
}
