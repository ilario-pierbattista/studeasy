package org.oop.controller;

import org.oop.model.Libretto;
import org.oop.model.dao.InsegnamentoDAO;
import org.oop.model.dao.UtenteDAO;
import org.oop.model.entities.Insegnamento;
import org.oop.model.entities.Utente;
import org.oop.view.Mainframe;
import org.oop.view.profilo.FormInsegnamento;
import org.oop.view.profilo.FormLibretto;
import org.oop.view.profilo.FormRegistrazione;
import org.oop.view.profilo.Profilo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class ProfiloController {
    private static ProfiloController instance;
    private Profilo view;
    private FormInsegnamento formInsegnamento;
    private FormLibretto formLibretto;
    private InsegnamentoDAO insegnamentoDAO;
    private UtenteDAO utenteDAO;

    public ProfiloController(Profilo view) {
        this.view = view;

        view.modificaProfiloButtonListener(new registraFormAction());
        view.modificaInsegnamentoButtonListener(new insegnamentoFormAction());
        view.modificaLibrettoButtonListener(new ModificaLibrettoAction());
        instance = this;

        insegnamentoDAO = new InsegnamentoDAO();
        utenteDAO = new UtenteDAO();

        checkPianoStudi();
        updateView();
    }

    /**
     * Ritorna l'istanza attiva di ProfiloController
     *
     * @return
     */
    public static ProfiloController getInstance() {
        return instance;
    }

    /**
     * Controlla che l'utente abbia compilato il piano di studi
     */
    public void checkPianoStudi() {
        Utente utente = BaseController.getUtenteCorrente();
        if (utente.getLibretto().calcolaCFUPrevisti() < utente.getLibretto().getCorso().getTotaleCfu()) {
            // Seleziona la tab relativa al profilo
            BaseController.getMainframe().setSelectedTab(1);
            apriFormLibretto();
        }
    }

    /**
     * Aggiorna le informazioni nella vista
     */
    public void updateView() {
        view.setInfoUtente(BaseController.getUtenteCorrente());
        view.setInfoLibretto(BaseController.getUtenteCorrente().getLibretto());
    }

    private void apriFormLibretto() {
        formLibretto = new FormLibretto();
        formLibretto.setLibretto(BaseController.getUtenteCorrente().getLibretto());
        formLibretto.addSubmitButtonListener(new SubmitModificaLibrettoAction());
        formLibretto.addCloseButtonListener(new ChiudiModificaLibrettoAction());
        formLibretto.setVisible(true);
    }

    /**
     * Unisce i dati di una copia aggiornata del libretto con i dati gi&agrave; esistenti
     *
     * @param nuoviDati
     * @return Successo nel merge dei dai.
     */
    private boolean mergeLibretto(Libretto nuoviDati) {
        boolean success = false;
        final int ANNULLA = 0;
        final int CONFERMA = 1;
        final int CONFERMA_TUTTI = 2;
        int rispostaUtente = -1;
        Libretto libretto = BaseController.getUtenteCorrente().getLibretto();
        ArrayList<Insegnamento> nuoviInsegnamenti = new ArrayList<Insegnamento>(20);
        for (Insegnamento insegnamento : nuoviDati.getInsegnamentiOpzionali()) {
            if (rispostaUtente == CONFERMA) {
                rispostaUtente = -1;
            }
            Insegnamento insRegistrato = libretto.findInsegnamentoByInsegnamentoOfferto(
                    insegnamento.getInsegnamentoOfferto()
            );
            if (insRegistrato == null) {
                // L'insegnamento non era stato aggiunto al piano di studi.
                // Lo si aggiunge
                nuoviInsegnamenti.add(insegnamento);
            } else if (insRegistrato.esameSostenuto()) {
                if (rispostaUtente != CONFERMA_TUTTI && rispostaUtente != ANNULLA) {
                    rispostaUtente = showOptionDialog(insegnamento);
                }
                if (rispostaUtente == CONFERMA || rispostaUtente == CONFERMA_TUTTI) {
                    nuoviInsegnamenti.add(insegnamento);
                }
            }
        }

        // Reimposto gli insegnamenti obbigatori
        nuoviInsegnamenti.addAll(libretto.getInsegnamentiObbligatori());

        if (rispostaUtente != ANNULLA) {
            libretto.setInsegnamenti(nuoviInsegnamenti);
            success = true;
        }
        return success;
    }

    private int showOptionDialog(Insegnamento insegnamento) {
        Object[] opzioni = {
                "Annulla",
                "Conferma",
                "Conferma (Tutti)"
        };
        return JOptionPane.showOptionDialog(null,
                "Per l'insegnamento ".concat(insegnamento.getInsegnamentoOfferto().getNome())
                        .concat(" è già stato sostenuto l'esame. Cambiare comunque il piano di studi?"),
                "Conflitto nel cambiamento del piano di studi",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opzioni,
                opzioni[0]);
    }

    /**
     * Salva i cambiamenti apportati
     */
    private void saveChanges() {
        Utente utente = BaseController.getUtenteCorrente();
        Utente utenteSalvato = utenteDAO.find(utente.getMatricola());
        // Rimozione degli insegnamenti non più presenti
        System.out.println("Insegnamenti salvati " + utenteSalvato.getLibretto().getInsegnamentiOpzionali().toString());
        System.out.println("Insegnamenti nuovi " + utente.getLibretto().getInsegnamentiOpzionali().toString());
        for (Insegnamento ins : utenteSalvato.getLibretto().getInsegnamentiOpzionali()) {
            if (!utente.getLibretto().hasInsegnamentoOfferto(ins.getInsegnamentoOfferto())) {
                System.out.println(ins);
                insegnamentoDAO.remove(ins);
            }
        }
        // Salvataggio dei nuovi insegnamenti
        for (Insegnamento ins : utente.getLibretto().getInsegnamenti()) {
            if (ins.getId() == 0 || insegnamentoDAO.find(ins.getId()) == null) {
                insegnamentoDAO.persist(ins);
            }
        }
        utenteDAO.update(utente);
        utenteDAO.flush();
    }

    /**
     * Apertura del form per la registrazione di un utente ed avvio del controller che la gestisce
     */
    class registraFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            FormRegistrazione form = new FormRegistrazione();
            FormRegistrazioneController formcontroller = new FormRegistrazioneController(form);

            Mainframe.refreshView();
        }
    }

    /**
     * Apre il form per la modifica delle informazioni riguardanti ad un insegnamento
     * (voto e data di superamento)
     */
    class insegnamentoFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            Insegnamento insegnamento = view.getInsegnamentoSelezionato();
            formInsegnamento = new FormInsegnamento();
            formInsegnamento.setInsegnamento(insegnamento);
            formInsegnamento.addAnnullaButtonListener(new closeFormInsegnamentoAction());
            formInsegnamento.addConfermaButtonListener(new submitInsegnamentoFormAction());
            formInsegnamento.setVisible(true);
        }
    }

    /**
     * Gestione del submit del form per la modifica dei dati di un insegnamento
     */
    class submitInsegnamentoFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (formInsegnamento.isValid()) {
                insegnamentoDAO.update(formInsegnamento.salvaInsegnamento());
                insegnamentoDAO.flush();
                formInsegnamento.close();
                updateView();
            }
        }
    }

    /**
     * Chiusura del form per la modifica dei dati di un insegnamento
     */
    class closeFormInsegnamentoAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            formInsegnamento.close();
        }
    }

    /**
     * Apertura del form per la modifica del libretto
     */
    class ModificaLibrettoAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            apriFormLibretto();
        }
    }

    /**
     * Chiude il form per la modifica del libretto
     */
    class ChiudiModificaLibrettoAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            formLibretto.close();
        }
    }

    /**
     * Gestisce il salvataggio delle informazioni reperibili dal form
     * per la modifica del libretto
     */
    class SubmitModificaLibrettoAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean mergeSuccessful;
            if (formLibretto.isValid()) {
                mergeSuccessful = mergeLibretto(formLibretto.getNuovoLibretto());
                if (mergeSuccessful) {
                    saveChanges();
                }
                updateView();
                formLibretto.close();
            }
        }
    }
}
