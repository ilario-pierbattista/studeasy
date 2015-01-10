package org.oop.view.profilo;


import org.oop.model.Libretto;
import org.oop.model.entities.Insegnamento;
import org.oop.model.entities.InsegnamentoOfferto;
import org.oop.view.AbstractForm;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class FormLibretto extends AbstractForm {

    private JList<InsegnamentoOfferto> insList;
    private JButton submit;
    private JLabel cfuLabel;
    private JPanel mainPanel;
    private JButton chiudiButton;
    private DefaultListModel<InsegnamentoOfferto> listModel;
    private Libretto copiaLibretto;

    public FormLibretto() {
        super();
        frame = new JFrame("Compilazione del Libretto");
        frame.setContentPane(mainPanel);
        frame.setAlwaysOnTop(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        listModel = new DefaultListModel<InsegnamentoOfferto>();
        insList.setModel(listModel);
        insList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        insList.setSelectionModel(new LibrettoSelectionModel());
        insList.addListSelectionListener(new SelectionListener());

        copiaLibretto = new Libretto();
    }

    @Override
    public boolean isValid() {
        boolean valid = true;
        if (copiaLibretto.calcolaCFUPrevisti() < copiaLibretto.getCorso().getTotaleCfu()) {
            valid = false;
            JOptionPane.showMessageDialog(null, "Non sono stati selezionati esami a sufficienza per conseguire "
                    .concat(Integer.toString(copiaLibretto.getCorso().getTotaleCfu()))
                    .concat(" CFU, necessari per conseguire la laurea."));
        }
        return valid;
    }

    /**
     * Imposta i dati contenuti nel libretto
     *
     * @param libretto Libretto dell'utente
     */
    public void setLibretto(Libretto libretto) {
        copiaLibretto.setCorso(libretto.getCorso());
        ArrayList<Insegnamento> copiaInsegnamenti = new ArrayList<Insegnamento>(libretto.getInsegnamenti().size());
        copiaInsegnamenti.addAll(libretto.getInsegnamenti());
        copiaLibretto.setInsegnamenti(copiaInsegnamenti);
        setInsegnamenti();
        updateLibretto();
        setCfuLabel();
    }

    /**
     * Ritorna il libretto con i dati aggiornati
     *
     * @return
     */
    public Libretto getNuovoLibretto() {
        return copiaLibretto;
    }

    /**
     * Imposta nella lista gli insegnamenti opzionabili
     */
    private void setInsegnamenti() {
        ArrayList<InsegnamentoOfferto> insegnamenti = copiaLibretto.getCorso().getInsegnamentiOpzionali();
        ArrayList<Integer> selectedIndices = new ArrayList<Integer>(6);
        for (int i = 0; i < insegnamenti.size(); i++) {
            listModel.addElement(insegnamenti.get(i));
            if (copiaLibretto.hasInsegnamentoOfferto(insegnamenti.get(i))) {
                selectedIndices.add(i);
            }
        }
        int[] indici = new int[selectedIndices.size()];
        for (int i = 0; i < selectedIndices.size(); i++) {
            indici[i] = selectedIndices.get(i);
        }
        insList.setSelectedIndices(indici);
    }

    /**
     * Imposta il testo del conteggio dei cfu selezionati sul totale previsto
     * del corso di laurea
     */
    private void setCfuLabel() {
        cfuLabel.setText(Integer.toString(copiaLibretto.calcolaCFUPrevisti())
                .concat("/")
                .concat(Integer.toString(copiaLibretto.getCorso().getTotaleCfu())));
    }

    /**
     * In base alla selezione, aggiorna i dati del libretto
     */
    private void updateLibretto() {
        // Pulizia degli insegnamenti opzionali
        for (Iterator<Insegnamento> iterator = copiaLibretto.getInsegnamenti().iterator(); iterator.hasNext(); ) {
            Insegnamento insegnamento = iterator.next();
            if (insegnamento.getInsegnamentoOfferto().isOpzionale()) {
                iterator.remove();
            }
        }
        // Impostazione degli insegnamenti in base agli elementi selezionati
        int[] selectedIndices = insList.getSelectedIndices();
        ArrayList<Insegnamento> insegnamentiSelezionati = getInsegnamentiByRowIndices(selectedIndices);
        for (Insegnamento insegnamento : insegnamentiSelezionati) {
            if (!copiaLibretto.hasInsegnamentoOfferto(insegnamento.getInsegnamentoOfferto())) {
                copiaLibretto.addInsegnamento(insegnamento);
            }
        }
    }

    /**
     * Ottiene un ArrayList di Insegnamenti in base agli indici di questi ultimi
     * nella lista
     *
     * @param indices Indici degli Insegnamenti
     * @return Oggetti Insegnamento
     */
    private ArrayList<Insegnamento> getInsegnamentiByRowIndices(int[] indices) {
        ArrayList<Insegnamento> insegnamenti = new ArrayList<Insegnamento>(10);
        for (int index : indices) {
            Insegnamento ins = new Insegnamento(insList.getModel().getElementAt(index));
            insegnamenti.add(ins);
        }
        return insegnamenti;
    }

    /* Listeners */
    public void addSubmitButtonListener(ActionListener l) {
        submit.addActionListener(l);
    }

    public void addCloseButtonListener(ActionListener l) {
        chiudiButton.addActionListener(l);
    }

    /**
     * Ad ogni nuova selezione o deselezione, i dati della vista vengono aggiornati
     */
    class SelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            updateLibretto();
            setCfuLabel();
        }
    }
}
