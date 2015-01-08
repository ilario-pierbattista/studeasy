package org.oop.controller;

import org.oop.db.SQLParameters;
import org.oop.model.entities.Esame;
import org.oop.view.agenda.*;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class AttivitaController {
    private AttivitaView view;
    private FormAttivitaEvento formAttivitaEvento;
    private FormAttivitaPeriodica formAttivitaPeriodica;
    private FormEsame formEsame;
    private String newActivityType;

    public AttivitaController(AttivitaView view,String newActivityType){
        this.view = view;
        this.newActivityType = newActivityType;

        openForm();

        view.addEditButtonListener(new EditButtonAction());
    }

    /**
     * Metodo per aprire il form corretto in base al bottone cliccato.
     * Setta anche i listeners necessari per il funzionamento.
     */
    private void openForm(){
        if (newActivityType.equals("progetto") || newActivityType.equals("seminario")) {
            formAttivitaEvento = new FormAttivitaEvento();
            if (newActivityType.equals("progetto")) {
                formAttivitaEvento.setActivityname("Nuovo progetto");
            } else {
                formAttivitaEvento.setActivityname("Nuovo seminario");
            }
            formAttivitaEvento.addSubmitButtonListener(new SubmitFormEventoAction());
            formAttivitaEvento.addCancelButtonListener(new CloseFormEventoAction());
        } else if (newActivityType.equals("lezione") || newActivityType.equals("laboratorio")) {
            formAttivitaPeriodica = new FormAttivitaPeriodica();
            if (newActivityType.equals("lezione")) {
                formAttivitaPeriodica.setActivityname("Nuova lezione");
            } else {
                formAttivitaPeriodica.setActivityname("Nuovo laboratorio");
            }
            formAttivitaPeriodica.addSubmitButtonListener(new SubmitFormPeriodicaAction());
            formAttivitaPeriodica.addCancelButtonListener(new CloseFormPeriodicaAction());
        } else {
            formEsame = new FormEsame();
            formEsame.setActivityname("Nuovo esame");
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

        }
    }

    /**
     * Action per il submit del form attivitaevento
     */
    class SubmitFormEventoAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (formAttivitaEvento.isValid()) {

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
