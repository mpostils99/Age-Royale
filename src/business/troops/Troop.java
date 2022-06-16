package business.troops;

import business.managers.GameManager;
import business.threads.Board;
import presentation.listeners.PlayerListener;

/**
 * Classe general per a tots els tipus de tropes. Classe pare de les tropes.
 * @Authors: Marc Postils, Lluís Gumbau, Narcis Cisquella, David Tort i Jose Ignacio Alonso
 * @versio 01/05/2022
 */
public class Troop implements Runnable {
    protected String name;
    protected int live;
    protected int cost;
    protected int actionRange;
    protected int damage;
    protected boolean isOffensive;
    protected boolean player;
    protected Board board;
    protected boolean alive;
    protected PlayerListener playerListener;
    protected int posX;
    protected int posY;

    /**
     * Getter de mame
     * @return  El nom de la tropa
     */
    public String getName() {
        return name;
    }

    /**
     * Getter de cost
     * @return  El preu de la tropa
     */
    public int getCost() {
        return cost;
    }

    /**
     * Comprova si la tropa de del jugador o no
     * @return Si es del jugador o no
     */
    public boolean isPlayer() {
        return player;
    }

    /**
     * Setter del jugador
     * @param player Un boolea per indicar que es del jugador
     */
    public void setPlayer(boolean player) {
        this.player = player;
    }

    /**
     * Getter de live
     * @return La vida de la tropa
     */
    public int getLive() {
        return live;
    }

    /**
     * Setter de live
     * @param live int amb la vida que li volem donar a la tropa
     */
    public void setLive(int live) {
        this.live = live;
    }

    /**
     * Getter de damage
     * @return  El atac de la tropa
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Mètode on que s'encarrega de si la partida ha arribat al final, és a dir quan alguna de les torres arriba a 0 de vida
     */
    public void manageGameOver() {
        if (this.name.equals("Tower")) {

            if (this.isPlayer() && this.live <= 0) {
                GameManager.gameFinished = true;
                playerListener.gameFinished(false);
                //System.out.println("PLAYER TOWER DEATH");
            }
            if (!this.isPlayer() && this.live <= 0) {
                GameManager.gameFinished = true;
                playerListener.gameFinished(true);
                //System.out.println("MACHINE TOWER DEATH");
            }
        }
    }

    /**
     * Mètode on controlem el moviment de les tropes ofensives del jugador
     */
    public void managePlayerOffensive() {
        // Si la tropa no es troba a la primera fila (fila on està la base del enemic)
        if (this.posX != 0) {
            // Si la casella de davant de la tropa està buida...
            if (this.board.checkEmptySquare(this.posX - 1, this.posY)) {
                // L'afegim a la casella de davant.
                this.board.addTroopToRowSquare(this.posX, this.posY, true);
                // I posem a null la tropa de la casella d'on ha partit.
                this.board.deleteOldTroop(this.posX, this.posY);
                this.posX--;
            } else {
                this.board.checkEnemyFront(this.posX, this.posY, this.posX - 1, this.posY, true);
            }
            // Si la tropa està a la primera fila i està a la part esquerra de la base enemiga...
        } else if (this.posY < 3) {
            // Si la casella de la dreta de la tropa està buida...
            if (this.board.checkEmptySquare(this.posX, this.posY + 1)) {
                this.board.addTroopToRightColSquare(this.posX, this.posY);
                this.board.deleteOldTroop(this.posX, this.posY);
                this.posY++;
            } else {
                this.board.checkEnemyFront(this.posX, this.posY, this.posX, this.posY + 1, true);
            }
            // Si la tropa està a la primera fila i està a la part dreta de la base enemiga...
        } else if (this.posY > 3) {
            if (this.board.checkEmptySquare(this.posX, this.posY - 1)) {
                this.board.addTroopToLeftColSquare(this.posX, this.posY);
                this.board.deleteOldTroop(this.posX, this.posY);
                this.posY--;
            } else {
                this.board.checkEnemyFront(this.posX, this.posY, this.posX, this.posY - 1, true);
            }
        }
    }

    /**
     * Mètode on controlem el moviment de les tropes ofensives de la màquina
     */
    public void manageMachineOffensive() {
        if (this.posX != Board.NUM_FILES - 1) {
            // Si la casella de davant de la tropa està buida...
            if (this.board.checkEmptySquare(this.posX + 1, this.posY)) {
                // L'afegim a la casella de davant.
                this.board.addTroopToRowSquare(this.posX, this.posY, false);
                // I posem a null la tropa de la casella d'on ha partit.
                this.board.deleteOldTroop(this.posX, this.posY);
                this.posX++;
            } else {
                this.board.checkEnemyFront(this.posX, this.posY, this.posX + 1, this.posY, false);
            }
            // Si la tropa està a la primera fila i està a la part esquerra de la base enemiga...
        } else if (this.posY < 3) {
            // Si la casella de la dreta de la tropa està buida...
            if (this.board.checkEmptySquare(this.posX, this.posY + 1)) {
                this.board.addTroopToRightColSquare(this.posX, this.posY);
                this.board.deleteOldTroop(this.posX, this.posY);
                this.posY++;
            } else {
                this.board.checkEnemyFront(this.posX, this.posY, this.posX, this.posY + 1, false);
            }
            // Si la tropa està a la primera fila i està a la part dreta de la base enemiga...
        } else if (this.posY > 3) {
            if (this.board.checkEmptySquare(this.posX, this.posY - 1)) {
                this.board.addTroopToLeftColSquare(this.posX, this.posY);
                this.board.deleteOldTroop(this.posX, this.posY);
                this.posY--;
            } else {
                this.board.checkEnemyFront(this.posX, this.posY, this.posX, this.posY - 1, false);
            }
        }
    }

    /**
     * Mètode on gestionem el moviment i atac de les topres
     */
    public void manageMovementAndAttack() {
        if (this.player && this.isOffensive) {
            managePlayerOffensive();
        } else if (!this.player && this.isOffensive) {
            manageMachineOffensive();
        }
    }

    /**
     * Mètode on controlem tota la lògica/intel·ligència artificial de les tropes
     */
    public void manageTroop() {
        manageGameOver();

        if (this.live > 0) {
            manageMovementAndAttack();
        } else {
            this.board.deleteOldTroop2(this.posX, this.posY);
            this.alive = false;
            /*
            if (this.player) {
                this.board.setIncrement(true);

            } else {

            }
             */
        }
    }

    /**
     * Execució del thread de les tropes.
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

            if(GameManager.gameFinished) {
                GameManager.stopThread();
            }

            if (alive) {
                manageTroop();
            }
        }
    }
}
