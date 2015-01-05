package org.oop.controller;

import org.oop.model.dao.UtenteDAO;
import org.oop.model.entities.Utente;
import org.oop.view.Mainframe;
import org.oop.view.profilo.FormRegistrazione;

import java.util.ArrayList;


public class BaseController {
    private static Mainframe view;
    private static Utente utenteCorrente;

    public BaseController(Mainframe v) {
        view = v;

        UtenteDAO utenteDAO = new UtenteDAO();
        ArrayList<Utente> utenti = utenteDAO.findAll();
        if (utenti.isEmpty()) {
            utenteCorrente = null;
            FormRegistrazione reg = new FormRegistrazione();
            FormRegistrazioneController ctrl = new FormRegistrazioneController(reg);
        } else {
            utenteCorrente = utenti.get(0);
            startController();
            Mainframe.setVisible(true);
        }
    }

    public static Utente getUtenteCorrente() {
        return utenteCorrente;
    }

    public static void startController(){
        new AgendaController(view.agenda);
        new ProfiloController(view.profilo);
        new SegreteriaController(view.segreteria);
    }

    public static void setUtenteCorrente(Utente utenteCorrente) {
        BaseController.utenteCorrente = utenteCorrente;
    }
}
