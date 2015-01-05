package org.oop.controller;

import org.oop.model.dao.UtenteDAO;
import org.oop.model.entities.Utente;
import org.oop.view.Mainframe;
import org.oop.view.profilo.FormRegistrazione;

import java.util.ArrayList;


public class MainController {
    private Mainframe view;
    private static Utente utenteCorrente;

    public MainController(Mainframe view) {
        this.view = view;

        new AgendaController(view.agenda);
        new ProfiloController(view.profilo);
        new SegreteriaController(view.segreteria);

        UtenteDAO utenteDAO = new UtenteDAO();
        ArrayList<Utente> utenti = utenteDAO.findAll();
        if (utenti.isEmpty()) {
            utenteCorrente = null;
            FormRegistrazione reg = new FormRegistrazione();
            FormRegistrazioneController ctrl = new FormRegistrazioneController(reg);
        } else {
            utenteCorrente = utenti.get(0);
            Mainframe.setVisible(true);
        }
    }

    public static Utente getUtenteCorrente() {
        return utenteCorrente;
    }

    public static void setUtenteCorrente(Utente utenteCorrente) {
        MainController.utenteCorrente = utenteCorrente;
    }
}
