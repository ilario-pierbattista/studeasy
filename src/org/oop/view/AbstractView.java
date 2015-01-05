package org.oop.view;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @TODO non serve ad un cazzo per adesso. Potrebbe convenire utilizzare un classe astratta/interfaccia per tutti i form dove si mette il metodo isValid e altra roba che hanno tutti i form
 */
public class AbstractView<T> {
    public DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");

}
