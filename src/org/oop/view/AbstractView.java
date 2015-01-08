package org.oop.view;

import javax.swing.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * @TODO non serve ad un cazzo per adesso. Potrebbe convenire utilizzare un classe astratta/interfaccia per tutti i form dove si mette il metodo isValid e altra roba che hanno tutti i form
 */
public abstract class AbstractView {

    public DateFormat hourformat = new SimpleDateFormat("HH:mm");
    public DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
    public DateFormat dateformatYear = new SimpleDateFormat("y");
    public DecimalFormat dfMatricola = new DecimalFormat("#########");
    public DecimalFormat dfCAP = new DecimalFormat("#####");
    public DecimalFormat dfCFU = new DecimalFormat("###");
    public DecimalFormat dfVoto = new DecimalFormat("#########");

    public JFrame frame;


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
