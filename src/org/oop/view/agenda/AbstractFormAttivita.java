package org.oop.view.agenda;

import org.oop.model.dao.DocenteDAO;
import org.oop.model.entities.Ciclo;
import org.oop.model.entities.Docente;
import org.oop.model.entities.Insegnamento;
import org.oop.view.AbstractForm;
import org.oop.view.ArrayListComboBoxModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;


abstract class AbstractFormAttivita extends AbstractForm {

    protected int idAttivita = 0;

    /**
     * Imposta la lista dei docenti
     */
    protected void setListaDocenti(JComboBox<Docente> docentiBox) {
        ArrayList<Docente> docenti = new DocenteDAO().findAll();
        Collections.sort(docenti, new DocenteComparator());
        ArrayListComboBoxModel<Docente> model = new ArrayListComboBoxModel<Docente>(docenti);
        docentiBox.setModel(model);
        docentiBox.setSelectedIndex(0);
    }

    /**
     * Controlla che la data sia compresa nel periodo del ciclo selezionato
     *
     * @param date Data
     * @return True, se la data è compresa nel periodo del ciclo selezionato, false altrimenti
     */
    protected boolean isDataValid(Date date) {
        Ciclo ciclo = AgendaView.getInstance().getCicloSelected();
        boolean valid = (date.compareTo(ciclo.getInizio()) >= 0) && (date.compareTo(ciclo.getFine()) <= 0);
        if (!valid) {
            JOptionPane.showMessageDialog(null, "Non è possibile aggiungere un'attività con una data al di fuori del periodo del ciclo");
        }
        return valid;
    }

    /**
     * Imposta la selezione nella lista docenti al docente predefinito dell'insegnamento
     *
     * @param docentiBox   Oggetto JComboBox per i docenti
     * @param insegnamento Insegnamento da cui impostare il docente
     */
    protected void setDocenteSelected(JComboBox<Docente> docentiBox, Insegnamento insegnamento) {
        docentiBox.setSelectedItem(insegnamento.getInsegnamentoOfferto().getDocente());
    }

    /**
     * Implementazione del comparatore per l'oggetto docente
     */
    private class DocenteComparator implements Comparator<Docente> {
        @Override
        public int compare(Docente o1, Docente o2) {
            return o1.getCognome().compareTo(o2.getCognome());
        }
    }
}
