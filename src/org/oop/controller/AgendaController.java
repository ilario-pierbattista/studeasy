package org.oop.controller;

import org.oop.general.Utils;
import org.oop.model.dao.CicloDAO;
import org.oop.model.dao.CorsoDAO;
import org.oop.model.entities.Ciclo;
import org.oop.view.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
        view.addCicloButtonListener(new AddCicloAction());
        view.addRemoveCicloButtonListener(new RemoveCicloAction());

        view.getCiclilist().getSelectionModel().addListSelectionListener(new listaCicliSelectionAction());

        updateView();

    }

    class listaCicliSelectionAction implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();

        }
    }

    /**
     * Metodo che passa il model alla vista
     */
     public void updateView(){
         view.setListaCicli(cicloDAO.findAll());
         view.getCiclilist().setSelectedIndex(0);
     }

    /**
     * Action per aggiungere un'attività di tipo lezione
     */
    class AddAttivitaAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String activitytype = Utils.explodeStringForSpace(actionEvent.getActionCommand(),1);
            AttivitaView attivitaview = new AttivitaView(activitytype);
            AttivitaController attivitacontroller = new AttivitaController(attivitaview);
        }
    }


    /**
     * Action per aprire il form di aggiunta ciclo
     */
    class AddCicloAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            formcicloview = new FormCiclo();
            formcicloview.addSubmitButtonListener(new SubmitCicloFormAction());
            formcicloview.addCancelButtonListener(new CloseCicloFormAction());
        }
    }

    /**
     * Action per rimuovere un ciclo dalla lista dei cicli
     */
    class RemoveCicloAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = view.getCiclilist().getSelectedIndex();
            JList list = view.getCiclilist();
            DefaultListModel<Ciclo> listModel = (DefaultListModel<Ciclo>) list.getModel();
            int size = listModel.getSize();

            if (index == -1) { //Se non è selezionato niente
                JOptionPane.showMessageDialog(null, "Seleziona un ciclo per eliminarlo!");
            } else {
                CicloDAO cicloDAO = new CicloDAO();
                cicloDAO.remove(listModel.getElementAt(index));
                cicloDAO.flush();

                listModel.remove(index);

                if (size == 0) { //Se non ci sono più cicli, disabilita il bottone
                    view.getRemoveciclobutton().setEnabled(false);

                } else { //Seleziona un indice.
                    if (index == listModel.getSize()) {
                        //rimuove l'elemento nell'ultima posizione
                        index--;
                    }

                    list.setSelectedIndex(index);
                    list.ensureIndexIsVisible(index);
                }
            }

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
