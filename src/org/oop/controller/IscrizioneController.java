package org.oop.controller;

import org.oop.model.dao.IscrizioneDAO;
import org.oop.model.dao.UtenteDAO;
import org.oop.view.segreteria.FormIscrizione;
import org.oop.view.segreteria.Iscrizione;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class IscrizioneController {
    private Iscrizione view;
    private FormIscrizione form;
    private IscrizioneDAO iscrizioneDAO;
    private UtenteDAO utenteDAO;

    public IscrizioneController(Iscrizione view) {
        this.view = view;

        view.addAddButtonListener(new addIscrizioneAction());
        view.addDeleteButtonListener(new deleteIscrizioneAction());
        view.addEditButtonListener(new editIscrizioneAction());
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
                org.oop.model.entities.Iscrizione iscrizione = form.getNuovaIscrizione();
                view.addIscrizione(iscrizione);

                iscrizioneDAO.persist(iscrizione);
                utenteDAO.update(BaseController.getUtenteCorrente());
                utenteDAO.flush();

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
     * Action che elimina una riga dalla tabella delle iscrizioni
     */
    class deleteIscrizioneAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = view.getIscrizioneSelected();
            if (id != -1) {
                org.oop.model.entities.Iscrizione iscrizione = iscrizioneDAO.find(id);
                iscrizioneDAO.remove(iscrizione);
                iscrizioneDAO.flush();

                view.eliminaIscrizione();
                view.updateTabella();

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
                org.oop.model.entities.Iscrizione iscrizione = iscrizioneDAO.find(id);
                form = new FormIscrizione();
                form.fillForm(iscrizione);
            }
        }
    }
}
