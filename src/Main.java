import business.managers.UserManager;
import presentation.controllers.*;
import presentation.views.*;

/**
 * Classe principal del nostre projecte
 * @Authors: Marc Postils, David Tort, Jose Ignacio Alonso, Narcis Cisquella i Lluis Gumbau
 * @Version: 29/05/2022
 * */
public class Main {
    public static  void main(String[] args) {
        StartView startView = new StartView();
        RegisterView registerView = new RegisterView();
        LoginView loginView = new LoginView();
        MenuView menuView = new MenuView();
        OptionView optionView = new OptionView();
        GameView gameView = new GameView();
        RankingView rankingView = new RankingView();
        HistoryView historyView = new HistoryView();
        MainView mainView = new MainView(startView, registerView, loginView, menuView, optionView, gameView,rankingView,historyView);
        StartController startController = new StartController(mainView);
        startView.registerButtons(startController);
        UserManager userManager = new UserManager();
        RegisterController registerController = new RegisterController(userManager, registerView, mainView);
        registerView.registerButtons(registerController);
        LoginController loginController = new LoginController(userManager, loginView, mainView);
        loginView.registerButtons(loginController);
        GameController gameController = new GameController(userManager, gameView, mainView, optionView);
        HistoryController historyController = new HistoryController(userManager, mainView,historyView);
        RankingController rankingController = new RankingController(userManager, rankingView, mainView,historyView,historyController);
        MenuController menuController = new MenuController(menuView, mainView ,gameController,userManager,rankingController,rankingView,historyController,historyView);
        menuView.registerButtons(menuController);
        OptionController optionController = new OptionController(userManager, optionView, mainView);
        optionView.registerButtons(optionController);
        gameView.registerButtons(gameController);
        gameView.addMouseListener(gameController);

        //Comença a renderitzar la start view.
        mainView.start();
    }
}
