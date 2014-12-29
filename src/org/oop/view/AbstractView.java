package org.oop.view;

import org.oop.controller.ProfiloController;

/**
 * Created by toioski on 24/12/14.
 */
public class AbstractView<T> {
    private T instance;

    public T getInstance() {
        return this.instance; }
}
