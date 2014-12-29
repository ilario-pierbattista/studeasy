package org.oop.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Agenda extends AbstractView<Agenda> {
    public JPanel agendapanel;

    private JScrollPane sidebarpane;
    private JSplitPane splitpane;
    private JButton addinsbutton;
    private JPanel sidebarpanel;
    private JPanel activitypanel;
    private JLabel insegnamentolabel;
    private JLabel durataciclolabel;
    private JPanel buttonspanel;
    private JButton lezioneButton;
    private JButton laboratorioButton;
    private JButton esameButton;
    private JPanel activitiespanel;
    private JLabel sidebartitle;

    private DefaultListModel listaModelCicli;
    private int contatorecicli = 1;
    private JList list1;

    private JButton progettobutton;
    private JButton seminariobutton;
    private JButton deleteelement;

    public Agenda() {
        //Setta la larghezza della sidebar
        splitpane.setDividerLocation(200 + splitpane.getInsets().left);
        //Elimina i bordi
        splitpane.setBorder(null);
        sidebarpane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(126, 126, 126)));

        //Imposta il layout a 2 colonne
        activitiespanel.setLayout(new GridLayout(0, 2, 20, 20));

        //Parametri per la creazione e gestione della lista dei cicli
        listaModelCicli = new DefaultListModel();
        list1.setModel(listaModelCicli);
        //listciclipanel.add(listaModelCicli);
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void addAttivita(AttivitaView attivita) {
        activitiespanel.add(attivita.activitypanel);
    }

    /**
     * Metodo che permette di aggiungere un elemento alla lista dei cicli ogni volta che si preme
     * il bottone addinsbutton
     */
    public void addCiclo() {
        listaModelCicli.addElement("elemento" + contatorecicli);
        contatorecicli++;
    }

    /**
     * Metodo che permette di eleiminare un elemento dalla lista
     */
    public void deleteCiclo() {
        if (list1.getSelectedIndex() == -1) {
            System.out.println("Non hai selezionato nessun ciclo");
        } else {
            listaModelCicli.remove(list1.getSelectedIndex());
        }
    }

    /* Getters */
    public JButton getAddinsbutton() {
        return addinsbutton;
    }

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

    /* Listeners setters */
    public void addLezioneButtonListener(ActionListener listener) {
        lezioneButton.addActionListener(listener);
    }

    public void addEsameButtonListener(ActionListener listener) {
        esameButton.addActionListener(listener);
    }

    public void addLaboratorioButtonListener(ActionListener listener) {
        laboratorioButton.addActionListener(listener);
    }

    public void addInsButtonListener(ActionListener listener) {
        addinsbutton.addActionListener(listener);
    }

    /*
    public void addListListener(ListSelectionListener listener) {
        list1.addListSelectionListener(listener);

    }*/

    public void addProgettoButtonListener(ActionListener listener) {
        progettobutton.addActionListener(listener);
    }

    public void addSeminarioButtonListener(ActionListener listener) {
        seminariobutton.addActionListener(listener);
    }

    public void deleteListElementListener(ActionListener listener) {
        deleteelement.addActionListener(listener);
    }

}
