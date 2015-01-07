package org.oop.controller;

import org.oop.view.segreteria.Segreteria;


public class SegreteriaController {

    private Segreteria view;

    public SegreteriaController(Segreteria view) {
        new ImmatricolazioneController(view.getImmatricolazione());
        this.view = view;
    }

}
