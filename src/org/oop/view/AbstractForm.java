package org.oop.view;


public abstract class AbstractForm extends View {

    /**
     * Verifica il contenuto dei campi del form e ne conferma la validità
     *
     * @return True se valido, False altrimenti
     */
    public abstract boolean isValid();

    /**
     * Chiude il Form
     */
    public void close() {
        frame.dispose();
    }
}
