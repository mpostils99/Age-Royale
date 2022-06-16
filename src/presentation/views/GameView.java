package presentation.views;

import presentation.controllers.GameController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Aquesta es la vista principal del joc
 * @version 09/05/22
 * @author Narcís Cisquella, DavidTort, Marc Postils
 */
public class GameView extends JPanel {
    private JPanel taulell;
    private JPanel taulellFons;
    private JPanel dades;
    private JPanel tropes;
    private JPanel logica;
    private JPanel jMoney;
    private JPanel jTime;
    private JPanel jPlayerTroops;
    private JPanel jFons;
    private JLabel moneyAmount;
    private JLabel currentTime;
    private JLabel txtNumTroops;
    private JLabel numTroopsPlayer;
    private JLabel numTroopsMachine;
    private JLabel txtLive;
    private JLabel LivePlayer;
    private JLabel LiveMachine;
    private JPanel grafics;
    private JPanel graficsLabel;
    private JPanel graficsLine;
    private int livePlayer;
    private int liveMachine;
    private int numPlayer;
    private int numMachine;
    private GameView lives;

    private JButton jbArcher;
    private JButton jbWitch;
    private JButton jbMortar;
    private JButton jbTesla;

    private JButton jbLogout;
    private int live2;

    private ArrayList<ArrayList<JImagePanel>> squares;

    public static final int NUM_FILES = 8;
    public static final int NUM_COLUMNES = 7;

    public static final String BTN_ARCHER = "BTN_ARCHER";
    public static final String BTN_WITCH = "BTN_WITCH";
    public static final String BTN_TESLA = "BTN_TESLA";
    public static final String BTN_MORTAR = "BTN_MORTAR";

    public static final String BTN_LOGOUT = "BTN_LOGOUT";

    /**
     * Constructor del GameView
     */
    public GameView() {
        squares = new ArrayList<>(NUM_FILES);
    }

    /**
     * Mètode que configura tota la view
     * @return Retorna un JPanel per afegir-lo a la cardLayout
     */
    public JPanel configureView() {
        jFons = new JPanel();
        jFons.setBackground(Color.GRAY);
        jFons.setLayout(new BorderLayout());
        jFons.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));
        this.jFons.setLayout(new GridLayout(1,2));

        this.taulellFons = taulell();
        this.jFons.add(this.taulellFons);

        this.dades = dades();
        this.tropes = tropes();
        this.grafics = grafics();

        this.logica = new JPanel();
        this.logica.setLayout(new GridLayout(3,1));
        this.logica.add(this.dades);
        this.logica.add(grafics);
        this.logica.add(this.tropes);

        this.jFons.add(this.logica);

        return this.jFons;
    }

    /**
     * Mètode que s'encarrega de configurar el taulell.
     * @return Retorna un JPanel per afegir-lo a la cardLayout.
     */
    public JPanel taulell(){
        this.taulellFons = new JPanel();
        this.taulellFons.setOpaque(false);
        this.taulellFons.setLayout(new BorderLayout());
        this.taulellFons.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        this.taulell = new JPanel();
        this.taulell.setOpaque(false);
        this.taulell.setLayout(new GridLayout(NUM_FILES, NUM_COLUMNES));

        for (int i = 0; i < NUM_FILES; i++) {
            ArrayList<JImagePanel> casellaColum = new ArrayList<>();
            for (int j = 0; j < NUM_COLUMNES; j++) {
                // Si és una torre...
                JImagePanel casella;
                if ((i == 0  || i == 7) && (j==3)){
                    casella = new JImagePanel("assets/images/Troops/playerBase.png");
                    if (i == 0){
                        casella.setBackground(Color.RED);
                    }else {
                        casella.setBackground(Color.BLUE);
                    }
                    casella.setBorder(BorderFactory.createBevelBorder(0, Color.BLACK, Color.BLACK));
                    casella.setPreferredSize(new Dimension(5, 5));
                    this.taulell.add(casella);

                }else{
                    casella = new JImagePanel("assets/images/white.png");
                    casella.setName((i+1)+"-"+(j+1));
                    casella.setBorder(BorderFactory.createBevelBorder(0, Color.BLACK, Color.BLACK));
                    casella.setPreferredSize(new Dimension(5, 5));
                    this.taulell.add(casella);
                }
                casellaColum.add(casella);
            }
            this.squares.add(casellaColum);
        }

        this.taulellFons.add(taulell, BorderLayout.CENTER);
        return this.taulellFons;
    }

    /**
     * Mètode que s'encarrega de configurar part del taulell.
     * @return Retorna un JPanel per afegir-lo a la cardLayout.
     */
    public JPanel dades(){
        this.dades = new JPanel();
        dades.setBackground(Color.WHITE);
        currentTime = new JLabel("CURRENT TIME: 00:00");
        currentTime.setFont(new Font("Arial", Font.ITALIC, 30));
        currentTime.setAlignmentX(CENTER_ALIGNMENT);
        jTime = new JPanel();
        jTime.setBackground(Color.WHITE);
        jTime.setLayout(new BoxLayout(jTime, BoxLayout.Y_AXIS));

        moneyAmount = new JLabel("MONEY: 10");
        moneyAmount.setFont(new Font("Arial", Font.ITALIC, 30));
        moneyAmount.setAlignmentX(CENTER_ALIGNMENT);
        jMoney = new JPanel();
        jMoney.setBackground(Color.WHITE);
        jMoney.setLayout(new BoxLayout(jMoney, BoxLayout.Y_AXIS));

        this.jTime.add(currentTime);
        this.jMoney.add(moneyAmount);
        this.dades.add(jTime);
        this.dades.add(jMoney);

        //Boton de Logout
        JLabel espacio = new JLabel("                   ");
        jbLogout = new JButton("Logout");
        jbLogout.setActionCommand(BTN_LOGOUT);
        jbLogout.setAlignmentX(LEFT_ALIGNMENT);
        jbLogout.setFont(new Font("K2D", Font.ITALIC, 20));
        this.dades.add(espacio);
        this.dades.add(jbLogout);

        return this.dades;
    }

    /**
     * Mètode que s'encarrega de configurar els botons de les tropes.
     * @return Retorna un JPanel per afegir-lo a la cardLayout.
     */
    public JPanel tropes(){
        this.tropes = new JPanel();
        this.tropes.setLayout(new GridLayout(2,2));

        jbArcher = new JButton("Archer");
        jbArcher.setAlignmentX(CENTER_ALIGNMENT);
        jbArcher.setFont(new Font("K2D", Font.ITALIC, 20));

        jbWitch = new JButton("Witch");
        jbWitch.setAlignmentX(CENTER_ALIGNMENT);
        jbWitch.setFont(new Font("K2D", Font.ITALIC, 20));

        jbMortar = new JButton("Mortar");
        jbMortar.setAlignmentX(CENTER_ALIGNMENT);
        jbMortar.setFont(new Font("K2D", Font.ITALIC, 20));

        jbTesla = new JButton("Tesla");
        jbTesla.setAlignmentX(CENTER_ALIGNMENT);
        jbTesla.setFont(new Font("K2D", Font.ITALIC, 20));

        this.tropes.add(jbArcher);
        this.tropes.add(jbWitch);
        this.tropes.add(jbMortar);
        this.tropes.add(jbTesla);

        return this.tropes;
    }

    /**
     * Mètode que s'encarrega de configurar els gràfics del taulell.
     * @return Retorna un JPanel per afegir-lo a la cardLayout.
     */
    public JPanel grafics(){
        this.grafics = new JPanel();
        this.graficsLabel = new JPanel();
        this.graficsLine = new JPanel();
        graficsLabel.setLayout(new GridLayout(6,1));
        graficsLine.setLayout(new BoxLayout(graficsLine, BoxLayout.Y_AXIS));
        graficsLabel.setBackground(Color.white);
        grafics.setLayout(new BorderLayout());
        grafics.setBackground(Color.white);
        graficsLabel.setBackground(Color.white);

        txtLive = new JLabel("Live");
        txtLive.setFont(new Font("Arial", Font.ITALIC, 30));
        txtLive.setAlignmentX(LEFT_ALIGNMENT);

        LivePlayer = new JLabel("1000");
        LivePlayer.setFont(new Font("Arial", Font.ITALIC, 30));

        LiveMachine = new JLabel("1000");
        LiveMachine.setFont(new Font("Arial", Font.ITALIC, 30));

        txtNumTroops = new JLabel("Troops");
        txtNumTroops.setFont(new Font("Arial", Font.ITALIC, 30));
        txtNumTroops.setAlignmentX(CENTER_ALIGNMENT);

        numTroopsPlayer = new JLabel("0");
        numTroopsPlayer.setFont(new Font("Arial", Font.ITALIC, 30));
        numTroopsPlayer.setAlignmentX(CENTER_ALIGNMENT);

        numTroopsMachine = new JLabel("0");
        numTroopsMachine.setFont(new Font("Arial", Font.ITALIC, 30));
        numTroopsMachine.setAlignmentX(CENTER_ALIGNMENT);

        jPlayerTroops = new JPanel();
        jPlayerTroops.setBackground(Color.WHITE);
        jPlayerTroops.setLayout(new BoxLayout(jPlayerTroops, BoxLayout.Y_AXIS));

        this.jPlayerTroops.add(numTroopsPlayer);
        this.jPlayerTroops.add(numTroopsMachine);

        this.lives = new GameView();
        lives.setBackground(Color.white);

        graficsLine.add(lives);
        graficsLabel.add(txtLive);
        graficsLabel.add(LivePlayer);
        graficsLabel.add(LiveMachine);
        graficsLabel.add(txtNumTroops);
        graficsLabel.add(numTroopsPlayer);
        graficsLabel.add(numTroopsMachine);
        grafics.add(graficsLine, BorderLayout.CENTER);
        grafics.add(graficsLabel,BorderLayout.EAST);

        return this.grafics;
    }


    /**
     * Funció per a dibuixar les gràfiques
     * @param g Gràfics.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setColor (Color.BLUE);
        g.fillRect (130, 70, livePlayer/2, 25);
        g.setColor (Color.black);
        g.drawRect (130, 70, 500, 25);

        g.setColor(Color.WHITE);
        g.setColor (Color.RED);
        g.fillRect (130, 100, liveMachine/2, 25);
        g.setColor (Color.black);
        g.drawRect (130, 100, 500, 25);

        g.setColor(Color.WHITE);
        g.setColor (Color.BLUE);
        g.fillRect (130, 200, numPlayer*100, 25);
        g.setColor (Color.black);
        g.drawRect (130, 200, 500, 25);

        g.setColor(Color.WHITE);
        g.setColor (Color.RED);
        g.fillRect (130, 230, numMachine*100, 25);
        g.setColor (Color.black);
        g.drawRect (130, 230, 500, 25);
    }

    /**
     * Funcio que printa el temps actual de partida
     * @param currentTime El temps actual de la partida
     */
    public void printCurrentTime(String currentTime){
        this.currentTime.setText("CURRENT TIME: " + currentTime);
        //System.out.println(money);
    }

    /**
     * Funció per dibuixar la gràfica de la vida del Player
     * @param live Vida del jugador.
     */
    public void moveLifePlayer(int live) {
        this.livePlayer=live;
        repaint();
    }

    /**
     * Funció per dibuixar la gràfica de la vida de la màquina
     * @param live Vida de la IA.
     */
    public void moveLifeMachine(int live) {
        this.liveMachine=live;
        repaint();
    }

    /**
     * Funció per dibuixar la gràfica de les tropes del Player
     * @param troops Número de tropes del jugador.
     */
    public void updateNumTroopsPlayer(int troops) {
        this.numPlayer = troops;
        repaint();
    }

    /**
     * Funció per dibuixar la gràfica de les tropes de la màquina
     * @param troops Número de tropes de la IA.
     */
    public void updateNumTroopsMachine(int troops) {
        this.numMachine = troops;
        repaint();
    }

    /**
     * Funció que ens indica el nombre de tropes que té el Player
     * @param live Vida actual del jugador.
     */
    public void printCurrentLivePlayerTroops(int live) {

        if(live < 0){
            this.live2=0;
        }else{
            this.live2=live;
        }
        lives.moveLifePlayer(live2);
        this.LivePlayer.setText(String.valueOf(live2));
    }

    /**
     * Funció que ens indica el nombre de tropes que té la màquina
     * @param live Vida actual del jugador.
     */
    public void printCurrentLiveMachineTroops(int live) {
        if(live < 0){
            this.live2=0;
        }else{
            this.live2=live;
        }

        lives.moveLifeMachine(live2);
        this.LiveMachine.setText(String.valueOf(live2));
    }

    /**
     * Funció que printa els diners actuals de partida
     * @param money Els diners actuals de la partida
     */
    public void printMoneyView(int money){
        moneyAmount.setText("MONEY: " + String.valueOf(money));
        //System.out.println(money);
    }

    /**
     * Funció que actulitza el nombre de tropes que té el Player
     * @param troops Número de tropes del jugador.
     */
    public void updateTroopsPlayer(int troops) {
        this.numTroopsPlayer.setText(String.valueOf(troops));
        numPlayer = troops;
        lives.updateNumTroopsPlayer(numPlayer);
    }

    /**
     * Funció que actulitza el nombre de tropes que té la màquina
     * @param troops Número de tropes de la màquina.
     */
    public void updateTroopsMachine(int troops) {
        this.numTroopsMachine.setText(String.valueOf(troops));
        numMachine=troops;
        lives.updateNumTroopsMachine(numMachine);
    }

    /**
     * Funcio que actualitza el taulell a cada moment
     * @param row           Fila del taulell a actualitzar
     * @param column        Columna del taulell a actualitzar
     * @param troopName     Tropa del taulell a actualitzar
     * @param isPlayer      Indica si la tropa es de un jugador o no amb blau o vermell
     */
    public void updateBoard(int row, int column, String troopName, boolean isPlayer) {

        switch (troopName) {
            case "Archer":
                this.squares.get(row).get(column).setBackgroundImage("assets/images/Troops/archerTroop.png");
                break;
            case "Witch":
                this.squares.get(row).get(column).setBackgroundImage("assets/images/Troops/witchTroop.png");
                break;
            case "Tesla":
                this.squares.get(row).get(column).setBackgroundImage("assets/images/Troops/teslaTroop.png");
                break;
            case "Mortar":
                this.squares.get(row).get(column).setBackgroundImage("assets/images/Troops/mortarTroop.png");
                break;
            case "Tower":
                this.squares.get(row).get(column).setBackgroundImage("assets/images/Troops/playerBase.png");
                break;
            case "Empty":
                this.squares.get(row).get(column).setBackgroundImage("assets/images/white.png");
                break;
        }

        if(isPlayer) {
            if(!troopName.equals("Empty")) {
                this.squares.get(row).get(column).setBackground(Color.BLUE);
            }
        } else {
            this.squares.get(row).get(column).setBackground(Color.RED);
        }

    }

    /**
     * Funció per a mostrar el guanyador de la partida
     * @param playerWon Booleà que indica si el jugador ha guanyat la partida.
     */
    public void showWinner(boolean playerWon) {
        if (playerWon) {
            JOptionPane.showMessageDialog(this,"YOU HAVE WON","The game is over",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,"YOU HAVE LOST","The game is over",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Funció per a guardar la partida amb el nom introduït per l'usuari
     * @return Retorna el nom de la partidaque ha introduit l'usuari.
     */
    public String saveGame() {
        String gameName = JOptionPane.showInputDialog("Enter the name of the game:");

        return gameName;
    }

    /**
     * Mètode en que li afegim un mouse listener a les caselles habilitades de l'usuari.
     * @param gameController Controlador del joc.
     */
    public void addMouseListener(GameController gameController) {
        for (int i = 0; i < NUM_FILES; i++) {
            if (i >= NUM_FILES/2) {
                for (int j = 0; j < NUM_COLUMNES; j++) {
                    if (i == NUM_FILES - 1 && j == 3) {
                    } else {
                        squares.get(i).get(j).addMouseListener(gameController);
                    }
                }
            }
        }
    }

    /**
     * Registra els botons de la vista
     * @param gameController El controlador del joc.
     */
    public void registerButtons(GameController gameController){
        jbArcher.setActionCommand(BTN_ARCHER);
        jbArcher.addActionListener(gameController);
        jbWitch.setActionCommand(BTN_WITCH);
        jbWitch.addActionListener(gameController);
        jbTesla.setActionCommand(BTN_TESLA);
        jbTesla.addActionListener(gameController);
        jbMortar.setActionCommand(BTN_MORTAR);
        jbMortar.addActionListener(gameController);
        jbLogout.setActionCommand(BTN_LOGOUT);
        jbLogout.addActionListener(gameController);
    }

}

