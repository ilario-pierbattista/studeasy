package org.oop.view.agenda;

import org.oop.view.ArrayListComboBoxModel;
import org.oop.model.dao.DocenteDAO;
import org.oop.model.entities.Docente;
import org.oop.view.AbstractForm;

import javax.swing.*;
import java.util.ArrayList;


abstract class AbstractFormAttivita extends AbstractForm {

    protected int idAttivita = 0;

    /**
     * Setta la lista dei docenti
     */
    protected void setListaDocenti(JComboBox<Docente> docentiBox) {
        ArrayList<Docente> docenti = new DocenteDAO().findAll();
        ArrayListComboBoxModel<Docente> model = new ArrayListComboBoxModel<Docente>(docenti);
        docentiBox.setModel(model);
        docentiBox.setSelectedIndex(0);
    }
}
