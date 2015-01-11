package org.oop.general.exceptions;

import org.oop.general.Utils;


/**
 * Viene lanciata questa eccezione quando una risorsa non viene trovata
 */
public class RisorsaNonTrovata extends Exception {
    public RisorsaNonTrovata(String resPath) {
        super("La risorsa ".concat(Utils.singleQuotesToString(resPath)).concat(" non è stata trovata"));
    }
}
