package org.oop.view.agenda;

import org.oop.model.entities.Attivita;
import org.oop.model.entities.Ciclo;
import org.oop.model.entities.Insegnamento;
import org.oop.view.AbstractView;
import org.oop.view.Mainframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class Agenda extends AbstractView {
    private static Agenda instance;
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
     *
     * @param attivita
     */
    public void addAttivita(AttivitaEventoView attivita) {
        activitiespanel.add(attivita.activitypanel);
    }

    /**
     * Metodo che aggiunge ogni Ciclo dell'Arraylist che gli si passa alla lista dei cicli
     *
     * @param list
     */
    public void setListaCicli(ArrayList<Ciclo> list) {
        DefaultListModel<Ciclo> cicliListModel = new DefaultListModel<Ciclo>();
        for (Ciclo ciclo : list) {
            cicliListModel.addElement(ciclo);
        }
        ciclilist.setModel(cicliListModel);
        ciclilist.setSelectedIndex(0);
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
     * Aggiorna la lista dei cicli mantenendo la selezione e aggiornando lo status
     * dei bottoni della lista cicli
     */
    public void updateListaCicli() {
        int index = ciclilist.getSelectedIndex();
        DefaultListModel<Ciclo> listmodel = (DefaultListModel<Ciclo>) ciclilist.getModel();

        if (listmodel.getSize() <= 0) {
            removeciclobutton.setEnabled(false);
            durataCicloLabel.setText("");
        } else {
            removeciclobutton.setEnabled(true);
            if (index == listmodel.getSize()) {
                index--;
                ciclilist.setSelectedIndex(index);
                ciclilist.ensureIndexIsVisible(index);
            } else {
                ciclilist.setSelectedIndex(0);
            }
        }
    }

    /**
     * Aggiorna la lista degli insegnamenti mantenendo la selezione e aggiornando lo status
     * dei bottoni della lista degli insegnamenti
     */
    public void updateListaInsegnamenti() {
        int index = insegnamentiList.getSelectedIndex();
        DefaultListModel<Insegnamento> listModel = (DefaultListModel<Insegnamento>) insegnamentiList.getModel();

        if (insegnamentiList.getModel().getSize() <= 0 || ciclilist.getModel().getSize() <= 0) {
            removeInsegnamentoButton.setEnabled(false);
            insegnamentoLabel.setText("");
        } else {
            removeInsegnamentoButton.setEnabled(true);
            if (index == listModel.getSize()) {
                index--;
                insegnamentiList.setSelectedIndex(index);
                insegnamentiList.ensureIndexIsVisible(index);
            } else {
                insegnamentiList.setSelectedIndex(0);
            }
        }
    }


    /**
     * Metodo che crea la lista di insegnamenti per il ciclo che gli si passa.
     * Indipendentemente dal fatto che il ciclo abbia già insegnamenti associati o meno,
     * ricrea il model della lista.
     * @param ciclo Ciclo
     */
    public void setInsegnamentiFromCiclo(Ciclo ciclo) {
        if (ciclo != null) { //ciclo è null solo se non esiste neanche un ciclo. Guarda getCicloSelected
            ArrayList<Insegnamento> listaInsegnamenti = ciclo.getInsegnamenti();
            DefaultListModel<Insegnamento> model = new DefaultListModel<Insegnamento>();
            for (Insegnamento ins : listaInsegnamenti) {
                model.addElement(ins);
            }
            insegnamentiList.setModel(model);
            insegnamentiList.setSelectedIndex(0);
        }
    }

    /**
     * Metodo che carica la lista delle attivita in relazione all'insegnamento
     * che gli si è passato come parametro
     */
    public void updateElencoAttivita(Insegnamento insegnamento) {
        ArrayList<Attivita> listaAttivita = insegnamento.getAttivita();
        activitiespanel.removeAll();
        Mainframe.refreshView();

        if (listaAttivita.size() <= 0) {
            activitiespanel.add(noAttivitaLabel);
            noAttivitaLabel.setVisible(true);
        } else {
            for (Attivita attivita : listaAttivita) {
                String categoria = attivita.getCategoria();
                String docente = attivita.getDocente().toString();
                String luogo = attivita.getLuogo();
                //String giorno = attivita.getData() Vale solo se AttivitaEvento ;
                Date orainizio = attivita.getOraInizio();
                Date orafine = attivita.getOraFine();

                AttivitaEventoView attivitaEventoView = new AttivitaEventoView(categoria, docente, luogo, orainizio, orainizio, orafine);
                activitiespanel.add(attivitaEventoView.activitypanel);

            }
        }
    }

    /**
     * Metodo che ritorna l'istanza della vista
     *
     * @return
     */
    public static Agenda getInstance() {
        return instance;
    }

    /* Getters */
    public JLabel getInsegnamentoLabel() {
        return insegnamentoLabel;
    }

    public JLabel getDurataCicloLabel() {
        return durataCicloLabel;
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

    public JList getInsegnamentiList() {
        return insegnamentiList;
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
