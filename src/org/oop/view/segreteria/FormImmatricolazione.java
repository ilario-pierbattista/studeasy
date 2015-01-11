package org.oop.view.segreteria;

import org.oop.general.Validator;
import org.oop.model.entities.Utente;
import org.oop.view.AbstractForm;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

public class FormImmatricolazione extends AbstractForm {
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

    public FormImmatricolazione() {
        super();
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
    }

    @Override
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
        } else if (Validator.isFormattedFieldEmpty(voto, "Voto") || !(Validator.inputVotoDiploma(Integer.parseInt(voto.getText())))) {
            flag = false;
        } else if (Validator.isFormattedFieldEmpty(annoConseguimento1, "Anno di conseguimento")) {
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
        voto = new JFormattedTextField(dfVoto);
    }

    /* Listeners */
    public void insSubmitFormButtonListener(ActionListener l) {
        submit.addActionListener(l);
    }

    public void insQuitFormButtonListener(ActionListener l) {
        quit.addActionListener(l);
    }

    public void addFocusListenerMatricola(FocusListener listener) {
        matricola.addFocusListener(listener);
    }

    /* Getters */
    public JFormattedTextField getMatricola() {
        return matricola;
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

    public JFormattedTextField getNome() {
        return nome;
    }

    public JFormattedTextField getCognome() {
        return cognome;
    }

    public JFormattedTextField getLuogonascita() {
        return luogonascita;
    }

    public JFormattedTextField getDiploma() {
        return diploma;
    }
}
