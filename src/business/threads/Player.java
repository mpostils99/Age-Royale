package business.threads;
import business.managers.GameManager;
import presentation.listeners.PlayerListener;
/**
 * Classe on guardem tota la informació rellevant del player dins d'una partida, és a dir de l'usuari actualment logejat.
 * Com podem veure, guardem la vida de la torre principal, els diners i el número de tropes que té en el taulell.
 * @Authors: Marc Postils , Lluís Gumbau , Narcís Cisquella i David Tort
 * @version 01/05/2022
 */
public class Player implements Runnable{
    private final int live;
    private int money;
    private int numTroops;
    private boolean isPlaying;
    private PlayerListener playerListener;
    protected static final int INITIAL_LIVE = 300;
    protected static final int INITIAL_MONEY = 10;
    protected boolean player;

    /**
     * Constructor de Player
     * @param playerListener    interficie de PlayerListener
     */
    public Player(PlayerListener playerListener) {
        this.playerListener = playerListener;
        this.live = INITIAL_LIVE;
        this.money = INITIAL_MONEY;
        this.numTroops = 0;
        this.player = true;
        this.isPlaying = false;
    }

    /**
     * Segon constructor de Player
     * Aquest no te la interficie
     */
    public Player() {
        this.live = INITIAL_LIVE;
        this.money = INITIAL_MONEY;
        this.numTroops = 0;
    }

    /**
     * Getter de money
     * @return retorna els diners del jugador
     */
    public int getMoney() {
        return money;
    }

    /**
     * Setter de money
     * @param money Els diners que li volem setejar a l'usuari
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Funcio que incrementa el nombre de tropes de l'usuari
     */
    public void incrementNumTroops() {
        this.numTroops++;
    }

    /**
     * Execució del thread de player, sumem els diners i ho pasem a la vista
     */
    @Override
    public void run() {
        while (this.live > 0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

            if(GameManager.gameFinished) {
                this.money = INITIAL_MONEY;
                GameManager.stopThread();
            }

            if (this.money < 100) {
                this.money = money + 10;
            }

            if(player) {
                playerListener.printMoneyPlayer(money);
            }
        }
    }
}
