package org.oop.view.segreteria;

import org.oop.general.Utils;
import org.oop.model.entities.Tassa;
import org.oop.view.CustomTableModel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TasseView {
    public JPanel tassepanel;
    private CustomTableModel model;
    private JTable tabellaTasse;
    private JButton removeButton;
    private JButton editButton;
    private JButton addButton;

    public TasseView() {
        super();
        model = new CustomTableModel("Anno Accademico", "Importo", "Scadenza", "Stato");
        tabellaTasse.setModel(model);
        removeButton.setEnabled(false);
        editButton.setEnabled(false);

        setupTableAspect();
    }

    /**
     * Imposta le preferenze grafiche per la tabella
     */
    private void setupTableAspect() {
        tabellaTasse.setRowHeight(30);
    }

    public void setTasse(ArrayList<Tassa> tasse) {
        model = new CustomTableModel("Anno Accademico", "Importo", "Scadenza", "Stato");
        tabellaTasse.setModel(model);
        for (Tassa tassa : tasse) {
            addTassa(tassa);
        }
    }

    /**
     * Metodo per aggiungere una tassa alla tabella
     */
    public void addTassa(Tassa tassa) {
        Object[] riga = new Object[]{tassa.getAnnoAccademico(), tassa.getImporto(), Utils.dateToString(tassa.getScadenza()), tassa.isPagataToString(), tassa.getId()};
        model.addRow(riga);
        tabellaTasse.getSelectedRow();
        removeButton.setEnabled(true);
        editButton.setEnabled(true);
    }

    /**
     * Metodo che restituisce l'id della tassa selezionata in tabella
     *
     * @return Id tassa
     */
    public int getTassaSelected() {
        int row = tabellaTasse.getSelectedRow();
        int id = -1;

        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Devi selezionare un tassa per eliminarla");
        } else {
            id = (Integer) model.getValueAt(row, 4);
        }
        return id;
    }

    /**
     * Metodo che aggiorna i bottoni della tabella
     */
    public void updateButtonStatus() {
        if (model.getRowCount() == 0) {
            removeButton.setEnabled(false);
            editButton.setEnabled(false);
        }
    }

    /**
     * Metodo per eliminare una riga dalla tabella
     */
    public void eliminaTassa() {
        int n = tabellaTasse.getSelectedRow();
        model.deleteRow(tabellaTasse.getSelectedRow());
        n--;
        tabellaTasse.changeSelection(n, 0, false, false);
    }

    /* Listeners setter */
    public void addAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addRemoveButtonListener(ActionListener listener) {
        removeButton.addActionListener(listener);
    }

    public void addEditButtonListener(ActionListener listener) {
        editButton.addActionListener(listener);
    }
}
