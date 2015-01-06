package org.oop.view.segreteria;

import org.oop.view.CustomTableModel;

import javax.swing.*;
import java.awt.event.ActionListener;


public class Iscrizione {
    public JPanel iscrizionepanel;
    private JTable tabellaiscrizione;
    private JScrollPane scrollpanetable;
    private JButton deleteButton;
    private JButton editButton;
    private JButton addButton;

    private CustomTableModel model = new CustomTableModel("Anno", "Anno Accademico", "Corso di Laurea", "Esami Superati", "CFU");
    int contarighe = 1;

    public Iscrizione() {
        super();
        tabellaiscrizione.setModel(model);
        deleteButton.setEnabled(false);
        tabellaiscrizione.setRowHeight(30);
    }


    /**
     * Metodo che aggiunge una riga alla tabellaiscrizione utilizzando il model di tipo DefaultTableModel
     */
    public void addRiga() {

        Object[] appoggio = new Object[]{"Anno " + contarighe, "Anno Accademico " + contarighe, "Corso " + contarighe, "Esami " + contarighe, "CFU" + contarighe};
        model.addRow(appoggio);
        contarighe++;
        deleteButton.setEnabled(true);
    }

    /**
     * Metodo che elimina una riga dalla tabellaiscrizione utlizzando il model di tipo DefaultTableModel
     */
    public void eliminaRiga() {
        int n = tabellaiscrizione.getSelectedRow();
        if (tabellaiscrizione.getSelectedRow() == -1) {
            System.out.println("Non hai selezionato nessun elemento da eliminare");
            JOptionPane.showMessageDialog(iscrizionepanel, "Selezionare un anno per eliminarlo");
        } else {
            model.deleteRow(tabellaiscrizione.getSelectedRow());
            n--;
            tabellaiscrizione.changeSelection(n,0,false,false);
        }
        int size = model.getRowCount();
        if (size == 0) {
            deleteButton.setEnabled(false);
        }

    }

    /* Listeners setter */
    public void addEditButtonListener(ActionListener listener) {
        editButton.addActionListener(listener);
    }

    public void addDeleteButtonListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void addAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }
}
