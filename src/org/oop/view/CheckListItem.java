package org.oop.view;


public class CheckListItem {

    private String label;
    private boolean isSelected;

    public CheckListItem(String label) {
        isSelected = false;
        this.label = label;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String toString() {
        return label;
    }
}
