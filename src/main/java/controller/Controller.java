package controller;
import java.sql.*;

public class Controller {
    Class.forName("com.mysql.jdbc.Driver");
    Connection connect = DriverManager.getConnection("srv-bdens.insa-toulouse.fr", "projet_gei_011", "shu6AeNg");
    Statement state = connect.createStatement();

    public static int logIn(String email char[] password) {
        int logStatus = 0;
        String loginAttempt = "EXISTS (SELECT * FROM USER WHERE Email = " + email + " AND Password = " + password + ")";
    }
    public static int createNewUser(String firstName, String lastName, String email, String password, boolean isNeeder, boolean isVolunteer, boolean isValidator) {
        String createUserAttempt = "EXISTS (SELECT * FROM USER WHERE EMAIL = " + email + ")";
        ResultSet userExists = state.executeQuery(createUserAttempt);
        if (userExists != null){
            system.out.println("User already exists");
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
        ResultSet userCreation = state.executeQuery(userCreationQuery);
        return 0;


    }
}
