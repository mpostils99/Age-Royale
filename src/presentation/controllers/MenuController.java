package presentation.controllers;

import business.managers.UserManager;
import presentation.views.HistoryView;
import presentation.views.MainView;
import presentation.views.MenuView;
import presentation.views.RankingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe que te el controller del Menu per controlar les accions fetes a aquesta vista
 * @version 10/04/2022
 * @author Jose Ignacio Alonso, Narcis Cisquella, Marc Postils
 */
public class MenuController implements ActionListener {

    private MenuView menuView;
    private final MainView mainView;
    private final GameController gameController;
    private final RankingController rankingController;
    private final RankingView rankingView;
    private final HistoryController historyController;
    private final HistoryView historyView;
    private final UserManager userManager;

    /**
     * Constructor del MenuController
     * @param menuView          vista del Menu
     * @param mainView          mainView (pel CardLayout)
     * @param gameController    controlador del joc
     * @param userManager       userManager
     * @param rankingController controlador del ranquing
     * @param rankingView       vista del ranquing
     * @param historyController controlador de l'historial
     * @param historyView       vista de l'historial
     */
    public MenuController(MenuView menuView, MainView mainView, GameController gameController, UserManager userManager, RankingController rankingController,RankingView rankingView,HistoryController historyController, HistoryView historyView) {
        this.menuView = menuView;
        this.mainView = mainView;
        this.gameController = gameController;
        this.userManager = userManager;
        this.rankingController = rankingController;
        this.rankingView = rankingView;
        this.historyController = historyController;
        this.historyView = historyView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case MenuView.BTN_GAME:
                mainView.showGameCard();
                gameController.initGame();
                break;
            case MenuView.BTN_RANKING:
                mainView.showRankingCard();
                mainView.configureRanking(userManager.getRanking());
                rankingView.registerButtons(rankingController);
                break;
            case MenuView.BTN_REPRODUCE:
                mainView.showHistoryCard();
                String[][] historyPlayer = userManager.getHistory(userManager.getNom());
                mainView.configureHistory(historyPlayer);
                historyView.registerButtons(historyController);
                break;
            case MenuView.BTN_OPTION:
                mainView.showOptionCard();
                break;
        }
    }
}
