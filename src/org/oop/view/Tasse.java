package org.oop.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

/**
 * Created by MelvinMancini on 30/12/14.
 */
public class Tasse {
    public JPanel tassepanel;
    private JTable tabellatasse;
    private JButton eliminaButton;
    private JButton aggiungiButton;
    private JButton confermaButton;

    private String[] colonne = {"Anno Accademico", "Corso di Laurea", "Importo", "Stato"};
    private Object[][] data;
    private DefaultTableModel model = new DefaultTableModel(data, colonne){
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };
    int contarighe = 1;

    public Tasse(){
        super();
        tabellatasse.setModel(model);
        eliminaButton.setEnabled(false);
    }

    //Metodi che settano i listener ai bottoni
    public void addAggiungiTassaListener(ActionListener listener)
    {
        aggiungiButton.addActionListener(listener);
    }
    public void addEliminaTassaListener(ActionListener listener)
    {
        eliminaButton.addActionListener(listener);
    }
    public void addConfermaTassaListener(ActionListener listener)
    {
        confermaButton.addActionListener(listener);
    }

    //Metodi che permettono di aggungere o togliere elementi dalla tabellatasse

    public void addTassa ()
    {

        Object[] appoggio = new Object[]{"Anno Accademico " + contarighe, "Corso " + contarighe, "Importo " + contarighe, "Stato"+ contarighe};
        model.addRow(appoggio);
        contarighe++;
        eliminaButton.setEnabled(true);
    }
    public void eliminaTassa()
    {
        if (tabellatasse.getSelectedRow() == -1) {
            System.out.println("Non hai selezionato nessun elemento da eliminare");
            JOptionPane.showMessageDialog(tassepanel, "Selezionare un elemento per eliminarlo");
        } else {
            model.removeRow(tabellatasse.getSelectedRow());
        }
        int size = model.getRowCount();
        if(size==0)
        {
            eliminaButton.setEnabled(false);
        }
    }


}
