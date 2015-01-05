package org.oop.general;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    /**
     * Controlla che una stringa sia una email valida
     * @param str Indirizzo email
     * @return
     */
    public static boolean email(String str) {
        Pattern p = Pattern.compile("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}");
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
