package org.oop.view.segreteria;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Tirocinio {

    public JPanel tirociniopanel;
    private JButton quit;
    private JButton submit;
    private JFormattedTextField datanascita;
    private JFormattedTextField nome;
    private JFormattedTextField matricola;
    private JFormattedTextField luogonascita;
    private JFormattedTextField residenza;
    private JFormattedTextField cognome;
    private JFormattedTextField provincia;
    private JFormattedTextField cap;
    private JFormattedTextField via;
    private JFormattedTextField codicefiscale;
    private JLabel nomelabel;
    private JLabel cognomelabel;
    private JLabel matricolalabel;
    private JLabel datanascitalabel;
    private JLabel luogonascitalabel;
    private JLabel residenzalabel;
    private JLabel provincialabel;
    private JLabel caplabel;
    private JLabel vialabel;
    private JLabel codicefiscalelabel;
    private JLabel cfulabel;
    private JFormattedTextField cfu;

    public void insSubmitButtonListener (ActionListener l) {
        submit.addActionListener(l);
    }
    public void insQuitFormButtonListener (ActionListener l) {
        quit.addActionListener(l);
    }

    public JFormattedTextField getDatanascita() {
        return datanascita;
    }

    public void setDatanascita(JFormattedTextField datanascita) {
        this.datanascita = datanascita;
    }

    public JFormattedTextField getNome() {
        return nome;
    }

    public void setNome(JFormattedTextField nome) {
        this.nome = nome;
    }

    public JFormattedTextField getMatricola() {
        return matricola;
    }

    public void setMatricola(JFormattedTextField matricola) {
        this.matricola = matricola;
    }

    public JFormattedTextField getLuogonascita() {
        return luogonascita;
    }

    public void setLuogonascita(JFormattedTextField luogonascita) {
        this.luogonascita = luogonascita;
    }

    public JFormattedTextField getResidenza() {
        return residenza;
    }

    public void setResidenza(JFormattedTextField residenza) {
        this.residenza = residenza;
    }

    public JFormattedTextField getCognome() {
        return cognome;
    }

    public void setCognome(JFormattedTextField cognome) {
        this.cognome = cognome;
    }

    public JFormattedTextField getProvincia() {
        return provincia;
    }

    public void setProvincia(JFormattedTextField provincia) {
        this.provincia = provincia;
    }

    public JFormattedTextField getCap() {
        return cap;
    }

    public void setCap(JFormattedTextField cap) {
        this.cap = cap;
    }

    public JFormattedTextField getVia() {
        return via;
    }

    public void setVia(JFormattedTextField via) {
        this.via = via;
    }

    public JFormattedTextField getCodicefiscale() {
        return codicefiscale;
    }

    public void setCodicefiscale(JFormattedTextField codicefiscale) {
        this.codicefiscale = codicefiscale;
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

    public JLabel getMatricolalabel() {
        return matricolalabel;
    }

    public void setMatricolalabel(JLabel matricolalabel) {
        this.matricolalabel = matricolalabel;
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

    public JLabel getResidenzalabel() {
        return residenzalabel;
    }

    public void setResidenzalabel(JLabel residenzalabel) {
        this.residenzalabel = residenzalabel;
    }

    public JLabel getProvincialabel() {
        return provincialabel;
    }

    public void setProvincialabel(JLabel provincialabel) {
        this.provincialabel = provincialabel;
    }

    public JLabel getCaplabel() {
        return caplabel;
    }

    public void setCaplabel(JLabel caplabel) {
        this.caplabel = caplabel;
    }

    public JLabel getVialabel() {
        return vialabel;
    }

    public void setVialabel(JLabel vialabel) {
        this.vialabel = vialabel;
    }

    public JLabel getCodicefiscalelabel() {
        return codicefiscalelabel;
    }

    public void setCodicefiscalelabel(JLabel codicefiscalelabel) {
        this.codicefiscalelabel = codicefiscalelabel;
    }
    public JFormattedTextField getCfu() {
        return cfu;
    }
}
