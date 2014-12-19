package org.oop;

import org.oop.controller.Controller;
import org.oop.view.Layout;

public class Main {
    public static void main(String[] args) {
        Layout mainlayout = new Layout();
        new Controller(mainlayout);
    }
}
