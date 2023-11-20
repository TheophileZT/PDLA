package controller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Controller {

    static SingletonBDD bdd = SingletonBDD.getInstance();

    public static String[] logIn(String email, char[] password) throws SQLException {
        String logStatus = "";
        String userType = "Not defined";
        ResultSet login = getUserData(email);
        if (login.next()) {
            if (Arrays.equals(password, login.getString("Password").toCharArray())) {
                logStatus = "Login successful";
                userType = login.getString("UserType");
            }
            else {
                logStatus = "Login failed : wrong password";
            }
        } else {
            logStatus = "Login failed : no user registered with this email";
        }
        String[] logStatusAndUserType = new String[2];
        logStatusAndUserType[0] = logStatus;
        logStatusAndUserType[1] = userType;
        return logStatusAndUserType;
    }
    
    public static String createNewUser(String firstName, String lastName, String BirthDate, String email, String password, boolean isNeeder, boolean isHelper, boolean isValidator) throws SQLException{
        String userCreationStatus = "";
        ResultSet userExists = getUserData(email);
        if (userExists.next()){
            userCreationStatus = "User already exists";
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
        try {
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
        } catch (SQLException e) {
            e.printStackTrace();
            userCreationStatus = "User creation failed";
        }
        return userCreationStatus;
    }

    public static ResultSet getUserData(String email) {
        ResultSet User = null;
        try {
            String getUserRequest = "SELECT * FROM USER WHERE Email = '" + email + "'";
            User = bdd.state.executeQuery(getUserRequest);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return User;
    }
}
