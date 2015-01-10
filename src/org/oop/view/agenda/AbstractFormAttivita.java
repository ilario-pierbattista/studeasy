package org.oop.view.agenda;

import org.oop.model.entities.Insegnamento;
import org.oop.view.ArrayListComboBoxModel;
import org.oop.model.dao.DocenteDAO;
import org.oop.model.entities.Docente;
import org.oop.view.AbstractForm;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


abstract class AbstractFormAttivita extends AbstractForm {

    protected int idAttivita = 0;

    /**
     * Setta la lista dei docenti
     */
    protected void setListaDocenti(JComboBox<Docente> docentiBox) {
        ArrayList<Docente> docenti = new DocenteDAO().findAll();
        Collections.sort(docenti, new DocenteComparator());
        ArrayListComboBoxModel<Docente> model = new ArrayListComboBoxModel<Docente>(docenti);
        docentiBox.setModel(model);
        docentiBox.setSelectedIndex(0);
    }

    protected void setDocenteSelected(JComboBox<Docente> docentiBox, Insegnamento insegnamento) {
        docentiBox.setSelectedItem(insegnamento.getInsegnamentoOfferto().getDocente());
    }

    class DocenteComparator implements Comparator<Docente> {
        @Override
        public int compare(Docente o1, Docente o2) {
            return o1.getCognome().compareTo(o2.getCognome());
        }
    }
}
