package org.oop.controller;

import org.oop.model.entities.Insegnamento;
import org.oop.view.profilo.FormInsegnamento;
import org.oop.view.profilo.FormRegistrazione;
import org.oop.view.Mainframe;
import org.oop.view.profilo.Profilo;

import java.awt.event.ActionEvent;


public class ProfiloController {
    private static ProfiloController instance;
    private Profilo view;
    private FormInsegnamento formInsegnamento;

    public ProfiloController(Profilo view) {
        this.view = view;

        view.modificaProfiloButtonListener(new registraFormAction());
        view.modificaInsegnamentoButtonListener(new insegnamentoFormAction());
        instance = this;

        updateView();
    }

    public void updateView() {
        view.setInfoUtente(BaseController.getUtenteCorrente());
        view.setInfoLibretto(BaseController.getUtenteCorrente().getLibretto());
    }

    /**
     * Ritorna l'istanza attiva di ProfiloController
     * @return
     */
    public static ProfiloController getInstance() {
        return instance;
    }

    /**
     * Action per il submit del form
     * @Todo eliminare, non è utile
     *
    class submitInsegnamentoction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (formInsegnamento.isValid()){
                String nomeinsegnamento = formInsegnamento.getTextFieldnomeinsegnamento().getText();
                String cfuinsegnamento = formInsegnamento.getFormattedTextFieldCFU().getText();
                String cicloinsegnamento = formInsegnamento.getFormattedTextFieldCiclo().getText();
                String datainsegnamento = formInsegnamento.getFormattedTextFieldData().getText();
                String votoinsegnamento = formInsegnamento.getFormattedTextFieldVoto().getText();

                view.addElementTable(nomeinsegnamento, cfuinsegnamento, datainsegnamento, votoinsegnamento);

                FormInsegnamento.closeFrame();
            }
        }
    } */

    class insegnamentoFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            Insegnamento insegnamento = view.getInsegnamentoSelezionato();
            formInsegnamento = new FormInsegnamento();
            formInsegnamento.setInsegnamento(insegnamento);
            formInsegnamento.addAnnullaButtonListener(new closeFormInsegnamentoAction());
            formInsegnamento.setVisible(true);
        }
    }

    /**
     * Action per chiudere il form
     */
    class closeFormInsegnamentoAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            formInsegnamento.closeFrame();
        }
    }


    /**
     * Action per aprire il form di registrazione
     */
    class registraFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            FormRegistrazione form = new FormRegistrazione();
            FormRegistrazioneController formcontroller = new FormRegistrazioneController(form);

            Mainframe.refreshView();
        }
    }
}
