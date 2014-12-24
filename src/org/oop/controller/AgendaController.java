package org.oop.controller;

import org.oop.view.Agenda;
import org.oop.view.AttivitaView;
import org.oop.view.FormAttivita;
import org.oop.view.Mainframe;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;


public class AgendaController{
    private Agenda view;

    public AgendaController(Agenda view) {
        this.view = view;
        view.addLezioneButtonListener(new AddLezioneAction());
        view.addEsameButtonListener(new AddEsameAction());
        view.addLaboratorioButtonListener(new AddLaboratorioAction());
        view.addInsButtonListener(new AddInsButtonAction());
        view.addListListener(new SelectionListener());

    }

    /**
     * Action per aggiungere un'attività di tipo lezione
     */
    class AddLezioneAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.addLezione(new AttivitaView("Lezione"));
            Mainframe.refreshView();

            FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita());
        }
    }

    /**
     * Action per aggiungere un'attività di tipo esame
     */
    class AddEsameAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.addLezione(new AttivitaView("Esame"));
            Mainframe.refreshView();
        }
    }


    /**
     * Action per aggiungere un'attività di tipo laboratorio
     */
    class AddLaboratorioAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.addLezione(new AttivitaView("Laboratorio"));
            Mainframe.refreshView();
        }
    }

    class AddInsButtonAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            view.addCiclo();
            Mainframe.refreshView();
        }
    }

    class SelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            if(!e.getValueIsAdjusting()) {
                JList list = (JList)e.getSource();
                System.out.println("cioa a tutti");
                Object[] selectedItems = list.getSelectedValues();
                for(int i=0;i<selectedItems.length;i++)
                    System.out.println(selectedItems[i].toString() + "\n");
            }
        }
    }

}
