package org.oop.general;


import org.oop.general.exceptions.RisorsaNonTrovata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    /**
     * Racchiude una stringa tra apici singoli
     * @param string
     * @return
     */
    public static String singleQuotesToString(String string) {
        return "'".concat(string).concat("'");
    }

    /**
     * Sostituisce i caratteri speciali in una stringa in sequenze escape
     * adeguate per una chiamata sql
     * @param string
     * @return
     */
    public static String escapeSql(String string) {
        return string.replaceAll("'", "''");
    }

    /**
     * Incolla le parti di un ArrayList di stringhe
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
     * Legge le righe di un file
     * @param path
     * @return
     * @throws RisorsaNonTrovata
     */
    public static ArrayList<String> readFileLines(String path) throws RisorsaNonTrovata {
        ArrayList<String> records = new ArrayList<String>(10);
        InputStream is = Utils.class.getClassLoader().getResourceAsStream(path);
        if(is == null) {
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
     * @param ago
     * @param pagliaio
     * @param <T>
     * @return
     */
    public static <T> T arraySearch(T ago, ArrayList<T> pagliaio) {
        boolean found = false;
        T obj = null;
        for (int i = 0; i < pagliaio.size() && !found; i++) {
            if(pagliaio.get(i).equals(ago)) {
                obj = pagliaio.get(i);
                found = true;
            }
        }
        return obj;
    }

    /**
     * Metodi per il controllo dell'input ("espressioni regolari")
     * scrive la stringa passata come parametro con la lettera maiuscola
     */
    public static String stringToCapital(String s) {
        char first = s.charAt(0);
        char fupper = Character.toUpperCase(first);
        return fupper+s.substring(1,s.length());
    }
    /**
     *  controlla che il flusso di input sia un anno
     */

    public static boolean inputYearControl(String s) {
        //controllo se nella stringa s ci sono solo 4 numeri fra 0 e 9
        Pattern p = Pattern.compile("[0-9]{4}");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    /**
     * controlla che la stringa passata sia una mail
     * @param s
     * @return
     */
    public static boolean inputMailControl (String s) {
        Pattern p = Pattern.compile("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    /**
     * controlla che la stringa passata sia una data nel formato xx/xx/xxxx
     * @param s
     * @return
     */
    public static boolean inputDateControl(String s) {
        Pattern p = Pattern.compile("\\d{2}+/+\\d{2}+/+\\d{4}");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    /**
     * controlla che la stringa passata sia un numero a 3 cifre massimo (cfu)
     */

    public static boolean inputCfuControl(String s) {
        Pattern p = Pattern.compile("\\d{1,3}");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    /**
     * controlla che la stringa passata sia di sole lettere
     */

    public static boolean inputNameControl(String s) {
        Pattern p = Pattern.compile("([a-zA-Z]){1,}");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    /**
     * controlla che la matricola sia scritta in modo corretto:
     * di soli numeri
     */

    public static boolean inputMatricolaControl(String s) {
        Pattern p = Pattern.compile("\\d{1,}");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    /**
     * controlla che nella stringa passata ci siano solo lettere
     */

    public static boolean inputCorsoLaureaControl(String s) {
        Pattern p = Pattern.compile("[a-zA-Z ]+");
        Matcher m = p.matcher(s);
        return m.matches();
    }
}
