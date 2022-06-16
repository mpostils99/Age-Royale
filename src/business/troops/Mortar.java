package business.troops;

import business.threads.Board;

/**
 * Classe on guardem tota la informació referent amb la tropa defensiva morter, caracteristiques diferents a la classe tesla.
 * Herència de la classe DefensiveTroop
 * @Authors: Marc Postils i Lluís Gumbau
 * @version 01/05/2022
 */
public class Mortar extends Troop {

    /**
     * Constructor de Mortar
     * @param player    Si es del jugador o no
     * @param posX      Posicio a l'eix X de la tropa
     * @param posY      Posicio a l'eix Y de la tropa
     * @param board     El taulell de joc
     */
    public Mortar(boolean player, int posX, int posY, Board board) {
        this.name = "Mortar";
        this.live = 500;
        this.cost = 40;
        this.actionRange = 5;
        this.damage = 100;
        this.isOffensive = false;
        this.player = player;
        this.posX = posX;
        this.posY = posY;
        this.board = board;
        this.alive = true;
    }

    /**
     * Thread de la tropa morter, intel·ligència artificial de la tropa
     */
    @Override
    public void run() {
        super.run();
    }
}
