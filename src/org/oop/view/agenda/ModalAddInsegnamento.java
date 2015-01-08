package org.oop.view.agenda;

import org.oop.model.entities.Insegnamento;
import org.oop.model.entities.InsegnamentoOfferto;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ModalAddInsegnamento {
    public static  JFrame frame = new JFrame("Aggiungi insegnamento");
    private JButton confermaButton;
    private JButton annullaButton;
    private JList listInsegnamenti;
    private JPanel contentPane;

    public ModalAddInsegnamento() {
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Metodo per chiudere il modal
     */
    public void closeFrame() {
        frame.dispose();
    }

    /**
     * Metodo per popolare la lista di insegnamenti
     */
    public void setListaInsegnamenti(ArrayList<Insegnamento> lista){
        DefaultListModel<Insegnamento> listModel = new DefaultListModel<Insegnamento>();
        for (Insegnamento insegnamento : lista){
            listModel.addElement(insegnamento);
        }
        listInsegnamenti.setModel(listModel);
        listInsegnamenti.setSelectedIndex(0);
    }

    /**
     * Metodo che ritorna l'Insegnamento selezionato
     * @return Insegnamento
     */
    public Insegnamento getInsegnamentoSelected(){
        int index = listInsegnamenti.getSelectedIndex();

        return (Insegnamento) listInsegnamenti.getModel().getElementAt(index);
    }

    /* Getters */
    public JButton getConfermaButton() {
        return confermaButton;
    }

    public JButton getAnnullaButton() {
        return annullaButton;
    }

    public JList getListInsegnamenti() {
        return listInsegnamenti;
    }

    /* Listeners setters */
    public void addConfermaButtonListener(ActionListener listener){
        confermaButton.addActionListener(listener);
    }

    public void addAnnullaButtonListener(ActionListener listener){
        annullaButton.addActionListener(listener);
    }
}
