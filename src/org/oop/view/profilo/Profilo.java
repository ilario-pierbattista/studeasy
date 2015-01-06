package org.oop.view.profilo;

import org.oop.controller.BaseController;
import org.oop.model.Libretto;
import org.oop.model.entities.Utente;
import org.oop.view.AbstractView;
import org.oop.view.CustomTableModel;
import org.oop.view.agenda.Agenda;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Profilo extends AbstractView<Profilo> {
    public JPanel profilopanel;
    private JPanel sidebarpanel;
    private JSplitPane splitpane;
    private JPanel librettopanel;
    private JLabel sidebartitle;
    private JTable insegnamentotable;
    private JButton addriga;
    private JButton deleteriga;
    private JButton aggiungiprofilo;
    private JButton modificaButton;
    private JLabel userNameField;
    private JLabel userEmailField;
    private JLabel userSurnameField;
    private JLabel userMatricolaField;
    private JLabel userCorsoField;
    private JLabel userTipoCorsoField;
    private JScrollPane scrolpanetable;

    private Utente utente;
    CustomTableModel model = new CustomTableModel("Insegnamento", "Ciclo", "CFU", "Data","Voto" );

    public Profilo() {
        super();
        //Setta la larghezza della sidebar
        splitpane.setDividerLocation(260 + splitpane.getInsets().left);
        //Elimina i bordi
        splitpane.setBorder(null);
        //Setta il modello alla tabella
        insegnamentotable.setModel(model);
        insegnamentotable.setRowHeight(30);
        //inizialmente quando la tabella è vuota rendo il bottone elimina non accessibile
        deleteriga.setEnabled(false);

        utente = BaseController.getUtenteCorrente();
        setUserInfo();
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
        int n = insegnamentotable.getSelectedRow();
        //Controllo se è stata selezionata una riga. Se non è stata selezionata nessuna riga compare un messaggio di errore
        if (insegnamentotable.getSelectedRow() == -1) {
            System.out.println("Non hai selezionato nessun elemento da eliminare");
            JOptionPane.showMessageDialog(profilopanel, "Selezionare un Insegnamento per eliminarlo");
        } else {
            model.deleteRow(insegnamentotable.getSelectedRow());
            n--;
            insegnamentotable.changeSelection(n,0,false,false);
        }
        //Controllo quanti elementi ci sono nella tabella. Se non c'è nessun elemento rendo il bottone elimina non visibile
        int size = model.getRowCount();
        if(size==0)
        {
            deleteriga.setEnabled(false);
        }
    }

    /**
     * Imposta le informazioni utente nella sidebar
     */
    public void setUserInfo(){
        /*
        userNameField.setText(utente.getNome());
        userSurnameField.setText(utente.getCognome());
        userEmailField.setText(utente.getEmail());
        userMatricolaField.setText(String.valueOf(utente.getMatricola()));
        /*
        userCorsoField.setText(libretto.getCorso().toString());
        userTipoCorsoField.setText(libretto.getCorso().getNomeLivello());
        */

    }


    /* Listeners setter */
    public void addTableListener(ActionListener listener) {
        addriga.addActionListener(listener);
    }

    public void addDeletetableListener(ActionListener listener) {
        deleteriga.addActionListener(listener);
    }

    public void insFormButtonListener(ActionListener l) {
        aggiungiprofilo.addActionListener(l);
    }

    /* Getters */
    public JButton getAggiungiform() { return aggiungiprofilo;}
}



