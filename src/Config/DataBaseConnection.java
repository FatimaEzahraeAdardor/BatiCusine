package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection{
    private static DataBaseConnection instance;
    private static Connection connection;
    private String url ="jdbc:postgresql://localhost:5432/baticuisine";
    private String user = "GreenPulse";
    private String password = "";
    private   DataBaseConnection(){
        try {
            connection = DriverManager.getConnection(url , user , password);
            System.out.println("Connected to the database.");
        }catch (SQLException e){

            System.out.println("Failed to make connection" + e.getMessage());
        }
    }
    public  static DataBaseConnection getInstance(){
        if(instance == null){
            instance = new DataBaseConnection();
        }
        return instance;

    }
    public static Connection getConnection(){
        return connection;
    }
}

