package org.oop.controller;

import org.oop.model.dao.TassaDAO;
import org.oop.model.dao.UtenteDAO;
import org.oop.model.entities.Tassa;
import org.oop.model.entities.Utente;
import org.oop.view.segreteria.FormTasse;
import org.oop.view.segreteria.Tasse;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TasseController {
    private Tasse view;
    private FormTasse form;
    private TassaDAO tassaDAO;
    private UtenteDAO utenteDAO;

    public TasseController(Tasse view) {
        this.view = view;
        tassaDAO = new TassaDAO();
        utenteDAO = new UtenteDAO();
        for(Tassa tassa : BaseController.getUtenteCorrente().getTasse()) {
            view.addTassa(tassa);
        }
        view.addAddButtonListener(new addTassaAction());
        view.addRemoveButtonListener(new eliminaTassaAction());
        view.addEditButtonListener(new editTassaAction());
    }

    /**
     * Action per aprire il form di aggiunta tassa
     */
    class addTassaAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            form = new FormTasse();
            form.addSubmitButtonListener(new submitFormAction());
            form.addCancelButtonListener(new closeFormAction());
            form.addPagamentoRadioButtonListener(new SetPagamentoTassaAction());
        }
    }

    /**
     * Action per il submit del form
     */
    class submitFormAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (form.isValid()) {
                Utente utente = BaseController.getUtenteCorrente();
                Tassa tassa = form.getNuovaTassa();
                if(utente.findTassa(tassa.getId()) != null) {
                    tassaDAO.update(tassa);
                    // Aggiornamento dei dati
                    utente.removeTassa(tassa.getId());
                } else {
                    tassaDAO.persist(tassa);
                }
                utente.addTassa(tassa);
                utenteDAO.update(BaseController.getUtenteCorrente());
                utenteDAO.flush();
                view.addTassa(tassa);
                form.closeFrame();
            }
        }
    }

    /**
     * Action per chiudere il form
     */
    class closeFormAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            form.closeFrame();
        }
    }

    /**
     * Action per eliminare una tassa dalla tabella
     */
    class eliminaTassaAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = view.getTassaSelected();
            if (id != -1) {
                Tassa tassa = tassaDAO.find(id);
                tassaDAO.remove(tassa);
                tassaDAO.flush();

                view.eliminaTassa();
                view.updateTabella();
            }

        }
    }

    /**
     * Action per modificare una tassa
     */
    class editTassaAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = view.getTassaSelected();
            if (id != -1) {
                Tassa tassa = tassaDAO.find(id);
                form = new FormTasse();
                form.fillForm(tassa);
            }
        }
    }

    class SetPagamentoTassaAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AbstractButton button = (AbstractButton) e.getSource();
            manageRadioButton(button);
        }

        private void manageRadioButton(AbstractButton b) {
            form.getPagatoRadioButton().setSelected(false);
            form.getNonPagatoRadioButton().setSelected(false);

            if (b.getText().equals("Pagato")) {
                form.getPagatoRadioButton().setSelected(true);
            } else {
                form.getNonPagatoRadioButton().setSelected(true);
            }
        }
    }
}
