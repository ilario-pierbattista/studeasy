package org.oop;

import org.oop.controller.AgendaController;
import org.oop.controller.FormRegistrazioneController;
import org.oop.controller.ProfiloController;
import org.oop.controller.SegreteriaController;
import org.oop.model.dao.CicloDAO;
import org.oop.model.entities.Ciclo;
import org.oop.services.Importatore;
import org.oop.test.db.DatabaseManagerTest;
import org.oop.test.db.DatabaseUtilsTest;
import org.oop.view.FormRegistrazione;
import org.oop.view.Mainframe;

import javax.swing.*;
import java.util.Date;

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

        /*
        FormRegistrazione registrazione = new FormRegistrazione();
        FormRegistrazioneController regCtrl = new FormRegistrazioneController(registrazione);
        */

        Mainframe mainframe = new Mainframe();
        new AgendaController(mainframe.agenda);
        new ProfiloController(mainframe.profilo);
        new SegreteriaController(mainframe.segreteria);

    }

    private static void mainInitProcedure(String args[]) {
        tests();
        try {
            Importatore importatore = new Importatore(false);
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
