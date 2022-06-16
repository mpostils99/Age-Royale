package presentation.views;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que correspon al manager de totes les vistes del joc. S'encarrega de gestionar quina de les vistes mostrar en
 * cada moment.
 *
 * @author Marc Postils,Narcís Cisquella i David Tort
 * @version 23/04/2022
 */
public class MainView extends JFrame {
    private final CardLayout cardLayout;
    private final StartView startView;
    private final RegisterView registerView;
    private final LoginView loginView;
    private final MenuView menuView;
    private final OptionView optionView;
    private final GameView gameView;
    private final RankingView rankingView;
    private final HistoryView historyView;

    public static final String CARD_START = "CARD_START";
    public static final String CARD_REGISTER = "CARD_REGISTER";
    public static final String CARD_LOGIN = "CARD_LOGIN";
    public static final String CARD_MENU = "CARD_MENU";
    public static final String CARD_GAME = "CARD_GAME";
    public static final String CARD_RANKING = "CARD_RANKING";
    public static final String CARD_REPRODUCE = "CARD_REPRODUCE";
    public static final String CARD_OPTION = "CARD_OPTION";
    public static final String CARD_HISTORY = "CARD_HISTORY";

    /**
     * Constructor del MainView, rep totes les altres views per fer el CardLayout
     * @param startView Vista inicial.
     * @param registerView Vista del registre.
     * @param loginView Vista del login.
     * @param menuView Vista del menu.
     * @param optionView Vista de les opcions.
     * @param gameView Vista del joc.
     * @param rankingView Vista del ranking.
     * @param historyView Vista del historial.
     */
    public MainView(StartView startView, RegisterView registerView, LoginView loginView, MenuView menuView, OptionView optionView, GameView gameView, RankingView rankingView,HistoryView historyView) {
        this.cardLayout = new CardLayout();
        this.startView = startView;
        this.registerView = registerView;
        this.loginView = loginView;
        this.menuView = menuView;
        this.optionView = optionView;
        this.gameView = gameView;
        this.rankingView = rankingView;
        this.historyView = historyView;
        getContentPane().setLayout(cardLayout);
        configureWindow();
        configureStartMenu();
        configureRegister();
        configureLogin();
        configureMenu();
        configureOption();
        configureGame();
    }

    /**
     * Mètode que posa la vista visible.
     */
    public void start() {
        setVisible(true);
        showStartMenuCard();
    }

    /**
     * Mètode que afegeix la StartView.
     */
    public void configureStartMenu() {
        getContentPane().add(this.startView.configureView(), CARD_START);
    }

    /**
     * Mètode que afegeix la RegisterView.
     */
    public void configureRegister() {
        getContentPane().add(registerView.configureView(), CARD_REGISTER);
    }

    /**
     * Mètode que afegeix la LoginView.
     */
    public void configureLogin() {
        getContentPane().add(loginView.configureView(), CARD_LOGIN);
    }

    /**
     * Mètode que afegeix la MenuView.
     */
    public void configureMenu() {
        getContentPane().add(menuView.configureView(), CARD_MENU);
    }

    /**
     * Mètode que afegeix la OptionView.
     */
    public void configureOption() {
        getContentPane().add(optionView.configureView(), CARD_OPTION);
    }

    /**
     * Mètode que afegeix la GameView.
     */
    public void configureGame() {
        getContentPane().add(gameView.configureView(), CARD_GAME);
    }

    /**
     * Mètode que afegeix la RankingView.
     */
    public void configureRanking(String [][] data) {
        getContentPane().add(rankingView.configureView(data), CARD_RANKING);
    }

    /**
     * Mètode que afegeix la HistoryView.
     */
    public void configureHistory(String [][] data) {
        getContentPane().add(historyView.configureView(data), CARD_HISTORY);
    }

    /**
     * Mètode per configura la vista.
     */
    public void configureWindow() {
        setTitle("Age Royale");
        //T'ho fica a la màxima ressolucio possible.
        //setExtendedState(MAXIMIZED_BOTH);
        setSize(1920,1080);
        //pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Mètode que mostra la StartView.
     */
    public void showStartMenuCard() {
        this.cardLayout.show(getContentPane(), CARD_START);
    }

    /**
     * Mètode que mostra la RegisterView
     */
    public void showRegisterCard() {
        this.cardLayout.show(getContentPane(), CARD_REGISTER);
    }

    /**
     * Mètode que mostra la LoginView
     */
    public void showLoginCard() {
        this.cardLayout.show(getContentPane(), CARD_LOGIN);
    }

    /**
     * Mètode que mostra la MenuView
     */
    public void showMenuCard() {
        this.cardLayout.show(getContentPane(), CARD_MENU);
    }

    /**
     * Mètode que mostra la OptionView
     */
    public void showOptionCard() {
        this.cardLayout.show(getContentPane(), CARD_OPTION);
    }

    /**
     * Mètode que mostra la GameView
     */
    public void showGameCard() {
        this.cardLayout.show(getContentPane(), CARD_GAME);
    }

    /**
     * Mètode que mostra la RankingView
     */
    public void showRankingCard() {
        this.cardLayout.show(getContentPane(), CARD_RANKING);
    }

    /**
     * Mètode que mostra la ReproduceView
     */
    public void showReproduceCard() {
        this.cardLayout.show(getContentPane(), CARD_REPRODUCE);
    }

    /**
     * Mètode que mostra la HistoryView
     */
    public void showHistoryCard() {
        this.cardLayout.show(getContentPane(), CARD_HISTORY);
    }
}
