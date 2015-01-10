package org.oop.view.segreteria;

import org.oop.general.Utils;
import org.oop.model.entities.Tassa;
import org.oop.view.CustomTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionListener;

public class Tasse {
    public JPanel tassepanel;
    CustomTableModel model = new CustomTableModel("Anno Accademico", "Importo", "Scadenza", "Stato", "ID");
    private JTable tabellaTasse;
    private JButton removeButton;
    private JButton editButton;
    private JButton addButton;

    public Tasse() {
        super();
        tabellaTasse.setModel(model);
        removeButton.setEnabled(false);

        setupTableAspect();
    }

    /**
     * Imposta le preferenze grafiche per la tabella
     */
    private void setupTableAspect() {
        tabellaTasse.setRowHeight(30);
    }

    /**
     * Metodo per aggiungere una tassa alla tabella
     */
    public void addTassa(Tassa tassa) {

        Object[] riga = new Object[]{tassa.getAnnoAccademico(), tassa.getImporto(), Utils.dateToString(tassa.getScadenza(),0), tassa.isPagataToString(), tassa.getId()};
        model.addRow(riga);
        tabellaTasse.getSelectedRow();
        removeButton.setEnabled(true);
    }

    /**
     * Metodo che restituisce l'id della tassa selezionata in tabella
     *
     *  @return Id tassa
     */
    public int getTassaSelected() {
        int row = tabellaTasse.getSelectedRow();
        int id;

        if (row == -1) {
            JOptionPane.showMessageDialog(null,"Devi selezionare un tassa per eliminarla");
            id = -1;
        } else {
            id = (Integer) model.getValueAt(row,0);
        }

        return id;
    }

    /**
     * Metodo che aggiorna la tabella
     */
    public void updateTabella() {
        int size = model.getRowCount();
        if (size == 0) {
            removeButton.setEnabled(false);
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

    /* Getters */

    public JButton getRemoveButton() {
        return removeButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getAddButton() {
        return addButton;
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
