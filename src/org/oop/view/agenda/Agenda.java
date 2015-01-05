package org.oop.view.agenda;

import org.oop.model.entities.Ciclo;
import org.oop.model.entities.InsegnamentoOfferto;
import org.oop.view.AbstractView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JList<Ciclo> ciclilist;
    private JList insegnamentilist;
    private JButton addciclobutton;
    private JButton removeciclobutton;
    private JButton addInsegnamentoButton;
    private JButton removeInsegnamentoButton;
    private JLabel listaInsegnamentiTitle;

    public Agenda() {
        //Setto istance a questa instanza in modo da rendere statica la vista
        instance = this;
        //Setta la larghezza della sidebar
        splitpane.setDividerLocation(230 + splitpane.getInsets().left);
        //Elimina i bordi
        splitpane.setBorder(null);
        sidebarpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(126, 126, 126)));

        insidesplitpane.setDividerLocation(150 + insidesplitpane.getInsets().top);
        insidesplitpane.setBorder(null);

        //Imposta il layout a 2 colonne
        activitiespanel.setLayout(new GridLayout(0, 2, 20, 20));
    }

    /**
     * Metodo che aggiunge l'attività che gli viene passata nel JPanel
     * @param attivita
     */
    public void addAttivita(AttivitaView attivita) {
        activitiespanel.add(attivita.activitypanel);
    }

    /**
     * Metodo che aggiunge ogni Ciclo dell'Arraylist che gli si passa alla lista dei cicli
     * @param list
     */
    public void setListaCicli(ArrayList<Ciclo> list) {
        DefaultListModel<Ciclo> cicliListModel = new DefaultListModel<Ciclo>();
        for (Ciclo ciclo : list) {
            cicliListModel.addElement(ciclo);
        }
        ciclilist.setModel(cicliListModel);
    }

    /**
     * Metodo che aggiorna la lista dei cicli
     */
    public void updateListaCicli(){
        if (ciclilist.getModel().getSize() <= 0)  {
            removeciclobutton.setEnabled(false);
        } else {
            removeciclobutton.setEnabled(true);
            ciclilist.setSelectedIndex(0);
        }
    }

    /**@TODO riguardare meglio sta funzione **/
    public void updateListaCicli(int index){
        DefaultListModel<Ciclo> listmodel = (DefaultListModel<Ciclo>) ciclilist.getModel();

        if (listmodel.getSize() <= 0)  {
            removeciclobutton.setEnabled(false);
        } else {
            removeciclobutton.setEnabled(true);
            if (index == listmodel.getSize() ) {
                index--;
                ciclilist.setSelectedIndex(index);
                ciclilist.ensureIndexIsVisible(index);
            }
        }
    }

    /**
     * Metodo che aggiunge l'insegnamento che gli si passa alla lista degli insegnamenti
     */
    public void addInsegnamentoToList(InsegnamentoOfferto insegnamento) {
        DefaultListModel<InsegnamentoOfferto> listModel =  (DefaultListModel<InsegnamentoOfferto>) insegnamentilist.getModel();
        listModel.addElement(insegnamento);
    }

    /**
     * Metodo che carica la lista delle attivita in relazione all'insegnamento
     * che gli si è passato come parametro
     */
    public void updateElencoAttivita (InsegnamentoOfferto insegnamento){

    }

    /**
     * Metodo che ritorna l'istanza della vista
     * @return
     */
    public static Agenda getInstance() {
        return instance;
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

    public JLabel getListaInsegnamentiTitle() {
        return listaInsegnamentiTitle;
    }

    public JButton getAddInsegnamentoButton() {
        return addInsegnamentoButton;
    }

    public JButton getRemoveInsegnamentoButton() {
        return removeInsegnamentoButton;
    }

    public JList getInsegnamentilist() {
        return insegnamentilist;
    }

    /* Listeners setters */
    public void addCicloButtonListener(ActionListener listener) {
        addciclobutton.addActionListener(listener);
    }

    public void addRemoveCicloButtonListener(ActionListener listener) {
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

    public void addInsegnamentoButtonListener(ActionListener listener){
        addInsegnamentoButton.addActionListener(listener);
    }

    public void addRemoveInsegnamentoButtonListener(ActionListener listener){
        removeInsegnamentoButton.addActionListener(listener);
    }



}
