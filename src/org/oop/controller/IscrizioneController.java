package org.oop.controller;

import org.oop.model.dao.IscrizioneDAO;
import org.oop.model.entities.Iscrizione;
import org.oop.view.segreteria.FormIscrizione;
import org.oop.view.segreteria.IscrizioneView;
import org.oop.view.segreteria.Segreteria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


/**
 * Controller per gestire il form delle iscrizioni
 */
public class IscrizioneController {
    private IscrizioneView view;
    private FormIscrizione form;
    private IscrizioneDAO iscrizioneDAO;

    public IscrizioneController(IscrizioneView view) {
        this.view = view;

        iscrizioneDAO = new IscrizioneDAO();

        view.addAddButtonListener(new AddIscrizioneAction());
        view.addDeleteButtonListener(new DeleteIscrizioneAction());
        view.addEditButtonListener(new EditIscrizioneAction());

        view.setIscrizioni(BaseController.getUtenteCorrente().getIscrizioni());
        Segreteria.getInstance().getMaintabpane().addFocusListener(new CustomFocusListener());
    }

    /**
     * Action per far partire il form di aggiunta
     */
    class AddIscrizioneAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            form = new FormIscrizione();
            form.addSubmitButtonListener(new SubmitFormAction());
            form.addCancelButtonListener(new CloseFormAction());
        }
    }

    /**
     * Action per il submit del form di aggiunta di iscrizione
     */
    class SubmitFormAction implements ActionListener {
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
    class CloseFormAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            form.close();
        }
    }

    /**
     * Action che elimina una riga dalla tabella delle iscrizioni
     */
    class DeleteIscrizioneAction implements ActionListener {
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
    class EditIscrizioneAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = view.getIscrizioneSelected();
            if (id != -1) {
                Iscrizione iscrizione = iscrizioneDAO.find(id);
                form = new FormIscrizione();
                form.fillForm(iscrizione);
                form.addSubmitButtonListener(new SubmitFormAction());
                form.addCancelButtonListener(new CloseFormAction());
            }
        }
    }

    /**
     * Aggiornamento delle iscrizioni nella vista alla selezione della tab
     */
    class CustomFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent e) {
            view.setIscrizioni(BaseController.getUtenteCorrente().getIscrizioni());
        }

        @Override
        public void focusLost(FocusEvent e) {

        }
    }
}
