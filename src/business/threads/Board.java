package business.threads;

import business.entities.Square;
import business.managers.GameManager;
import business.troops.Troop;
import presentation.controllers.GameController;
import presentation.listeners.PlayerListener;

/**
 * Classe on guardem i actualitzem la logica del taulell.
 * @version: 15/05/22
 * @Authors: Marc Postils, Narcis Cisquella, Lluís Gumbau i David Tort
 */
public class Board implements Runnable {
    private Square[][] squares;
    private final PlayerListener playerListener;
    //private boolean increment;

    public static final int NUM_FILES = 8;
    public static final int NUM_COLUMNES = 7;

    /**
     * Constructor de Board
     * @param numFiles          nombre de files que tindra el taulell
     * @param numColumnes       nombre de columnes que tindra el taulell
     * @param playerListener    interficie del playerListener
     */
    public Board(int numFiles, int numColumnes, PlayerListener playerListener) {
        this.squares = new Square[numFiles][numColumnes];
        this.playerListener = playerListener;

        for (int i = 0; i < numFiles; i++) {
            for (int j = 0; j < numColumnes; j++) {
                this.squares[i][j] = new Square(i, j, null);
            }
        }
    }

    /**
     * Funcio que coloca una tropa al taulell
     * @param row       posicio respecte a l'eix X on es vol colocar la tropa
     * @param column    posicio respecte a l'eix Y on es vol colocar la tropa
     * @param troop     tropa que es vol colocar
     */
    public void placeTroop(int row, int column, Troop troop) {
        this.squares[row][column].setTroop(troop);
    }

    /**
     * Mètode on setejem totes les caselles de la board a null
     */
    public void restartBoard() {
        for (int i = 0; i < NUM_FILES; i++) {
            for (int j = 0; j < NUM_COLUMNES; j++) {
                this.squares[i][j].setTroop(null);
            }
        }
    }

    /**
     * Funcio que comprova si el Square esta buit
     * @param row   fila on es el Square
     * @param col   columna on es el Square
     * @return      retorna si esta buit o no
     */
    public boolean checkEmptySquare(int row, int col) {
        return this.squares[row][col].isEmpty();
    }

    /**
     * Funcio que comprova si la tropa es del jugador o no
     * @param row   fila on es la tropa
     * @param col   columna on es la tropa
     * @return      retorna si es del jugador o no
     */
    public boolean checkPlayerTroop(int row, int col) {
        return this.squares[row][col].isPlayer();
    }

    /**
     * Funcio que retorna la tropa que hi ha a un Square concret
     * @param row   fila on es el Square
     * @param col   columna on es el Square
     * @return      Retorna la tropa que hi hagi a aquest Square
     */
    public Troop getSquareTroop(int row, int col) {
        return this.squares[row][col].getTroop();
    }

    /**
     * Mou la tropa verticalment
     * @param row       Fila on es la tropa
     * @param col       Columna on es la tropa
     * @param player    Si la tropa es del jugador o IA (es moura cap a dalt si jugador, a baix si IA)
     */
    public synchronized void addTroopToRowSquare(int row, int col, boolean player) {
        if(player) {
            this.squares[row - 1][col].setTroop(getSquareTroop(row, col));
        } else {
            this.squares[row + 1][col].setTroop(getSquareTroop(row, col));
        }
    }

    /**
     * Mou la tropa cap a la dreta
     * @param row   Fila on es la tropa
     * @param col   Columna on es la tropa
     */
    public synchronized void addTroopToRightColSquare(int row, int col) {
        this.squares[row][col + 1].setTroop(getSquareTroop(row, col));
    }

    /**
     * Mou la tropa cap a l'esquerra
     * @param row   Fila on es la tropa
     * @param col   Columna on es la tropa
     */
    public synchronized void addTroopToLeftColSquare(int row, int col) {
        this.squares[row][col - 1].setTroop(getSquareTroop(row, col));
    }

    /**
     * Treu la tropa de la posicio anterior
     * @param row   Fila on es la tropa
     * @param col   Columna on es la tropa
     */
    public synchronized void deleteOldTroop(int row, int col) {
        this.squares[row][col].setTroop(null);
    }

    /**
     * Treu la tropa de la posicio anterior i verifica
     * @param row   Fila on es la tropa
     * @param col   Columna on es la tropa
     */
    public synchronized void deleteOldTroop2(int row, int col) {
        this.squares[row][col].setTroop(null);
    }

    /**
     * Funcio que calcula el combat de dues tropes enemigues
     * @param playerTroop   Informacio sobre la tropa del jugador
     * @param machineTroop  Informacio sobre la tropa de la IA
     */
    public synchronized void battleTroop(Troop playerTroop, Troop machineTroop) {
        playerTroop.setLive(playerTroop.getLive() - machineTroop.getDamage());
        machineTroop.setLive(machineTroop.getLive() - playerTroop.getDamage());
    }

    /**
     * Comprova si hi ha un enemic davant de la tropa
     * @param row       Fila de la tropa
     * @param col       Columna de la tropa
     * @param row2      Fila que es comprova
     * @param col2      Columna que es comprova
     * @param player    Si la tropa es d'un jugador o no
     */
    public void checkEnemyFront(int row, int col, int row2, int col2, boolean player) {

        if(!this.squares[row][col].isEmpty() && !this.squares[row2][col2].isEmpty()) {

            if (player) {
                if (!this.squares[row2][col2].isPlayer()) {
                    battleTroop(this.squares[row][col].getTroop(), this.squares[row2][col2].getTroop());
                }
            } else {
                if (this.squares[row2][col2].isPlayer()) {
                    battleTroop(this.squares[row][col].getTroop(), this.squares[row2][col2].getTroop());
                }
            }
        }
    }

    /**
     * Mètode per saber la vida de la torre de la IA
     * @return retornem la vida de la torre de la màquina
     */
    public int towerMachineLive(){
        Troop troop = this.getSquares()[0][GameManager.TOWER_COLUMN].getTroop();
        if(troop != null){
            return troop.getLive();
        }
        return 0;
    }

    /**
     * Mètode per saber la vida de la torre del nostre jugador
     * @return retornem la vida de la torre del jugador
     */
    public int towerPLayerLive(){
        Troop troop = this.getSquares()[NUM_FILES-1][GameManager.TOWER_COLUMN].getTroop();
        if(troop != null){
            return troop.getLive();
        }
        return 0;
    }

    /*
    public boolean isIncrement() {
        return increment;
    }

    public void setIncrement(boolean increment) {
        this.increment = increment;
    }
     */

    /**
     * Execució del thread del taulell
     */
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            if(GameManager.gameFinished) {
                GameManager.stopThread();
            }

            /*
            if (increment) {
               increment = false;
               playerListener.incrementMoney();
            }
             */

            playerListener.printCurrentLiveMachineTroops(towerMachineLive());
            playerListener.printCurrentLivePLayerTroops(towerPLayerLive());
            playerListener.updateBoard(this.squares);
        }
    }

    /**
     * Getter de Squares
     * @return Retorna una matriu de Squares
     */
    public Square[][] getSquares() {
        return squares;
    }
}
