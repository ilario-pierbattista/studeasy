package org.oop.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by MelvinMancini on 30/12/14.
 */
public class Iscrizione {
    public JPanel iscrizionepanel;
    private JTable tabellaiscrizione;
    private JScrollPane scrollpanetable;

    private String[] columnHeaders = {"Anno", "Anno Accademico", "Corso di Laurea", "Esami Superati","CFU"};
    private Object[][] data = {{"1","2012/2013","ING","15","120"}};
    private DefaultTableModel model = new DefaultTableModel(data, columnHeaders);

    public Iscrizione (){
        super();
        tabellaiscrizione.setModel(model);
        Object[] appoggio = {"2","2013/2014","ING","20","160" };
        model.addRow(appoggio);
    }


}
