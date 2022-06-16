package presentation.controllers;

import business.managers.UserManager;
import presentation.views.LoginView;
import presentation.views.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe que correspon al controlador de la vista de registre. S'encarrega de gestionar quines accions realitzar
 * quan l'usuari fa click a registrar-se o a tirar endarrera.
 *
 * @author Marc Postils, Narcis Cisquella, Lluis Gumabu i David Tort
 * @version 23/04/2022
 */
public class LoginController implements ActionListener {
    private final UserManager userManager;
    private final LoginView loginView;
    private final MainView mainView;

    /**
     * Constructor del LoginController
     * @param userManager   userManager
     * @param loginView     view del Login
     * @param mainView      mainView (pel CardLayout)
     */
    public LoginController(UserManager userManager, LoginView loginView, MainView mainView) {
        this.userManager = userManager;
        this.loginView = loginView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case LoginView.BTN_LOGIN:
                userManager.setUser(loginView.getUsernameEmail(),loginView.getUsernameEmail(), loginView.getPassword(), "");
                if(userManager.login(userManager.getUser())) {
                    mainView.showMenuCard();
                    //System.out.println("IN");
                } else {
                    loginView.registerErrors();
                    //System.out.println("OUT");
                }

                break;

            case LoginView.BTN_BACK:
                mainView.showStartMenuCard();
                break;
        }
    }

}