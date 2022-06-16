package persistence;

import business.entities.User;

import java.util.ArrayList;

/**
 * Interficie per reunuir els mètodes del package persistence
 */
public interface UserDAO {
    void useAgeRoyale();
    boolean checkExistantUser(User user);
    void insertUser(User user);
    ArrayList<String> login(User user);
    boolean deleteUser(User user);
    String[][] getRanking();
    String[][] getHistory(String userName);
    void updateStatistics(boolean playerWon, String userName);
    void saveGame(String gameName, int winner, String userName, String data);
}
