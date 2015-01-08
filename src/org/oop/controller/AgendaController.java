package org.oop.controller;

import org.oop.general.Utils;
import org.oop.model.Libretto;
import org.oop.model.dao.CicloDAO;
import org.oop.model.dao.InsegnamentoDAO;
import org.oop.model.dao.UtenteDAO;
import org.oop.model.entities.Ciclo;
import org.oop.model.entities.Insegnamento;
import org.oop.view.agenda.Agenda;
import org.oop.view.agenda.AttivitaView;
import org.oop.view.agenda.FormCiclo;
import org.oop.view.agenda.ModalAddInsegnamento;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;


public class AgendaController {
    private Agenda view;
    private FormCiclo formcicloview;
    private ModalAddInsegnamento modalAddInsegnamento;
    private org.oop.model.Agenda agenda;
    private Libretto libretto;

    public AgendaController(Agenda view) {
        this.view = view;
        agenda = BaseController.getUtenteCorrente().getAgenda();
        libretto = BaseController.getUtenteCorrente().getLibretto();

        view.addLezioneButtonListener(new AddAttivitaAction());
        view.addEsameButtonListener(new AddAttivitaAction());
        view.addLaboratorioButtonListener(new AddAttivitaAction());
        view.addSeminarioButtonListener(new AddAttivitaAction());
        view.addProgettoButtonListener(new AddAttivitaAction());
        view.addCicloButtonListener(new AddCicloAction());
        view.addRemoveCicloButtonListener(new RemoveCicloAction());
        view.addInsegnamentoButtonListener(new addInsegnamentoAction());
        view.addRemoveInsegnamentoButtonListener(new removeInsegnamentoAction());

        view.getCiclilist().getSelectionModel().addListSelectionListener(new listaCicliSelectionAction());
        view.getInsegnamentiList().getSelectionModel().addListSelectionListener(new listaInsegnamentiSelectionAction());

        updateView();
    }

    /**
     * Action che aggiorna la lista degli insegnamenti in base al ciclo selezionato
     * dalla lista dei cicli
     */
    class listaCicliSelectionAction implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();

            if (!lsm.isSelectionEmpty()) {
                Ciclo ciclo = view.getCicloSelected();
                view.setInsegnamentiFromCiclo(ciclo);
                view.updateListaInsegnamenti();
                view.getListaInsegnamentiTitle().setText("Insegnamenti di " + ciclo.getLabel());
                view.getDurataCicloLabel().setText(ciclo.getInizio() + "/" + ciclo.getFine());


            }
        }
    }

    /**
     * Metodo che passa il model alla vista e la mantiene aggiornata
     */
    public void updateView() {
        view.setListaCicli(agenda.getCicli());
        view.setInsegnamentiFromCiclo(view.getCicloSelected());
        view.updateListaCicli();
        view.updateListaInsegnamenti();
    }

    /**
     * @TODO: da rivedere meglio. Per adesso è necessario perchè altrimenti si hanno problemi di selezione con liste e cicli
     */
    public void updateInsegnamenti() {
        view.setInsegnamentiFromCiclo(view.getCicloSelected());
        view.updateListaInsegnamenti();
    }


    /**
     * Action per aggiungere un'attività
     */
    class AddAttivitaAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String activityType = Utils.explodeStringForSpace(actionEvent.getActionCommand(), 1);
            AttivitaView attivitaview = new AttivitaView(activityType);
            AttivitaController attivitacontroller = new AttivitaController(attivitaview, activityType);
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
     * Action per aggiungere un nuovo ciclo
     */
    class SubmitCicloFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (formcicloview.isValid()){
                Ciclo ciclo = formcicloview.getNuovoCiclo();
                CicloDAO cicloDAO = new CicloDAO();
                agenda.addCiclo(ciclo);

                cicloDAO.persist(ciclo);
                UtenteDAO utenteDAO = new UtenteDAO();
                utenteDAO.update(BaseController.getUtenteCorrente());
                cicloDAO.flush();

                updateView();
                formcicloview.closeFrame();
            }
        }
    }

    /**
     * Action per rimuovere un ciclo dalla lista dei cicli
     */
    class RemoveCicloAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = view.getCiclilist().getSelectedIndex();
            Ciclo ciclo = view.getCicloSelected();

            if (index == -1) { //Se non è selezionato niente
                JOptionPane.showMessageDialog(null, "Seleziona un ciclo per eliminarlo!");
            } else {
                CicloDAO cicloDAO = new CicloDAO();
                cicloDAO.remove(ciclo);
                cicloDAO.flush();

                agenda.removeCiclo(ciclo);

                updateView();
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

            modalAddInsegnamento.setListaInsegnamenti(libretto.getInsegnamenti());
        }
    }

    /**
     * Action aggiunge l'insegnamento selezionato nel modal alla lista degli insegnamenti
     * nella Agenda
     */
    class submitModalInsegnamento extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            Insegnamento ins = modalAddInsegnamento.getInsegnamentoSelected();
            Ciclo ciclo = view.getCicloSelected();

            //Lego l'insegnamento con il ciclo nell'agenda
            ciclo.addInsegnamento(ins);

            InsegnamentoDAO insegnamentoDAO = new InsegnamentoDAO();
            CicloDAO cicloDAO = new CicloDAO();
            insegnamentoDAO.persist(ins);
            cicloDAO.update(ciclo);
            cicloDAO.flush();

            updateInsegnamenti();
        }
    }

    /**
     * Action per rimuovere un insegnamento da un ciclo
     */
    class removeInsegnamentoAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = view.getInsegnamentiList().getSelectedIndex();
            Insegnamento insegnamento = view.getInsegnamentoSelected();
            Ciclo ciclo = view.getCicloSelected();

            if (index == -1) { //Se non è selezionato niente
                JOptionPane.showMessageDialog(null, "Seleziona un insegnamento per eliminarlo!");
            } else {
                InsegnamentoDAO insegnamentoDAO = new InsegnamentoDAO();
                insegnamentoDAO.remove(insegnamento);
                insegnamentoDAO.flush();

                ciclo.removeInsegnamento(insegnamento.getId());

                updateInsegnamenti();
            }
        }
    }

    /**
     * Action che mostra tutte le attività relative all'insegnamento selezionato
     * dalla lista degli insegnamenti
     */
    class listaInsegnamentiSelectionAction implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel) e.getSource();

            if (!lsm.isSelectionEmpty()) {
                Ciclo ciclo = view.getCicloSelected();
                Insegnamento insegnamento = view.getInsegnamentoSelected();

                view.getInsegnamentoLabel().setText(insegnamento.getInsegnamentoOfferto().getNome());

                view.updateElencoAttivita(insegnamento);
            }
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
}
