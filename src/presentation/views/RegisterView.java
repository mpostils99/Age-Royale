package presentation.views;

import presentation.controllers.RegisterController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Classe que correspon a la vista on l'usuari es pot registrar. Disposa d'un botó per registrar-se i l'altre per tirar
 * endarrera.
 *
 * @version 23/04/22
 * @author David Tort, Marc Postils, Lluis Gumbau
 */

public class RegisterView extends JPanel {

    private JButton jbSubmit;
    private JButton jbBack;

    private JTextField registerName;
    private JTextField registerEmail;
    private JPasswordField registerPassword;
    private JPasswordField registerPassword2;
    public static final String BTN_SUBMIT = "BTN_SUBMIT";
    public static final String BTN_BACK = "BTN_BACK";

    /**
     * Constructor de la RegisterView
     */
    public RegisterView() {

    }

    public JLabel addElements(JLabel label, JLabel name, JLabel email, JLabel password, JLabel password2) {
        label.add(Box.createRigidArea(new Dimension(0,250)));
        label.add(name);
        label.add(Box.createRigidArea(new Dimension(0,10)));
        label.add(registerName);
        label.add(Box.createRigidArea(new Dimension(0,10)));
        label.add(email);
        label.add(Box.createRigidArea(new Dimension(0,10)));
        label.add(registerEmail);
        label.add(Box.createRigidArea(new Dimension(0,10)));
        label.add(password);
        label.add(Box.createRigidArea(new Dimension(0,10)));
        label.add(registerPassword);
        label.add(Box.createRigidArea(new Dimension(0,10)));
        label.add(password2);
        label.add(Box.createRigidArea(new Dimension(0,10)));
        label.add(registerPassword2);
        label.add(Box.createRigidArea(new Dimension(0,30)));
        label.add(jbSubmit);
        label.add(Box.createRigidArea(new Dimension(0,30)));
        label.add(jbBack);

        return label;
    }

    /**
     * Funcio que configura tota la view
     * @return Retorna un JPanel per afegir-lo a la cardLayout
     */
    public JLabel configureView() {
        ImageIcon background = new ImageIcon("assets/images/background_registerView.png");
        JLabel label = new JLabel("",background, JLabel.CENTER);
        label.setBounds(0,0,1920,1080);
        label.setLayout(new BoxLayout(label,BoxLayout.Y_AXIS));

        JLabel name = new JLabel("User Name: ");
        name.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        name.setAlignmentX(CENTER_ALIGNMENT);
        name.setForeground(Color.WHITE);

        JLabel password = new JLabel("Password: ");
        password.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        password.setAlignmentX(CENTER_ALIGNMENT);
        password.setForeground(Color.WHITE);

        JLabel password2 = new JLabel("Confirm Password: ");
        password2.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        password2.setAlignmentX(CENTER_ALIGNMENT);
        password2.setForeground(Color.WHITE);

        JLabel email = new JLabel("Email: ");
        email.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        email.setAlignmentX(CENTER_ALIGNMENT);
        email.setForeground(Color.WHITE);

        registerName = new JTextField();
        registerName.setAlignmentX(CENTER_ALIGNMENT);
        registerName.setFont(new Font("Helvetica", Font.ITALIC, 20));
        registerName.setMaximumSize(new Dimension(250,50));

        registerEmail = new JTextField();
        registerEmail.setAlignmentX(CENTER_ALIGNMENT);
        registerEmail.setFont(new Font("Helvetica", Font.ITALIC, 20));
        registerEmail.setMaximumSize(new Dimension(250,50));

        registerPassword = new JPasswordField();
        registerPassword.setAlignmentX(CENTER_ALIGNMENT);
        registerPassword.setMaximumSize(new Dimension(250,50));
        registerPassword.setFont(new Font("Helvetica", Font.ITALIC, 20));

        registerPassword2 = new JPasswordField();
        registerPassword2.setAlignmentX(CENTER_ALIGNMENT);
        registerPassword2.setMaximumSize(new Dimension(250,50));
        registerPassword2.setFont(new Font("Helvetica", Font.ITALIC, 20));

        jbSubmit = new JButton("Submit");
        jbSubmit.setActionCommand(BTN_SUBMIT);
        jbSubmit.setAlignmentX(CENTER_ALIGNMENT);
        jbSubmit.setFont(new Font("Helvetica", Font.ITALIC, 20));

        jbBack = new JButton("Back");
        jbBack.setActionCommand(BTN_BACK);
        jbBack.setAlignmentX(CENTER_ALIGNMENT);
        jbBack.setFont(new Font("Helvetica", Font.ITALIC, 20));

        label = addElements(label, name, email, password, password2);
        label.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        return label;
    }

    /**
     * Recull l'email que l'usuari hagi introduit
     * @return Retorna un string amb l'email introduit
     */
    public String getEmail() {
        return registerEmail.getText();
    }

    /**
     * Recull el username que l'usuari hagi introduit
     * @return Retorna un string amb el username introduit
     */
    public String getUsername() {
        return registerName.getText();
    }

    /**
     * Recull la contrasenya que l'usuari hagi introduit
     * @return Retorna un string amb la contrasenya introduida
     */
    public String getPassword(){
        return new String(registerPassword.getPassword());
    }

    /**
     * Recull la contrasenya repetida que l'usuari hagi introduit
     * @return Retorna un string amb la contrasenya repetida introduida
     */
    public String getPasswordConfirmation() {
        return new String(registerPassword2.getPassword());
    }

    /**
     * Funcio que mostra per pantalla si hi ha hagut algun error
     * @param errors Rep un ArrayList amb la lista d'errors que hi ha hagut
     */
    public void registerErrors(ArrayList<String> errors) {
        JOptionPane.showMessageDialog(this,errors,"Register error",JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Registra els botons de la vista
     * @param registerController El controlador de la View
     */
    public void registerButtons(RegisterController registerController){
        jbSubmit.setActionCommand(BTN_SUBMIT);
        jbSubmit.addActionListener(registerController);
        jbBack.setActionCommand(BTN_BACK);
        jbBack.addActionListener(registerController);
    }
}
