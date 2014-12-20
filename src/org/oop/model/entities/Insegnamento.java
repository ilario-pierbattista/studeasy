/**
 * Created by Lvns on 12/20/14.
 */
package org.oop.model.entities;

import java.util.Date;

public class Insegnamento extends InsegnamentoOfferto {

    private int voto;
    private boolean lode;
    private Date data;
    private Docente docente;
    //da decidere se i due seguenti attributi saranno una stringa o meno
    private String ruoloDocente;
    private String categoria;


    public Insegnamento(String name, int credits, int year, int semester, boolean optional, Docente teacher, int voto) {
        super(name, credits, year, semester, optional, teacher);

    }
}
