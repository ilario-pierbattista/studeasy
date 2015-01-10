package org.oop.view.agenda;

import org.oop.model.entities.Ciclo;
import org.oop.model.entities.Insegnamento;
import org.oop.view.AbstractView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AgendaView extends AbstractView {
    private static AgendaView instance;
    public JPanel agendapanel;

    private JSplitPane splitpane;
    private JPanel sidebarpanel;
    private JPanel activitypanel;
    private JLabel insegnamentoLabel;
    private JLabel durataCicloLabel;
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
    private JList<Insegnamento> insegnamentiList;
    private JButton addciclobutton;
    private JButton removeciclobutton;
    private JButton addInsegnamentoButton;
    private JButton removeInsegnamentoButton;
    private JLabel listaInsegnamentiTitle;
    private JLabel noAttivitaLabel;
    private JLabel nomeCicloLabel;

    public AgendaView() {
        //Setto istance a questa instanza in modo da rendere statica la vista
        instance = this;
        //Setta la larghezza della sidebar
        splitpane.setDividerLocation(230 + splitpane.getInsets().left);
        //Elimina i bordi
        splitpane.setBorder(null);
        sidebarpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(230, 230, 230)));

        insidesplitpane.setDividerLocation(150 + insidesplitpane.getInsets().top);
        insidesplitpane.setBorder(null);

        //Imposta il layout a 2 colonne
        activitiespanel.setLayout(new GridLayout(0, 2, 20, 20));

        lezioneButton.setActionCommand(org.oop.model.entities.Attivita.LEZIONE);
        laboratorioButton.setActionCommand(org.oop.model.entities.Attivita.LABORATORIO);
        seminariobutton.setActionCommand(org.oop.model.entities.Attivita.SEMINARIO);
        progettobutton.setActionCommand(org.oop.model.entities.Attivita.PROGETTO);
        esameButton.setActionCommand(org.oop.model.entities.Attivita.ESAME);
    }

    /**
     * Metodo che ritorna l'istanza della vista
     *
     * @return
     */
    public static AgendaView getInstance() {
        return instance;
    }

    /**
     * Metodo che aggiunge ogni Ciclo dell'Arraylist che gli si passa alla lista dei cicli
     *
     * @param list
     */
    public void setListaCicli(ArrayList<Ciclo> list) {
        int selected = ciclilist.getSelectedIndex();
        DefaultListModel<Ciclo> cicliListModel = new DefaultListModel<Ciclo>();
        for (Ciclo ciclo : list) {
            cicliListModel.addElement(ciclo);
        }
        ciclilist.setModel(cicliListModel);
        if(selected < 0) {
            ciclilist.setSelectedIndex(0);
        } else {
            ciclilist.setSelectedIndex(selected);
        }

        if (cicliListModel.getSize() <= 0) {
            removeciclobutton.setEnabled(false);
            durataCicloLabel.setText("");
        } else {
            removeciclobutton.setEnabled(true);
            if (selected == cicliListModel.getSize()) {
                selected--;
                ciclilist.setSelectedIndex(selected);
                ciclilist.ensureIndexIsVisible(selected);
            }
        }
    }

    /**
     * Metodo che ritorna il ciclo selezionato
     *
     * @return Ciclo
     */
    public Ciclo getCicloSelected() {
        int index = ciclilist.getSelectedIndex();
        Ciclo ciclo;

        if (index < 0) { //non ci sono cicli
            ciclo = null;
        } else {
            ciclo = ciclilist.getModel().getElementAt(index);
        }

        return ciclo;
    }

    /**
     * Metodo che ritorna l'insegnamento selezionato
     *
     * @return Insegnamento
     */
    public Insegnamento getInsegnamentoSelected() {
        int index = insegnamentiList.getSelectedIndex();
        Insegnamento insegnamento;

        if (index < 0) { //non ci sono insegnamenti
            insegnamento = null;
        } else {
            insegnamento = insegnamentiList.getModel().getElementAt(index);
        }

        return insegnamento;
    }

    /**
     * Metodo che crea la lista di insegnamenti per il ciclo che gli si passa.
     * Indipendentemente dal fatto che il ciclo abbia già insegnamenti associati o meno,
     * ricrea il model della lista.
     *
     * @param ciclo Ciclo
     */
    public void setInsegnamentiFromCiclo(Ciclo ciclo) {
        if (ciclo != null) { //ciclo è null solo se non esiste neanche un ciclo. Guarda getCicloSelected
            int selected = insegnamentiList.getSelectedIndex();
            ArrayList<Insegnamento> listaInsegnamenti = ciclo.getInsegnamenti();
            DefaultListModel<Insegnamento> model = new DefaultListModel<Insegnamento>();
            for (Insegnamento ins : listaInsegnamenti) {
                model.addElement(ins);
            }
            insegnamentiList.setModel(model);
            if(selected < 0) {
                insegnamentiList.setSelectedIndex(0);
            } else {
                insegnamentiList.setSelectedIndex(selected);
            }

            if (model.getSize() <= 0 || ciclilist.getModel().getSize() <= 0) {
                removeInsegnamentoButton.setEnabled(false);
                insegnamentoLabel.setText("");
            } else {
                removeInsegnamentoButton.setEnabled(true);
                if (selected == model.getSize()) {
                    selected--;
                    insegnamentiList.setSelectedIndex(selected);
                    insegnamentiList.ensureIndexIsVisible(selected);
                }
            }
        }
    }

    /**
     * Metodo per cambiare lo stato dei bottoni di aggiunta di attività
     *
     * @param value True o false
     */
    public void toggleAttivitaButtons(boolean value) {
        lezioneButton.setEnabled(value);
        progettobutton.setEnabled(value);
        seminariobutton.setEnabled(value);
        esameButton.setEnabled(value);
        laboratorioButton.setEnabled(value);
    }

    public void addAttivitaView(AttivitaView view) {
        activitiespanel.add(view.activitypanel);
    }

    public void setNoAttivita() {
        activitiespanel.removeAll();
        activitiespanel.add(noAttivitaLabel);
        noAttivitaLabel.setVisible(true);
    }

    /* Getters */
    public JLabel getInsegnamentoLabel() {
        return insegnamentoLabel;
    }

    public JLabel getDurataCicloLabel() {
        return durataCicloLabel;
    }

    public JList getCiclilist() {
        return ciclilist;
    }

    public JLabel getListaInsegnamentiTitle() {
        return listaInsegnamentiTitle;
    }

    public JList getInsegnamentiList() {
        return insegnamentiList;
    }

    public JPanel getActivitiespanel() {
        return activitiespanel;
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

    public void addInsegnamentoButtonListener(ActionListener listener) {
        addInsegnamentoButton.addActionListener(listener);
    }

    public void addRemoveInsegnamentoButtonListener(ActionListener listener) {
        removeInsegnamentoButton.addActionListener(listener);
    }
}
