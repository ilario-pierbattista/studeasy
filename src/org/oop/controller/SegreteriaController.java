package org.oop.controller;

import org.oop.view.segreteria.Segreteria;


/**
 * Gestisce la vista della segreteria
 */
public class SegreteriaController {

    public SegreteriaController(Segreteria view) {
        new ImmatricolazioneController(view.getImmatricolazione());
        new TirocinioController(view.getTirocinio());
        new TesiController(view.getTesi());
        new TasseController(view.getTasseView());
        new IscrizioneController(view.getIscrizioneView());
    }
}
