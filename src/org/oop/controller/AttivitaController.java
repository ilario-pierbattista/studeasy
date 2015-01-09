package org.oop.controller;

import org.oop.model.dao.AttivitaDAO;
import org.oop.model.dao.CicloDAO;
import org.oop.model.dao.InsegnamentoDAO;
import org.oop.model.entities.*;
import org.oop.view.agenda.*;
import org.oop.view.agenda.Attivita;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class AttivitaController {
    private FormAttivitaEvento formAttivitaEvento;
    private FormAttivitaPeriodica formAttivitaPeriodica;
    private FormEsame formEsame;
    private AttivitaDAO attivitaDAO;
    private CicloDAO cicloDAO;
    private InsegnamentoDAO insegnamentoDAO;

    public AttivitaController() {
        attivitaDAO = new AttivitaDAO();
        cicloDAO = new CicloDAO();
        insegnamentoDAO = new InsegnamentoDAO();
    }

    public void setListenersToView(Attivita attivita) {
        attivita.addEditButtonListener(new EditButtonAction());
        attivita.addDeleteButtonListener(new DeleteButtonAction());
    }

    /**
     * Metodo che aggiorna la view
     */
    private void updateView() {
        AgendaController.getInstance().updateAttivita(AgendaController.getInstance().getView().getInsegnamentoSelected());
    }

    /**
     * Metodo per aprire il form corretto in base al bottone cliccato.
     * Setta anche i listeners necessari per il funzionamento.
     */
    public void openForm(String newActivityType) {
        if (newActivityType.equals(org.oop.model.entities.Attivita.CATEGORIA_PROGETTO) || newActivityType.equals(org.oop.model.entities.Attivita.CATEGORIA_SEMINARIO)) {
            formAttivitaEvento = new FormAttivitaEvento();
            if (newActivityType.equals(org.oop.model.entities.Attivita.CATEGORIA_PROGETTO)) {
                formAttivitaEvento.setActivityname("Nuovo progetto");
            } else {
                formAttivitaEvento.setActivityname("Nuovo seminario");
            }
            formAttivitaEvento.setCategoria(newActivityType);
            formAttivitaEvento.addSubmitButtonListener(new SubmitFormEventoAction());
            formAttivitaEvento.addCancelButtonListener(new CloseFormEventoAction());
        } else if (newActivityType.equals(org.oop.model.entities.Attivita.CATEGORIA_LEZIONE) || newActivityType.equals(org.oop.model.entities.Attivita.CATEGORIA_LABORATORIO)) {
            formAttivitaPeriodica = new FormAttivitaPeriodica();
            if (newActivityType.equals(org.oop.model.entities.Attivita.CATEGORIA_LEZIONE)) {
                formAttivitaPeriodica.setActivityname("Nuova lezione");
            } else {
                formAttivitaPeriodica.setActivityname("Nuovo laboratorio");
            }
            formAttivitaPeriodica.setCategoria(newActivityType);
            formAttivitaPeriodica.addSubmitButtonListener(new SubmitFormPeriodicaAction());
            formAttivitaPeriodica.addCancelButtonListener(new CloseFormPeriodicaAction());
        } else {
            // In questo caso la categoria è ESAME
            formEsame = new FormEsame();
            formEsame.setActivityname("Nuovo esame");
            formEsame.addSubmitButtonListener(new SubmitFormEsameAction());
            formEsame.addCancelButtonListener(new CloseFormEsameAction());
            formEsame.addTipologiaProvaRadioListener(new SetTipologiaEsameAction());
        }
    }


    /**
     * Metodo per aprire il form corretto di modifica in base all'attivita cliccata.
     * Setta anche i listeners necessari per il funzionamento.
     */
    public void openFormToModify(org.oop.model.entities.Attivita attivita) {
        String categoria = attivita.getCategoria();
        if (categoria.equals(org.oop.model.entities.Attivita.CATEGORIA_PROGETTO) || categoria.equals(org.oop.model.entities.Attivita.CATEGORIA_SEMINARIO)) {
            formAttivitaEvento = new FormAttivitaEvento();
            if (categoria.equals(org.oop.model.entities.Attivita.CATEGORIA_PROGETTO)) {
                formAttivitaEvento.setActivityname("Modifica progetto");
                formAttivitaEvento.fillForm((AttivitaEvento) attivita);
            } else {
                formAttivitaEvento.setActivityname("Modifica seminario");
                formAttivitaEvento.fillForm((AttivitaEvento) attivita);
            }
            formAttivitaEvento.addSubmitButtonListener(new SubmitFormEventoAction());
            formAttivitaEvento.addCancelButtonListener(new CloseFormEventoAction());
        } else if (categoria.equals(org.oop.model.entities.Attivita.CATEGORIA_LEZIONE) || categoria.equals(org.oop.model.entities.Attivita.CATEGORIA_LABORATORIO)) {
            formAttivitaPeriodica = new FormAttivitaPeriodica();
            if (categoria.equals(org.oop.model.entities.Attivita.CATEGORIA_LEZIONE)) {
                formAttivitaPeriodica.setActivityname("Modifica lezione");
                formAttivitaPeriodica.fillForm((AttivitaPeriodica) attivita);
            } else {
                formAttivitaPeriodica.setActivityname("Modifica laboratorio");
                formAttivitaPeriodica.fillForm((AttivitaPeriodica) attivita);
            }
            formAttivitaPeriodica.addSubmitButtonListener(new SubmitFormPeriodicaAction());
            formAttivitaPeriodica.addCancelButtonListener(new CloseFormPeriodicaAction());
        } else {
            // In questo caso la categoria è ESAME
            formEsame = new FormEsame();
            formEsame.setActivityname("Modifica esame");
            formEsame.fillForm((Esame) attivita);
            formEsame.addSubmitButtonListener(new SubmitFormEsameAction());
            formEsame.addCancelButtonListener(new CloseFormEsameAction());
            formEsame.addTipologiaProvaRadioListener(new SetTipologiaEsameAction());
        }
    }

    /**
     * Action per modifica un'attività
     */
    class EditButtonAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(e.getActionCommand());
            org.oop.model.entities.Attivita attivita = attivitaDAO.find(id);
            openFormToModify(attivita);

        }
    }

    /**
     * Action che permetta l'eliminazione di un'attivita
     */
    class DeleteButtonAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(e.getActionCommand());
            org.oop.model.entities.Attivita attivita = attivitaDAO.find(id);
            attivitaDAO.remove(attivita);
            attivitaDAO.flush();
            AgendaController.getInstance().refreshUtente();
            updateView();
        }
    }

    /**
     * Action per il submit del form attivitaevento
     */
    class SubmitFormEventoAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (formAttivitaEvento.isValid()) {
                Ciclo ciclo = Agenda.getInstance().getCicloSelected();
                Insegnamento insegnamento = Agenda.getInstance().getInsegnamentoSelected();
                AttivitaEvento attivitaEvento = formAttivitaEvento.getNuovaAttivita();

                insegnamento.addAttivita(attivitaEvento);

                attivitaDAO.persist(attivitaEvento);
                cicloDAO.update(ciclo);
                cicloDAO.flush();

                updateView();
                formAttivitaEvento.closeFrame();
            }
        }
    }

    /**
     * Action per il submit del form attivita periodica
     */
    class SubmitFormPeriodicaAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (formAttivitaPeriodica.isValid()) {
                Ciclo ciclo = Agenda.getInstance().getCicloSelected();
                Insegnamento insegnamento = Agenda.getInstance().getInsegnamentoSelected();
                AttivitaPeriodica attivitaPeriodica = formAttivitaPeriodica.getNuovaAttivita();

                insegnamento.addAttivita(attivitaPeriodica);

                attivitaDAO.persist(attivitaPeriodica);
                cicloDAO.update(ciclo);
                cicloDAO.flush();

                updateView();
                formAttivitaPeriodica.closeFrame();
            }
        }


    }

    /**
     * Action per il submit del form attivita esame
     */
    class SubmitFormEsameAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (formEsame.isValid()) {
                Ciclo ciclo = Agenda.getInstance().getCicloSelected();
                Insegnamento insegnamento = Agenda.getInstance().getInsegnamentoSelected();
                Esame attivitaEsame = formEsame.getNuovaAttivita();
                attivitaEsame.setTipologiaProva(findTipologiaEsameFromSelection());

                insegnamento.addAttivita(attivitaEsame);

                attivitaDAO.persist(attivitaEsame);
                cicloDAO.update(ciclo);
                cicloDAO.flush();

                updateView();
                formEsame.closeFrame();
            }
        }

        /**
         * Restituisce la tipologia di esame selezionata
         *
         * @return
         */
        private String findTipologiaEsameFromSelection() {
            String tipologia;
            AbstractButton scrittoButton = formEsame.getScrittoRadioButton();
            AbstractButton oraleButton = formEsame.getOraleRadioButton();
            AbstractButton laboratorioButton = formEsame.getLaboratorioRadioButton();

            if (scrittoButton.isSelected()) {
                tipologia = Esame.TIPOLOGIA_SCRITTO;
            } else if (oraleButton.isSelected()) {
                tipologia = Esame.TIPOLOGIA_ORALE;
            } else {
                tipologia = Esame.TIPOLOGIA_LABORATORIO;
            }
            return tipologia;
        }
    }

    /**
     * Permette di gestire i radio button per la tipologia dell'esame
     */
    class SetTipologiaEsameAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            AbstractButton button = (AbstractButton) e.getSource();
            manageRadioButtons(button);
        }

        private void manageRadioButtons(AbstractButton b) {
            formEsame.getOraleRadioButton().setSelected(false);
            formEsame.getScrittoRadioButton().setSelected(false);
            formEsame.getLaboratorioRadioButton().setSelected(false);

            if (b.getText().equals("Scritto")) {
                formEsame.getScrittoRadioButton().setSelected(true);
            } else if (b.getText().equals("Orale")) {
                formEsame.getOraleRadioButton().setSelected(true);
            } else {
                formEsame.getLaboratorioRadioButton().setSelected(true);
            }
        }

    }

    /**
     * Action per chiudere il form attivita evento
     */
    class CloseFormEventoAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            formAttivitaEvento.closeFrame();
        }
    }

    /**
     * Action per chiudere il form attivita periodica
     */
    class CloseFormPeriodicaAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            formAttivitaPeriodica.closeFrame();
        }
    }

    /**
     * Action per chiudere il form esame
     */
    class CloseFormEsameAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            formEsame.closeFrame();
        }
    }


}
