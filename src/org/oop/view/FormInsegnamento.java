package org.oop.view;

import org.oop.model.entities.Insegnamento;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by MelvinMancini on 02/01/15.
 */
public class FormInsegnamento {
    public JFrame frame = new JFrame("Inserimento Insegnamento");
    private JPanel forminsegnamentopanel;
    private JLabel titoloforminsegnamento;
    private JButton annullaButton;
    private JButton confermaButton;
    private JFormattedTextField formattedTextFieldCiclo;
    private JFormattedTextField formattedTextFieldCFU;
    private JFormattedTextField formattedTextFieldData;
    private JFormattedTextField formattedTextFieldVoto;
    private JRadioButton triennaleRadioButton;
    private JRadioButton magistraleRadioButton;
    private JRadioButton cicloUnicoRadioButton;
    private JList listainsegnamenti;
    private JScrollPane scrollpaneinsegnamenti;
    private JTextField textFieldnomeinsegnamento;

    public FormInsegnamento() {
        DefaultTableModel modellist = new DefaultTableModel();
//        listainsegnamenti.setModel((ListModel) modellist);
        listainsegnamenti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        frame.setContentPane(forminsegnamentopanel);
        // frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        textFieldnomeinsegnamento.setEditable(false);
        textFieldnomeinsegnamento.setText("Ciaooooooo");

    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public void setInsegnamentiList(ArrayList<Insegnamento> insegnamenti) {
        ArrayList<String> labels = new ArrayList<String>(insegnamenti.size());
        for (Insegnamento insegnamento : insegnamenti) {
            labels.add(insegnamento.getNome());
        }
        listainsegnamenti.setListData(labels.toArray());
    }

    /**
     * Metodi che permettono di associare ai bottoni i rispettivi listener
     *
     * @param listener
     */
    public void addConfermaButtonListener(ActionListener listener) {
        confermaButton.addActionListener(listener);
    }

    public void addAnnullaButtonListener(ActionListener listener) {
        annullaButton.addActionListener(listener);
    }


    /**
     * Getter dei componenti del frame FormInsegnamento
     */
    public JTextField getTextFieldnomeinsegnamento() {
        return textFieldnomeinsegnamento;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getForminsegnamentopanel() {
        return forminsegnamentopanel;
    }

    public JLabel getTitoloforminsegnamento() {
        return titoloforminsegnamento;
    }

    public JButton getAnnullaButton() {
        return annullaButton;
    }

    public JButton getConfermaButton() {
        return confermaButton;
    }

    public JFormattedTextField getFormattedTextFieldCiclo() {
        return formattedTextFieldCiclo;
    }

    public JFormattedTextField getFormattedTextFieldCFU() {
        return formattedTextFieldCFU;
    }

    public JFormattedTextField getFormattedTextFieldData() {
        return formattedTextFieldData;
    }

    public JFormattedTextField getFormattedTextFieldVoto() {
        return formattedTextFieldVoto;
    }

    public JRadioButton getTriennaleRadioButton() {
        return triennaleRadioButton;
    }

    public JRadioButton getMagistraleRadioButton() {
        return magistraleRadioButton;
    }

    public JRadioButton getCicloUnicoRadioButton() {
        return cicloUnicoRadioButton;
    }

    public JList getListainsegnamenti() {
        return listainsegnamenti;
    }

    public JScrollPane getScrollpaneinsegnamenti() {
        return scrollpaneinsegnamenti;
    }


}
