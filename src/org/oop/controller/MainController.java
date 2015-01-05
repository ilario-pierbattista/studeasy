package org.oop.controller;

import org.oop.model.dao.UtenteDAO;
import org.oop.model.entities.Utente;
import org.oop.view.Mainframe;
import org.oop.view.profilo.FormRegistrazione;


public class MainController {
    private Mainframe view;

    public MainController(Mainframe view) {
        this.view = view;

        new AgendaController(view.agenda);
        new ProfiloController(view.profilo);
        new SegreteriaController(view.segreteria);

        UtenteDAO utenteDAO = new UtenteDAO();
        if (utenteDAO.findAll().isEmpty()) {
            FormRegistrazione reg = new FormRegistrazione();
            FormRegistrazioneController ctrl = new FormRegistrazioneController(reg);
        } else {
            Mainframe.setVisible(true);
        }
    }
}
