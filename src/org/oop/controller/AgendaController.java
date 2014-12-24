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
        view.addLezioneButtonListener(new AddAttivitaAction());
        view.addEsameButtonListener(new AddEsameAction());
        view.addLaboratorioButtonListener(new AddLaboratorioAction());

        view.addInsButtonListener(new AddInsButtonAction());
        view.addListListener(new SelectionListener());

        view.addSeminarioButtonListener(new AddSeminarioAction());
        view.addProgettoButtonListener(new AddProgettoAction());


    }

    /**
     * Action per aggiungere un'attività di tipo lezione
     */
    class AddAttivitaAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {


            AttivitaView attivitaview = new AttivitaView("Lezione");

            AttivitaController attivitacontroller = new AttivitaController(attivitaview);

            view.addAttivita(attivitaview);
            Mainframe.refreshView();
        }
    }

    /**
     * Action per aggiungere un'attività di tipo esame
     */
    class AddEsameAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            view.addAttivita(new AttivitaView("Esame"));
            Mainframe.refreshView();

            FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita(),"esame");

        }
    }


    /**
     * Action per aggiungere un'attività di tipo laboratorio
     */
    class AddLaboratorioAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.addAttivita(new AttivitaView("Laboratorio"));
            Mainframe.refreshView();

            FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita(),"laboratorio");

        }
    }

    /**
     * Action per aggiungere un'attività di tipo progetto
     */
    class AddProgettoAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.addAttivita(new AttivitaView("Progetto"));
            Mainframe.refreshView();

            FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita(),"progetto");

        }
    }

    /**
     * Action per aggiungere un'attività di tipo seminario
     */
    class AddSeminarioAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            view.addAttivita(new AttivitaView("Seminario"));
            Mainframe.refreshView();

            FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita(),"seminario");

        }
    }

    /**
     * Action per aggiungere la lista dei cicli nella sidebar dell'Agenda
     */
    class AddInsButtonAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            view.addCiclo();
            Mainframe.refreshView();
        }
    }

    /**
     * Listener che permette di selezionare un elemento dalla lista della sidebar dell'Agenda. Una volta selezionato
     * stampa in output la stringa "Ciao a tutti" e l'elemento selezionato
     */
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
