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
import org.oop.view.profilo.ProfiloView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ProfiloController {
    private static ProfiloController instance;
    private ProfiloView view;
    private FormInsegnamento formInsegnamento;
    private FormLibretto formLibretto;
    private InsegnamentoDAO insegnamentoDAO;
    private UtenteDAO utenteDAO;

    public ProfiloController(ProfiloView view) {
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

        ArrayList<Insegnamento> insegnamentiSalvati = BaseController.getUtenteCorrente()
                .getLibretto().getInsegnamentiOpzionali();
        ArrayList<Insegnamento> nuovaSelezione = nuoviDati.getInsegnamentiOpzionali();

        ArrayList<Insegnamento> insegnamentiDaEliminare = diffArrayInsegnamenti(insegnamentiSalvati, nuovaSelezione);
        ArrayList<Insegnamento> insegnamentiDaAggiungere = diffArrayInsegnamenti(nuovaSelezione, insegnamentiSalvati);

        for (Insegnamento insegnamentoDaEliminare : insegnamentiDaEliminare) {
            if (rispostaUtente == CONFERMA) {
                rispostaUtente = -1;
            }
            if (insegnamentoDaEliminare.esameSostenuto() &&
                    rispostaUtente != CONFERMA_TUTTI &&
                    rispostaUtente != ANNULLA) {
                rispostaUtente = showOptionDialog(insegnamentoDaEliminare);
            }
        }

        if (rispostaUtente != ANNULLA) {
            success = true;
            boolean found;
            // Eliminazione degli insegnamenti di troppo
            for (Insegnamento daEliminare : insegnamentiDaEliminare) {
                found = false;
                for (int i = 0; i < insegnamentiSalvati.size() && !found; i++) {
                    if (daEliminare.getInsegnamentoOfferto().getId() == insegnamentiSalvati.get(i)
                            .getInsegnamentoOfferto().getId()) {
                        found = true;
                        insegnamentiSalvati.remove(i);
                    }
                }
            }
            // Aggiunta dei nuovi
            insegnamentiSalvati.addAll(insegnamentiDaAggiungere);
            // Reimpostazione degli insegnamenti obbligatori
            insegnamentiSalvati.addAll(BaseController.getUtenteCorrente().getLibretto().getInsegnamentiObbligatori());
            BaseController.getUtenteCorrente().getLibretto().setInsegnamenti(insegnamentiSalvati);
        }
        return success;
    }

    /**
     * Calcola la differenza tra il primo e il secondo ArrayList di insegnamenti: restituisce tutti gli elementi del
     * primo ArrayList che non sono presenti nel secondo
     *
     * @param a Primo ArrayList
     * @param b Secondo ArrayList
     * @return ArrayList differenza
     */
    private ArrayList<Insegnamento> diffArrayInsegnamenti(ArrayList<Insegnamento> a, ArrayList<Insegnamento> b) {
        ArrayList<Insegnamento> result = new ArrayList<Insegnamento>(5);
        boolean found;
        for (int i = 0; i < a.size(); i++) {
            found = false;
            for (int j = 0; j < b.size() && !found; j++) {
                found = a.get(i).getInsegnamentoOfferto().getId() == b.get(j).getInsegnamentoOfferto().getId();
            }
            if (!found) {
                result.add(a.get(i));
            }
        }
        return result;
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
        ArrayList<Insegnamento> insegnamentiNuovi = utente.getLibretto().getInsegnamenti();
        ArrayList<Insegnamento> insegnamentiVecchi = utenteSalvato.getLibretto().getInsegnamenti();
        ArrayList<Insegnamento> daEliminare = diffArrayInsegnamenti(insegnamentiVecchi, insegnamentiNuovi);
        ArrayList<Insegnamento> daAggiungere = diffArrayInsegnamenti(insegnamentiNuovi, insegnamentiVecchi);
        for (Insegnamento rem : daEliminare) {
            insegnamentoDAO.remove(rem);
        }
        for (Insegnamento add : daAggiungere) {
            insegnamentoDAO.persist(add);
        }
        utenteDAO.update(utente);
        utenteDAO.flush();
    }

    /**
     * Apertura del form per la registrazione di un utente ed avvio del controller che la gestisce
     */
    class registraFormAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            FormRegistrazione form = new FormRegistrazione();
            FormRegistrazioneController formcontroller = new FormRegistrazioneController(form);

            Mainframe.refreshView();
        }
    }

    /**
     * Apre il form per la modifica delle informazioni riguardanti ad un insegnamento (voto e data di superamento)
     */
    class insegnamentoFormAction implements ActionListener {
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
    class submitInsegnamentoFormAction implements ActionListener {
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
    class closeFormInsegnamentoAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            formInsegnamento.close();
        }
    }

    /**
     * Apertura del form per la modifica del libretto
     */
    class ModificaLibrettoAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            apriFormLibretto();
        }
    }

    /**
     * Chiude il form per la modifica del libretto
     */
    class ChiudiModificaLibrettoAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            formLibretto.close();
        }
    }

    /**
     * Gestisce il salvataggio delle informazioni reperibili dal form per la modifica del libretto
     */
    class SubmitModificaLibrettoAction implements ActionListener {
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
