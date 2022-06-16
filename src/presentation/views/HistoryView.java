package presentation.views;

import presentation.controllers.HistoryController;

import javax.swing.*;
import java.awt.*;

/**
 * Vista de l'historial del joc
 * @version 25/05/2022
 * @author David Tort
 */
public class HistoryView extends JPanel{
    private JTable jtHistorial;
    private String[][] data;
    private JButton jbBack;
    private JButton jbLogout;
    private JButton jbReproduir;

    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_LOGOUT = "BTN_LOGOUT";
    public static final String BTN_PLAY = "BTN_PLAY";

    /**
     * Constructor del HistoryView
     */
    public HistoryView() {
    }

    /**
     * Funcio que configura tota la view
     * @return Retorna un JPanel per afegir-lo a la cardLayout
     */
    public JLabel configureView(String [][] data){

        ImageIcon background = new ImageIcon("assets/images/background_HistoryView.png");

        JLabel label = new JLabel("",background, JLabel.CENTER);
        label.setBounds(0,0,1400,1020);
        label.setLayout(new BoxLayout(label,BoxLayout.Y_AXIS));

        String[] columnsName ={"GAME NAME","DATE","RESULT"};
        this.data = data;
        jtHistorial = new JTable(data,columnsName);
        JScrollPane jscroll = new JScrollPane(jtHistorial);
        jscroll.setOpaque(false);
        jscroll.getViewport().setOpaque(false);
        jscroll.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));

        jbReproduir = new JButton("Play");
        jbReproduir.setFont(new Font("K2D", Font.ITALIC, 20));

        jbBack = new JButton("Back");
        jbBack.setFont(new Font("K2D", Font.ITALIC, 20));
        jbBack.setVerticalAlignment(SwingConstants.CENTER);

        jbLogout = new JButton("Logout");
        jbLogout.setFont(new Font("K2D", Font.ITALIC, 20));

        JPanel jpButtons = new JPanel(new FlowLayout());
        jpButtons.add(jbReproduir);
        jpButtons.add(jbBack);
        jpButtons.add(jbLogout);
        Dimension dimension = super.getToolkit().getScreenSize();
        jpButtons.setPreferredSize(new Dimension((int)dimension.getWidth(),100));
        jpButtons.setOpaque(false);
        label.add(jscroll,BorderLayout.CENTER);
        label.add(jpButtons,BorderLayout.SOUTH);

        this.add(label);

        return label;
    }

    /**
     * Regista els botons perque reaccionin
     * @param historyController El controller de la view
     */
    public void registerButtons(HistoryController historyController) {
        jbBack.setActionCommand(BTN_BACK);
        jbBack.addActionListener(historyController);
        jbLogout.setActionCommand(BTN_LOGOUT);
        jbLogout.addActionListener(historyController);
    }
}
