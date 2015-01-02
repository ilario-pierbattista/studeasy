package org.oop.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by toioski on 20/12/14.
 */
public class Profilo extends AbstractView<Mainframe> {
    public JPanel profilopanel;
    private JPanel sidebarpanel;
    private JSplitPane splitpane;
    private JPanel librettopanel;
    private JLabel sidebartitle;
    private JTable insegnamentotable;
    private JButton addriga;
    private JButton deleteriga;
    private JButton aggiungiprofilo;
    private JScrollPane scrolpanetable;

    //Colums e data servono per costruire il model della tabella
    private String[] colums = new String[]{"Insegnamento", "Ciclo", "CFU", "Data","Voto"};
    private Object[][] data = new Object[0][3];
    private DefaultTableModel model = new DefaultTableModel(data, colums){
        @Override
        public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }
    };

    public Profilo() {
        super();
        //Setta la larghezza della sidebar
        splitpane.setDividerLocation(200 + splitpane.getInsets().left);
        //Elimina i bordi
        splitpane.setBorder(null);
        sidebarpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(126, 126, 126)));
        //Setta il modello alla tabella
        insegnamentotable.setModel(model);
        //inizialmente quando la tabella è vuota rendo il bottone elimina non accessibile
        deleteriga.setEnabled(false);
    }

    /**
     * Crea l'ascoltatore per il bottone addriga
     *
     * @param listener
     */
    public void addTableListener(ActionListener listener) {
        addriga.addActionListener(listener);
    }

    /**
     * Crea l'ascoltatore per il bottone deleteriga
     *
     * @param listener
     */
    public void addDeletetableListener(ActionListener listener) {
        deleteriga.addActionListener(listener);
    }

    /**
     * Metodo che permette di inserire una nuova riga nella tabella
     */
    public void addElementTable(String insegnamento, String ciclo, String cfu, String data, String voto) {

        Object[] appoggio = new Object[]{insegnamento , ciclo , cfu , data , voto };
        model.addRow(appoggio);

        //Nel momento in cui si aggiunge una riga alla tabella si rende il bottone elimina accessibile.
        deleteriga.setEnabled(true);
    }

    /**
     * Permette di eliminare un elemento dalla tabella
     */
    public void DeleteElementTable() {

        //Controllo se è stata selezionata una riga. Se non è stata selezionata nessuna riga compare un messaggio di errore
        if (insegnamentotable.getSelectedRow() == -1) {
            System.out.println("Non hai selezionato nessun elemento da eliminare");
            JOptionPane.showMessageDialog(profilopanel, "Selezionare un Insegnamento per eliminarlo");
        } else {
            model.removeRow(insegnamentotable.getSelectedRow());
        }
        //Controllo quanti elementi ci sono nella tabella. Se non c'è nessun elemento rendo il bottone elimina non visibile
        int size = model.getRowCount();
        if(size==0)
        {
            deleteriga.setEnabled(false);
        }
    }

    /**
     * aggiunge il listener per il bottone inserisci form
     * @param l
     */
    public void insFormButtonListener(ActionListener l) {
        aggiungiprofilo.addActionListener(l);
    }


    public JButton getAggiungiform() { return aggiungiprofilo;}
}



