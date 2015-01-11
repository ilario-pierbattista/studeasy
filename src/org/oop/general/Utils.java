package org.oop.general;


import org.oop.general.exceptions.RisorsaNonTrovata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

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
     * Sostituisce i caratteri speciali in una stringa in sequenze escape adeguate per una chiamata sql
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
     * Scrive la stringa passata come parametro con la lettera maiuscola Legge le righe di un file
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
     *
     * @param value         Numero da arrotondare
     * @param decimalPlaces Numero di cifre dopo la virgola richieste
     * @return Numero arrotondato
     */
    public static double round(double value, int decimalPlaces) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException("'decimalPlaces' deve essere un intero maggiore di zero");
        }
        value *= Math.pow(10, decimalPlaces);
        value = Math.round(value);
        return value / Math.pow(10, decimalPlaces);
    }

    /**
     * Converte la data che gli si passa in stringa nel formato scelto
     *
     * @param date Data da convertire
     */
    public static String dateToString(Date date) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(date);
    }

    public static LocalTime dateToLocaltime(Date date) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        String st = df.format(date);
        String[] parts = st.split(":");
        return LocalTime.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }

    public static Date localtimeToDate(LocalTime localTime) {
        DateFormat df = new SimpleDateFormat("dd-MM-YYYY HH:mm");
        Date date = null;
        try {
            date = df.parse("01-01-1970 ".concat(localTime.toString()));
        } catch (ParseException ee) {
            ee.printStackTrace();
        }
        return date;
    }

    public static LocalTime parseLocalTime(String localTime) {
        String parts[] = localTime.split(":");
        LocalTime time = null;
        switch (parts.length) {
            case 2:
                time = LocalTime.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                break;
            case 3:
                time = LocalTime.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                break;
            case 4:
                time = LocalTime.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                break;
        }
        return time;
    }

    /**
     * 
     * @param relativePath
     * @return
     */
    public static InputStream getResourceAsInputStream(String relativePath) {
        return Utils.class.getClassLoader().getResourceAsStream(relativePath);
    }
}
