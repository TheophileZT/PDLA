package controller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

public class Controller {

    static SingletonBDD bdd = SingletonBDD.getInstance();

    public static String logIn(String email, char[] password) {
        String logStatus = "";
        try {
            String login_request = "SELECT * FROM USER WHERE Email = '" + email + "'' AND Password = '" + Arrays.toString(password) + "'";
            ResultSet login = bdd.state.executeQuery(login_request);
            if (login.next()) {
                
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
    public static String createNewUser(String firstName, String lastName, String BirthDate, String email, String password, boolean isNeeder, boolean isHelper, boolean isValidator) throws SQLException {
        HashMap<ResultSet, Boolean> user_to_create = getUserData(email);
        String userCreationStatus = "";
        try{
            String createUserAttempt = "SELECT * FROM USER WHERE EMAIL = '" + email + "'";
            ResultSet userExists = bdd.state.executeQuery(createUserAttempt);
            if (userExists.next()){
                userCreationStatus = "User already exists";
                System.out.println("User already exists");
                return userCreationStatus;
            }
            String userType = "";
            if (isNeeder) {
                userType = "Needer";
            } else if (isHelper) {
                userType = "Helper";
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
    public static HashMap<ResultSet, Boolean> getUserData(String email) {
        boolean userExists = false;
        ResultSet User = null;
        
        try {
            String getUserRequest = "SELECT * FROM USER WHERE Email = '" + email + "'";
            User = bdd.state.executeQuery(getUserRequest);
            if (User.next()) {
                userExists = true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        HashMap<ResultSet, Boolean> userData = new HashMap<ResultSet, Boolean>();
        userData.put(User, userExists);
        return userData;
    }
}
