package org.oop;

import org.oop.controller.BaseController;
import org.oop.db.DatabaseConfig;
import org.oop.general.exceptions.RisorsaNonTrovata;
import org.oop.services.Importatore;
import org.oop.view.Mainframe;

import javax.swing.*;

public class Studeasy {
    public static void main(String[] args) {
        // Impostazione del Look&Feel dalle impostazioni di sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ee) {
            ee.printStackTrace();
        }

        initialize(args);

        Mainframe mainframe = new Mainframe();
        new BaseController(mainframe);
    }

    /**
     * Effettua operazioni di inizializzazione delle risorse
     *
     * @param args Argomenti passati da terminale
     */
    private static void initialize(String args[]) {
        boolean overrideDatabase = false;
        boolean changeDBUser = false, changeDBPass = false;
        String newDBUser = null, newDBPass = null;

        // Lettura degli argomenti
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--override-db")) {
                overrideDatabase = true;
            } else if (args[i].equals("-u")) {
                changeDBUser = true;
                newDBUser = args[i + 1];
                i++;
            } else if (args[i].equals("-p")) {
                changeDBPass = true;
                newDBPass = args[i + 1];
                i++;
            }
        }

        // Impostazione dei parametri per il database
        if (changeDBUser && newDBUser != null) {
            DatabaseConfig.getInstance().setUser(newDBUser);
        }
        if (changeDBPass && newDBPass != null) {
            DatabaseConfig.getInstance().setPass(newDBPass);
        }

        // Esecuzione dei controlli iniziali sul database ed eventualmente importazione dei dati
        try {
            Importatore importatore = new Importatore(overrideDatabase);
            importatore.importaDati();
        } catch (RisorsaNonTrovata ee) {
            ee.printStackTrace();
            System.exit(1);
        } catch (Exception ee) {
            ee.printStackTrace();
            System.exit(1);
        }
    }
}