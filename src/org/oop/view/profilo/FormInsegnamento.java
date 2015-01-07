package org.oop.view.profilo;

import org.oop.general.Validator;
import org.oop.model.entities.Insegnamento;
import org.oop.view.AbstractView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormInsegnamento extends AbstractView<FormInsegnamento> {
    public static JFrame frame = new JFrame("Inserimento Insegnamento");
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
    private JTextField TextFieldnomeinsegnamento;

    public FormInsegnamento() {
        listainsegnamenti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        frame.setContentPane(forminsegnamentopanel);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    /**
     * Metodo che aggiunge al model della lista di insegnamenti l'arraylist di
     * insegnamenti che gli è stata passata
     * @param insegnamenti Lista insegnamenti
     */
    public void setInsegnamentiList(ArrayList<Insegnamento> insegnamenti) {
        //Attenzione, probabilmente non riceve un arraylist di Insegnamento, ma di InsegnamentoOfferto
        ArrayList<String> labels = new ArrayList<String>(insegnamenti.size());
        for (Insegnamento insegnamento : insegnamenti) {
            labels.add(insegnamento.getInsegnamentoOfferto().getNome());
        }
        listainsegnamenti.setListData(labels.toArray());
    }

    /**
     * Metodo che restituisce l'insegnamento selezionato dalla lista
     */
    public Insegnamento getInsegnamentoSelected(){
        int index = listainsegnamenti.getSelectedIndex();
        Insegnamento insegnamento = (Insegnamento) listainsegnamenti.getModel().getElementAt(index);

        return insegnamento;
    }

    /**
     * Metodo di appoggio che controlla che i campi del form siano stati compilati
     * correttamente
     * @return
     */
    public boolean isValid(){
        boolean flag;

        if (Validator.isFormattedFieldEmpty(formattedTextFieldData,"Data di superamento")){
            flag = false;
        } else if (Validator.isTextFieldEmpty(formattedTextFieldVoto,"Voto")){
            flag = false;
        } else {
            flag = true;
        }

        return flag;
    }

    /**
     * Setta componenti GUI custom (rispetto all'editor visuale)
     */
    private void createUIComponents() {
        formattedTextFieldData = new JFormattedTextField(dateformat);
    }


    /**
     * Metodo che chiude il form
     */
    public static void closeFrame() {
        frame.dispose();
    }


    /* Listener setters */
    public void addConfermaButtonListener(ActionListener listener) {
        confermaButton.addActionListener(listener);
    }

    public void addAnnullaButtonListener(ActionListener listener) {
        annullaButton.addActionListener(listener);
    }


    /* Getters */
    public JFrame getFrame() {
        return frame;
    }

    public JTextField getTextFieldnomeinsegnamento() {
        return TextFieldnomeinsegnamento;
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
