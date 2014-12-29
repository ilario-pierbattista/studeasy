package org.oop;

import org.oop.controller.AgendaController;
import org.oop.controller.MainController;
import org.oop.controller.SegreteriaController;
import org.oop.db.DatabaseConfig;
import org.oop.model.dao.CorsoDAO;
import org.oop.model.dao.DocenteDAO;
import org.oop.model.dao.InsegnamentoOffertoDAO;
import org.oop.model.entities.Corso;
import org.oop.model.entities.Docente;
import org.oop.model.entities.InsegnamentoOfferto;
import org.oop.services.Importatore;
import org.oop.test.db.DatabaseManagerTest;
import org.oop.view.Mainframe;
import org.oop.view.Segreteria;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.net.URL;

import static org.oop.general.Utils.inputDateControl;

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

        //testDatabase();
        testImportatore();

        Mainframe mainframe = new Mainframe();
        new AgendaController(mainframe.agenda);
        new SegreteriaController(mainframe.segreteria);
    }

    private static void testDatabase() {
        tests();

        InsegnamentoOffertoDAO insDao = new InsegnamentoOffertoDAO();
        DocenteDAO docenteDAO = new DocenteDAO();
        CorsoDAO corsoDAO = new CorsoDAO();

        Docente docente = new Docente();
        docente.setNome("Pippo")
                .setCognome("Pippo")
                .setEmail("pippo@univpm.it");
        docenteDAO.persist(docente);
        System.out.println("Docente inserito " + docente.toString());

        InsegnamentoOfferto ins = new InsegnamentoOfferto();
        ins.setNome("Analisi 1")
                .setCfu(9)
                .setAnno(1)
                .setSemestre(1)
                .setOpzionale(false)
                .setDocente(docente);
        insDao.persist(ins);
        System.out.println("Insegnamento inserito " + ins.toString());

        Corso corso = new Corso();
        corso.setNome("Ingegneria informatica")
                .setTotaleCfu(180)
                .setLivello(1);
        corsoDAO.persist(corso);

        corso = corsoDAO.find(corso.getId());
        System.out.println("Corso selezionato " + corso.toString());

        ins = insDao.find(ins.getId());
        System.out.println("Insegnamento selezionato " + ins.toString());

        ins.setAnno(2);
        insDao.update(ins);
        ins = insDao.find(ins.getId());
        System.out.println("Insegnamento aggiornato " + ins.toString());
    }

    private static void testImportatore() {
        try {
            //Importatore importatore = new Importatore(true); Sovrascrive lo schema ogni volta
            Importatore importatore = new Importatore();
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    private static void tests() {
        boolean test_failed = false;
        DatabaseManagerTest dbt = new DatabaseManagerTest();
        try {
            dbt.testSetParameters();
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
