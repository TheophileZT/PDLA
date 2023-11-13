package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SingletonBDD {

    String url = "srv-bdens.insa-toulouse.fr";
    String user = "projet_gei_011";
    String password = "shu6AeNg";

    private static SingletonBDD instance = null;
    Statement state;
    private SingletonBDD(){
        Connection connection = createConnection();
    }

    public Connection createConnection() {
        Connection connection = null;
        try {
            // Load the JDBC driver
            Class<?> driver_class = Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static SingletonBDD getInstance() {
        if (instance == null) {
            instance = new SingletonBDD();
        }
        return instance;
    }
}
