package org.oop.controller;

import org.oop.view.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;

/**
 * Created by MelvinMancini on 27/12/14.
 */
public class ProfiloController {
    private Profilo view;

    public ProfiloController(Profilo view) {
        this.view = view;
        view.addTableListener(new addTableElementAction());
        view.addDeletetableListener(new addDeleteElementAction());
    }

    class addTableElementAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            view.addElementTable();
        }
    }
    class addDeleteElementAction extends AbstractAction{
        public void actionPerformed(ActionEvent e){
            view.DeleteElementTable();
        }
    }



}
