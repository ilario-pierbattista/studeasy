package org.oop.view;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.Objects;
import java.util.Vector;


/* TODO Ci sono molti metodi sovrascritti. È necessario?
 */
public class CustomTableModel extends DefaultTableModel {

    private String[] columnNames;
    private Vector<Object> dataVec;

    public CustomTableModel(String... strings) {
        columnNames = strings;
        dataVec = new Vector<Object>(2);
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        int count = 0;
        if (dataVec != null) {
            count = dataVec.size();
        }
        return count;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object[] rowData = (Object[]) dataVec.elementAt(row);
        return rowData[col];
    }

    @Override
    public void addRow(Object[] e) {
        dataVec.addElement(e);
        fireTableChanged(new TableModelEvent(this));
    }

    public void deleteRow(int n) {
        dataVec.removeElementAt(n);
        fireTableChanged(new TableModelEvent(this));
    }

    public void clearData() {
        dataVec.clear();
        fireTableChanged(new TableModelEvent(this));
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}