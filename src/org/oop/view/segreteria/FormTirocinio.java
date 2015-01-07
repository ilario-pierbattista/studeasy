package org.oop.view.segreteria;

import org.oop.general.Validator;
import org.oop.model.entities.Utente;
import org.oop.view.AbstractForm;
import org.oop.view.AbstractView;

import javax.swing.*;
import java.awt.event.ActionListener;

public class FormTirocinio extends AbstractForm {

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

    /**
     * Metodo di appoggio che controlla che i campi del form siano stati compilati
     * correttamente
     *
     * @return
     */
    public boolean isValid() {
        boolean flag;

        if (Validator.isFormattedFieldEmpty(nome, "Nome") || !Validator.inputSentenceControl(nome.getText(), "Nome")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(cognome, "Cognome") || !Validator.inputSentenceControl(cognome.getText(), "Cognome")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(matricola, "Matricola") ) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(datanascita, "Data di nascita")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(luogonascita, "Luogo di nascita") || !Validator.inputSentenceControl(luogonascita.getText(), "Luogo di nascita")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(residenza, "Residenza") || !Validator.inputSentenceControl(residenza.getText(), "residenza")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(provincia, "Provincia") || !Validator.inputProvinciaControl(provincia.getText())) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(cap, "CAP")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(via, "Via") || !Validator.inputSentenceControl(via.getText(), "Via")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(codicefiscale, "Codice Fiscale") || !Validator.inputCodiceFiscaleControl(codicefiscale.getText())) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(cfu, "CFU") || !Validator.controlloNumeroCFU(Double.parseDouble(cfu.getText()))) {
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    public void setInfoUtente(Utente utente) {
        nome.setValue(utente.getNome());
        cognome.setValue(utente.getCognome());
        matricola.setValue(utente.getMatricola());
        cfu.setValue(utente.getLibretto().calcolaCFU());
    }

    public void insSubmitButtonListener(ActionListener l) {
        submit.addActionListener(l);
    }

    public void insQuitFormButtonListener(ActionListener l) {
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

    private void createUIComponents() {
        datanascita = new JFormattedTextField(dateformat);
        matricola = new JFormattedTextField(dfMatricola);
        cap = new JFormattedTextField((dfCAP));
        cfu= new JFormattedTextField(dfCFU);
    }
}
