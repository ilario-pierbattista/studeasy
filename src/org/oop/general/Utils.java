package org.oop.general;


import java.util.ArrayList;
import java.util.Vector;
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
        Pattern p = Pattern.compile( "[0-9]{4}" );
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
