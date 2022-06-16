package presentation.views;

import presentation.controllers.StartController;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que correspon a la vista principal del joc, un cop s'inicia l'aplicació apareix aquesta vista. L'usuari pot
 * seleccionar si registrars-se o fer login.
 *
 * @author Marc Postils
 * @version 23/04/2022
 */
public class StartView extends JPanel {
    private JButton jbRegister;
    private JButton jbLogin;

    public static final String BTN_REGISTER = "BTN_REGISTER";
    public static final String BTN_LOGIN = "BTN_LOGIN";

    /**
     * Constructor de la vista
     */
    public StartView() {

    }

    /**
     * Funcio que configura tota la view
     * @return Retorna un JPanel per afegir-lo a la cardLayout
     */
    public JLabel configureView() {
        ImageIcon background = new ImageIcon("assets/images/background_startView.png");

        JLabel label = new JLabel("",background, JLabel.CENTER);
        label.setBounds(0,0,1400,1020);
        label.setLayout(new BoxLayout(label,BoxLayout.Y_AXIS));

        jbRegister = new JButton("Register");
        jbRegister.setAlignmentX(CENTER_ALIGNMENT);
        jbRegister.setFont(new Font("K2D", Font.ITALIC, 47));

        jbLogin = new JButton("Login");
        jbLogin.setAlignmentX(CENTER_ALIGNMENT);
        jbLogin.setFont(new Font("K2D", Font.ITALIC, 47));

        label.add(Box.createRigidArea(new Dimension(0,350)));
        label.add(Box.createRigidArea(new Dimension(0,30)));
        label.add(jbRegister);
        label.add(Box.createRigidArea(new Dimension(0,30)));
        label.add(jbLogin);

        return label;
    }

    /**
     * Regista els botons perque reaccionin
     * @param startController El controller de la view
     */
    public void registerButtons(StartController startController) {
        jbRegister.setActionCommand(BTN_REGISTER);
        jbRegister.addActionListener(startController);
        jbLogin.setActionCommand(BTN_LOGIN);
        jbLogin.addActionListener(startController);
    }

}
