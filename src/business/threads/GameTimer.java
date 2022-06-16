package business.threads;

import business.managers.GameManager;
import presentation.listeners.TimeListener;

import java.sql.Time;
import java.text.DecimalFormat;

/**
 * Classe que controla el temps de cada partida
 *
 * @Authors: Marc Postils, Narcis Cisquella i Lluis Gumbau
 * @version: 13/05/2022
 */
public class GameTimer implements Runnable {
    private final TimeListener timeListener;

    /**
     * Constructor de GameTimer
     *
     * @param timeListener Interficie TimeListener
     */
    public GameTimer(TimeListener timeListener) {
        this.timeListener = timeListener;
    }

    /**
     * Execució del thread encarregat del temps de la partida
     */
    @Override
    public void run() {
        DecimalFormat format = new DecimalFormat("00");
        String actualTime;
        try {
            for (int minutes = 0; minutes < 60; minutes++) {
                for (int seconds = 0; seconds < 60; seconds++) {
                    //actualTimeCounter();
                    if (GameManager.gameFinished) {
                        GameManager.stopThread();
                    }
                    actualTime = format.format(minutes) + ":" + format.format(seconds);
                    timeListener.printCurrentTime(actualTime);
                    Thread.sleep(1000);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
