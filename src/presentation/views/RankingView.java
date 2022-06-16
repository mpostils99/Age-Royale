package presentation.views;

import presentation.controllers.OptionController;
import presentation.controllers.RankingController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Classe on gestionem tota la vista del rànquing
 * @Authors: David Tort, Narcis Cisquella, Lluis Gumbau
 */
public class RankingView extends JPanel {
    private JTable jtRanking;
    private JButton jbHistory;
    private JButton jbBack;
    private JButton jbLogout;

    public static final String BTN_BACK = "BTN_BACK";
    public static final String BTN_HISTORIAL = "BTN_HISTORIAL";
    public static final String BTN_LOGOUT = "BTN_LOGOUT";

    private String[][] data;

    /**
     * Mètode que configura la vista.
     * @param data Informació del ranking.
     * @return Retorna el JLabel.
     */
    public JLabel configureView(String[][] data){
        ImageIcon background = new ImageIcon("assets/images/background_HistoryView.png");
        JLabel label = new JLabel("",background, JLabel.CENTER);
        label.setBounds(0,0,1400,1020);
        label.setLayout(new BoxLayout(label,BoxLayout.Y_AXIS));
        String[] columnsName ={"PLAYER","WINS","WIN RATIO"};
        this.data = data;
        jtRanking = new JTable(data,columnsName);
        JScrollPane jscroll = new JScrollPane(jtRanking);
        jscroll.setOpaque(false);
        jscroll.getViewport().setOpaque(false);
        jscroll.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));

        jbHistory = new JButton("History");
        jbHistory.setFont(new Font("K2D", Font.ITALIC, 20));
        jbBack = new JButton("Back");
        jbBack.setFont(new Font("K2D", Font.ITALIC, 20));
        jbLogout = new JButton("Logout");
        jbLogout.setFont(new Font("K2D", Font.ITALIC, 20));
        jbBack.setVerticalAlignment(SwingConstants.CENTER);
        jbHistory.setVerticalAlignment(SwingConstants.CENTER);

        JPanel jpButtons = new JPanel(new FlowLayout());
        jpButtons.add(jbHistory);
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
     * Registra els botons de la vista
     * @param rankingController El controlador del ranking.
     */
    public void registerButtons(RankingController rankingController) {
        jbHistory.setActionCommand(BTN_HISTORIAL);
        jbHistory.addActionListener(rankingController);
        jbBack.setActionCommand(BTN_BACK);
        jbBack.addActionListener(rankingController);
        jbLogout.setActionCommand(BTN_LOGOUT);
        jbLogout.addActionListener(rankingController);
    }

    /**
     * Funció que retorna el jugador.
     * @return Retorna el jugador.
     */
    public String getPlayer(){
        int fila = jtRanking.getSelectedRow();

        return data[1][0];
    }

}