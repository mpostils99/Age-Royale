package presentation.controllers;

import business.managers.UserManager;
import presentation.views.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe que controla la HistoryView
 * @version 25/05/2022
 * @author David Tort, Jose Ignacio Alonso, Marc Postils
 */
public class HistoryController implements ActionListener {
    private final UserManager userManager;
    private final MainView mainView;
    private HistoryView historyView;

    /**
     * Constructor del HistoryController
     * @param userManager   userManager
     * @param mainView      mainView pel CardLayout
     * @param historyView   vista de l'historial
     */
    public HistoryController(UserManager userManager, MainView mainView, HistoryView historyView) {
        this.userManager = userManager;
        this.mainView = mainView;
        this.historyView = historyView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case HistoryView.BTN_BACK:
                mainView.showMenuCard();
                break;

            case OptionView.BTN_LOGOUT:
                userManager.setUser("","","","");
                mainView.showStartMenuCard();
                break;
        }
    }

}
