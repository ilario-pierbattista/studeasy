package org.oop.view.segreteria;

import org.oop.general.Validator;
import org.oop.view.AbstractView;

import javax.swing.*;
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
    private JFormattedTextField annoConseguimento1;
    private JLabel annoconseguimentolabel;
    private JFormattedTextField nome;
    private JFormattedTextField cognome;
    private JFormattedTextField luogonascita;
    private JFormattedTextField diploma;
    private JFormattedTextField provincia;
    private JLabel provinciaLabel;
    private JFormattedTextField annoConseguimento2;
    private JLabel annoConseguimento2Label;



    public Immatricolazione() {
        super();

    }

    /**
     * Metodo di appoggio che controlla che i campi del form siano stati compilati
     * correttamente
     *
     * @return
     */
    public boolean isValid() {
        boolean flag;

        if (Validator.isFormattedFieldEmpty(matricola, "Matricola")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(nome, "Nome") || !Validator.inputSentenceControl(nome.getText(), "Nome")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(cognome, "Cognome") || !Validator.inputSentenceControl(cognome.getText(), "Cognome")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(datanascita, "Data di nascita")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(luogonascita, "Luogo di nascita") || !Validator.inputSentenceControl(luogonascita.getText(), "Luogo di nascita")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(provincia, "Provincia") || !Validator.inputProvinciaControl(provincia.getText())) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(codicefiscale, "Codice Fiscale") || !Validator.inputCodiceFiscaleControl(codicefiscale.getText())) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(diploma, "Diploma") || !Validator.inputSentenceControl(diploma.getText(), "Diploma")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(voto, "Voto") || !(Validator.inputVotoDiploma(Double.parseDouble(voto.getText())))) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(annoConseguimento1, "Anno di conseguimento") || Validator.isFormattedFieldEmpty(annoConseguimento2, "Anno di conseguimento") || !Validator.inputYearControl(annoConseguimento1.getText()) || !Validator.inputYearControl(annoConseguimento2.getText())) {
            flag = false;
        } else if (!Validator.controlloAnno(Double.parseDouble(annoConseguimento1.getText()), Double.parseDouble(annoConseguimento2.getText()))) {
            flag = false;
        } else {
            flag = true;
        }

        return flag;
    }

    private void createUIComponents() {

        datanascita = new JFormattedTextField(dateformat);
        matricola = new JFormattedTextField(dfMatricola);
        annoConseguimento1 = new JFormattedTextField(dateformatYear);
        annoConseguimento2 = new JFormattedTextField(dateformatYear);
        voto = new JFormattedTextField(dfVoto);
    }

    public void insSubmitFormButtonListener(ActionListener l) {
        submit.addActionListener(l);
    }

    public void insQuitFormButtonListener(ActionListener l) {
        quit.addActionListener(l);
    }

    public JFormattedTextField getMatricola() {
        return matricola;
    }

    public void setMatricola(JFormattedTextField matricola) {
        this.matricola = matricola;
    }

    public JFormattedTextField getProvincia() {
        return provincia;
    }

    public JFormattedTextField getDatanascita() {
        return datanascita;
    }

    public JFormattedTextField getCodicefiscale() {
        return codicefiscale;
    }

    public JFormattedTextField getVoto() {
        return voto;
    }

    public JFormattedTextField getAnnoConseguimento1() {
        return annoConseguimento1;
    }

    public JFormattedTextField getAnnoConseguimento2() {
        return annoConseguimento2;
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
