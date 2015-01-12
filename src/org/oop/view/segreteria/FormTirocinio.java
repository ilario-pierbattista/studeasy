package org.oop.view.segreteria;

import org.oop.controller.BaseController;
import org.oop.general.Validator;
import org.oop.model.entities.Utente;
import org.oop.view.AbstractForm;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

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

    @Override
    public boolean isValid() {
        boolean flag;

        if (Validator.isFormattedFieldEmpty(nome, "Nome") || !Validator.inputSentenceControl(nome.getText(), "Nome")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(cognome, "Cognome") || !Validator.inputSentenceControl(cognome.getText(), "Cognome")) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(matricola, "Matricola")) {
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
        } else if (Validator.isFormattedFieldEmpty(cfu, "CFU") || !Validator.controlloNumeroCFU(Double.parseDouble(cfu.getText()), BaseController.getUtenteCorrente().getLibretto().getCorso())) {
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * Imposta le informazioni dell'utente
     *
     * @param utente Utente corrente
     */
    public void setInfoUtente(Utente utente) {
        nome.setValue(utente.getNome());
        cognome.setValue(utente.getCognome());
        matricola.setValue(utente.getMatricola());
        cfu.setValue(utente.getLibretto().calcolaCFU());
    }

    /* Setter dei listeners */
    public void addFocusListenerCfu(FocusListener listener) {
        cfu.addFocusListener(listener);
    }

    public void addFocusListenerMatricola(FocusListener listener) {
        matricola.addFocusListener(listener);
    }

    public void addFocusListenerCap(FocusListener listener) {
        cap.addFocusListener(listener);
    }

    public void insSubmitButtonListener(ActionListener l) {
        submit.addActionListener(l);
    }

    public void insQuitFormButtonListener(ActionListener l) {
        quit.addActionListener(l);
    }

    /* Getters*/

    public JFormattedTextField getDatanascita() {
        return datanascita;
    }

    public JFormattedTextField getNome() {
        return nome;
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


    public JFormattedTextField getResidenza() {
        return residenza;
    }


    public JFormattedTextField getCognome() {
        return cognome;
    }

    public JFormattedTextField getProvincia() {
        return provincia;
    }

    public JFormattedTextField getCap() {
        return cap;
    }

    public JFormattedTextField getVia() {
        return via;
    }

    public JFormattedTextField getCodicefiscale() {
        return codicefiscale;
    }

    public JFormattedTextField getCfu() {
        return cfu;
    }

    private void createUIComponents() {
        datanascita = new JFormattedTextField(dateformat);
        matricola = new JFormattedTextField(dfMatricola);
        cap = new JFormattedTextField((dfCAP));
        cfu = new JFormattedTextField(dfCFU);
    }
}
