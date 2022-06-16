package presentation.controllers;

import business.managers.UserManager;


import presentation.views.MainView;
import presentation.views.OptionView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Classe que correspon al controlador de la vista de logout.
 *
 * @author Marc Postils,Narcis Cisquella
 * @version 23/04/2022
 */

public class OptionController implements ActionListener {

        private final UserManager userManager;
        private final OptionView optionView;
        private final MainView mainView;

    /**
     * Constructor del OptionController
     * @param userManager   userManager
     * @param optionView    vista de les opcions
     * @param mainView      mainView (pel CardLayout)
     */
        public OptionController(UserManager userManager, OptionView optionView, MainView mainView) {
            this.userManager = userManager;
            this.optionView = optionView;
            this.mainView = mainView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case OptionView.BTN_DELETE:
                    userManager.deleteUser(userManager.getUser());
                    userManager.setUser("","","","");
                    mainView.showStartMenuCard();
                    break;
                case OptionView.BTN_LOGOUT:
                    userManager.setUser("","","","");
                    mainView.showStartMenuCard();
                    break;
                case OptionView.BTN_BACK:
                    mainView.showMenuCard();
                    break;
            }
        }


}
