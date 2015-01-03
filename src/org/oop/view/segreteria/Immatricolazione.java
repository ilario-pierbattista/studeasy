package org.oop.view.segreteria;

import org.oop.view.AbstractView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Immatricolazione extends AbstractView {
    public JPanel immatricolazionepanel;
    private JButton submit;
    private JButton quit;
    private JFormattedTextField matricola;
    private JLabel matricolalabel;
    private JLabel nomelabel;
    private JLabel cognomelabel;
    private JFormattedTextField datanascita;
    private JLabel datanascitalabel;
    private JLabel luogonascitalabel;
    private JFormattedTextField codicefiscale;
    private JLabel codicefiscalelabel;
    private JLabel diplomalabel;
    private JFormattedTextField voto;
    private JLabel votolabel;
    private JFormattedTextField annoconseguimento;
    private JLabel annoconseguimentolabel;
    private JFormattedTextField nome;
    private JFormattedTextField cognome;
    private JFormattedTextField luogonascita;
    private JFormattedTextField diploma;

    public Immatricolazione() {
        super();
    }

    private void createUIComponents() {
        datanascita = new JFormattedTextField(dateformat);
    }
    public void insSubmitFormButtonListener (ActionListener l) {
        submit.addActionListener(l);
    }
    public void insQuitFormButtonListener (ActionListener l) {
        quit.addActionListener(l);
    }

    public JFormattedTextField getMatricola() {
        return matricola;
    }

    public void setMatricola(JFormattedTextField matricola) {
        this.matricola = matricola;
    }

    public JLabel getMatricolalabel() {
        return matricolalabel;
    }

    public void setMatricolalabel(JLabel matricolalabel) {
        this.matricolalabel = matricolalabel;
    }

    public JLabel getNomelabel() {
        return nomelabel;
    }

    public void setNomelabel(JLabel nomelabel) {
        this.nomelabel = nomelabel;
    }

    public JLabel getCognomelabel() {
        return cognomelabel;
    }

    public void setCognomelabel(JLabel cognomelabel) {
        this.cognomelabel = cognomelabel;
    }

    public JFormattedTextField getDatanascita() {
        return datanascita;
    }

    public void setDatanascita(JFormattedTextField datanascita) {
        this.datanascita = datanascita;
    }

    public JLabel getDatanascitalabel() {
        return datanascitalabel;
    }

    public void setDatanascitalabel(JLabel datanascitalabel) {
        this.datanascitalabel = datanascitalabel;
    }

    public JLabel getLuogonascitalabel() {
        return luogonascitalabel;
    }

    public void setLuogonascitalabel(JLabel luogonascitalabel) {
        this.luogonascitalabel = luogonascitalabel;
    }

    public JFormattedTextField getCodicefiscale() {
        return codicefiscale;
    }

    public void setCodicefiscale(JFormattedTextField codicefiscale) {
        this.codicefiscale = codicefiscale;
    }

    public JLabel getCodicefiscalelabel() {
        return codicefiscalelabel;
    }

    public void setCodicefiscalelabel(JLabel codicefiscalelabel) {
        this.codicefiscalelabel = codicefiscalelabel;
    }

    public JLabel getDiplomalabel() {
        return diplomalabel;
    }

    public void setDiplomalabel(JLabel diplomalabel) {
        this.diplomalabel = diplomalabel;
    }

    public JFormattedTextField getVoto() {
        return voto;
    }

    public void setVoto(JFormattedTextField voto) {
        this.voto = voto;
    }

    public JLabel getVotolabel() {
        return votolabel;
    }

    public void setVotolabel(JLabel votolabel) {
        this.votolabel = votolabel;
    }

    public JFormattedTextField getAnnoconseguimento() {
        return annoconseguimento;
    }

    public void setAnnoconseguimento(JFormattedTextField annoconseguimento) {
        this.annoconseguimento = annoconseguimento;
    }

    public JLabel getAnnoconseguimentolabel() {
        return annoconseguimentolabel;
    }

    public void setAnnoconseguimentolabel(JLabel annoconseguimentolabel) {
        this.annoconseguimentolabel = annoconseguimentolabel;
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

    public JFormattedTextField getLuogonascita() {
        return luogonascita;
    }

    public void setLuogonascita(JFormattedTextField luogonascita) {
        this.luogonascita = luogonascita;
    }

    public JFormattedTextField getDiploma() {
        return diploma;
    }

    public void setDiploma(JFormattedTextField diploma) {
        this.diploma = diploma;
    }
}
