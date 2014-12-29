package org.oop.services.exceptions.importatore;


import org.oop.general.Utils;
import org.oop.services.exceptions.ImportatoreException;

public class DatiNonTrovati extends ImportatoreException {
    public DatiNonTrovati(String resourceName) {
        super("Nessuna risorsa trovata con il nome " + Utils.singleQuotesToString(resourceName) +
            ". Controllare la configurazione delle cartelle delle risorse.");
    }
}
