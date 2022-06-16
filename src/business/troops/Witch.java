package business.troops;

import business.threads.Board;

/**
 * Classe on guardem tota la informació referent amb la tropa ofensiva bruixa, com podem veure, fa la funció de tanc.
 * Herència de la classe OffensiveTrop
 * @Authors: Marc Postils i Lluís Gumbau
 * @version 01/05/2022
 */
public class Witch extends Troop {

    /**
     * Constructor de Witch
     * @param player    Si es del jugador o no
     * @param posX      Posicio a l'eix X de la tropa
     * @param posY      Posicio a l'eix Y de la tropa
     * @param board     El taulell de joc
     */
    public Witch(boolean player, int posX, int posY, Board board) {
        this.name = "Witch";
        this.live = 350;
        this.cost = 40;
        this.actionRange = 1;
        this.damage = 250;
        this.isOffensive = true;
        this.player = player;
        this.posX = posX;
        this.posY = posY;
        this.board = board;
        this.alive = true;
    }


    /**
     * Thread de la tropa bruixa, intel·ligència artificial de la tropa
     */
    @Override
    public void run() {
        super.run();
    }
}
