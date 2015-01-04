package org.oop.view;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

/**
 * Created by MelvinMancini on 30/12/14.
 */
public class Iscrizione {
    public JPanel iscrizionepanel;
    private JTable tabellaiscrizione;
    private JScrollPane scrollpanetable;
    private JButton eliminarigabutton;
    private JButton aggiungirigabutton;
    private JButton confermabutton;

    //private String[] columnHeaders = {"Anno", "Anno Accademico", "Corso di Laurea", "Esami Superati", "CFU"};
    //private Object[][] data;
    /*private DefaultTableModel model = new DefaultTableModel(data, columnHeaders) {
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };*/
    //
    private CustomTableModel model = new CustomTableModel("Anno", "Anno Accademico", "Corso di Laurea", "Esami Superati", "CFU");
    int contarighe = 1;

    public Iscrizione() {
        super();
        tabellaiscrizione.setModel(model);
        eliminarigabutton.setEnabled(false);
        tabellaiscrizione.setRowHeight(30);
    }

    //Metodi che settano gli ascoltatori ai bottoni
    public void addAggiungiRigaButtonListener(ActionListener listener) {
        aggiungirigabutton.addActionListener(listener);
    }

    public void addEliminaRigaButtonListener(ActionListener listener) {
        eliminarigabutton.addActionListener(listener);
    }

    public void addConfermaButtonListener(ActionListener listener) {
        confermabutton.addActionListener(listener);
    }

    /**
     * Metodo che aggiunge una riga alla tabellaiscrizione utilizzando il model di tipo DefaultTableModel
     */
    public void addRiga() {

        Object[] appoggio = new Object[]{"Anno " + contarighe, "Anno Accademico " + contarighe, "Corso " + contarighe, "Esami " + contarighe, "CFU" + contarighe};
        model.addRow(appoggio);
        contarighe++;
        eliminarigabutton.setEnabled(true);
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
            eliminarigabutton.setEnabled(false);
        }

    }
}
