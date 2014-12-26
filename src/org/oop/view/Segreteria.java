package org.oop.view;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by toioski on 20/12/14.
 */
public class Segreteria extends AbstractView<Segreteria> {
    public JPanel segreteriapanel;

    private JPanel inserisciform;
    private JButton annullaform;
    private JButton submitform;

    private JLabel text;
    private JLabel annoiscrizione;
    private JLabel annocorso;
    private JLabel creditiacquisiti;
    private JLabel corsodilaurea;
    private JLabel statosuperamentoesami;
    private JButton aggiungiform;

    public Segreteria() {
        super();
    }

    //costruttore per completare il form di immissione dati anagrafici
    public Segreteria(String annoiscrizione, String annocorso, String creditiacquisiti, String corsodilaurea, String statosuperamentoesami) {
        this.annoiscrizione.setText(annoiscrizione);
        this.annocorso.setText(annocorso);
        this.creditiacquisiti.setText(creditiacquisiti);
        this.corsodilaurea.setText(corsodilaurea);
        this.statosuperamentoesami.setText(statosuperamentoesami);
    }


    public void addInsForm () {segreteriapanel.add(inserisciform);}
    public void insFormButtonListener(ActionListener l) {
        aggiungiform.addActionListener(l);
    }

    public JButton getAggiungiform() { return aggiungiform;}
}
