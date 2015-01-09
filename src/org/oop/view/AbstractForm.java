package org.oop.view;


public abstract class AbstractForm extends AbstractView {

    /**
     * Verifica il contenuto dei campi del form e ne conferma la validità
     * @return
     */
    public abstract boolean isValid();

    /**
     * Chiude il Form
     */
    public void close() {
        frame.dispose();
    }
}
