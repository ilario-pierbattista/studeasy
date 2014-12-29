package org.oop.services;


import org.oop.db.DatabaseConfig;
import org.oop.db.DatabaseManager;
import org.oop.db.DatabaseUtils;
import org.oop.general.Utils;
import org.oop.general.exceptions.RisorsaNonTrovata;
import org.oop.model.entities.Corso;
import org.oop.model.entities.Docente;
import org.oop.model.entities.InsegnamentoOfferto;
import org.oop.services.exceptions.importatore.DatiNonTrovati;

import javax.print.Doc;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Questa classe si occupa dell'importazione dei dati.
 * Nel dettaglio:
 * 1) Importa lo schema
 * 2) Legge i dati dal file e crea gli oggetti con la mappatura corretta
 * 3) Salva gli oggetti nel database
 */
public class Importatore {
    private boolean overrideSchema;
    private String SCHEMA_PATH = "data/schema.sql";
    private String DATA_PATH = "data/data.csv";
    private ArrayList<String> records;
    private ArrayList<String> header;
    private ArrayList<Map<String, Object>> data;
    private ArrayList<Corso> corsi;

    public Importatore(boolean overrideSchema) {
        this.overrideSchema = overrideSchema;
        records = new ArrayList<String>(10);
        // Creazione del database
        initSchema();
        System.out.println("Inizializzazione completata\nLeggendo i dati");
        // Importazione dei dati
        try {
            records = Utils.readFileLines(DATA_PATH);
            header = parseLine(records.get(0));
            records.remove(0);
            System.out.println(header.toString());
            data = parseData();
            System.out.println(data.toString());
        } catch (RisorsaNonTrovata ee) {
            ee.printStackTrace();
        }
    }

    public Importatore() {
        this(false);
    }

    /**
     * Se il database non è presente oppure è stata specificata la sua sovrascrittura,
     * allora lo crea
     */
    public void initSchema() {
        DatabaseConfig config = DatabaseConfig.getInstance();
        if(DatabaseUtils.databaseExists() && !overrideSchema) {
            System.out.println("Il database esiste");
        } else {
            System.out.println("Il database non esiste, oppure è stata forzata la ricrezione dello stesso.\n" +
                    "Inizializzazione del database in corso");
            try {
                DatabaseUtils.execSQLScript(SCHEMA_PATH);
            } catch (RisorsaNonTrovata ee) {
                ee.printStackTrace();
            }
        }
    }

    public ArrayList<Map<String, Object>> parseData() {
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(10);
        for (String line : records) {
            ArrayList<String> values = parseLine(line);
            for(int i = 0; i < header.size(); i++) {
                Map<String, Object> row = new HashMap<String, Object>(9);
                String key = header.get(i);
                if(values.get(i).equals("\\N")) {
                    // Rappresentazione dei valori null
                    row.put(key, null);
                } else if(key.equals("corso_livello") || key.equals("insegnamento_cfu") ||
                        key.equals("insegnamento_anno") || key.equals("insegnamento_semestre")) {
                    // Questi valori devono essere interi
                    row.put(key, Integer.parseInt(values.get(i)));
                } else {
                    row.put(key, values.get(i));
                }
                data.add(row);
            }
        }
        return data;
    }

    public ArrayList<Corso> createCorsi() {
        ArrayList<Corso> corsi = new ArrayList<Corso>(16);

        return corsi;
    }

    /**
     * Lettura dell'header del file csv
     */
    private ArrayList<String> parseLine(String line) {
        String parts[] = line.split(",");
        ArrayList<String> valori = new ArrayList<String>(10);
        for (String part : parts) {
            part = part.trim();
            // Rimozione dei doppi apici dalle stringhe
            part = part.trim().replaceAll("^\"|\"$", "");
            valori.add(part.trim());
        }
        return valori;
    }

    private Corso searchCorso(ArrayList<Corso> pagliaio, Corso ago) {
        boolean found = false;
        Corso corso = null;
        for (int i = 0; i < pagliaio.size() && !found; i++) {
            if(pagliaio.get(i).equals(ago)) {
                found = true;
                corso = pagliaio.get(i);
            }
        }
        return corso;
    }

    private InsegnamentoOfferto searchInsegnamento(ArrayList<InsegnamentoOfferto> pagliaio, InsegnamentoOfferto ago) {
        boolean found = false;
        InsegnamentoOfferto insegnamentoOfferto = null;
        for (int i = 0; i < pagliaio.size() && !found; i++) {
            if(pagliaio.get(i).equals(ago)) {
                found = true;
                insegnamentoOfferto = pagliaio.get(i);
            }
        }
        return insegnamentoOfferto;
    }

    private Docente searchDocente(ArrayList<Docente> pagliaio, Docente ago) {
        boolean found = false;
        Docente docente = null;
        for (int i = 0; i < pagliaio.size() && !found; i++) {
            if(pagliaio.get(i).equals(ago)) {
                found = true;
                docente = pagliaio.get(i);
            }
        }
        return docente;
    }
}
