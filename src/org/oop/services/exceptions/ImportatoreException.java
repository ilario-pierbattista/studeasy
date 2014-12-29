package org.oop.services.exceptions;


import org.oop.services.Importatore;

public class ImportatoreException extends Exception {
    public ImportatoreException() {
        super();
    }

    public ImportatoreException(String str) {
        super(str);
    }
}