package org.oop.view.segreteria;

import javax.swing.*;
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

    private String[] columnHeaders = {"Anno", "Anno Accademico", "Corso di Laurea", "Esami Superati", "CFU"};
    private Object[][] data;
    private DefaultTableModel model = new DefaultTableModel(data, columnHeaders);
    int contarighe = 1;

    public Iscrizione() {
        super();
        tabellaiscrizione.setModel(model);


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
    public void addRiga()
    {
        Object[] appoggio = new Object[]{"Anno " + contarighe, "Anno Accademico " + contarighe, "Corso " + contarighe, "Esami " + contarighe, "CFU"+ contarighe};
        model.addRow(appoggio);
        contarighe++;
    }

    /**
     * Metodo che elimina una riga dalla tabellaiscrizione utlizzando il model di tipo DefaultTableModel
     */
    public void eliminaRiga()
    {
        if (tabellaiscrizione.getSelectedRow() == -1) {
            System.out.println("Non hai selezionato nessun elemento da eliminare");
        } else {
            model.removeRow(tabellaiscrizione.getSelectedRow());
        }

    }
}
