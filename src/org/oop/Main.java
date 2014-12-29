package org.oop;

import org.oop.controller.AgendaController;
import org.oop.controller.ProfiloController;
import org.oop.controller.SegreteriaController;
import org.oop.services.Importatore;
import org.oop.test.db.DatabaseManagerTest;
import org.oop.test.db.DatabaseUtilsTest;
import org.oop.view.Mainframe;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        /** @TODO Eliminare questi messaggi, servono solo per rendersi conto
         * della velocità di esecuzione del programma
         */
        System.out.println("Programma avviato");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ee) {
            ee.printStackTrace();
        }

        /*Layout mainlayout = new Layout();

        new Controller(mainlayout);*/

        //testImportatore();

        Mainframe mainframe = new Mainframe();
        new AgendaController(mainframe.agenda);
        new ProfiloController(mainframe.profilo);
        new SegreteriaController(mainframe.segreteria);
    }

    /**
     * Test per l'importazione dei dati
     */
    private static void testImportatore() {
        tests();
        try {
            Importatore importatore = new Importatore(true);
            //Importatore importatore = new Importatore();
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
