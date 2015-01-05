package org.oop.controller;

import org.oop.view.profilo.FormInsegnamento;
import org.oop.view.profilo.FormRegistrazione;
import org.oop.view.Mainframe;
import org.oop.view.profilo.Profilo;
import sun.text.resources.cldr.ia.FormatData_ia;

import java.awt.event.ActionEvent;


public class ProfiloController {
    private Profilo view;
    private FormInsegnamento formInsegnamento;

    public ProfiloController(Profilo view) {
        this.view = view;
        view.addTableListener(new addInsegnamentoAction());
        view.addDeletetableListener(new deleteInsegnamentoAction());
        view.insFormButtonListener(new formAction());
    }

    /**
     * Action per aprire il form di aggiunta di insegnamenti
     */
    class addInsegnamentoAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            formInsegnamento = new FormInsegnamento();
            formInsegnamento.addConfermaButtonListener(new submitInsegnamentoction());
            formInsegnamento.addAnnullaButtonListener(new closeFormInsegnamentoAction());

            Mainframe.refreshView();
        }
    }

    /**
     * Action per eliminare l'elemento della tabella selezionato
     */
    class deleteInsegnamentoAction extends AbstractAction{
        public void actionPerformed(ActionEvent e){
            view.DeleteElementTable();
        }
    }

    /**
     * Action per il submit del form
     */
    class submitInsegnamentoction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (formInsegnamento.isValid()){
                String nomeinsegnamento = formInsegnamento.getTextFieldnomeinsegnamento().getText();
                String cfuinsegnamento = formInsegnamento.getFormattedTextFieldCFU().getText();
                String cicloinsegnamento = formInsegnamento.getFormattedTextFieldCiclo().getText();
                String datainsegnamento = formInsegnamento.getFormattedTextFieldData().getText();
                String votoinsegnamento = formInsegnamento.getFormattedTextFieldVoto().getText();

                view.addElementTable(nomeinsegnamento, cicloinsegnamento, cfuinsegnamento, datainsegnamento, votoinsegnamento);

                FormInsegnamento.closeFrame();
            }
        }
    }

    /**
     * Action per chiudere il form
     */
    class closeFormInsegnamentoAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            FormInsegnamento.closeFrame();
        }
    }



    /**
     * Action per aprire il form di registrazione
     */
    class formAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            FormRegistrazione form = new FormRegistrazione();
            FormRegistrazioneController formcontroller = new FormRegistrazioneController(form);

            Mainframe.refreshView();
        }
    }
}
