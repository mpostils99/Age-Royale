package business.threads;

import business.managers.GameManager;
import business.troops.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe pirncipal per actualitzar la view de la partida, on podrem veure el moviment de les tropes
 * @version 15/05/22
 * @Authors: Marc Postils, Narcis Cisquella, Lluís Gumbau i David Tort
 */
public class InvokeIATroops implements Runnable {
    private final Board board;
    private final Machine machine;

    /**
     * Constructor de InvokeIATroops
     * @param board     Taulell del joc
     * @param machine   Jugador IA
     */
    public InvokeIATroops(Board board, Machine machine) {
        this.board = board;
        this.machine = machine;
    }

    /**
     * Comprova si la IA te suficients diners per invocar una tropa
     * @param troop Tropa que s'intenta invocar
     * @return  Retorna si te diners suficients o no
     */
    public boolean checkTroopCost(Troop troop) {
        boolean enoughMoney = false;

        if (machine.getMoney() >= troop.getCost()) {
            enoughMoney = true;
        }

        return enoughMoney;
    }

    /**
     * Funcio que invoca una tropa defensiva aleatoria
     * @param row   Fila on es vol invocar la tropa
     * @param col   Columna on es vol invocar la tropa
     */
    public void invokeRandomDefensiveTroop(int row, int col) {
        Random random = new Random();
        int num = random.nextInt(2) + 1;

        switch (num) {
            case 1:

                Mortar mortar = new Mortar(false, row, col, this.board);
                if(checkTroopCost(mortar)) {
                    this.machine.setMoney(this.machine.getMoney() - mortar.getCost());
                    this.board.placeTroop(row, col, mortar);
                    this.machine.incrementNumTroops();
                    new Thread(mortar).start();
                    //board.setNumMachineTroops(board.getNumMachineTroops() + 1);
                }

                break;
            case 2:
                Tesla tesla = new Tesla(false, row, col, this.board);
                if(checkTroopCost(tesla)) {
                    this.machine.setMoney(this.machine.getMoney() - tesla.getCost());
                    this.board.placeTroop(row, col, tesla);
                    this.machine.incrementNumTroops();
                    new Thread(tesla).start();
                    //board.setNumMachineTroops(board.getNumMachineTroops() + 1);
                }

                break;
        }
    }

    /**
     * Funcio que comprova que el Square davant la tropa estigui buit
     * @param row   Fila per comprovar
     * @param col   Columna per comprovar
     * @return  Retorna 1 si esta buida i -1 si no
     */
    public int checkTroopFront(int row, int col) {
        for (int i = row; i >= 0; i--) {
            if(board.checkEmptySquare(i, col)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Funcio que comprova que algun Square alrededor la tropa estigui buit
     */
    public void checkIfTroopAround() {
        for (int i = 0; i < Board.NUM_FILES/2; i++) {
            for (int j = 0; j < Board.NUM_COLUMNES; j++) {
                if (!board.checkEmptySquare(i, j)) {
                    if (board.checkPlayerTroop(i, j)) {
                        if(checkTroopFront(i, j) != -1) {
                            invokeRandomDefensiveTroop(checkTroopFront(i, j), j);
                        }
                    }
                }
            }
        }
    }

    /**
     * Funcio que invoca una tropa ofensiva aleatoria a una posicio aleatoria
     */
    public void invokeRandomOfensiveTroop (){
        Random random = new Random();
        ArrayList<Integer> rows = new ArrayList<>();
        ArrayList<Integer> cols = new ArrayList<>();
        int num = random.nextInt(2) + 1;
        for (int i = 0; i < Board.NUM_FILES/2; i++) {
            for (int j = 0; j < Board.NUM_COLUMNES; j++) {
                if (this.board.checkEmptySquare(i,j)){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        int size = random.nextInt(rows.size() - 1) + 1;
        switch (num) {
            case 1:

                Archer archer = new Archer(false, rows.get(size), cols.get(size), this.board);
                if(checkTroopCost(archer)) {
                    this.machine.setMoney(this.machine.getMoney() - archer.getCost());
                    this.board.placeTroop(rows.get(size), cols.get(size), archer);
                    this.machine.incrementNumTroops();
                    new Thread(archer).start();
                    //board.setNumMachineTroops(board.getNumMachineTroops() + 1);
                }

                break;
            case 2:
                Witch witch = new Witch(false, rows.get(size), cols.get(size), this.board);
                if(checkTroopCost(witch)) {
                    this.machine.setMoney(this.machine.getMoney() - witch.getCost());
                    this.board.placeTroop(rows.get(size), cols.get(size), witch);
                    this.machine.incrementNumTroops();
                    new Thread(witch).start();
                    //board.setNumMachineTroops(board.getNumMachineTroops() + 1);
                }

                break;
        }
    }

    /**
     * Execució del thread de la invocació de les tropes per part de la màquina
     */
    @Override
    public void run() {
        int counter = 0;

        while(!GameManager.gameFinished) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            counter++;
            if(counter == 4){
                invokeRandomOfensiveTroop();
                counter = 0;
            }

            checkIfTroopAround();

        }

        if(GameManager.gameFinished) {
            GameManager.stopThread();
        }
    }
}

