package org.oop.view.profilo;

import org.oop.controller.BaseController;
import org.oop.model.Libretto;
import org.oop.model.entities.Corso;
import org.oop.model.entities.Insegnamento;
import org.oop.model.entities.Utente;
import org.oop.view.AbstractView;
import org.oop.view.CustomTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Profilo extends AbstractView {
    public JPanel profiloPanel;
    private JPanel sidebarpanel;
    private JSplitPane splitpane;
    private JPanel librettopanel;
    private JLabel sidebartitle;
    private JTable librettoTable;
    private JButton modificaProfiloButton;
    private JButton modificaInsegnamentoButton;
    private JLabel userNameField;
    private JLabel userEmailField;
    private JLabel userSurnameField;
    private JLabel userMatricolaField;
    private JLabel userCorsoField;
    private JLabel userTipoCorsoField;
    private JScrollPane scrolpanetable;
    private CustomTableModel model;

    public Profilo() {
        super();
        //Setta la larghezza della sidebar
        splitpane.setDividerLocation(260 + splitpane.getInsets().left);
        //Elimina i bordi
        splitpane.setBorder(null);
        //Setta il modello alla tabella
        model = new CustomTableModel("Insegnamento", "Anno", "Semestre", "CFU", "Data", "Voto");
        librettoTable.setModel(model);
        librettoTable.setRowHeight(30);
        // Impostazione dei renderer delle celle
        setCellRenderers();
        modificaInsegnamentoButton.setEnabled(false);
        // Aggiunta di un listener sulla selezione
        librettoTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                modificaInsegnamentoButton.setEnabled(true);
            }
        });
    }

    /**
     * Imposta le informazioni dell'utente nella sidebar
     *
     * @param utente Utente corrente
     */
    public void setInfoUtente(Utente utente) {
        userNameField.setText(utente.getNome());
        userSurnameField.setText(utente.getCognome());
        userEmailField.setText(utente.getEmail());
        userMatricolaField.setText(Integer.toString(utente.getMatricola()));
        Corso corso = utente.getLibretto().getCorso();
        userCorsoField.setText(corso.getNome());
        userTipoCorsoField.setText(corso.getNomeLivello());
    }

    /**
     * Imposta le informazioni sugli insegnamenti
     *
     * @param libretto
     */
    public void setInfoLibretto(Libretto libretto) {
        // Pulizia della tabella
        model.clearData();
        // Impostazione dei nuovi dati
        for (Insegnamento insegnamento : libretto.getInsegnamenti()) {
            addElementTable(insegnamento);
        }
    }

    /**
     * Metodo che permette di inserire una nuova riga nella tabella
     */
    public void addElementTable(Insegnamento insegnamento) {
        Object[] row = new Object[]{
                insegnamento.getInsegnamentoOfferto().getNome(),
                insegnamento.getInsegnamentoOfferto().getAnno(),
                insegnamento.getInsegnamentoOfferto().getSemestre(),
                insegnamento.getInsegnamentoOfferto().getCfu(),
                insegnamento.getData(),
                insegnamento.getVoto(),
                insegnamento.getId()    // Questo campo è invisibile
        };
        model.addRow(row);
    }

    public Insegnamento getInsegnamentoSelezionato() {
        int riga = librettoTable.getSelectedRow();
        int id = (Integer) model.getValueAt(riga, 6);
        Insegnamento ins = null;
        ArrayList<Insegnamento> insegnamenti = BaseController.getUtenteCorrente().getLibretto().getInsegnamenti();
        for (int i = 0; i < insegnamenti.size() && ins == null; i++) {
            if (insegnamenti.get(i).getId() == id) {
                ins = insegnamenti.get(i);
            }
        }
        return ins;
    }

    private void setCellRenderers() {
        TableCellRenderer dateCellRenderer = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof Date) {
                    value = dateformat.format(value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        TableCellRenderer votoCellRenderer = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value == null || (value instanceof Integer && (Integer) value == 0)) {
                    value = "";
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        librettoTable.getColumnModel().getColumn(4).setCellRenderer(dateCellRenderer);
        librettoTable.getColumnModel().getColumn(5).setCellRenderer(votoCellRenderer);
    }

    /* Listeners */
    public void modificaProfiloButtonListener(ActionListener l) {
        modificaProfiloButton.addActionListener(l);
    }

    public void modificaInsegnamentoButtonListener(ActionListener l) {
        modificaInsegnamentoButton.addActionListener(l);
    }

    /* Getters */
    public JButton getAggiungiform() {
        return modificaProfiloButton;
    }
}



