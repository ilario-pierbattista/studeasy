package org.oop;

import org.oop.controller.*;
import org.oop.services.Importatore;
import org.oop.test.db.DatabaseManagerTest;
import org.oop.test.db.DatabaseUtilsTest;
import org.oop.view.Mainframe;
import org.oop.view.profilo.FormRegistrazione;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        /** @TODO
         * Eliminare questi messaggi, servono solo per rendersi conto
         * della velocità di esecuzione del programma
         */
        System.out.println("Programma avviato");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ee) {
            ee.printStackTrace();
        }

        mainInitProcedure(args);

        Mainframe mainframe = new Mainframe();
        new MainController(mainframe);
        new AgendaController(mainframe.agenda);
        new ProfiloController(mainframe.profilo);
        new SegreteriaController(mainframe.segreteria);

    }

    private static void mainInitProcedure(String args[]) {
        boolean executeTests = false;
        boolean overrideDatabase = false;
        for (String arg : args) {
            if(arg.equals("--override-db")) {
                overrideDatabase = true;
            } else if (arg.equals("--test")) {
                executeTests = true;
            }
        }
        if(executeTests) {
            tests();
        }
        try {
            Importatore importatore = new Importatore(overrideDatabase);
            importatore.importaDati();
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    /**
     * Test degli altri metodi
     */
    private static void tests() {
        boolean test_failed = false;
        DatabaseManagerTest dbt = new DatabaseManagerTest();
        DatabaseUtilsTest dbut = new DatabaseUtilsTest();
        try {
            dbt.testSetParameters();
            dbut.testGenerateCondition();
        } catch (Exception ee) {
            ee.printStackTrace();
            test_failed = true;
        } finally {
            if (test_failed) {
                System.exit(0);
            }
        }
    }
}
