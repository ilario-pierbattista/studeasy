package org.oop.view.segreteria;

import org.oop.view.CustomTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionListener;

public class Tasse {
    public JPanel tassepanel;
    CustomTableModel model = new CustomTableModel("Anno Accademico", "Importo", "Scadenza", "Stato");
    int contarighe = 1;
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
     * Metodo per aggiungere una riga alla tabella
     */
    public void addTassa() {

        Object[] appoggio = new Object[]{"Anno Accademico " + contarighe, "Corso " + contarighe, "Importo " + contarighe, "Stato" + contarighe};
        model.addRow(appoggio);
        contarighe++;
        removeButton.setEnabled(true);
    }

    /**
     * Metodo per eliminare una riga dalla tabella
     */
    public void eliminaTassa() {
        int n = tabellaTasse.getSelectedRow();
        if (tabellaTasse.getSelectedRow() == -1) {
            System.out.println("Non hai selezionato nessun elemento da eliminare");
            JOptionPane.showMessageDialog(tassepanel, "Selezionare un elemento per eliminarlo");
        } else {
            model.deleteRow(tabellaTasse.getSelectedRow());
            n--;
            tabellaTasse.changeSelection(n, 0, false, false);
        }
        int size = model.getRowCount();
        if (size == 0) {
            removeButton.setEnabled(false);
        }
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
