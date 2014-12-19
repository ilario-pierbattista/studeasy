package org.oop.controller;

import org.oop.view.Layout;

import java.awt.event.ActionEvent;

/**
 * Created by ilario on 12/19/14.
 */
public class Controller {
    private Layout view;
    private int counter;

    public Controller(Layout view) {
        this.view = view;
        view.addMainButtonListener(new MainAction());
        view.addExitButtonListener(new ExitAction());
        counter = 0;
    }

    class MainAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            counter++;
            view.getMainButton().setText("Clicked # " + counter);
        }
    }

    class ExitAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }
}
