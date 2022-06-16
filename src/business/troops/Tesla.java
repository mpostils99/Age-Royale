package business.troops;

import business.threads.Board;

/**
 * Classe on guardem tota la informació referent amb la tropa defensiva tesla.
 * Herència de la classe DefensiveTroop
 * @Authors: Marc Postils i Lluís Gumbau
 * @version 01/05/2022
 */
public class Tesla extends Troop {

    /**
     * Constructor de Tesla
     * @param player    Si es del jugador o no
     * @param posX      Posicio a l'eix X de la tropa
     * @param posY      Posicio a l'eix Y de la tropa
     * @param board     El taulell de joc
     */
    public Tesla(boolean player, int posX, int posY, Board board) {
        this.name = "Tesla";
        this.live = 450;
        this.cost = 50;
        this.actionRange = 3;
        this.damage = 125;
        this.isOffensive = false;
        this.player = player;
        this.posX = posX;
        this.posY = posY;
        this.board = board;
        this.alive = true;
    }

    /**
     * Thread de la tropa tesla, intel·ligència artificial de la tropa
     */
    @Override
    public void run() {
        super.run();
    }

}
