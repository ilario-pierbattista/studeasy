package org.oop.general;


import javax.swing.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    /**
     * Metodo che restituisce true se il numero dei caratteri della stringa passata è uguale al parametro int cifreMax.
     *
     * @param s
     * @param cifreMax
     * @return
     */

    public static boolean controlloCifre(String s, int cifreMax) {
        boolean flag;
        int counter;
        counter = s.length();
        if (counter != cifreMax) {
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }


    /**
     * Controlla che il numero dei CFU sia maggiore di 120 per conseguire il tirocinio.
     * Ritorna true se i CFU sono maggiori di 120
     */

    public static boolean controlloNumeroCFU(double cfu) {

        boolean flag;
        if (cfu < 120) {
            JOptionPane.showMessageDialog(null, "Il numero dei CFU non è abbastanza elevato per conseguire il tirocinio");
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }


    /**
     * Controlla che il codice fiscale passato sia scritto in maniera corretta
     *
     * @param s
     * @return
     */
    public static boolean inputCodiceFiscaleControl(String s) {
        Pattern p = Pattern.compile("[a-zA-Z]{6}\\d\\d[a-zA-Z]\\d\\d[a-zA-Z]\\d\\d\\d[a-zA-Z]");
        Matcher m = p.matcher(s);
        if (!m.matches()) {
            JOptionPane.showMessageDialog(null, "Il formato del Codice Fiscale non è corretto.");
        }
        return m.matches();
    }

    /**
     * Controlla che l'anno di fine sia maggiore di quello di inizio.
     * Ritorna true se l'anno di fine è meggiore di quello di inizio.
     *
     * @param anno1
     * @param anno2
     * @return
     */

    public static boolean controlloAnno(double anno1, double anno2) {
        boolean flag;
        if (anno1 < anno2) {
            flag = true;
        } else {
            JOptionPane.showMessageDialog(null, "La data di fine deve essere maggiore di quella di inizio.");
            flag = false;
        }
        return flag;
    }

    /**
     * Controlla che la stringa passata sia un anno.
     * Ritorna true se la stringa è compasta da soli 4 numeri.
     */

    public static boolean inputYearControl(String s) {
        //controllo se nella stringa s ci sono solo 4 numeri fra 0 e 9
        Pattern p = Pattern.compile("[0-9]{4}");
        Matcher m = p.matcher(s);
        if (!m.matches()) {
            JOptionPane.showMessageDialog(null, "Il campo 'Anno' non è corretto.");
        }
        return m.matches();
    }

    /**
     * Controlla che la variabile di tipo int sia compresa tra 60 e 100 (estremi inclusi).
     * ritorna true se il valore è compreso tra 60 e 100.
     *
     * @param voto
     * @return
     */
    public static boolean inputVotoDiploma(double voto) {
        boolean flag;
        if (voto >= 60 && voto <= 100) {
            flag = true;
        } else {
            JOptionPane.showMessageDialog(null, "Il campo 'Voto' deve essere compreso tra 60 e 100 (estremi inclusi)");
            flag = false;
        }
        return flag;
    }


    /**
     * controlla che la stringa passata sia di due lettere (Provincia).
     * Ritorna true se la stringa è composta di sole due lettere.
     */
    public static boolean inputProvinciaControl(String s) {
        Pattern p = Pattern.compile("[a-zA-Z]{2}");
        Matcher m = p.matcher(s);
        if (!m.matches()) {
            JOptionPane.showMessageDialog(null, "Il campo 'Provincia' deve essere di sole 2 lettere");
        }
        return m.matches();
    }


    /**
     * Controlla che nella stringa passata ci siano solo lettere.
     * Ritorna true se la stringa è formata da sole lettere.
     */

    public static boolean inputSentenceControl(String s, String fieldName) {
        Pattern p = Pattern.compile("[a-zA-Z ]+");
        Matcher m = p.matcher(s);
        if (!m.matches()) {
            JOptionPane.showMessageDialog(null, "Il campo \"" + fieldName + "\" deve essere di sole lettere");
        }
        return m.matches();
    }



    /* La funzione inputMatricolaControl(String s) è inutilizzata. Si mantiene per evitare errori in fase di lavoro */

    /**
     * Controlla che la matricola sia scritta in modo corretto:
     * di soli numeri. Se è corretta ritorna true.
     */

    public static boolean inputMatricolaControl(String s) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(s);
        if (!m.matches()) {
            JOptionPane.showMessageDialog(null, "Il campo 'Matricola' deve essere di soli numeri");

        }
        return m.matches();
    }

    /**
     * Controlla che una stringa sia una email valida
     *
     * @param str Indirizzo email
     * @return
     */
    public static boolean email(String str) {
        Pattern p = Pattern.compile("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
        Matcher m = p.matcher(str);
        if (!m.matches()) {
            JOptionPane.showMessageDialog(null, "Il campo 'Email' non è corretto");
        }
        return m.matches();
    }

    /**
     * Ritorna true solo se il JTextField è vuoto
     *
     * @param field     JTextfield
     * @param fieldName Nome del campo
     * @return
     */
    public static boolean isTextFieldEmpty(JTextField field, String fieldName) {
        boolean flag;

        if (field.getText().equals("") || field.getText() == null) {
            JOptionPane.showMessageDialog(null, "Il campo \"" + fieldName + "\" deve avere un valore");
            flag = true;
        } else {
            flag = false;
        }

        return flag;
    }

    /**
     * Ritorna true solo se il JFormattedTextField è vuoto
     *
     * @param field     JFormattedTextField
     * @param fieldName Nome del campo
     * @return
     */
    public static boolean isFormattedFieldEmpty(JFormattedTextField field, String fieldName) {
        boolean flag;

        if (field.getText().equals("") || field.getText() == null) {
            JOptionPane.showMessageDialog(null, "Il campo \"" + fieldName + "\" deve avere un valore");
            flag = true;
        } else {
            flag = false;
        }

        return flag;
    }

    /**
     * Controlla che la data di fine sia effetivamente successiva a quella di inizio
     *
     * @param start Data inizio
     * @param end   Data fine
     * @return
     */
    public static boolean isDateGreater(Date start, Date end) {
        boolean flag;

        if (end.after(start)) {
            flag = true;
        } else {
            JOptionPane.showMessageDialog(null, "La data di fine deve essere successiva a quella di inizio!");
            flag = false;
        }
        return flag;
    }

}
