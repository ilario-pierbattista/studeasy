package org.oop.services;


import org.oop.db.DatabaseUtils;
import org.oop.general.Utils;
import org.oop.general.exceptions.RisorsaNonTrovata;
import org.oop.model.dao.CorsoDAO;
import org.oop.model.dao.DocenteDAO;
import org.oop.model.dao.InsegnamentoOffertoDAO;
import org.oop.model.entities.Corso;
import org.oop.model.entities.Docente;
import org.oop.model.entities.InsegnamentoOfferto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Si occupa dell'importazione dei dati
 */
public class Importatore {
    private boolean overrideSchema;
    private String SCHEMA_PATH = "data/schema.sql";
    private String DATA_PATH = "data/data.csv";
    private ArrayList<String> records;
    private ArrayList<String> header;
    private ArrayList<Map<String, String>> data;
    private ArrayList<Corso> corsi;

    /**
     * Crea un'istanza di Importatore
     *
     * @param overrideSchema True per sovrascrivere il database, False altrimenti
     */
    public Importatore(boolean overrideSchema) {
        this.overrideSchema = overrideSchema;
    }

    /**
     * Crea il database ed importa i dati
     *
     * @throws RisorsaNonTrovata
     */
    public void importaDati() throws RisorsaNonTrovata {
        if (!DatabaseUtils.databaseExists() || overrideSchema) {
            // Creazione dello schema
            DatabaseUtils.execSQLScript(SCHEMA_PATH);
            // Lettura delle righe del file dei dati
            records = Utils.readFileLines(DATA_PATH);
            // Parsing dell'header del file csv
            header = parseHeader();
            // Parsing dei dati del file csv
            data = parseData();
            // Generazione degli oggetti da importare
            corsi = generateObjectStructure();
            // Salvataggio nel database
            saveObjects();
        }
    }

    /**
     * Prende la prima riga del file csv (che contiene il nome dei campi)
     *
     * @return ArrayList di Stringhe con i nomi dei campi
     */
    private ArrayList<String> parseHeader() {
        ArrayList<String> header = parseLine(records.get(0));
        records.remove(0);
        return header;
    }

    /**
     * Organizza i dati nel file in un ArrayList di oggetti Map<String,String>, in cui il valore di ogni campo viene
     * associato con il nome del campo stesso
     *
     * @return ArrayList di oggetti Map<String,String>, risultato del parsing
     */
    private ArrayList<Map<String, String>> parseData() {
        ArrayList<Map<String, String>> data = new ArrayList<Map<String, String>>(10);
        for (String line : records) {
            ArrayList<String> values = parseLine(line);
            Map<String, String> row = new HashMap<String, String>(10);
            for (int i = 0; i < header.size(); i++) {
                String key = header.get(i);
                if (values.get(i).equals("\\N")) {
                    // Rappresentazione dei valori nulli
                    row.put(key, null);
                } else {
                    row.put(key, values.get(i));
                }
            }
            data.add(row);
        }
        return data;
    }

    /**
     * Creazione degli oggetti Corso, completi dei relativi oggetti InsegnamentoOfferto, a loro volta completi dei
     * relativi oggetti Docente.
     *
     * @return ArrayList di oggetti Corso
     */
    private ArrayList<Corso> generateObjectStructure() {
        ArrayList<Corso> corsi = new ArrayList<Corso>(16);
        ArrayList<Docente> docenti = new ArrayList<Docente>(100);
        Corso corso, corsoEsistente;
        InsegnamentoOfferto insegnamento;
        Docente docente, docenteEsistente;

        /* Creazione dei corsi, degli insegnamenti e dei docenti
         * e mappatura degli stessi
         */
        for (Map<String, String> row : data) {
            corso = createCorsoFromRow(row);
            corsoEsistente = Utils.arraySearch(corso, corsi);
            if (corsoEsistente == null) {
                // Mancano i dati riguardanti l'esame d'inglese, l'esame di tirocinio
                // e l'esame di laurea. Ce li "inventiamo".
                fixEsamiMancanti(corso);
                corsi.add(corso);
            } else {
                corso = corsoEsistente;
            }

            docente = createDocenteFromRow(row);
            docenteEsistente = Utils.arraySearch(docente, docenti);
            if (docenteEsistente == null) {
                docenti.add(docente);
            } else {
                docente = docenteEsistente;
            }

            insegnamento = createInsegnamentoFromRow(row);
            insegnamento.setDocente(docente);
            corso.addInsegnamentoOfferto(insegnamento);
        }
        return corsi;
    }

    /**
     * Salvataggio della struttura di oggetti nel database
     */
    private void saveObjects() {
        CorsoDAO corsoDAO = new CorsoDAO();
        InsegnamentoOffertoDAO insegnamentoOffertoDAO = new InsegnamentoOffertoDAO();
        DocenteDAO docenteDAO = new DocenteDAO();

        int i = 0;
        for (Corso corso : corsi) {
            for (InsegnamentoOfferto insegnamento : corso.getInsegnamentiOfferti()) {
                Docente docente = insegnamento.getDocente();
                // Controllo che il docente non sia già stato inserito nel database
                if (docente != null && (docente.getId() == 0 || docenteDAO.find(docente.getId()) == null)) {
                    docenteDAO.persist(docente);
                }

                insegnamentoOffertoDAO.persist(insegnamento);
            }
            corsoDAO.persist(corso);

            i++;
            System.out.println("Corsi importati " + i + " su " + corsi.size());
        }
        corsoDAO.flush();
    }

    /**
     * Lettura di una riga file e generazione di un ArrayList di stringhe contenenti i valori dei campi (che nel file
     * sono sperati da stringhe)
     *
     * @param line Riga del file
     * @return ArrayList di stringhe con i valori
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

    /**
     * Crea un oggetto Corso da un oggetto da una Mappa di dati
     *
     * @param row Mappa
     * @return Corso
     */
    private Corso createCorsoFromRow(Map<String, String> row) {
        Corso corso = new Corso();
        corso.setNome(row.get("corso_nome"));
        int livelloCorso = Integer.parseInt(row.get("corso_livello"));
        corso.setLivello(livelloCorso);
        corso.setTotaleCfu(calcolaCfuTotali(livelloCorso));
        return corso;
    }

    /**
     * Crea un oggetto InsegnamentoOfferto da un oggetto da una Mappa di dati
     *
     * @param row Mappa
     * @return InsegnamentoOfferto
     */
    private InsegnamentoOfferto createInsegnamentoFromRow(Map<String, String> row) {
        InsegnamentoOfferto insegnamentoOfferto = new InsegnamentoOfferto();
        insegnamentoOfferto.setNome(row.get("insegnamento_nome"));
        insegnamentoOfferto.setAnno(Integer.parseInt(row.get("insegnamento_anno")));
        insegnamentoOfferto.setSemestre(Integer.parseInt(row.get("insegnamento_semestre")));
        insegnamentoOfferto.setCfu(Integer.parseInt(row.get("insegnamento_cfu")));
        insegnamentoOfferto.setOpzionale(row.get("insegnamento_opzionale").equals("1"));
        return insegnamentoOfferto;
    }

    /**
     * Crea un oggetto Docente da un oggetto da una Mappa di dati
     *
     * @param row Mappa
     * @return Docente
     */
    private Docente createDocenteFromRow(Map<String, String> row) {
        Docente docente = new Docente();
        docente.setNome(row.get("docente_nome"));
        docente.setCognome(row.get("docente_cognome"));
        docente.setEmail(row.get("docente_email"));
        return docente;
    }

    /**
     * Calcola i cfu totali di un corso di laurea. In generale non è molto accurato, ma per la realtà di nostro
     * interesse va più che bene.
     *
     * @param livello Livello del corso di laurea.
     * @return Cfu totali per conseguire la laurea.
     */
    private int calcolaCfuTotali(int livello) {
        int totale;
        if (livello == 1) {
            totale = 180;
        } else if (livello == 2) {
            totale = 120;
        } else {
            totale = 300;
        }
        return totale;
    }

    /**
     * Aggiunge ulteriori insegnamenti all'offerta, per far quadrare il conto dei cfu
     *
     * @param corso Oggetto Corso a cui aggiungere gli insegnamenti mancanti
     */
    private void fixEsamiMancanti(Corso corso) {
        InsegnamentoOfferto inglese = new InsegnamentoOfferto();
        inglese.setNome("Inglese")
                .setAnno(1)
                .setSemestre(1)
                .setCfu(3)
                .setOpzionale(false);
        InsegnamentoOfferto tirocinio = new InsegnamentoOfferto();
        tirocinio.setNome("Tirocinio")
                .setAnno(3)
                .setSemestre(2)
                .setCfu(3)
                .setOpzionale(false);
        InsegnamentoOfferto laurea = new InsegnamentoOfferto();
        laurea.setNome("Prova Finale")
                .setAnno(3)
                .setSemestre(2)
                .setOpzionale(false);
        if (corso.getLivello() == Corso.TRIENNALE) {
            corso.addInsegnamentoOfferto(inglese)
                    .addInsegnamentoOfferto(tirocinio)
                    .addInsegnamentoOfferto(laurea.setCfu(3));
        } else if (corso.getLivello() == Corso.MAGISTRALE) {
            corso.addInsegnamentoOfferto(tirocinio)
                    .addInsegnamentoOfferto(laurea.setCfu(12));
        } else {
            corso.addInsegnamentoOfferto(inglese)
                    .addInsegnamentoOfferto(tirocinio.setCfu(5))
                    .addInsegnamentoOfferto(laurea.setCfu(15));
        }
    }
}
