package presentation.controllers;

import business.managers.UserManager;
import presentation.views.MainView;
import presentation.views.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Classe que correspon al controlador de la vista de registre. S'encarrega de gestionar quines accions realitzar
 * quan l'usuari fa click a registrar-se o a tirar endarrera.
 *
 * @author Marc Postils, David Tort, Lluis Gumbau, Jose Ignacio Alonso i Narcis Cisquella
 * @version 23/04/2022
 */
public class RegisterController implements ActionListener {
    private final UserManager userManager;
    private final RegisterView registerView;
    private final MainView mainView;

    /**
     * Constructor del RegisterController
     * @param userManager   userManager
     * @param registerView  vista del registre
     * @param mainView      mainView (pel CardLayout)
     */
    public RegisterController(UserManager userManager, RegisterView registerView, MainView mainView) {
        this.userManager = userManager;
        this.registerView = registerView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case RegisterView.BTN_SUBMIT:
                userManager.setUser(registerView.getUsername(), registerView.getEmail(),registerView.getPassword(), registerView.getPasswordConfirmation());
                ArrayList<String> errors = userManager.comprovaErrors(userManager.getUser());
                if(errors.size()==0){
                    userManager.register(userManager.getUser());
                    mainView.showMenuCard();
                }else{
                    registerView.registerErrors(errors);
                }

                break;

            case RegisterView.BTN_BACK:
                mainView.showStartMenuCard();
                break;
        }
    }

}
