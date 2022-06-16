package business.managers;

import business.threads.*;
import business.troops.*;
import presentation.listeners.PlayerListener;
import presentation.listeners.TimeListener;

import java.util.ArrayList;

/**
 * Aquesta classe és l'encarregada de gestionar el joc
 * @version 15/05/22
 * @author Narcís Cisquella, Marc Postils, Lluis Gumbau i David Tort
 */
public class GameManager {
    private final GameTimer gameTimer;
    private final Player player;
    private final Machine machine;
    private final Board board;
    private final InvokeIATroops invokeIATroops;
    private static final ArrayList<Thread> threads = new ArrayList<>();

    public static boolean gameFinished;
    // És 3
    public static final int TOWER_COLUMN = (int) Math.floor(Board.NUM_COLUMNES / 2);

    /**
     * Constructor principal de la classe
     * @param playerListener    un cop tenim la informació del model a la vista
     * @param timeListener      passem el temps del model a la vista
     */
    public GameManager(PlayerListener playerListener, TimeListener timeListener) {
        this.gameTimer = new GameTimer(timeListener);
        this.player = new Player(playerListener);
        this.machine = new Machine();
        this.gameFinished = false;
        this.board = new Board(Board.NUM_FILES, Board.NUM_COLUMNES, playerListener);
        this.invokeIATroops = new InvokeIATroops(this.board, this.machine);
        //initBoard(playerListener);
    }

    /**
     * Inicialitzem els thread de temps a 0
     */
    public void initTime() {
        Thread time = new Thread(gameTimer);
        time.start();
        threads.add(time);
    }

    /**
     * Inicialitzem els diners de la partida a 0, tant de la màquina com del jugador
     */
    public void initMoney() {
        Thread money = new Thread(player);
        money.start();
        threads.add(money);
        Thread moneyMachine = new Thread(machine);
        moneyMachine.start();
        threads.add(moneyMachine);

    }

    /**
     * Inicialitzem el taulell de la partida
     * @param playerListener per saber si la vida de la torre és 0, s'ha acabat la partida
     */
    public void initBoard(PlayerListener playerListener) {
        // Posem les 2 bases
        Tower towerPlayer = new Tower(false,0,TOWER_COLUMN, board, playerListener);
        Tower towerMachine = new Tower(true,Board.NUM_FILES - 1,TOWER_COLUMN, board, playerListener);
        this.board.getSquares()[0][GameManager.TOWER_COLUMN].setTroop(towerPlayer);
        this.board.getSquares()[Board.NUM_FILES - 1][GameManager.TOWER_COLUMN].setTroop(towerMachine);
        Thread tP = new Thread(towerPlayer);
        tP.start();
        threads.add(tP);
        Thread tM = new Thread(towerMachine);
        tM.start();
        threads.add(tP);
    }

    /**
     * Inicia el thread de les tropes
     */
    public void initTroopMovement(){
        Thread tB = new Thread(board);
        tB.start();
        threads.add(tB);
        Thread inv = new Thread(invokeIATroops);
        inv.start();
        threads.add(inv);
    }

    /**
     * Funcio que comprova si una determinada casella esta buida o no
     * @param position  Posicio que volem comprovar
     * @return  Retorna si la casella en qüestio està buida o no
     */
    public boolean checkEmptySquare(String position) {
        int row = getRow(position);
        int column = getColumn(position);

        if(board.checkEmptySquare(row, column)) {

            return true;
        } else {

            return false;
        }
    }

    public void incrementMoney() {
        this.player.setMoney(this.player.getMoney() + 1);
    }

    /**
     * Funcio que rep una posicio sencera y agafa només la coordenada X
     * @param position Posicio sencera (coordinades X i Y)
     * @return Retorna la fila a la que està la posicio (eix X)
     */
    public int getRow(String position) {
        String parts[] = position.split("-");

        return Integer.parseInt(parts[0]) - 1;
    }

    /**
     * Funcio que rep una posicio sencera y agafa només la coordenada Y
     * @param position Posicio sencera (coordinades X i Y)
     * @return Retorna la columna a la que està la posicio (eix Y)
     */
    public int getColumn(String position) {
        String parts[] = position.split("-");

        return Integer.parseInt(parts[1]) - 1;
    }

    /**
     * Funcio que afegeix tropes al taulell
     * @param position La posicio en la que colocarem la tropa
     * @param troop La tropa que volem invocar
     */
    public synchronized void addTroop(String position, Troop troop) {
        int row = getRow(position);
        int column = getColumn(position);

        this.board.placeTroop(row, column, troop);
        this.player.incrementNumTroops();

        Thread troop2 = new Thread(troop);
        troop2.start();
        threads.add(troop2);
    }

    /**
     * Mètode que finalitza l'execució del thread, al finalitzar la partida o al fer logout
     */
    public static void stopThread() {
        for (int i = 0; i < threads.size(); i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Funcio que mira si hi ha suficients diners per invocar la tropa
     * @param troop La tropa que es vol invocar
     * @return Retorna si hi ha suficients diners o no
     */
    public boolean checkMoney(Troop troop) {
        boolean enoughMoney = false;

        if (player.getMoney() >= troop.getCost()) {
            enoughMoney = true;
        }

        return enoughMoney;
    }

    /**
     * Funcio que fa a l'usuari pagar el cost de la tropa per invocar-la
     * @param troopName el tipus de tropa que es vol invocar
     * @param position  la posicio on es vol invocar la tropa
     * @return  retorna la tropa sencera que es vol invocar
     */
    public Troop checkTroopCost(String troopName, String position) {
        int row = getRow(position);
        int column = getColumn(position);

        switch (troopName) {
            case "Archer":
                Archer archer = new Archer(true, row, column, this.board);
                if(checkMoney(archer)) {
                    this.player.setMoney(this.player.getMoney() - archer.getCost());

                    return archer;
                } else {
                    return null;
                }

            case "Witch":
                Witch witch = new Witch(true, row, column, this.board);
                if(checkMoney(witch)) {
                    this.player.setMoney(this.player.getMoney() - witch.getCost());

                    return witch;
                } else {
                    return null;
                }

            case "Tesla":
                Tesla tesla = new Tesla(true, row, column, this.board);
                if(checkMoney(tesla)) {
                    this.player.setMoney(this.player.getMoney() - tesla.getCost());

                    return tesla;
                } else {
                    return null;
                }

            case "Mortar":
                Mortar mortar = new Mortar(true, row, column, this.board);
                if(checkMoney(mortar)) {
                    this.player.setMoney(this.player.getMoney() - mortar.getCost());

                    return mortar;
                } else {
                    return null;
                }
        }

        return null;
    }

    /**
     * Getter del player
     * @return Retorna el player
     */
    public Player getPlayer() {
        return player;
    }

    public static void setGameFinished(boolean gameFinished) {
        GameManager.gameFinished = gameFinished;
    }

    public void restartBoard() {
        board.restartBoard();
    }
}
