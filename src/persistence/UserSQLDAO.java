package persistence;


import java.sql.*;
import java.util.ArrayList;

import business.entities.User;

/**
 * Aquesta classe és l'encarregada de fer totes les operacions i comprovacions pertinents a l'hora d'inserir i borrar informació a la base de dades.
 * @version 23/04/22
 * @author Narcís Cisquella, Marc Postils
 */
public class UserSQLDAO implements UserDAO {
    private final Connection remoteConn;

    /**
     * Constructor de la classe.
     */
    public UserSQLDAO() {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        this.remoteConn = databaseConnector.getRemoteConn();
    }

    /**
     * Funció que selecciona el nom de la BBDD que es fara servir
     */
    public void useAgeRoyale(){
        try {
            Statement statement = null;
            statement = remoteConn.createStatement();
            statement.executeQuery("USE ageroyale");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Funcio que comprova que existi l'usuari que s'introdueix
     * @param user L'usuari que intenta fer login al joc
     * @return Retorna si l'usuari existeix o no
     */
    public boolean checkExistantUser(User user) {
        Statement statement = null;
        try {
            useAgeRoyale();
            //Binary \ es per fer un cast del nom i correu
            String selectUser = "SELECT COUNT(*) AS numUsuaris FROM Usuari WHERE nom_usuari = BINARY\"" + user.getUserName() + "\" OR correu = BINARY\"" + user.getEmail() + "\"";
            //comprovar que email i password siguin correctes
            statement = remoteConn.createStatement();
            ResultSet resultSet = statement.executeQuery(selectUser);
            resultSet.next();
            if (resultSet.getInt("numUsuaris") == 0) {
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    /**
     * Funcio que afegeix un usuari a la BBDD
     * @param user L'usuari que es vol inserir
     */
    public void insertUser(User user) {
        Statement statement = null;
        try {
            useAgeRoyale();
            statement = remoteConn.createStatement();
            String insert = "INSERT INTO Usuari (nom_usuari, correu, contrasenya, wins, partides) VALUES (\"" + user.getUserName() + "\",\"" + user.getEmail() + "\",\"" + user.getPassword() + "\", 0, 0)";

            statement.executeUpdate(insert);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Funcio que fa el login amb l'usuari
     * @param user L'usuari que vol fer el login
     * @return Retorna un ArrayList amb tota la info de l'usuari
     */
    public ArrayList<String> login(User user) {
        ArrayList<String> infoUser = new ArrayList<>();
        Statement statement = null;
        try {
            statement = remoteConn.createStatement();
            useAgeRoyale();
            String query = "SELECT COUNT(*) AS numUsuaris FROM Usuari WHERE (nom_usuari = BINARY\"" + user.getUserName() + "\" OR correu = BINARY\"" + user.getEmail() + "\") AND contrasenya = \"" + user.getPassword() + "\"";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            if (resultSet.getInt("numUsuaris") == 1) {
                String selectUser = "SELECT nom_usuari,correu FROM Usuari WHERE nom_usuari = BINARY\"" + user.getUserName() +"\"OR correu = BINARY\"" + user.getEmail() + "\"";
                resultSet = statement.executeQuery(selectUser);
                resultSet.next();

                infoUser.add(resultSet.getString("nom_usuari"));
                infoUser.add(resultSet.getString("correu"));

                return infoUser;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return infoUser;
    }

    /**
     * Funcio que elimina un usuari de la BBDD
     * @param user L'usuari que es vol eliminar
     * @return Retorna un boolea per indicar si la eliminacio ha funcionat
     */
    public boolean deleteUser(User user){
        useAgeRoyale();
        String deleteHistorial = "DELETE FROM Historial WHERE nom_usuari=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = remoteConn.prepareStatement(deleteHistorial);

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.executeUpdate();

            String eliminarUser = "DELETE FROM Usuari WHERE nom_usuari = ? ";
            preparedStatement = remoteConn.prepareStatement(eliminarUser);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.executeUpdate();

        ///aqui hem d'eliminar json de files

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    /**
     * Funcio que recupera les dades del ranking de la BBDD
     * @return Retorna una matriu de String amb tota la informacio necessaria del ranking
     */
    public String[][] getRanking() {
        useAgeRoyale();
        Statement statement = null;
        String searchRanking = "SELECT nom_usuari,wins,((wins/partides)*100) as ratio FROM Usuari ORDER BY ratio DESC;";

        ArrayList<String> nom_usuari = new ArrayList<>();
        ArrayList<String> wins = new ArrayList<>();
        ArrayList<String> ratio = new ArrayList<>();

        int rows = 0;
        try {
            statement = remoteConn.createStatement();
            ResultSet resultSet = statement.executeQuery(searchRanking);

            while (resultSet.next()) {
                nom_usuari.add(resultSet.getString("nom_usuari"));
                wins.add(resultSet.getString("wins"));
                String winRatio = resultSet.getString("ratio");
                if (winRatio == null) {
                    winRatio = "No ha jugat cap partida"; //no hi ha partides
                } else {
                    winRatio = winRatio + "%";
                }
                ratio.add(winRatio);
                rows++;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String[][] dades = new String[rows][3];

        for (int i = 0; i < rows; i++) {
            dades[i][0] = nom_usuari.get(i);
            dades[i][1] = wins.get(i);
            dades[i][2] = ratio.get(i);
        }

        return dades;
    }

    /**
     * Funcio que recupera l'historial d'un usuari
     * @param userName L'usuari del que volem recuperar l'historial
     * @return Retorna una matriu de String amb l'historial del jugador
     */
    public String[][] getHistory(String userName) {
        useAgeRoyale();
        String[][] basura = null;
        String query = "Select nom_partida,url_partida,DATE_FORMAT(data, '%Y-%m-%d %H:%i') as data,guanyador FROM Historial WHERE nom_usuari=? ORDER BY data DESC";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = remoteConn.prepareStatement(query);

            preparedStatement.setString(1, userName);
            ResultSet res = preparedStatement.executeQuery();

            ArrayList<String>game_name = new ArrayList<>();
            ArrayList<String>url_name = new ArrayList<>();
            ArrayList<String>date = new ArrayList<>();
            ArrayList<String>win = new ArrayList<>();

            Integer rows = 0;
            while (res.next()){
                String GameName = res.getString("nom_partida");
                String urlName = res.getString("url_partida");
                game_name.add(GameName);
                url_name.add(urlName);

                date.add(res.getString("data"));
                String result;
                if(res.getBoolean("guanyador")){
                    result = "win";
                }else{
                    result = "loss";
                }
                win.add(result);
                rows++;
            }

            String[][] dades = new String[rows][3];
            for (int i = 0; i < rows; i++) {
                dades[i][0]= game_name.get(i);
                dades[i][1]= date.get(i);
                dades[i][2]=win.get(i);
            }

            return dades;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return basura;
    }

    /**
     * Mètode on actualitzem les estadistiques en la nostra base de dades.
     * @param playerWon booleà que ens indica si el jugador ha guanyat la partida
     * @param userName nom de l'usuari actiu
     */
    public void updateStatistics(boolean playerWon, String userName) {
        Statement statement = null;
        useAgeRoyale();
        try {
            statement = remoteConn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (playerWon) {
            String queryWin = "UPDATE Usuari SET partides=partides+1,wins = wins + 1 WHERE nom_usuari = \"" + userName + "\"";
            try {
                statement.executeUpdate(queryWin);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            String queryLose = "UPDATE Usuari set partides = partides + 1 WHERE nom_usuari = \"" + userName + "\"";
            try {
                statement.executeUpdate(queryLose);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * Guardem la partida a la base de dades
     * @param gameName el nom de la partida
     * @param winner si hem guanyat la partida
     * @param userName el nom de l'usuari actiu
     * @param data data de la partida
     */
    public void saveGame(String gameName, int winner, String userName, String data) {
        Statement statement = null;
        String url = data.concat(gameName);
        try {
            useAgeRoyale();
            statement = remoteConn.createStatement();
            String insert = "INSERT INTO Historial (url_partida, nom_partida, data, guanyador, nom_usuari) VALUES (\"" + url + "\",\"" + gameName + "\",\"" + data + "\",\"" + winner + "\",\"" + userName + "\")";
            statement.executeUpdate(insert);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}