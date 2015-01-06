package org.oop.view;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @TODO non serve ad un cazzo per adesso. Potrebbe convenire utilizzare un classe astratta/interfaccia per tutti i form dove si mette il metodo isValid e altra roba che hanno tutti i form
 */
public abstract class AbstractView {
    public DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
    public JFrame frame;

    /**
     * Imposta la visibilità del frame
     * @param visible
     */
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

}
