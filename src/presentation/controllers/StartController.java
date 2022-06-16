package presentation.controllers;

import presentation.views.MainView;
import presentation.views.StartView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe que correspon al controlador de la vista principal del joc. S'encarrega de gestionar quines accions realitzar
 * quan l'usuari fa click a registrar-se o a logejar-se.
 *
 * @author Marc Postils
 * @version 23/04/2022
 */
public class StartController implements ActionListener {
    private final MainView mainView;

    /**
     * Constructor del StartController
     * @param mainView  mainView (pel CardLayout)
     */
    public StartController(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case StartView.BTN_REGISTER:
                mainView.showRegisterCard();
                break;
            case StartView.BTN_LOGIN:
                mainView.showLoginCard();
                break;
        }
    }
}
