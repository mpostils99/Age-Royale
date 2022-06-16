package business.entities;

import business.troops.Troop;

/**
 * Classe que guarda la informacio de cada casella del taulell de joc
 * @version 02/05/2022
 * @author David Tort, Marc Postils
 */
public class Square {
    private final int x;
    private final int y;
    private Troop troop;

    /**
     * Constructor de Square
     * @param x     Posicio a l'eix X de la casella
     * @param y     Posicio a l'eix Y de la casella
     * @param troop Informacio de la tropa que hi hagi a la casella
     */
    public Square(int x, int y, Troop troop) {
        this.x = x;
        this.y = y;
        this.troop = troop;
    }

    /**
     * Funcio que revisa si la casella te una tropa o no
     * @return Retorna si hi ha tropa o no
     */
    public boolean isEmpty() {
        if(this.troop == null) {

            return true;
        } else {

            return false;
        }
    }

    /**
     * Funcio que comprova si la tropa de la casella es del jugador
     * @return Retorna si es una tropa del jugador o no
     */
    public boolean isPlayer() {
        if(this.troop.isPlayer()) {

            return true;
        } else {

            return false;
        }
    }

    /**
     * Getter de X
     * @return Retorna la posicio X
     */
    public int getX() {
        return x;
    }

    /**
     * Getter de Y
     * @return Retorna la posicio Y
     */
    public int getY() {
        return y;
    }

    /**
     * Getter de troop
     * @return Retorna la tropa que hi hagi a la casella
     */
    public Troop getTroop() {
        return troop;
    }

    /**
     * Setter de troop
     * @param troop La tropa que volem ficar a aquesta casella
     */
    public void setTroop(Troop troop) {
        this.troop = troop;
    }
}
