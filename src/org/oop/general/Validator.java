package org.oop.general;


import org.oop.model.entities.Corso;

import javax.swing.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    /**
     * Metodo che restituisce true se il numero dei caratteri della stringa passata è uguale al parametro int cifreMax.
     *
     * @param s        Stringa da controllare
     * @param cifreMax Cifre massime ammesse
     * @return True se la stringa è valida, false altrimenti
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
     * Controlla che il numero dei CFU sia maggiore di 120 per conseguire il tirocinio, nel caso in cui l'utente sia
     * iscritto ad un corso di laurea triennale.
     *
     * @param cfu   Cfu da controllare
     * @param corso Corso di laurea dell'utente
     * @return True se sono a sufficienza per chiedere il tirocinio, false altrimenti
     */
    public static boolean controlloNumeroCFU(double cfu, Corso corso) {
        boolean flag;
        if (cfu < 120 && corso.getLivello() == Corso.TRIENNALE) {
            JOptionPane.showMessageDialog(null, "Il numero dei CFU non è abbastanza elevato per fare richiesta di tirocinio");
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }


    /**
     * Controlla che il codice fiscale passato sia scritto in maniera corretta
     *
     * @param s Stringa da controllare
     * @return True se è valida, False altrimenti
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
     * Controlla che la stringa passata sia un anno. Ritorna true se la stringa è compasta da soli 4 numeri.
     *
     * @param s Stringa da controllare
     * @return True se valida, False altrimenti
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
     * Controlla che la variabile di tipo int sia compresa tra 60 e 100 (estremi inclusi). ritorna true se il valore è
     * compreso tra 60 e 100.
     *
     * @param voto Voto da controllare
     * @return True se è valido, False altrimenti
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
     * Controlla che la stringa passata sia di due lettere (Provincia). Ritorna true se la stringa è composta di sole
     * due lettere.
     *
     * @param s Stringa da controllare
     * @return True se è valida, False altrimenti
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
     * Controlla che nella stringa passata ci siano solo lettere. Ritorna true se la stringa è formata da sole lettere.
     *
     * @param s         Stringa da controllare
     * @param fieldName Nome del campo
     * @return True se il contenuto è valido, False altrimenti
     */
    public static boolean inputSentenceControl(String s, String fieldName) {
        Pattern p = Pattern.compile("[a-zA-Z ,\']+");
        Matcher m = p.matcher(s);
        if (!m.matches()) {
            JOptionPane.showMessageDialog(null, "Il campo \"" + fieldName + "\" deve essere di sole lettere");
        }
        return m.matches();
    }

    /**
     * Controlla che una stringa sia una email valida
     *
     * @param str Indirizzo email
     * @return True se l'indirizzo è valido, False altrimenti
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
     * @return True se il campo è vuoto, False altrimenti
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
     * @return True se il campo è vuoto, False altrimenti
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
     * Ritorna true solo se non è stato selezionato nessun elemento dal ComboBox
     *
     * @param field     JFormattedTextField
     * @param fieldName Nome del campo
     * @return True se il campo è vuoto, False altrimenti
     */
    public static boolean isComboBoxEmpty(JComboBox field, String fieldName) {
        boolean flag;

        if (field.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Devi selezionare un valore dal campo \"" + fieldName + "\" ");
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
     * @return True se la data d'inizio è antecedente a quella di fine, false altrimenti
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

    /**
     * Controlla il contenuto di due oggetti JFormattedTextField per l'immissione di un orario. Il loro contenuto è
     * valido se l'orario di inizio è precedente all'orario di fine. Precondizione: begin ed end devono essere impostati
     * per accettare stringhe del formato HH:mm, dove HH sono le ore e mm i minuti
     *
     * @param begin Campo con l'ora d'inizio
     * @param end   Campo con l'ora di fine
     * @return True se l'ora d'inizio è antecedente a quella di fine, False altrimenti
     */
    public static boolean checkTimeJFormattedText(JFormattedTextField begin, JFormattedTextField end) {
        Date oraInizio = (Date) begin.getValue();
        Date oraFine = (Date) end.getValue();

        boolean flag = true;
        try {
            LocalTime inizio = Utils.dateToLocaltime(oraInizio);
            LocalTime fine = Utils.dateToLocaltime(oraFine);

            // All'interno dei campi JFormattedTextField, quando l'orario viene editato dall'utente,
            // anche la data cambia in modo imprevedebile, falsando la validazione. È necessario ricreare
            // nuovamente due oggetti Date dall'orario per evitare inconvenienti di questo tipo.
            if (isDateGreater(Utils.localtimeToDate(inizio), Utils.localtimeToDate(fine))) {
                flag = false;
            }
        } catch (NullPointerException ee) {
            // C'è stato qualche problema, l'ora non è stata letta come una data
            LocalTime startTime = Utils.parseLocalTime(begin.getText());
            LocalTime endTime = Utils.parseLocalTime(end.getText());
            if (startTime.isAfter(endTime)) {
                JOptionPane.showMessageDialog(null, "L'ora di fine deve essere successiva a quella di inizio!");
                flag = false;
            }
        }
        return flag;
    }
}
