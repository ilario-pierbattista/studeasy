package org.oop.view.profilo;

import org.oop.db.SQLParameters;
import org.oop.general.Validator;
import org.oop.model.dao.CorsoDAO;
import org.oop.model.entities.Corso;
import org.oop.model.entities.Utente;
import org.oop.view.AbstractForm;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class FormRegistrazione extends AbstractForm {
    private JPanel panel1;
    private JTextField nome;
    private JTextField cognome;
    private JButton Submit;
    private JButton Quit;
    private JTextField email;
    private JList<Corso> corsiList;
    private DefaultListModel<Corso> listaCorsiModel;
    private JRadioButton triennaleRadioButton;
    private JRadioButton magistraleRadioButton;
    private JRadioButton cicloUnicoRadioButton;
    private JFormattedTextField matricola;

    public FormRegistrazione() {
        frame = new JFrame("Registrazione");
        frame.setContentPane(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Imposta le informazioni di default
     *
     * @param utente Utente da cui prendere le informazioni
     */
    public void initInfo(Utente utente) {
        nome.setText(utente.getNome());
        cognome.setText(utente.getCognome());
        email.setText(utente.getEmail());
        matricola.setText(Integer.toString(utente.getMatricola()));
        int livello = utente.getLibretto().getCorso().getLivello();
        Corso corso = utente.getLibretto().getCorso();
        SQLParameters parameters = new SQLParameters();
        parameters.add("livello", livello);
        setCorsiList(new CorsoDAO().findBy(parameters));
        if (livello == Corso.TRIENNALE) {
            triennaleRadioButton.setSelected(true);
        } else if (livello == Corso.MAGISTRALE) {
            magistraleRadioButton.setSelected(true);
        } else {
            cicloUnicoRadioButton.setSelected(true);
        }
        corsiList.setSelectedValue(corso, true);
    }

    /**
     * Imposta la lista dei corsi opzionabili
     *
     * @param corsi
     */
    public void setCorsiList(ArrayList<Corso> corsi) {
        corsiList.setEnabled(true);
        listaCorsiModel.clear();
        for (Corso corso : corsi) {
            listaCorsiModel.addElement(corso);
        }
    }

    /**
     * Convalida gli attributi del form ed apre una finestra d'errore nel caso qualcosa non vada bene
     *
     * @return
     */
    public boolean isValid() {
        boolean valid = true;

        if (Validator.isTextFieldEmpty(nome, "Nome") || !Validator.inputSentenceControl(nome.getText(), "Nome")) {
            valid = false;
        } else if (Validator.isTextFieldEmpty(cognome, "Cognome") || !Validator.inputSentenceControl(cognome.getText(), "Cognome")) {
            valid = false;
        } else if (Validator.isTextFieldEmpty(email, "Email") || !Validator.email(email.getText())) {
            valid = false;
        } else if (Validator.isFormattedFieldEmpty(matricola, "Matricola")) {
            valid = false;
        } else if (corsiList.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(null, "Nessun corso selezionato");
            valid = false;
        }
        return valid;
    }

    /**
     * Restituisce un oggetto utente dai campi del form
     *
     * @return
     */
    public Utente getUtente() {
        Utente utente = new Utente();
        utente.setNome(nome.getText())
                .setCognome(cognome.getText())
                .setMatricola(Integer.parseInt(matricola.getText()))
                .setEmail(email.getText())
                .getLibretto().setCorso(corsiList.getSelectedValue());
        return utente;
    }

    /*listeners adders*/
    public void addSubmitFormButtonListener(ActionListener l) {
        Submit.addActionListener(l);
    }

    public void addQuitFormButtonListener(ActionListener l) {
        Quit.addActionListener(l);
    }

    public void addLivelloRadiusButtonsListener(ActionListener l) {
        triennaleRadioButton.addActionListener(l);
        magistraleRadioButton.addActionListener(l);
        cicloUnicoRadioButton.addActionListener(l);
    }

    public void addFocusListenerMatricola(FocusListener listener) {
        matricola.addFocusListener(listener);
    }

    /**
     * GETTER @TODO ottimizzarli, togliere quelli che non servono
     */
    public JTextField getNome() {
        return nome;
    }

    public JTextField getCognome() {
        return cognome;
    }

    public JButton getSubmit() {
        return Submit;
    }

    public JButton getQuit() {
        return Quit;
    }

    public JTextField getMatricola() {
        return matricola;
    }

    public JTextField getEmail() {
        return email;
    }

    public JRadioButton getTriennaleRadioButton() {
        return triennaleRadioButton;
    }

    public JRadioButton getMagistraleRadioButton() {
        return magistraleRadioButton;
    }

    public JRadioButton getCicloUnicoRadioButton() {
        return cicloUnicoRadioButton;
    }

    private void createUIComponents() {
        listaCorsiModel = new DefaultListModel<Corso>();
        listaCorsiModel.addElement(new Corso().setNome("Selezionare il livello di laurea"));
        corsiList = new JList<Corso>(listaCorsiModel);
        corsiList.setEnabled(false);
        matricola = new JFormattedTextField(dfMatricola);
    }
}
