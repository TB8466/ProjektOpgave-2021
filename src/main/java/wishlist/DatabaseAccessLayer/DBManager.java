package wishlist.DatabaseAccessLayer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {

    private static String user;
    private static String password;
    private static String url;
    private static Connection connection = null;

    //Method for establishing connection to mySQL database
    //Make sure that application.properties file has Username, password and url
    public static Connection getConnection(){

        if(connection != null) return connection;
        try (InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try{
            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}