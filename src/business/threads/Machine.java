package business.threads;

import business.threads.Player;

/**
 * Classe on guardem tota la informació rellevant de la IA dins d'una partida, és a dir de l'oponent a l'usuari.
 * Com podem veure, guardem la vida de la torre principal, els diners i el número de tropes que té en el taulell.
 * @Authors: Marc Postils , Lluís Gumbau , Narcís Cisquella
 * @version 01/05/2022
 */
public class Machine extends Player {
    /**
    * Constructor de la classe maquina
    */
    public Machine() {
        super();
        this.player = false;
    }

    /**
     * Execució del thread, suma els diners de la màquina.
     */
    @Override
    public void run() {
        super.run();
    }
}
