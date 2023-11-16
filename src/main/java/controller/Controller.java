package controller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Controller {

    static SingletonBDD bdd = SingletonBDD.getInstance();

    public static String logIn(String email, char[] password) {
        String logStatus = "";
        try {
            String login_request = "EXISTS (SELECT * FROM USER WHERE Email = " + email + " AND Password = " + Arrays.toString(password) + ")";
            ResultSet login = bdd.state.executeQuery(login_request);
            if (login != null) {
                logStatus = "Login successful";
            } else {
                logStatus = "Login failed";
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return logStatus;
    }
    public static String createNewUser(String firstName, String lastName, String BirthDate, String email, String password, boolean isNeeder, boolean isVolunteer, boolean isValidator) throws SQLException {
        String userCreationStatus = "";
        try{
            String createUserAttempt = "SELECT IdUser FROM USER WHERE EMAIL = '" + email + "'";
            ResultSet userExists = bdd.state.executeQuery(createUserAttempt);
            if (userExists.next()){
                userCreationStatus = "User already exists";
                System.out.println("User already exists");
                return userCreationStatus;
            }
            String userType = "";
            if (isNeeder) {
                userType = "Needer";
            } else if (isVolunteer) {
                userType = "Volunteer";
            } else if (isValidator) {
                userType = "Validator";
            }
            String userCreationQuery = "INSERT INTO USER (UserFirstName, UserLastName, Email, UserType, Password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = bdd.conn.prepareStatement(userCreationQuery);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            //BirthDate not implemented yet
            preparedStatement.setString(4, userType);
            preparedStatement.setString(5, password);
            preparedStatement.executeUpdate();
            userCreationStatus = "User sucessfully created";
            System.out.println("User created");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        
        return userCreationStatus;
    }
}
