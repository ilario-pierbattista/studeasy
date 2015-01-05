package org.oop.general;


import javax.swing.*;
import java.sql.Time;
import java.util.Date;
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

    /**
     * Ritorna true solo se il JTextField è vuoto
     * @param field JTextfield
     * @return
     */
    public static boolean isTextFieldEmpty(JTextField field) {
        boolean flag;

        if (field.getText().equals("") || field.getText() == null) {
            flag = true;
        } else {
            flag = false;
        }

        return flag;
    }

    /**
     * Ritorna true solo se il JFormattedTextField è vuoto
     * @param field JFormattedTextField
     * @return
     */
    public static boolean isFormattedFieldEmpty(JFormattedTextField field){
        boolean flag;

        if (field.getText().equals("") || field.getText() == null){
            flag = true;
        } else {
            flag = false;
        }

        return flag;
    }

    /**
     * Controlla che la data di fine sia effetivamente successiva a quella di inizio
     * @param start Data inizio
     * @param end Data fine
     * @return
     */
    public static boolean isDateGreater(Date start, Date end){
        boolean flag;

        if (end.after(start)){
            flag = true;
        } else {
            JOptionPane.showMessageDialog(null, "La data di fine deve essere successiva a quella di inizio!");
            flag = false;
        }
        return flag;
    }

}
