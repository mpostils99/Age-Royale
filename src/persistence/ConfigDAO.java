package persistence;

import business.entities.Config;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;


/**
 * Classe que utilitzem per llegir el config.json, que conté la informació per connectarnos a la base de dades
 * amb MySQL.
 * @version 22/04/22
 * @author Narcis Cisquella, Marc Postils
 */
public class ConfigDAO {
    /**
     * Mètode que s'encarrega de llegir el fitxer config.json, que conté
     * @return Retorna un objecte de tipus Config.
     */
    public Config readConfigFile(){
        Gson gson = new Gson();
        Config config = null;

        try {
            String path = "files/config.json";
            JsonReader jsonReader = new JsonReader(new FileReader(path));
            config = gson.fromJson(jsonReader, Config.class);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

        return config;
    }
}
