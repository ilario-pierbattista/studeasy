package org.oop.view;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * @TODO non serve ad un cazzo per adesso. Potrebbe convenire utilizzare un classe astratta/interfaccia per tutti i form dove si mette il metodo isValid e altra roba che hanno tutti i form
 */
public abstract class AbstractView {
    public DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
    public DateFormat dateformatYear = new SimpleDateFormat("yyyy");
    public DecimalFormat dfMatricola = new DecimalFormat("#########");
   public NumberFormatter cifreMatricola = new NumberFormatter(dfMatricola);
    public DecimalFormat dfCAP = new DecimalFormat("#####");
    public NumberFormatter cifreCAP = new NumberFormatter(dfCAP);
    public DecimalFormat dfCFU = new DecimalFormat("###");
    public NumberFormatter cifreCFU = new NumberFormatter(dfCFU);
    public DecimalFormat dfVoto = new DecimalFormat("#########");
    public NumberFormatter cifreVoto = new NumberFormatter(dfVoto);

   public JFrame frame;

    /**
     * Imposta la visibilità del frame
     * @param visible
     */
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }


}
