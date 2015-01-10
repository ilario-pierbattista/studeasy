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
    private CustomTableModel model = new CustomTableModel("ID","Anno", "Anno Accademico", "Corso di Laurea", "Esami Superati", "CFU");

    public Iscrizione() {
        super();
        tabellaiscrizione.setModel(model);
        deleteButton.setEnabled(false);
        tabellaiscrizione.setRowHeight(30);
    }


    /**
     * Metodo che restituisce l'id dell'iscrizione selezionata in tabella
     *
     * @return Id iscrizione
     */
    public int getIscrizioneSelected() {
        int row = tabellaiscrizione.getSelectedRow();
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
            deleteButton.setEnabled(false);
        }
    }

    /**
     * Metodo per eliminare una riga dalla tabella
     */
    public void eliminaIscrizione() {
        int n = tabellaiscrizione.getSelectedRow();

        model.deleteRow(tabellaiscrizione.getSelectedRow());
        n--;
        tabellaiscrizione.changeSelection(n, 0, false, false);

    }

    /**
     * Metodo che aggiunge un'iscrizione alla tabella
     */
    public void addIscrizione(org.oop.model.entities.Iscrizione iscrizione) {

        Object[] riga = new Object[]{iscrizione.getId(), iscrizione.getAnno(), iscrizione.getAnnoAccademico(), "nome utente", "esami superati", "cfu realizzati"};
        model.addRow(riga);
        deleteButton.setEnabled(true);
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
