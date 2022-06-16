package presentation.listeners;

import business.entities.Square;

/**
 * Interficie del PlayerListener que controla els diners i el taulell
 * @version 05/05/2022
 * @author Jose Ignacio Alonso, Marc Postils
 */
public interface PlayerListener {
    void printMoneyPlayer(int money);
    void updateBoard(Square[][] squares);
    void gameFinished(boolean playerWon);
    void printCurrentLiveMachineTroops(int live);
    void printCurrentLivePLayerTroops(int live);
    void incrementMoney();
}
