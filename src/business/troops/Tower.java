package business.troops;

import business.threads.Board;
import presentation.listeners.PlayerListener;

/**
 * Classe on guardem tota la informació referent amb la tropa defensiva tesla.
 * Herència de la classe DefensiveTroop
 * @Author: Jose Ignacio Alonso
 * @version 01/05/2022
 */
public class Tower extends Troop {

    /**
     * Constructor de Tower
     * @param player    Si es del jugador o no
     * @param posX      Posicio a l'eix X de la tropa
     * @param posY      Posicio a l'eix Y de la tropa
     * @param board     El taulell de joc
     */
    public Tower(boolean player, int posX, int posY, Board board, PlayerListener playerListener) {
        this.name = "Tower";
        this.live = 1000;
        this.actionRange = 0;
        this.damage = 100;
        this.isOffensive = false;
        this.player = player;
        this.board = board;
        this.posX = posX;
        this.posY = posY;
        this.alive = true;
        this.playerListener = playerListener;
    }

    /**
     * Thread de la torre, intel·ligència artificial de la torre
     */
    @Override
    public void run() {
        super.run();
    }
}
