package org.oop.controller;

import org.oop.view.segreteria.Segreteria;


public class SegreteriaController {

    public SegreteriaController(Segreteria view) {
        new ImmatricolazioneController(view.getImmatricolazione());
        new TirocinioController(view.getTirocinio());
        new TesiController(view.getTesi());
        new TasseController(view.getTasse());
        new IscrizioneController(view.getIscrizione());
    }
}
