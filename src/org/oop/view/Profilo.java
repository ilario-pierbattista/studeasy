package org.oop.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by toioski on 20/12/14.
 */
public class Profilo extends AbstractView<Profilo> {
    public JPanel profilopanel;
    private JPanel sidebarpanel;
    private JSplitPane splitpane;
    private JPanel librettopanel;
    private JLabel sidebartitle;
    private JTable instable;
    private JButton addriga;
    private JButton deleteriga;
    private JButton aggiungiprofilo;
    private JScrollPane scrolpanetable;

    //Colums e data servono per costruire il model della tabella
    private String[] colums = new String[]{"Insegnamento", "Ciclo", "CFU", "Data","Voto"};
    private Object[][] data = new Object[0][3];
    private DefaultTableModel model = new DefaultTableModel(data, colums);
    private int contarighe = 1;

    public Profilo() {
        super();
        //Setta la larghezza della sidebar
        splitpane.setDividerLocation(200 + splitpane.getInsets().left);
        //Elimina i bordi
        splitpane.setBorder(null);
        sidebarpanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(126, 126, 126)));
        //Setta il modello alla tabella
        instable.setModel(model);
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
    public void addElementTable() {
        Object[] appoggio = new Object[]{"Insegnamento" + contarighe, "Ciclo" + contarighe, "CFU" + contarighe, "data" + contarighe, "voto" + contarighe};
        model.addRow(appoggio);
        contarighe++;
    }

    /**
     * Permette di eliminare un elemento dalla tabella
     */
    public void DeleteElementTable() {

        if (instable.getSelectedRow() == -1) {
            System.out.println("Non hai selezionato nessun elemento da eliminare");
        } else {
            model.removeRow(instable.getSelectedRow());
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



