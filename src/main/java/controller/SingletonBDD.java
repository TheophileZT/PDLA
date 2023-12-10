package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * SingletonBDD is a singleton class that creates a unique connection to the database
 */
public class SingletonBDD {

    String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_011";
    String user = "projet_gei_011";
    String password = "shu6AeNg";

    private static SingletonBDD instance = null;
    Statement state = null;
    Connection conn;

    /**
     * Constructor of the SingletonBDD class
     */
    private SingletonBDD() {
        conn = createConnection();
    }

    /**
     * Creates a connection to the database
     * @return the connection to the database
     */
    private Connection createConnection() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            state = conn.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Returns the singleton instance of the connection to the database
     * @return the singleton instance
     */
    public static SingletonBDD getInstance() {
        if (instance == null) {
            instance = new SingletonBDD();
        }
        return instance;
    }
}
