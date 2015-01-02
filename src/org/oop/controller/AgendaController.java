package org.oop.controller;

import org.oop.general.Utils;
import org.oop.model.dao.CicloDAO;
import org.oop.model.dao.CorsoDAO;
import org.oop.model.entities.Ciclo;
import org.oop.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class AgendaController {
    private Agenda view;
    private FormCiclo formcicloview;
    private CicloDAO cicloDAO;

    public AgendaController(Agenda view) {
        this.view = view;
        cicloDAO = new CicloDAO();

        view.addLezioneButtonListener(new AddAttivitaAction());
        view.addEsameButtonListener(new AddAttivitaAction());
        view.addLaboratorioButtonListener(new AddAttivitaAction());
        view.addSeminarioButtonListener(new AddAttivitaAction());
        view.addProgettoButtonListener(new AddAttivitaAction());
        view.addCicloButtonListener(new AddCicloButton());

        updateView();

    }

    /**
     * Metodo che passa il model alla vista
     */
     public void updateView(){
         view.setListaCicli(cicloDAO.findAll());
     }

    /**
     * Action per aggiungere un'attività di tipo lezione
     */
    class AddAttivitaAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String activitytype = Utils.explodeStringForSpace(actionEvent.getActionCommand(), 1);
            AttivitaView attivitaview = new AttivitaView(activitytype);
            AttivitaController attivitacontroller = new AttivitaController(attivitaview);
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

            FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita(), "esame");

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

            FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita(), "laboratorio");

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

            FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita(), "progetto");

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

            FormAttivitaController formcontroller = new FormAttivitaController(new FormAttivita(), "seminario");

        }
    }

    /**
     * Action per aprire il form di aggiunta ciclo
     */
    class AddCicloButton extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            formcicloview = new FormCiclo();
            formcicloview.addSubmitButtonListener(new SubmitCicloFormAction());
            formcicloview.addCancelButtonListener(new CloseCicloFormAction());
        }
    }

    /**
     * Action per aggiungere un nuovo ciclo
     */
    class SubmitCicloFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            Ciclo ciclo = formcicloview.getNuovoCiclo();
            CicloDAO cicloDAO = new CicloDAO();

            cicloDAO.persist(ciclo);
            cicloDAO.flush();

            updateView();
            formcicloview.closeFrame();
        }
    }

    /**
     * Action per chiudere la finestra di aggiunta di un nuovo ciclo
     */
    class CloseCicloFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            formcicloview.closeFrame();
        }
    }

}
