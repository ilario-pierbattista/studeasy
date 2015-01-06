package org.oop.view.profilo;

import org.oop.general.Utils;
import org.oop.general.Validator;
import org.oop.model.entities.Corso;
import org.oop.model.entities.Utente;
import org.oop.view.AbstractView;
import org.oop.view.agenda.Agenda;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static org.oop.general.Utils.inputMatricolaControl;
import static org.oop.general.Utils.inputAppelControl;

public class FormRegistrazione extends AbstractView<Agenda> {
    public JFrame frame = new JFrame("Registrazione");
    private JPanel panel1;
    private JTextField nome;
    private JTextField cognome;
    private JButton Submit;
    private JButton Quit;
    private JTextField matricola;
    private JTextField email;
    private JList<Corso> corsiList;
    private DefaultListModel<Corso> listaCorsiModel;
    private JRadioButton triennaleRadioButton;
    private JRadioButton magistraleRadioButton;
    private JRadioButton cicloUnicoRadioButton;

    public FormRegistrazione() {
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        Utils.centerJFrame(frame);
    }

    /**
     * Rende visibile la finestra
     * @param visible
     */
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    /**
     * Imposta la lista dei corsi opzionabili
     * @param corsi
     */
    public void setCorsiList(ArrayList<Corso> corsi) {
        corsiList.setEnabled(true);
        listaCorsiModel.clear();
        for(Corso corso : corsi) {
            listaCorsiModel.addElement(corso);
        }
    }

    /**
     * Convalida gli attributi del form ed apre una finestra d'errore
     * nel caso qualcosa non vada bene
     * @return
     */
    public boolean isValid() {
        boolean valid = true;

        if(!inputAppelControl(nome.getText())) {
            JOptionPane.showMessageDialog(null, "Nome non valido");
            valid = false;
        } else if(!inputAppelControl(cognome.getText())) {
            JOptionPane.showMessageDialog(null, "Cognome non valido");
            valid = false;
        } else if(!Validator.email(email.getText())) {
            JOptionPane.showMessageDialog(null, "Email non valida");
            valid = false;
        } else if(!inputMatricolaControl(matricola.getText())) {
            JOptionPane.showMessageDialog(null, "Matricola non valida");
            valid = false;
        } else if(corsiList.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(null, "Nessun corso selezionato");
            valid = false;
        }
        return valid;
    }

    /**
     * Restituisce un oggetto utente dai campi del form
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

    /** GETTER @TODO ottimizzarli, togliere quelli che non servono */
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
    }
}
