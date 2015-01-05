package org.oop.general;


<<<<<<< HEAD
=======
import javax.swing.*;
import java.sql.Time;
import java.util.Date;
>>>>>>> 4e8f8eef96213ac71c7f036ce57323fb035dfd39
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
<<<<<<< HEAD
=======

    /**
     * Metodo che ritorna true solo se sul JTextField è stato scritto qualcosa
     * @param field JTextfield
     * @return
     */
    public static boolean isTextFieldFilled(JTextField field) {
        boolean flag;

        if (field.getText().equals("") || field.getText() == null) {
            flag = false;
        } else {
            flag = true;
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

        if (end.after(start) || start != null){
            flag = true;
        } else {
            JOptionPane.showMessageDialog(null, "La data di fine deve essere successiva a quella di inizio!");
            flag = false;
        }
        return flag;
    }

>>>>>>> 4e8f8eef96213ac71c7f036ce57323fb035dfd39
}
