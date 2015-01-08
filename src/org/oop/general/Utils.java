package org.oop.general;


import org.oop.general.exceptions.RisorsaNonTrovata;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {



    /**
     * Racchiude una stringa tra apici singoli
     *
     * @param string
     * @return
     */
    public static String singleQuotesToString(String string) {
        return "'".concat(string).concat("'");
    }

    /**
     * Sostituisce i caratteri speciali in una stringa in sequenze escape
     * adeguate per una chiamata sql
     *
     * @param string
     * @return
     */
    public static String escapeSql(String string) {
        return string.replaceAll("'", "''");
    }

    /**
     * Incolla le parti di un ArrayList di stringhe
     *
     * @param parts
     * @param glue
     * @return
     */
    public static String stringJoin(ArrayList<String> parts, String glue) {
        StringBuilder sb = new StringBuilder();
        sb.append(parts.get(0));
        for (int i = 1; i < parts.size(); i++) {
            sb.append(glue);
            sb.append(parts.get(i));
        }
        return sb.toString();
    }

    /**
     * Scrive la stringa passata come parametro con la lettera maiuscola
     * Legge le righe di un file
     *
     * @param path
     * @return
     * @throws RisorsaNonTrovata
     */
    public static ArrayList<String> readFileLines(String path) throws RisorsaNonTrovata {
        ArrayList<String> records = new ArrayList<String>(10);
        InputStream is = Utils.class.getClassLoader().getResourceAsStream(path);
        if (is == null) {
            throw new RisorsaNonTrovata(path);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                records.add(line);
            }
        } catch (IOException ee) {
            ee.printStackTrace();
        }
        return records;
    }

    /**
     * Cerca nell'array
     *
     * @param ago
     * @param pagliaio
     * @param <T>
     * @return
     */
    public static <T> T arraySearch(T ago, ArrayList<T> pagliaio) {
        boolean found = false;
        T obj = null;
        for (int i = 0; i < pagliaio.size() && !found; i++) {
            if (pagliaio.get(i).equals(ago)) {
                obj = pagliaio.get(i);
                found = true;
            }
        }
        return obj;
    }

    /**
     * Arrotonda un numero ad uno specifico numero di cifre
     * @param value Numero da arrotondare
     * @param decimalPlaces Numero di cifre dopo la virgola richieste
     * @return Numero arrotondato
     */
    public static double round(double value, int decimalPlaces) {
        if(decimalPlaces < 0) {
            throw new IllegalArgumentException("'decimalPlaces' deve essere un intero maggiore di zero");
        }
        value *= Math.pow(10, decimalPlaces);
        value = Math.round(value);
        return value / Math.pow(10, decimalPlaces);
    }

    /**
     * Metodi per il controllo dell'input ("espressioni regolari")
     * scrive la stringa passata come parametro con la lettera maiuscola
     */
    public static String stringToCapital(String s) {
        char first = s.charAt(0);
        char fupper = Character.toUpperCase(first);
        return fupper + s.substring(1, s.length());
    }


    /**
     * Controlla che la stringa passata sia una mail
     *
     * @param s
     * @return
     */
    public static boolean inputMailControl(String s) {
        Pattern p = Pattern.compile("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    /**
     * Controlla che la stringa passata sia una data nel formato xx/xx/xxxx
     *
     * @param s
     * @return
     */
    public static boolean inputDateControl(String s) {
        Pattern p = Pattern.compile("\\d{2}+/+\\d{2}+/+\\d{4}");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    /**
     * Controlla che la stringa passata sia un numero a 3 cifre massimo (cfu)
     */

    public static boolean inputCfuControl(String s) {
        Pattern p = Pattern.compile("\\d{1,3}");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    /**
     * Controlla che la stringa passata sia di sole lettere
     */
    public static boolean inputNameControl(String s) {
        Pattern p = Pattern.compile("([a-zA-Z])+");
        Matcher m = p.matcher(s);
        return m.matches();
    }


    /**
     * Esplode la stringa per spazi, e lo mette in un array.
     * Ritorna il valore dell'array all'indice passatogli dopo averlo messo in lowercase.
     */
    public static String explodeStringForSpace(String s, int i) {
        String[] array = s.split(" ");
        return array[i].toLowerCase();
    }


    /**
     * Converte la data che gli si passa in stringa nel formato scelto
     * @param date Data da convertire
     * @param format 0 per gg-mm-yyyy , 1 per hh:mm
     */
    public static String dateToString(Date date, int format) {
        String dstring;
        if (format == 0) {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            dstring = df.format(date);
        } else {
            DateFormat df = new SimpleDateFormat("HH:mm");
            dstring = df.format(date);
        }

        return dstring;
    }
}
