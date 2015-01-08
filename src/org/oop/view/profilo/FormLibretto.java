package org.oop.view.profilo;


import org.oop.general.Utils;
import org.oop.model.Libretto;
import org.oop.model.entities.Corso;
import org.oop.model.entities.Insegnamento;
import org.oop.model.entities.InsegnamentoOfferto;
import org.oop.view.AbstractForm;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormLibretto extends AbstractForm {

    private JList<InsegnamentoOfferto> insList;
    private JButton submit;
    private JLabel cfuLabel;
    private JPanel mainPanel;
    private DefaultListModel<InsegnamentoOfferto> listModel;
    private Libretto copiaLibretto;

    public FormLibretto() {
        super();
        frame = new JFrame("Compilazione del Libretto");
        frame.setContentPane(mainPanel);
        frame.pack();
        Utils.centerJFrame(frame);
        listModel = new DefaultListModel<InsegnamentoOfferto>();
        insList.setModel(listModel);
        insList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        insList.setSelectionModel(new LibrettoSelectionModel());
        insList.addListSelectionListener(new SelectionListener());

        copiaLibretto = new Libretto();
    }

    @Override
    public boolean isValid() {
        return true;
    }

    public void setLibretto(Libretto libretto) {
        copiaLibretto.setCorso(libretto.getCorso());
        ArrayList<Insegnamento> copiaInsegnamenti = new ArrayList<Insegnamento>(libretto.getInsegnamenti().size());
        copiaInsegnamenti.addAll(libretto.getInsegnamenti());
        copiaLibretto.setInsegnamenti(copiaInsegnamenti);
        setInsegnamenti(copiaLibretto.getCorso().getInsegnamentiOpzionali());
    }

    private void setInsegnamenti(ArrayList<InsegnamentoOfferto> insegnamenti) {
        for (InsegnamentoOfferto insegnamentoOfferto : insegnamenti) {
            listModel.addElement(insegnamentoOfferto);
        }
    }

    private void setCfuLabel() {
        int cfu = copiaLibretto.calcolaCFU();
        cfuLabel.setText(Integer.toString(cfu)
                .concat("/")
                .concat(Integer.toString(copiaLibretto.getCorso().getTotaleCfu())));
    }

    /* Listeners */
    public void addSubmitButtonListener(ActionListener l) {
        submit.addActionListener(l);
    }

    class SelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            setCfuLabel();
        }
    }
}
