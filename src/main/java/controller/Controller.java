package controller;
import java.sql.*;
import java.util.Arrays;

public class Controller {

    static SingletonBDD bdd = SingletonBDD.getInstance();

    public static int logIn(String email, char[] password) {
        int logStatus = 0;
        String loginAttempt = "EXISTS (SELECT * FROM USER WHERE Email = " + email + " AND Password = " + Arrays.toString(password) + ")";
        return logStatus;
    }
    public static int createNewUser(String firstName, String lastName, String email, String password, boolean isNeeder, boolean isVolunteer, boolean isValidator) throws SQLException {
        String createUserAttempt = "EXISTS (SELECT * FROM USER WHERE EMAIL = " + email + ")";
        ResultSet userExists = bdd.state.executeQuery(createUserAttempt);
        if (userExists != null){
            System.out.println("User already exists");
            return 1;
        }
        String userType = "";
        if (isNeeder) {
            userType = "Needer";
        } else if (isVolunteer) {
            userType = "Volunteer";
        } else if (isValidator) {
            userType = "Validator";
        }
        String userCreationQuery = "INSERT INTO USER (FirstName, LastName, Email, Password, UserType) VALUES (" + firstName + ", " + lastName + ", " + email + ", " + password + ", " + userType + ")";
        ResultSet userCreation = bdd.state.executeQuery(userCreationQuery);
        return 0;

    }
}
