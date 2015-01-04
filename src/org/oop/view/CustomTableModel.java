package org.oop.view;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * Created by MelvinMancini on 03/01/15.
 */

class CustomTableModel extends DefaultTableModel {

    Object[][] data = {};
    String[] columnNames;

    Vector dataVec = new Vector();

    public CustomTableModel(String... strings) {
        columnNames = new String[strings.length];
        int i = 0;
        for (String stringa : strings) {
            columnNames[i] = stringa;
            i++;
        }
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    /*Ho dovuto fare l'override del metodo int getRowCount() perchè altrimenti produceva un errore di NullExceptionPointer */
    public int getRowCount() {
        if (dataVec == null) {
            return 0;
        } else {
            return dataVec.size();
        }
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Object[] rowData = (Object[]) dataVec.elementAt(row);
        return rowData[col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public void addRow(Object[] appoggio) {
        dataVec.addElement(appoggio);
        fireTableChanged(new TableModelEvent(this));
    }

    public void deleteRow(int n) {
        dataVec.removeElementAt(n);
        fireTableChanged(new TableModelEvent(this));
    }

    public boolean isCellEditable(int row, int column) {
        //all cells false
        return false;
    }
}

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
       /* public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                        + " to " + value
                        + " (an instance of "
                        + value.getClass() + ")");
            }

            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }*/

        /*private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }*/



