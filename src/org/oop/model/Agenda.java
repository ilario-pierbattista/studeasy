package org.oop.model;


import org.oop.model.entities.Ciclo;

import java.util.ArrayList;

public class Agenda {
    ArrayList<Ciclo> cicli;

    public Agenda() {
        cicli = new ArrayList<Ciclo>(3);
    }
}
