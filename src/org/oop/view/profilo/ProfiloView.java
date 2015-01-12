package org.oop.view.profilo;

import org.oop.controller.BaseController;
import org.oop.model.Libretto;
import org.oop.model.entities.Corso;
import org.oop.model.entities.Insegnamento;
import org.oop.model.entities.Utente;
import org.oop.view.View;
import org.oop.view.CustomTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class ProfiloView extends View {
    public JPanel profiloPanel;
    private JPanel sidebarPanel;
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
    private JLabel mediaAritmeticaLabel;
    private JLabel mediaPonderataLabel;
    private JButton modificaLibrettoButton;
    private JScrollPane scrolpanetable;
    private CustomTableModel model;

    public ProfiloView() {
        super();
        //Setta la larghezza della sidebar
        //splitpane.setDividerLocation(300 + splitpane.getInsets().left);
        //Elimina i bordi
        splitpane.setBorder(null);
        //Setta il modello alla tabella
        model = new CustomTableModel("Insegnamento", "Anno", "Semestre", "CFU", "Data", "Voto");
        librettoTable.setModel(model);
        // Impostazione dei renderer delle celle
        modificaInsegnamentoButton.setEnabled(false);
        // Aggiunta di un listener sulla selezione
        librettoTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                modificaInsegnamentoButton.setEnabled(true);
            }
        });

        setupTableAspect();
    }

    /**
     * Imposta le preferenze grafiche per la tabella
     */
    private void setupTableAspect() {
        //Imposta altezza righe
        librettoTable.setRowHeight(30);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        librettoTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        librettoTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        librettoTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        librettoTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        librettoTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        //Imposta dimensioni colonne
        librettoTable.getColumnModel().getColumn(0).setPreferredWidth(400);
        librettoTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        librettoTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        librettoTable.getColumnModel().getColumn(3).setPreferredWidth(40);
        librettoTable.getColumnModel().getColumn(4).setPreferredWidth(80);
        librettoTable.getColumnModel().getColumn(5).setPreferredWidth(40);
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
        String c = corso.getNome().replaceFirst("Ingegneria ", ""); //Elimina Ingegneria dal nome del corso
        userCorsoField.setText(c);
        userTipoCorsoField.setText(corso.getNomeLivello());
        mediaAritmeticaLabel.setText(Double.toString(utente.getLibretto().calcolaMediaAritmetica()));
        mediaPonderataLabel.setText(Double.toString(utente.getLibretto().calcolaMediaPonderata()));
    }

    /**
     * Imposta le informazioni sugli insegnamenti
     *
     * @param libretto Oggetto Libretto da cui prendere gli insegnamenti
     */
    public void setInfoLibretto(Libretto libretto) {
        ArrayList<Insegnamento> insegnamenti = libretto.getInsegnamenti();
        Collections.sort(insegnamenti, new InsegnamentoComparator());
        // Pulizia della tabella
        model.clearData();
        setCellRenderers();
        // Impostazione dei nuovi dati
        for (Insegnamento insegnamento : insegnamenti) {
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

    /**
     * Ritorna l'istanza dell'insegnamento selezionato
     *
     * @return Oggetto Insegnamento
     */
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

    /**
     * Impostazione del render delle celle riguardanti la data e il voto
     */
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

    public void modificaLibrettoButtonListener(ActionListener l) {
        modificaLibrettoButton.addActionListener(l);
    }

    /**
     * Comparatore personalizzato per gli oggetti Insegnamento
     */
    private class InsegnamentoComparator implements Comparator<Insegnamento> {
        @Override
        public int compare(Insegnamento o1, Insegnamento o2) {
            int comp = o1.getInsegnamentoOfferto().getAnno() - o2.getInsegnamentoOfferto().getAnno();
            if (comp == 0) {
                comp = o1.getInsegnamentoOfferto().getSemestre() - o2.getInsegnamentoOfferto().getSemestre();
            }
            return comp;
        }
    }
}



