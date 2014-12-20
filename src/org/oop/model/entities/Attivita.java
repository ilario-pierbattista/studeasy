/**
 * Created by Lvns on 12/20/14.
 */
package org.oop.model.entities;

import java.sql.Time;
import java.util.Date;

public class Attivita {

    private String aula;
    private Time oraInizio;
    private Time oraFine;
    private Date data;
    private Docente docente;
    //da decidere se i seguenti attributi saranno o meno stringhe
    private String ruoloDocente;
    private String categoria;
}
