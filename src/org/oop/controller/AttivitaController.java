package org.oop.controller;

import org.oop.model.dao.AttivitaDAO;
import org.oop.model.dao.CicloDAO;
import org.oop.model.entities.*;
import org.oop.view.agenda.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Controller per i form di aggiunta di un'attività
 */
public class AttivitaController {
    private FormAttivitaEvento formAttivitaEvento;
    private FormAttivitaPeriodica formAttivitaPeriodica;
    private FormEsame formEsame;
    private AttivitaDAO attivitaDAO;
    private CicloDAO cicloDAO;

    public AttivitaController() {
        attivitaDAO = new AttivitaDAO();
        cicloDAO = new CicloDAO();
    }

    /**
     * Imposta i listeners alla vista
     *
     * @param attivitaView Form a cui impostare i listeners
     */
    public void setListenersToView(AttivitaView attivitaView) {
        attivitaView.addEditButtonListener(new EditButtonAction());
        attivitaView.addDeleteButtonListener(new DeleteButtonAction());
    }

    /**
     * Metodo che aggiorna la view
     */
    private void updateView() {
        AgendaController.getInstance().updateAttivita(AgendaController.getInstance().getView().getInsegnamentoSelected());
    }

    /**
     * Metodo per aprire il form corretto in base al bottone cliccato. Setta anche i listeners necessari per il
     * funzionamento.
     */
    public void openForm(String newActivityType) {
        if (newActivityType.equals(Attivita.PROGETTO) || newActivityType.equals(Attivita.SEMINARIO)) {
            createFormAttivitaEvento(null, newActivityType);
        } else if (newActivityType.equals(Attivita.LEZIONE) || newActivityType.equals(Attivita.LABORATORIO)) {
            createFormAttivitaPeriodica(null, newActivityType);
        } else {
            createFormEsame(null);
        }
    }

    /**
     * Metodo per aprire il form corretto di modifica in base all'attivita cliccata. Setta anche i listeners necessari
     * per il funzionamento.
     */
    public void openFormToModify(Attivita attivita) {
        String categoria = attivita.getCategoria();
        if (categoria.equals(Attivita.PROGETTO) || categoria.equals(Attivita.SEMINARIO)) {
            createFormAttivitaEvento((AttivitaEvento) attivita, null);
        } else if (categoria.equals(Attivita.LEZIONE) || categoria.equals(Attivita.LABORATORIO)) {
            createFormAttivitaPeriodica((AttivitaPeriodica) attivita, null);
        } else if (categoria.equals(Attivita.ESAME)) {
            createFormEsame((Esame) attivita);
        }
    }

    /**
     * Crea un form per la modifica o la creazione di un esame
     *
     * @param esame Oggetto da cui estrarre i dati, se disponibile
     */
    private void createFormEsame(Esame esame) {
        formEsame = new FormEsame();
        if (esame != null) {
            formEsame.setActivityname("Modifica esame");
            formEsame.fillForm(esame);
        } else {
            formEsame.setActivityname("Nuovo esame");
        }
        formEsame.addSubmitButtonListener(new SubmitFormEsameAction());
        formEsame.addCancelButtonListener(new CloseFormEsameAction());
        formEsame.addTipologiaProvaRadioListener(new SetTipologiaEsameAction());
    }

    /**
     * Crea un form per la modifica o la creazione dei un'attività periodica
     *
     * @param attivitaPeriodica Oggetto da cui estrarre i dati, se disponibile
     * @param categoriaAttivita Categoria dell'attività
     */
    private void createFormAttivitaPeriodica(AttivitaPeriodica attivitaPeriodica, String categoriaAttivita) {
        formAttivitaPeriodica = new FormAttivitaPeriodica();
        String categoria;
        String activityName;
        if (attivitaPeriodica == null && categoriaAttivita != null) {
            categoria = categoriaAttivita;
            activityName = categoria.equals(Attivita.LEZIONE) ? "Nuova lezione" : "Nuovo laboratorio";
        } else if (attivitaPeriodica != null) {
            categoria = attivitaPeriodica.getCategoria();
            activityName = categoria.equals(Attivita.LEZIONE) ? "Modifica lezione" : "Modifica laboratorio";
        } else {
            throw new IllegalArgumentException("attivitaPeriodica e categoriaAttivita non possono essere entrambi null");
        }
        formAttivitaPeriodica.setActivityname(activityName);
        if (attivitaPeriodica != null) {
            formAttivitaPeriodica.fillForm(attivitaPeriodica);
        }
        formAttivitaPeriodica.setCategoria(categoria);
        formAttivitaPeriodica.addSubmitButtonListener(new SubmitFormPeriodicaAction());
        formAttivitaPeriodica.addCancelButtonListener(new CloseFormPeriodicaAction());
    }

    /**
     * Crea un form per la creazione o la modifica di un'attività evento
     *
     * @param attivitaEvento    Oggetto da cui estrarre i dati, se disponibile
     * @param categoriaAttivita Categoria dell'attività
     */
    public void createFormAttivitaEvento(AttivitaEvento attivitaEvento, String categoriaAttivita) {
        formAttivitaEvento = new FormAttivitaEvento();
        String categoria;
        String activityName;
        if (attivitaEvento == null && categoriaAttivita != null) {
            categoria = categoriaAttivita;
            activityName = categoria.equals(Attivita.PROGETTO) ? "Nuovo progetto" : "Nuovo seminario";
        } else if (attivitaEvento != null) {
            categoria = attivitaEvento.getCategoria();
            activityName = categoria.equals(Attivita.PROGETTO) ? "Modifica progetto" : "Modifica seminario";
        } else {
            throw new IllegalArgumentException("attivitaEvento e categoriaAttivita non possono essere entrambi null");
        }
        formAttivitaEvento.setActivityname(activityName);
        if (attivitaEvento != null) {
            formAttivitaEvento.fillForm(attivitaEvento);
        }
        formAttivitaEvento.setCategoria(categoria);
        formAttivitaEvento.addSubmitButtonListener(new SubmitFormEventoAction());
        formAttivitaEvento.addCancelButtonListener(new CloseFormEventoAction());
    }

    /**
     * Salva l'attività creata nel database
     *
     * @param attivita Attività da salvare
     */
    private void apply(Attivita attivita) {
        Ciclo ciclo = AgendaView.getInstance().getCicloSelected();
        Insegnamento insegnamento = AgendaView.getInstance().getInsegnamentoSelected();
        if (attivita.getId() != 0) {
            attivitaDAO.update(attivita);
        } else {
            attivitaDAO.persist(attivita);
            insegnamento.addAttivita(attivita);
        }
        cicloDAO.update(ciclo);
        cicloDAO.flush();
        AgendaController.getInstance().refreshUtente();
    }

    /**
     * Action per modifica un'attività
     */
    class EditButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(e.getActionCommand());
            Attivita attivita = attivitaDAO.find(id);
            openFormToModify(attivita);
        }
    }

    /**
     * Action che permetta l'eliminazione di un'attivita
     */
    class DeleteButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(e.getActionCommand());
            Attivita attivita = attivitaDAO.find(id);
            attivitaDAO.remove(attivita);
            attivitaDAO.flush();
            AgendaController.getInstance().refreshUtente();
            updateView();
        }
    }

    /**
     * Action per il submit del form attivitaevento
     */
    class SubmitFormEventoAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (formAttivitaEvento.isValid()) {
                AttivitaEvento attivitaEvento = formAttivitaEvento.getNuovaAttivita();
                apply(attivitaEvento);
                updateView();
                formAttivitaEvento.closeFrame();
            }
        }
    }

    /**
     * Action per il submit del form attivita periodica
     */
    class SubmitFormPeriodicaAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (formAttivitaPeriodica.isValid()) {
                AttivitaPeriodica attivitaPeriodica = formAttivitaPeriodica.getNuovaAttivita();
                apply(attivitaPeriodica);
                updateView();
                formAttivitaPeriodica.closeFrame();
            }
        }
    }

    /**
     * Action per il submit del form attivita esame
     */
    class SubmitFormEsameAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (formEsame.isValid()) {
                Esame attivitaEsame = formEsame.getNuovaAttivita();
                apply(attivitaEsame);
                updateView();
                formEsame.closeFrame();
            }
        }
    }

    /**
     * Permette di gestire i radio button per la tipologia dell'esame
     */
    class SetTipologiaEsameAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AbstractButton button = (AbstractButton) e.getSource();
            manageRadioButtons(button);
        }

        private void manageRadioButtons(AbstractButton b) {
            formEsame.getOraleRadioButton().setSelected(false);
            formEsame.getScrittoRadioButton().setSelected(false);
            formEsame.getLaboratorioRadioButton().setSelected(false);

            if (b.getText().equals("Scritto")) {
                formEsame.getScrittoRadioButton().setSelected(true);
            } else if (b.getText().equals("Orale")) {
                formEsame.getOraleRadioButton().setSelected(true);
            } else {
                formEsame.getLaboratorioRadioButton().setSelected(true);
            }
        }
    }

    /**
     * Action per chiudere il form attivita evento
     */
    class CloseFormEventoAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            formAttivitaEvento.closeFrame();
        }
    }

    /**
     * Action per chiudere il form attivita periodica
     */
    class CloseFormPeriodicaAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            formAttivitaPeriodica.closeFrame();
        }
    }

    /**
     * Action per chiudere il form esame
     */
    class CloseFormEsameAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            formEsame.closeFrame();
        }
    }
}
