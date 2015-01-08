package org.oop.controller;

import org.oop.model.dao.InsegnamentoDAO;
import org.oop.model.entities.Insegnamento;
import org.oop.view.profilo.FormInsegnamento;
import org.oop.view.profilo.FormLibretto;
import org.oop.view.profilo.FormRegistrazione;
import org.oop.view.Mainframe;
import org.oop.view.profilo.Profilo;

import java.awt.event.ActionEvent;


public class ProfiloController {
    private static ProfiloController instance;
    private Profilo view;
    private FormInsegnamento formInsegnamento;
    private FormLibretto formLibretto;
    private InsegnamentoDAO insegnamentoDAO;

    public ProfiloController(Profilo view) {
        this.view = view;

        view.modificaProfiloButtonListener(new registraFormAction());
        view.modificaInsegnamentoButtonListener(new insegnamentoFormAction());
        view.modificaLibrettoButtonListener(new ModificaLibrettoAction());
        instance = this;

        insegnamentoDAO = new InsegnamentoDAO();

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

    class submitInsegnamentoFormAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(formInsegnamento.isValid()) {
                insegnamentoDAO.update(formInsegnamento.salvaInsegnamento());
                insegnamentoDAO.flush();
                formInsegnamento.closeFrame();
                updateView();
            }
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

    class ModificaLibrettoAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            formLibretto = new FormLibretto();
            formLibretto.setLibretto(BaseController.getUtenteCorrente().getLibretto());
            formLibretto.setVisible(true);
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
