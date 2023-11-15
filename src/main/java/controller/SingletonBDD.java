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
    Statement state = null;
    Connection conn = null;
    
    private Connection createConnection() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            state = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static SingletonBDD getInstance() {
        if (instance == null) {
            instance = new SingletonBDD();
        }
        return instance;
    }
}
