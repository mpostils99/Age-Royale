package persistence;

import business.entities.Config;
import java.sql.*;

/**
 * Aquesta classe és l'encarregada de connectar el nostre projecte amb la nostra base de dades.
 * @version 23/04/22
 * @author Narcís Cisquella, Marc Postils
 */
public class DatabaseConnector {

    private Connection remoteConn = null;

    /**
     * Mètode que revisa i comprova la configuració de nostre host, en cas de no poder conectar-se ho notifica per la consola.
     */
    public DatabaseConnector() {
        ConfigDAO configDAO = new ConfigDAO();
        Config config = configDAO.readConfigFile();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:";
        try {
            remoteConn = DriverManager.getConnection(url + config.getPort() + "/?user=" + config.getDbUser() + "&password=" + config.getDbPassword() + "&serverTimezone=UTC");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Connected correctly to database.");
    }

    /**
     * Getter de de la connexió.
     * @return Retorna la connexió.
     */
    public Connection getRemoteConn() {
        return remoteConn;
    }

}