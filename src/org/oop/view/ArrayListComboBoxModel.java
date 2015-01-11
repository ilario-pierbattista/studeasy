package org.oop.view;


import javax.swing.*;
import java.util.ArrayList;

public class ArrayListComboBoxModel<T> extends AbstractListModel<T> implements ComboBoxModel<T> {
    private Object selectedItem;

    private ArrayList<T> anArrayList;

    public ArrayListComboBoxModel(ArrayList<T> arrayList) {
        anArrayList = arrayList;
    }

    public Object getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Object newValue) {
        selectedItem = newValue;
    }

    public int getSize() {
        return anArrayList.size();
    }

    public T getElementAt(int i) {
        return anArrayList.get(i);
    }
}
