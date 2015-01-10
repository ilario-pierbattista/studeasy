package org.oop.controller;

import org.oop.model.dao.IscrizioneDAO;
import org.oop.model.dao.UtenteDAO;
import org.oop.model.entities.Iscrizione;
import org.oop.view.segreteria.FormIscrizione;
import org.oop.view.segreteria.IscrizioneView;
import org.oop.view.segreteria.Segreteria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class IscrizioneController {
    private IscrizioneView view;
    private FormIscrizione form;
    private IscrizioneDAO iscrizioneDAO;
    private UtenteDAO utenteDAO;

    public IscrizioneController(IscrizioneView view) {
        this.view = view;

        iscrizioneDAO = new IscrizioneDAO();
        utenteDAO = new UtenteDAO();

        view.addAddButtonListener(new addIscrizioneAction());
        view.addDeleteButtonListener(new deleteIscrizioneAction());
        view.addEditButtonListener(new editIscrizioneAction());

        view.setIscrizioni(BaseController.getUtenteCorrente().getIscrizioni());
        Segreteria.getInstance().getMaintabpane().addFocusListener(new customFocusListener());
    }

    /**
     * Action per far partire il form di aggiunta
     */
    class addIscrizioneAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            form = new FormIscrizione();
            form.addSubmitButtonListener(new submitFormAction());
            form.addCancelButtonListener(new closeFormAction());
        }
    }

    /**
     * Action per il submit del form di aggiunta di iscrizione
     */
    class submitFormAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (form.isValid()) {
                Iscrizione iscrizione = form.getNuovaIscrizione();
                if (iscrizione.getId() == 0) {
                    iscrizioneDAO.persist(iscrizione);
                } else {
                    iscrizioneDAO.update(iscrizione);
                    BaseController.getUtenteCorrente().removeIscrizione(iscrizione.getId());
                }
                BaseController.getUtenteCorrente().addIscrizione(iscrizione);
                iscrizioneDAO.flush();

                view.setIscrizioni(BaseController.getUtenteCorrente().getIscrizioni());
                form.close();
            }
        }
    }

    /**
     * Action per chiudere il form
     */
    class closeFormAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            form.close();
        }
    }

    /**
     * Action che elimina una riga dalla tabella delle iscrizioni
     */
    class deleteIscrizioneAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = view.getIscrizioneSelected();
            if (id != -1) {
                Iscrizione iscrizione = iscrizioneDAO.find(id);
                iscrizioneDAO.remove(iscrizione);
                iscrizioneDAO.flush();
                BaseController.getUtenteCorrente().removeTassa(id);

                view.eliminaIscrizione();
                view.updateButtonStatus();
            }
        }
    }

    /**
     * Action per modificare un'iscrizione
     */
    class editIscrizioneAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = view.getIscrizioneSelected();
            if (id != -1) {
                Iscrizione iscrizione = iscrizioneDAO.find(id);
                form = new FormIscrizione();
                form.fillForm(iscrizione);
                form.addSubmitButtonListener(new submitFormAction());
                form.addCancelButtonListener(new closeFormAction());
            }
        }
    }

    class customFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            view.setIscrizioni(BaseController.getUtenteCorrente().getIscrizioni());
        }

        @Override
        public void focusLost(FocusEvent e) {

        }
    }
}
