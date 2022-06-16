package presentation.controllers;

import business.entities.Square;
import business.managers.GameManager;
import business.managers.UserManager;
import business.troops.Troop;
import presentation.listeners.PlayerListener;
import presentation.listeners.TimeListener;
import presentation.views.GameView;
import presentation.views.JImagePanel;
import presentation.views.MainView;
import presentation.views.OptionView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * Authors: Marc Postils, Narcis Cisquella, David Tort, Jose Ignacio Alonso i Lluís Gumbau
 */
public class GameController implements ActionListener, MouseListener, PlayerListener, TimeListener {
    private final GameManager gameManager;
    private final UserManager userManager;
    private final GameView gameView;
    private final MainView mainView;
    private OptionView optionView;
    private boolean clickedTroopa;
    private boolean clickedCasella;
    private boolean clickedArcher;
    private boolean clickedWitch;
    private boolean clickedTesla;
    private boolean clickedMortar;

    public static final int NUM_FILES = 8;
    public static final int NUM_COLUMNES = 7;

    /**
     * Constructor principal de la classe
     * @param userManager
     * @param gameView
     * @param mainView
     * @param optionView
     */
    public GameController(UserManager userManager, GameView gameView, MainView mainView, OptionView optionView) {
        this.userManager = userManager;
        this.gameManager = new GameManager(this, this);
        this.gameView = gameView;
        this.mainView = mainView;
        this.optionView = optionView;
        this.mainView.addMouseListener(this);
        this.clickedTroopa = false;
        this.clickedCasella = false;
        this.clickedArcher = false;
        this.clickedWitch = false;
        this.clickedTesla = false;
        this.clickedMortar = false;
    }

    /**
     * Inicialitza el joc (temps, diners i tropes)
     */
    public void initGame() {
        GameManager.setGameFinished(false);
        gameManager.restartBoard();
        gameManager.initTroopMovement();
        gameManager.initBoard(this);
        gameManager.initTime();
        gameManager.initMoney();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case GameView.BTN_ARCHER:

                this.clickedTroopa = true;
                this.clickedCasella = false;
                this.clickedArcher = true;
                this.clickedWitch = false;
                this.clickedTesla = false;
                this.clickedMortar = false;

                break;

            case GameView.BTN_WITCH:

                this.clickedTroopa = true;
                this.clickedCasella = false;
                this.clickedArcher = false;
                this.clickedWitch = true;
                this.clickedTesla = false;
                this.clickedMortar = false;

                break;

            case GameView.BTN_TESLA:

                this.clickedTroopa = true;
                this.clickedCasella = false;
                this.clickedArcher = false;
                this.clickedWitch = false;
                this.clickedTesla = true;
                this.clickedMortar = false;

                break;

            case GameView.BTN_MORTAR:

                this.clickedTroopa = true;
                this.clickedCasella = false;
                this.clickedArcher = false;
                this.clickedWitch = false;
                this.clickedTesla = false;
                this.clickedMortar = true;

                break;

            case OptionView.BTN_LOGOUT:
                userManager.setUser("", "", "", "");
                GameManager.setGameFinished(true);
                mainView.showStartMenuCard();

                break;
        }
    }

    /**
     * Mètode encarregat de actualiutzar la lògica dels diners del jugador
     * @param money diners actuals del jugador
     */
    @Override
    public void printMoneyPlayer(int money) {
        gameView.printMoneyView(money);
    }

    /**
     * Mètode que s'encarrega d'actualitzar la vida de la IA.
     * @param live
     */
    public void printCurrentLiveMachineTroops(int live) {
        gameView.printCurrentLiveMachineTroops(live);
    }

    /**
     * Mètode encarregat d'actualitzar la lògica de la vida de la torre central del jugador
     * @param live la vida actual
     */
    public void printCurrentLivePLayerTroops(int live) {
        gameView.printCurrentLivePlayerTroops(live);
    }


    @Override
    public void incrementMoney() {
        gameManager.incrementMoney();
    }

    /**
     * Mètode que s'encarrega d'actualitzar el taulell.
     * @param squares Caselles del taulell.
     */
    @Override
    public void updateBoard(Square[][] squares) {
        int player = -1;
        int machine = -1;
        for (int i = 0; i < NUM_FILES; i++) {
            for (int j = 0; j < NUM_COLUMNES; j++) {
                if (squares[i][j].getTroop() != null) {
                    if (squares[i][j].getTroop().isPlayer()) {
                        player++;
                    } else {
                        machine++;
                    }
                    gameView.updateBoard(i, j, squares[i][j].getTroop().getName(), squares[i][j].getTroop().isPlayer());
                } else {
                    gameView.updateBoard(i, j, "Empty", true);
                }
            }
        }
        gameView.updateTroopsPlayer(player);
        gameView.updateTroopsMachine(machine);
    }

    /**
     * Mètode encarregat de la lògica quan la partida ha finalitzat
     * @param playerWon booleà que controla si el jugador és el guanyador de la partida
     */
    @Override
    public void gameFinished(boolean playerWon) {
        gameView.showWinner(playerWon);
        String gameName = gameView.saveGame();
        userManager.updateStatistics(playerWon);
        if(gameName != null) {
            userManager.saveGame(gameName, playerWon);
        }

        mainView.showMenuCard();
    }

    /**
     * Mètode que s'encarrega de la lògica del temps de la partida
     * @param time temps actual
     */
    @Override
    public void printCurrentTime(String time) {
        gameView.printCurrentTime(time);
    }

    /**
     * Mètode que s'encarrega de la lògica dels clicks dins de la partida dins la teva part del taulell
     * @param e mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();

        if (source instanceof JImagePanel && clickedTroopa) {
            String troopName = assignTroopName();
            // Si la casella està buida afegim la tropa.
            if (gameManager.checkEmptySquare(((JImagePanel) source).getName())) {
                Troop troop = gameManager.checkTroopCost(troopName, ((JImagePanel) source).getName());
                if (troop != null) {
                    gameManager.addTroop(((JImagePanel) source).getName(), troop);
                }
            }

            this.clickedCasella = true;
            this.clickedTroopa = false;
            this.clickedArcher = false;
            this.clickedWitch = false;
            this.clickedTesla = false;
            this.clickedMortar = false;
        }
    }

    /**
     * Selecciona una tropa quan es clica a la pantalla
     * @return Retorna un String amb la tropa seleccionada
     */
    public String assignTroopName() {
        String troopName = null;

        if (this.clickedArcher) {
            troopName = "Archer";
        }
        if (this.clickedWitch) {
            troopName = "Witch";
        }
        if (this.clickedTesla) {
            troopName = "Tesla";
        }
        if (this.clickedMortar) {
            troopName = "Mortar";
        }

        return troopName;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
