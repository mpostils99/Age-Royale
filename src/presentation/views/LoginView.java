package presentation.views;

import presentation.controllers.LoginController;

import javax.swing.*;
import java.awt.*;

/**
 * Classe que correspon a la vista on l'usuari es pot logejar. Disposa d'un botó per logejar-se i l'altre per tirar
 * endarrera.
 *
 * @version 23/04/22
 * @author David Tort, Marc Postils, Lluis Gumbau
 */
public class LoginView extends JPanel {
    private JButton jbLogin;
    private JButton jbBack;

    private JTextField loginUsernameEmail;
    private JPasswordField loginPassword;

    public static final String BTN_LOGIN = "BTN_LOGIN";
    public static final String BTN_BACK = "BTN_BACK";

    /**
     * Constructor del LoginView
     */
    public LoginView() {
    }

    /**
     * Funcio que configura tota la view
     * @return Retorna un JPanel per afegir-lo a la cardLayout
     */
    public JLabel configureView(){
        ImageIcon background = new ImageIcon("assets/images/background_registerView.png");
        JLabel label = new JLabel("",background, JLabel.CENTER);
        label.setBounds(0,0,1400,1020);
        label.setLayout(new BoxLayout(label,BoxLayout.Y_AXIS));

        JLabel name = new JLabel("Username or Email:");
        name.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        name.setAlignmentX(CENTER_ALIGNMENT);
        name.setForeground(Color.WHITE);

        JLabel password = new JLabel("Password: ");
        password.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        password.setAlignmentX(CENTER_ALIGNMENT);
        password.setForeground(Color.WHITE);

        loginUsernameEmail = new JTextField();
        loginUsernameEmail.setAlignmentX(CENTER_ALIGNMENT);
        loginUsernameEmail.setFont(new Font("Helvetica", Font.ITALIC, 20));
        loginUsernameEmail.setMaximumSize(new Dimension(250,50));

        loginPassword = new JPasswordField();
        loginPassword.setAlignmentX(CENTER_ALIGNMENT);
        loginPassword.setFont(new Font("Helvetica", Font.ITALIC, 20));
        loginPassword.setMaximumSize(new Dimension(250,50));

        jbLogin = new JButton("Login");
        jbLogin.setAlignmentX(CENTER_ALIGNMENT);
        jbLogin.setFont(new Font("K2D", Font.ITALIC, 20));

        jbBack = new JButton("Back");
        jbBack.setAlignmentX(CENTER_ALIGNMENT);
        jbBack.setFont(new Font("K2D", Font.ITALIC, 20));

        label.add(Box.createRigidArea(new Dimension(0,330)));
        label.add(name);
        label.add(Box.createRigidArea(new Dimension(0,10)));
        label.add(loginUsernameEmail);
        label.add(Box.createRigidArea(new Dimension(0,10)));
        label.add(password);
        label.add(Box.createRigidArea(new Dimension(0,10)));
        label.add(loginPassword);
        label.add(Box.createRigidArea(new Dimension(0,30)));
        label.add(jbLogin);
        label.add(Box.createRigidArea(new Dimension(0,30)));
        label.add(jbBack);

        return label;
    }

    /**
     * Funcio que retorna el username o email que hagi introduit l'usuari
     * @return Retorna el username o email que ha introduit l'usuari per fer login
     */
    public String getUsernameEmail() {
        return loginUsernameEmail.getText();
    }

    /**
     * Funcio que retorna la contrasenya que hagi introduit l'usuari
     * @return La contrasenya introduida
     */
    public String getPassword() {
        return new String(loginPassword.getPassword());
    }

    /**
     * Regista els botons perque reaccionin
     * @param loginController El controller de la view
     */
    public void registerButtons(LoginController loginController){
        jbLogin.setActionCommand(BTN_LOGIN);
        jbLogin.addActionListener(loginController);
        jbBack.setActionCommand(BTN_BACK);
        jbBack.addActionListener(loginController);
    }

    /**
     * Funcio que mostra el missatge d'error si l'usuari o la contrasenya son incorrectes
     */
    public void registerErrors() {
        JOptionPane.showMessageDialog(this,"Username, email and/or password are incorrect.","Login error",JOptionPane.ERROR_MESSAGE);
    }
}
