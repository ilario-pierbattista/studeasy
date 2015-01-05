package org.oop.controller;

import org.oop.general.Utils;
import org.oop.model.dao.CicloDAO;
import org.oop.model.dao.InsegnamentoOffertoDAO;
import org.oop.model.entities.Ciclo;
import org.oop.model.entities.InsegnamentoOfferto;
import org.oop.view.agenda.Agenda;
import org.oop.view.agenda.AttivitaView;
import org.oop.view.agenda.FormCiclo;
import org.oop.view.agenda.ModalAddInsegnamento;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.util.Date;


public class AgendaController {
    private Agenda view;
    private FormCiclo formcicloview;
    private ModalAddInsegnamento modalAddInsegnamento;
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
        view.addInsegnamentoButtonListener(new addInsegnamentoAction());

        view.getCiclilist().getSelectionModel().addListSelectionListener(new listaCicliSelectionAction());
        view.getInsegnamentilist().getSelectionModel().addListSelectionListener(new listaInsegnamentiSelectionAction());

        updateView();

    }

    /**
     * Action che aggiorna la lista degli insegnamenti in base al ciclo selezionato
     * dalla lista dei cicli
     */
    class listaCicliSelectionAction implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();

            if (!lsm.isSelectionEmpty()){
                int index = lsm.getMinSelectionIndex();
                Ciclo ciclo = (Ciclo) view.getCiclilist().getModel().getElementAt(index);
                view.getListaInsegnamentiTitle().setText("Insegnamenti di " + ciclo.getLabel());
            }
        }
    }

    /**
     * Metodo che passa il model alla vista e la mantiene aggiornata
     */
     public void updateView(){
         view.setListaCicli(cicloDAO.findAll());
         view.updateListaCicli();

     }

    /**
     * Metodo che passa il model alla vista e la mantiene aggiornata.
     * L'indice permette il fallback della selezione nelle liste.
     * @param index
     */
    /**@TODO riguardare meglio sta funzione **/
    public void updateView(int index){
        view.setListaCicli(cicloDAO.findAll());
        view.updateListaCicli(index);

    }

    /**
     * Action per aggiungere un'attività di tipo lezione
     */
    class AddAttivitaAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String activityType = Utils.explodeStringForSpace(actionEvent.getActionCommand(), 1);
            AttivitaView attivitaview = new AttivitaView(activityType);
            AttivitaController attivitacontroller = new AttivitaController(attivitaview,activityType);
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

                updateView(index);
            }

        }

    }

    /**
     * Action per aggiungere un nuovo ciclo
     */
    class SubmitCicloFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (formcicloview.isValid()){
                Ciclo ciclo = formcicloview.getNuovoCiclo();
                CicloDAO cicloDAO = new CicloDAO();

                cicloDAO.persist(ciclo);
                cicloDAO.flush();

                updateView();
                formcicloview.closeFrame();
            }
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

    /**
     * Action che apre la finestra per aggiungere un insegnamento ad un ciclo
     */
    class addInsegnamentoAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            modalAddInsegnamento = new ModalAddInsegnamento();
            modalAddInsegnamento.addAnnullaButtonListener(new closeModalInsegnamento());
            modalAddInsegnamento.addConfermaButtonListener(new submitModalInsegnamento());

            //Manda gli insegnamenti alla vista
            InsegnamentoOffertoDAO insegnamentoOffertoDAO = new InsegnamentoOffertoDAO();
            modalAddInsegnamento.setListaInsegnamenti(insegnamentoOffertoDAO.findAll());
        }
    }

    /**
     * Action che mostra tutte le attività relative all'insegnamento selezionato
     * dalla lista degli insegnamenti
     */
    class listaInsegnamentiSelectionAction implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();

            if (!lsm.isSelectionEmpty()) {
                int index = lsm.getMinSelectionIndex();
                InsegnamentoOfferto insegnamentoOfferto = (InsegnamentoOfferto) view.getInsegnamentilist().getModel().getElementAt(index);

                view.updateElencoAttivita(insegnamentoOfferto);
            }
        }
    }

    /**
     * Action aggiunge l'insegnamento selezionato nel modal alla lista degli insegnamenti
     * nella Agenda
     */
    class submitModalInsegnamento extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = modalAddInsegnamento.getListInsegnamenti().getSelectedIndex();
            InsegnamentoOfferto ins = (InsegnamentoOfferto) modalAddInsegnamento.getListInsegnamenti().getModel().getElementAt(index);
            view.addInsegnamentoToList(ins);
            view.getInsegnamentilist().setSelectedIndex(0);
        }
    }

    /**
     * Action per chiudere il modal di aggiunta di un insegnamento
     */
    class closeModalInsegnamento extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            modalAddInsegnamento.closeFrame();
        }
    }

    /**
     * Metodo di supporto che controlla che
     */
}
