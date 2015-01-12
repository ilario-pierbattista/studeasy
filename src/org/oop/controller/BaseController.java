package org.oop.controller;

import org.oop.db.DatabaseManager;
import org.oop.model.dao.UtenteDAO;
import org.oop.model.entities.Utente;
import org.oop.view.Mainframe;
import org.oop.view.profilo.FormRegistrazione;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


/**
 * Controller di base del programma
 */
public class BaseController {
    private static Mainframe view;
    private static Utente utenteCorrente;

    /**
     * Aggiunta del listener alla vista principale
     * @param v Vista principale
     */
    public BaseController(Mainframe v) {
        view = v;

        // Chiusura della connessione con il database all'uscita
        v.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                DatabaseManager db = DatabaseManager.getInstance();
                db.closeConnection();
                super.windowClosing(e);
                System.exit(0);
            }
        });

        UtenteDAO utenteDAO = new UtenteDAO();
        ArrayList<Utente> utenti = utenteDAO.findAll();
        if (utenti.isEmpty()) {
            utenteCorrente = null;
            FormRegistrazione reg = new FormRegistrazione();
            FormRegistrazioneController ctrl = new FormRegistrazioneController(reg);
        } else {
            utenteCorrente = utenti.get(0);
            Mainframe.setVisible(true);
            startController();
        }
    }

    public static Utente getUtenteCorrente() {
        return utenteCorrente;
    }

    public static void setUtenteCorrente(Utente utenteCorrente) {
        BaseController.utenteCorrente = utenteCorrente;
    }

    /**
     * Crea i controller principali, assegnandovi le relative viste
     */
    public static void startController() {
        new AgendaController(view.agendaView);
        new ProfiloController(view.profiloView);
        new SegreteriaController(view.segreteria);
    }

    public static Mainframe getMainframe() {
        return view;
    }
}
