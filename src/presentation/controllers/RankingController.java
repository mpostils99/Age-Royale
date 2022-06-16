package presentation.controllers;

import business.entities.User;
import business.managers.UserManager;
import presentation.views.HistoryView;
import presentation.views.MainView;
import presentation.views.OptionView;
import presentation.views.RankingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe per controlar el ranking i les accions de la seva view
 * @version 15/05/2022
 * @author David Tort, Lluis Gumbau, Marc Postils
 */
public class RankingController implements ActionListener {
    private final UserManager userManager;
    private final RankingView rankingView;
    private final MainView mainView;
    private final HistoryView historyView;
    private final HistoryController historyController;

    /**
     * Constructor del RankingController
     * @param userManager       userManager
     * @param rankingView       vista del ranking
     * @param mainView          mainView (pel CardLayout)
     * @param historyView       vista de l'historial
     * @param historyController controlador de l'historial
     */
    public RankingController(UserManager userManager, RankingView rankingView, MainView mainView, HistoryView historyView, HistoryController historyController) {
        this.userManager = userManager;
        this.rankingView = rankingView;
        this.mainView = mainView;
        this.historyView = historyView;
        this.historyController = historyController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case RankingView.BTN_HISTORIAL:
                mainView.showHistoryCard();
                String[][] historyPlayer = userManager.getHistory(rankingView.getPlayer());
                mainView.configureHistory(historyPlayer);
                historyView.registerButtons(historyController);
                break;
            case RankingView.BTN_BACK:
                mainView.showMenuCard();
                break;

            case OptionView.BTN_LOGOUT:
                userManager.setUser("","","","");
                mainView.showStartMenuCard();
                break;
        }
    }
}
