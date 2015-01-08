package org.oop.controller;

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
            //formAttivitaEvento.addSubmitButtonListener();
            formAttivitaEvento.addCancelButtonListener(new CloseFormEventoAction());
        } else if (newActivityType.equals("lezione") || newActivityType.equals("laboratorio")) {
            formAttivitaPeriodica = new FormAttivitaPeriodica();
            //formAttivitaPeriodica.addSubmitButtonListener();
            formAttivitaPeriodica.addCancelButtonListener(new CloseFormPeriodicaAction());
        } else {
            formEsame = new FormEsame();
            //formEsame.addSubmitButtonListener();
            formEsame.addCancelButtonListener(new CloseFormEsameAction());
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
