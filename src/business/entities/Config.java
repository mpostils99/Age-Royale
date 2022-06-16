package business.entities;


/**
 * Classe que utilitzem per guardar la inf de la base de datos
 * @version 20/04/22
 * @author Marc Postils, Narcís Cisquella, Jose Ignacio Alonso
 */
public class Config {

    private String ip;
    private int port;
    private String dbName;
    private String dbUser;
    private String dbPassword;

    /**
     * Constructor de la classe Config.
     *
     * @param ip           És la ip de l'usuari.
     * @param port         És el port de l'usuari.
     * @param dbName        És el nom de la base de dades del usuari.
     * @param dbUser         És el usuari en el Workbench del usuari.
     * @param dbPassword     És la contrasenya en el Workbench del usuari.
     */
    public Config(String ip, int port, String dbName, String dbUser, String dbPassword){
        this.ip = ip;
        this.port = port;
        this.dbName = dbName;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }


    /**
     * Getter de la ip.
     * @return Retorna la ip.
     */
    public String getIp() {
        return ip;
    }
    /**
     * Setter de la ip.
     *
     * @param  ip  És la ip de l'usuari.
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Getter del port.
     * @return Retorna el port.
     */
    public Integer getPort() {
        return port;
    }


    /**
     * Setter del port.
     *
     * @param  port  És el port de l'usuari.
     */
    public void setPort(int port) {
        this.port = port;
    }
    /**
     * Getter del nom d'usuari.
     * @return Retorna el nom d'usuari.
     */
    public String getDbName() {
        return dbName;
    }
    /**
     * Setter del nom de la base de datos.
     * @param dbName És el nom de la base de dades del usuari.
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    /**
     * Getter del nom de la base de datos.
     * @return Retorna el nom d'usuari.
     */
    public String getDbUser() {
        return dbUser;
    }
    /**
     * Setter del nom d'usuari.
     * @param dbUser És el nom de la base de dades del usuari.
     */
    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }
    /**
     * Getter de la contrasenya.
     * @return Retorna la contrasenya.
     */
    public String getDbPassword() {
        return dbPassword;
    }
    /**
     * Setter de la contrasenya.
     * @param dbPassword     És la contrasenya en el Workbench del usuari.
     */
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

}
