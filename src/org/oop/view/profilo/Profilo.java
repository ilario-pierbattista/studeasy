package org.oop.view.profilo;

import org.oop.controller.BaseController;
import org.oop.model.Libretto;
import org.oop.model.entities.Corso;
import org.oop.model.entities.Insegnamento;
import org.oop.model.entities.Utente;
import org.oop.view.AbstractView;
import org.oop.view.CustomTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Profilo extends AbstractView {
    public JPanel profiloPanel;
    private JPanel sidebarpanel;
    private JSplitPane splitpane;
    private JPanel librettopanel;
    private JLabel sidebartitle;
    private JTable librettoTable;
    private JButton modificaProfiloButton;
    private JButton modificaInsegnamentoButton;
    private JLabel userNameField;
    private JLabel userEmailField;
    private JLabel userSurnameField;
    private JLabel userMatricolaField;
    private JLabel userCorsoField;
    private JLabel userTipoCorsoField;
    private JScrollPane scrolpanetable;
    private CustomTableModel model;

    public Profilo() {
        super();
        //Setta la larghezza della sidebar
        splitpane.setDividerLocation(260 + splitpane.getInsets().left);
        //Elimina i bordi
        splitpane.setBorder(null);
        //Setta il modello alla tabella
        model = new CustomTableModel("Insegnamento", "Anno", "Semestre", "CFU", "Data", "Voto");
        librettoTable.setModel(model);
        librettoTable.setRowHeight(30);
        modificaInsegnamentoButton.setEnabled(false);
        // Aggiunta di un listener sulla selezione
        librettoTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                modificaInsegnamentoButton.setEnabled(true);
            }
        });
    }

    /**
     * Imposta le informazioni dell'utente nella sidebar
     *
     * @param utente Utente corrente
     */
    public void setInfoUtente(Utente utente) {
        userNameField.setText(utente.getNome());
        userSurnameField.setText(utente.getCognome());
        userEmailField.setText(utente.getEmail());
        userMatricolaField.setText(Integer.toString(utente.getMatricola()));
        Corso corso = utente.getLibretto().getCorso();
        userCorsoField.setText(corso.getNome());
        userTipoCorsoField.setText(corso.getNomeLivello());
    }

    /**
     * Imposta le informazioni sugli insegnamenti
     *
     * @param libretto
     */
    public void setInfoLibretto(Libretto libretto) {
        for (Insegnamento insegnamento : libretto.getInsegnamenti()) {
            addElementTable(insegnamento);
        }
    }

    /**
     * Metodo che permette di inserire una nuova riga nella tabella
     */
    public void addElementTable(Insegnamento insegnamento) {
        Object[] row = new Object[]{
                insegnamento.getInsegnamentoOfferto().getNome(),
                insegnamento.getInsegnamentoOfferto().getAnno(),
                insegnamento.getInsegnamentoOfferto().getSemestre(),
                insegnamento.getInsegnamentoOfferto().getCfu(),
                insegnamento.getData(),
                insegnamento.getVoto(),
                insegnamento.getId()    // Questo campo è invisibile
        };
        model.addRow(row);
    }

    public Insegnamento getInsegnamentoSelezionato() {
        int riga = librettoTable.getSelectedRow();
        int id = (Integer) model.getValueAt(riga, 6);
        Insegnamento ins = null;
        ArrayList<Insegnamento> insegnamenti = BaseController.getUtenteCorrente().getLibretto().getInsegnamenti();
        for (int i = 0; i < insegnamenti.size() && ins == null; i++) {
            if(insegnamenti.get(i).getId() == id) {
                ins = insegnamenti.get(i);
            }
        }
        return ins;
    }

    /**
     * Permette di eliminare un elemento dalla tabella
     *
    public void deleteElementTable() {
        int n = librettoTable.getSelectedRow();
        //Controllo se è stata selezionata una riga. Se non è stata selezionata nessuna riga compare un messaggio di errore
        if (librettoTable.getSelectedRow() == -1) {
            System.out.println("Non hai selezionato nessun elemento da eliminare");
            JOptionPane.showMessageDialog(profiloPanel, "Selezionare un Insegnamento per eliminarlo");
        } else {
            model.deleteRow(librettoTable.getSelectedRow());
            n--;
            librettoTable.changeSelection(n, 0, false, false);
        }
    }*/

    /* Listeners */
    public void modificaProfiloButtonListener(ActionListener l) {
        modificaProfiloButton.addActionListener(l);
    }

    public void modificaInsegnamentoButtonListener(ActionListener l) {
        modificaInsegnamentoButton.addActionListener(l);
    }

    /* Getters */
    public JButton getAggiungiform() {
        return modificaProfiloButton;
    }
}



