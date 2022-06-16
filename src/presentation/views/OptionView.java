package presentation.views;

import presentation.controllers.OptionController;

import javax.swing.*;
import java.awt.*;
/**
 * Classe que correspon a la vista on l'usuari pot fer logout o delete. Disposa d'un botó per logout
 * un per delete i un per tornar a la vista del menu del joc
 *
 * @version 23/04/22
 * @author Narcis Cisquella, Marc Postils
 */
public class OptionView extends JPanel {
    private JButton jbLogout;
    private JButton jbDeleteAcc;
    private JButton jbBack;
    public static final String BTN_DELETE = "BTN_DELETE";
    public static final String BTN_LOGOUT = "BTN_LOGOUT";
    public static final String BTN_BACK = "BTN_BACK";

    /**
     * Constructor de la OptionView
     */
    public OptionView() {

    }

    /**
     * Funcio que configura la vista
     * @return Retorna un JLabel per afegir-lo a la MainView.
     */
    public JLabel configureView() {
        ImageIcon background = new ImageIcon("assets/images/background_registerView.png");
        JLabel label = new JLabel("",background, JLabel.CENTER);
        label.setBounds(0,0,1920,1080);
        label.setLayout(new BoxLayout(label,BoxLayout.Y_AXIS));

        //Boton de Logout
        jbLogout = new JButton("Logout");
        jbLogout.setActionCommand(BTN_LOGOUT);
        jbLogout.setAlignmentX(CENTER_ALIGNMENT);
        jbLogout.setFont(new Font("K2D", Font.ITALIC, 47));

        //Boton de Delete
        jbDeleteAcc = new JButton("Delete Account");
        jbDeleteAcc.setActionCommand(BTN_DELETE);
        jbDeleteAcc.setAlignmentX(CENTER_ALIGNMENT);
        jbDeleteAcc.setFont(new Font("K2D", Font.ITALIC, 47));

        jbBack = new JButton("Back");
        jbBack.setActionCommand(BTN_BACK);
        jbBack.setAlignmentX(CENTER_ALIGNMENT);
        jbBack.setFont(new Font("Helvetica", Font.ITALIC, 47));

        label.add(Box.createRigidArea(new Dimension(0,330)));
        label.add(jbLogout);
        label.add(Box.createRigidArea(new Dimension(0,30)));
        label.add(jbDeleteAcc);
        label.add(Box.createRigidArea(new Dimension(0,30)));
        label.add(jbBack);

        return label;
    }

    /**
     * Registra els botons de la View
     * @param optionController El controlador de la View
     */
    public void registerButtons(OptionController optionController){
        jbDeleteAcc.setActionCommand(BTN_DELETE);
        jbDeleteAcc.addActionListener(optionController);
        jbLogout.setActionCommand(BTN_LOGOUT);
        jbLogout.addActionListener(optionController);
        jbBack.setActionCommand(BTN_BACK);
        jbBack.addActionListener(optionController);
    }
}
