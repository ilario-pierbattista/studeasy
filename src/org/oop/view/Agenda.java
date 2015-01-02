package org.oop.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Agenda extends AbstractView<Agenda> {
    private static Agenda instance;
    public JPanel agendapanel;

    private JSplitPane splitpane;
    private JPanel sidebarpanel;
    private JPanel activitypanel;
    private JLabel insegnamentolabel;
    private JLabel durataciclolabel;
    private JPanel buttonspanel;
    private JButton lezioneButton;
    private JButton laboratorioButton;
    private JButton esameButton;
    private JPanel activitiespanel;
    private JButton progettobutton;
    private JButton seminariobutton;
    private JLabel sidebartitle;
    private JLabel sidebardescription;
    private JScrollPane cicliscrollpane;
    private JScrollPane insegnamentiscrollpane;
    private JSplitPane insidesplitpane;
    private JList ciclilist;
    private JList insegnamentilist;
    private JButton addciclobutton;
    private JButton removeciclobutton;


    public Agenda() {
        //Setto istance a questa instanza in modo da rendere statica la vista
        instance = this;
        //Setta la larghezza della sidebar
        splitpane.setDividerLocation(230 + splitpane.getInsets().left);
        //Elimina i bordi
        splitpane.setBorder(null);
        sidebarpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(126, 126, 126)));

        insidesplitpane.setDividerLocation(60 + insidesplitpane.getInsets().top);
        insidesplitpane.setBorder(null);

        //Imposta il layout a 2 colonne
        activitiespanel.setLayout(new GridLayout(0, 2, 20, 20));



    }

    public void addAttivita(AttivitaView attivita) {
        activitiespanel.add(attivita.activitypanel);
    }

    public void setListaCicli(DefaultListModel model){
        ciclilist.setModel(model);
    }



    /* Getters */
    public JLabel getInsegnamentolabel() {
        return insegnamentolabel;
    }

    public JLabel getDurataciclolabel() {
        return durataciclolabel;
    }

    public JButton getLezioneButton() {
        return lezioneButton;
    }

    public JButton getLaboratorioButton() {
        return laboratorioButton;
    }

    public JButton getEsameButton() {
        return esameButton;
    }

    public JButton getProgettobutton() {
        return progettobutton;
    }

    public JButton getSeminariobutton() {

        return seminariobutton;
    }

    public JList getCiclilist() {
        return ciclilist;
    }

    public JButton getRemoveciclobutton() {
        return removeciclobutton;
    }

    public JButton getAddciclobutton() {

        return addciclobutton;
    }

    /* Listeners setters */
    public void addCicloButtonListener(ActionListener listener){
        addciclobutton.addActionListener(listener);
    }

    public void addRemoveCicloButtonListener(ActionListener listener){
        removeciclobutton.addActionListener(listener);
    }

    public void addLezioneButtonListener(ActionListener listener) {
        lezioneButton.addActionListener(listener);
    }

    public void addEsameButtonListener(ActionListener listener) {
        esameButton.addActionListener(listener);
    }

    public void addLaboratorioButtonListener(ActionListener listener) {
        laboratorioButton.addActionListener(listener);
    }

    public void addProgettoButtonListener(ActionListener listener) {
        progettobutton.addActionListener(listener);
    }

    public void addSeminarioButtonListener(ActionListener listener) {
        seminariobutton.addActionListener(listener);
    }


    public static Agenda getInstance(){
        return instance;
    }

}
