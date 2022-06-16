package business.entities;

/**
 * Classe que correspon a controlar l'usuari actual del joc i la seva informacio
 * @version 20/04/22
 * @author Narcis Cisquella, Marc Postils
 */
public class User{
    private String userName;
    private String email;
    private String password;
    private String repeatPassword;

    /**
     * Constructor de l'usuari
     * @param nom nom de l'usuari
     * @param correu correu de l'usuari
     */
    public User(String nom, String correu, String contrasenya, String repeatPassword) {
        this.userName = nom;
        this.email = correu;
        this.password = contrasenya;
        this.repeatPassword = repeatPassword;
    }

    /**
     * Getter del nom d'usuari
     * @return el nom de l'usuari
     */
    public String getUserName() {
        return userName;
    }
    /**
     * Getter del correu de l'usuari
     * @return el correu del usuari
     */
    public String getEmail() {
        return email;
    }
    /**
     * Setter del nom del usuari
     * @param userName el nom d'usuari
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * Setter del correu de l'usuari
     * @param email el correu de l'usuari
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter de la contrasenya de l'usuari
     * @return la contrasenya de l'usuari
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter de la contrasenya de l'usuari
     * @param password la contrasenya de l'usuari
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * Setter de la contrasenya de l'usuari
     * @param repeatPassword la contrasenya de l'usuari
     */
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}