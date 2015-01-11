package org.oop.view.profilo;

import javax.swing.*;

/**
 * SelectionModel personalizzato per il form di modifica del libretto
 */
class LibrettoSelectionModel extends DefaultListSelectionModel {
    @Override
    public void setSelectionInterval(int index0, int index1) {
        if (super.isSelectedIndex(index0)) {
            super.removeSelectionInterval(index0, index1);
        } else {
            super.addSelectionInterval(index0, index1);
        }
    }
}
