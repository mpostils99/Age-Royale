package presentation.views;

import presentation.controllers.MenuController;
import presentation.controllers.OptionController;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que mostra el menu principal
 * @version 10/04/2022
 * @author Marc Postils, LluísGumbau, David Tort, Narcís Cisquella, José Ignacio Alonso
 */
public class MenuView extends JPanel {
    private JButton jbGame;
    private JButton jbRanking;
    private JButton jbReproduce;
    private JButton jbOption;
    public static final String BTN_GAME = "BTN_GAME";
    public static final String BTN_RANKING = "BTN_RANKING";
    public static final String BTN_REPRODUCE = "BTN_REPRODUCE";
    public static final String BTN_OPTION = "BTN_OPTION";

    /**
     * Constructor del MenuView
     */
    public MenuView() {

    }

    /**
     * Mètode que configura la view.
     * @return Retorna un JLabel per a afegir al MainView
     */
    public JLabel configureView() {
        ImageIcon background = new ImageIcon("assets/images/background_MenuView.png");
        JLabel label = new JLabel("",background, JLabel.CENTER);
        label.setBounds(0,0,1920,1080);
        label.setLayout(new BoxLayout(label,BoxLayout.Y_AXIS));

        jbGame = new JButton("Game");
        jbGame.setActionCommand(BTN_GAME);
        jbGame.setAlignmentX(CENTER_ALIGNMENT);
        jbGame.setFont(new Font("K2D", Font.ITALIC, 47));

        jbRanking = new JButton("Ranking");
        jbRanking.setActionCommand(BTN_RANKING);
        jbRanking.setAlignmentX(CENTER_ALIGNMENT);
        jbRanking.setFont(new Font("K2D", Font.ITALIC, 47));

        jbReproduce = new JButton("Reproduce");
        jbReproduce.setActionCommand(BTN_REPRODUCE);
        jbReproduce.setAlignmentX(CENTER_ALIGNMENT);
        jbReproduce.setFont(new Font("Helvetica", Font.ITALIC, 47));

        jbOption = new JButton("Options");
        jbOption.setActionCommand(BTN_OPTION);
        jbOption.setAlignmentX(CENTER_ALIGNMENT);
        jbOption.setFont(new Font("Helvetica", Font.ITALIC, 47));

        label.add(Box.createRigidArea(new Dimension(0,250)));
        label.add(jbGame);
        label.add(Box.createRigidArea(new Dimension(0,30)));
        label.add(jbRanking);
        label.add(Box.createRigidArea(new Dimension(0,30)));
        label.add(jbReproduce);
        label.add(Box.createRigidArea(new Dimension(0,30)));
        label.add(jbOption);

        return label;
    }

    /**
     * Mètode que registra els botons de la view perque funcionin
     * @param menuController El controlador de la MenuView
     */
    public void registerButtons(MenuController menuController){
        jbGame.setActionCommand(BTN_GAME);
        jbGame.addActionListener(menuController);
        jbRanking.setActionCommand(BTN_RANKING);
        jbRanking.addActionListener(menuController);
        jbReproduce.setActionCommand(BTN_REPRODUCE);
        jbReproduce.addActionListener(menuController);
        jbOption.setActionCommand(BTN_OPTION);
        jbOption.addActionListener(menuController);
    }
}
