package business.managers;

import business.entities.User;
import persistence.UserDAO;
import persistence.UserSQLDAO;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;
/**
 * Aquesta classe és l'encarregada de gestionar els usuaris
 * @version 23/04/22
 * @author Narcís Cisquella, Marc Postils
 */
public class UserManager {
    private UserDAO userDAO;
    private final User user;

    private static final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String passwordRegex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";

    /**
     * Constructor del UserManager
     */
    public UserManager(){
        this.user = new User("", "", "", "");
        this.userDAO = new UserSQLDAO();
    }

    /**
     * Getter de l'usuari
     * @return Retorna l'usuari
     */
    public User getUser() {
        return user;
    }

    /**
     * Actualitza l'usuari amb les dades que ha introduït
     * @param nom el nom introduït
     * @param correu el correu introduït
     */
    public void setUser(String nom, String correu, String contrasenya, String repeatPassword){
        this.user.setUserName(nom);
        this.user.setEmail(correu);
        this.user.setPassword(contrasenya);
        this.user.setRepeatPassword(repeatPassword);
    }

    /**
     * Comprova que no hi hagi cap error a l'usuari
     * @param user l'usuari que estem verificant
     * @return Retorna una llista amb tots els errors
     */
    public ArrayList<String > comprovaErrors(User user){
        ArrayList<String> errors = new ArrayList<>();

        Pattern pat = Pattern.compile(emailRegex);
        Pattern pat2 = Pattern.compile(passwordRegex);

        if (!userDAO.checkExistantUser(user)) {
            errors.add("This user already exists.");
        }

        if (!pat.matcher(user.getEmail()).matches()) {
            errors.add("The email has wrong format.");
        }

        if (!pat2.matcher(user.getPassword()).matches()) {
            errors.add("The password has wrong format.");
        }

        if(!user.getPassword().equals(user.getRepeatPassword())) {
            errors.add("The passwords doesn't match.");
        }

        return errors;
    }


    /**
     * Registra l'usuari
     * @param user L'usuari que es vol registrar
     */
    public void register(User user) {
         userDAO.insertUser(user);
    }

    /**
     * Fa login de l'usuari
     * @param user l'usuari que es vol logejar
     * @return retorna si ha pogut fer login correctamente
     */
    public boolean login(User user) {
        ArrayList<String> infoUser = userDAO.login(user);
        //aqui mirem si l'array esta buida o no si esta buida return false si esta plena update
        if(infoUser.size() == 0){
         return false;
        }
        this.user.setUserName(infoUser.get(0));
        this.user.setEmail(infoUser.get(1));

        return true;
    }

    /**
     * Elimina l'usuari
     * @param user  L'usuari que es vol eliminar
     * @return Retorna si ha pogut eliminar-se l'usuari
     */
    public boolean deleteUser(User user){

        return userDAO.deleteUser(user);
    }

    /**
     * Recull la informacio del ranking dels millors jugafors
     * @return  Retorna una matriu de String amb el ranking sencer
     */
    public String[][] getRanking() {
        return userDAO.getRanking();
    }

    /**
     * Recull l'historial d'un usuari
     * @param userName L'usuari del que volem l'historial
     * @return Retorna una matriu de String amb l'historial sencer
     */
    public String[][] getHistory(String userName) {

        return userDAO.getHistory(userName);
    }
    /**
     * Getter del nom d'usuari
     * @return el nom de l'usuari
     */
    public String getNom(){
        return user.getUserName();
    }

    /**
     * Mètode per actualitzar les estadístiques del jugador
     * @param playerWon li passem li el jugador ha guanyat la partida
     */
    public void updateStatistics(boolean playerWon) {
        userDAO.updateStatistics(playerWon, user.getUserName());
    }

    /**
     * Mètode per guardar la partida
     * @param gameName li passem el nom que l'usuari ha introduit
     * @param playerWon si el jugador ha estat el guanyador
     */
    public void saveGame(String gameName, boolean playerWon) {
        int winner = 0;
        long milis = System.currentTimeMillis();
        String currentDate = null;

        if (playerWon) {
            winner = 1;
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Timestamp date = new Timestamp(milis);
        currentDate = dateFormat.format(date);

        userDAO.saveGame(gameName, winner, user.getUserName(), currentDate);
    }

}
