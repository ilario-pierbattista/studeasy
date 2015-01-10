package org.oop.view.segreteria;

import org.oop.model.entities.Iscrizione;
import org.oop.view.CustomTableModel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class IscrizioneView {
    public JPanel iscrizionepanel;
    private JTable tabellaiscrizione;
    private JScrollPane scrollpanetable;
    private JButton deleteButton;
    private JButton editButton;
    private JButton addButton;
    private CustomTableModel model;

    public IscrizioneView() {
        super();
        model = new CustomTableModel("Anno", "Anno Accademico", "Esami Superati", "CFU");
        tabellaiscrizione.setModel(model);
        deleteButton.setEnabled(false);
        editButton.setEnabled(false);
        tabellaiscrizione.setRowHeight(30);
    }

    public void setIscrizioni(ArrayList<Iscrizione> iscrizioni) {
        model = new CustomTableModel("Anno", "Anno Accademico", "Esami Superati", "CFU");
        tabellaiscrizione.setModel(model);
        Collections.sort(iscrizioni, new IscrizioniComparator());
        for (Iscrizione iscrizione : iscrizioni) {
            addIscrizione(iscrizione);
        }
    }

    /**
     * Metodo che restituisce l'id dell'iscrizione selezionata in tabella
     *
     * @return Id iscrizione
     */
    public int getIscrizioneSelected() {
        int row = tabellaiscrizione.getSelectedRow();
        int id = -1;
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Devi selezionare un tassa per eliminarla");
        } else {
            id = (Integer) model.getValueAt(row, 4);
        }
        return id;
    }

    /**
     * Metodo che aggiorna la tabella
     */
    public void updateButtonStatus() {
        if (model.getRowCount() == 0) {
            deleteButton.setEnabled(false);
            editButton.setEnabled(false);
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
        int annoAccademico = iscrizione.getAnnoAccademico();

        Object[] riga = new Object[]{
                iscrizione.getAnno(),
                annoAccademico,
                iscrizione.getUtente().getLibretto().calcolaEsamiSuperatiPerAnnoAccademico(annoAccademico),
                iscrizione.getUtente().getLibretto().calcolaCFUConseguitiPerAnnoAccademico(annoAccademico),
                iscrizione.getId()
        };
        model.addRow(riga);
        deleteButton.setEnabled(true);
        editButton.setEnabled(true);
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

    private class IscrizioniComparator implements Comparator<Iscrizione> {
        @Override
        public int compare(Iscrizione o1, Iscrizione o2) {
            return o1.getAnno() - o2.getAnno();
        }
    }
}
