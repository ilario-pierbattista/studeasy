package org.oop.general;


import java.util.ArrayList;
import java.util.Vector;

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
}
