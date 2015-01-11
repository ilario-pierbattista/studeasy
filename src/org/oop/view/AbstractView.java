package org.oop.view;

import javax.swing.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public abstract class AbstractView {

    protected DateFormat hourformat = new SimpleDateFormat("HH:mm");
    protected DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
    protected DateFormat dateformatYear = new SimpleDateFormat("yyyy");
    protected DecimalFormat pagamentoformat = new DecimalFormat("###.##");
    protected DecimalFormat dfMatricola = new DecimalFormat("#########");
    protected DecimalFormat dfCAP = new DecimalFormat("#####");
    protected DecimalFormat dfCFU = new DecimalFormat("###");
    protected DecimalFormat dfVoto = new DecimalFormat("#########");

    protected JFrame frame;

    public AbstractView() {
        dfMatricola.setMaximumIntegerDigits(7);
        dfMatricola.setMinimumIntegerDigits(7);
        dfCAP.setMaximumIntegerDigits(5);
        dfCAP.setMinimumIntegerDigits(5);
        dfCFU.setMaximumIntegerDigits(3);
        dfCFU.setMinimumIntegerDigits(3);
        dfVoto.setMaximumIntegerDigits(3);
        dfVoto.setMinimumIntegerDigits(2);
    }

    /**
     * Imposta la visibilità del frame
     *
     * @param visible
     */
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
