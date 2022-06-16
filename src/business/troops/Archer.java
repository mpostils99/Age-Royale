package business.troops;

import business.threads.Board;

/**
 * Classe on guardem tota la informació referent amb la tropa ofensiva arquera, fa la funció de dps amb rang.
 * @Authors: Marc Postils i Lluís Gumbau
 * @version 01/05/2022
 */
public class Archer extends Troop {

    /**
     * Constructor de Archer
     * @param player    Si es del jugador o no
     * @param posX      Posicio a l'eix X de la tropa
     * @param posY      Posicio a l'eix Y de la tropa
     * @param board     El taulell de joc
     */
    public Archer(boolean player, int posX, int posY, Board board) {
        this.name = "Archer";
        this.live = 250;
        this.cost = 10;
        this.actionRange = 3;
        this.damage = 225;
        this.isOffensive = true;
        this.player = player;
        this.posX = posX;
        this.posY = posY;
        this.board = board;
        this.alive = true;
    }

    /**
     * Thread de la tropa arquera, intel·ligència artificial de la tropa
     */
    @Override
    public void run() {
        super.run();
    }

}
