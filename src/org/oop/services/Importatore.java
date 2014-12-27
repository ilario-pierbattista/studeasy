package org.oop.services;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class Importatore {
    private List<String> records;

    public Importatore() {
        System.out.println(Importatore.class.getClassLoader().getResource("data/data.csv").toString());
    }
}
