package org.oop;

import org.oop.controller.Controller;
import org.oop.view.Layout;
import org.oop.view.Mainframe;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        /*Layout mainlayout = new Layout();

        new Controller(mainlayout);*/

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ee) {
            ee.printStackTrace();
        }

        Mainframe mainframe = new Mainframe();
    }
}
