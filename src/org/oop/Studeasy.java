package org.oop;

import org.oop.controller.BaseController;
import org.oop.general.exceptions.RisorsaNonTrovata;
import org.oop.services.Importatore;
import org.oop.view.Mainframe;

import javax.swing.*;

public class Studeasy {
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
        new BaseController(mainframe);

    }

    private static void mainInitProcedure(String args[]) {
        boolean overrideDatabase = false;
        for (String arg : args) {
            if (arg.equals("--override-db")) {
                overrideDatabase = true;
            }
        }

        try {
            Importatore importatore = new Importatore(overrideDatabase);
            importatore.importaDati();
        } catch (RisorsaNonTrovata ee) {
            ee.printStackTrace();
            System.exit(1);
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }
}
