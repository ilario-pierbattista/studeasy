package org.oop.general.exceptions;

import org.oop.general.Utils;


public class RisorsaNonTrovata extends Exception {
    public RisorsaNonTrovata(String resPath) {
        super("La risorsa ".concat(Utils.singleQuotesToString(resPath)).concat(" non è stata trovata"));
    }
}
