package controller;
import model.Mission;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {

    static SingletonBDD bdd = SingletonBDD.getInstance();

    public static String[] logIn(String email, String password) throws SQLException {
        String logStatus = "";
        String userType = "Not defined";
        String idUser = "";
        ResultSet login = getUserData(email);
        if (login.next()) {
            if (login.getString("Password").equals(password)) {
                logStatus = "Login successful";
                userType = login.getString("UserType");
                idUser = login.getString("IdUser");
            }
            else {
                logStatus = "Login failed : wrong password";
            }
        } else {
            logStatus = "Login failed : no user registered with this email";
        }
        String[] logStatusAndUser = new String[3];
        logStatusAndUser[0] = logStatus;
        logStatusAndUser[1] = userType;
        logStatusAndUser[2] = idUser;
        return logStatusAndUser;
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
            userCreationStatus = "User successfully created";
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

    public static String createNewMission(String title, String description, String date, int idUser) {
        String missionCreationStatus = "";

        try {
            String missionCreationQuery = "INSERT INTO MISSIONS (Title, Description, Date, IdNeeder) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = bdd.conn.prepareStatement(missionCreationQuery);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, date);
            preparedStatement.setInt(4, idUser);
            preparedStatement.executeUpdate();
            missionCreationStatus = "Mission successfully created";
        } catch (SQLException e) {
            e.printStackTrace();
            missionCreationStatus = "Mission creation failed";
        }
        return missionCreationStatus;
    }

    public static ArrayList<Mission> getMissionsOfNeeder(int idNeeder){
        ArrayList<Mission> missions = new ArrayList<Mission>();
        try {
            String getMissionsRequest = "SELECT * FROM MISSIONS WHERE IdNeeder = '" + idNeeder + "'";
            ResultSet missionsRS = bdd.state.executeQuery(getMissionsRequest);
            while (missionsRS.next()) {
                Mission mission = new Mission(missionsRS.getInt("IdMission"), missionsRS.getString("Title"), missionsRS.getString("Description"), missionsRS.getString("Date"), missionsRS.getInt("IdNeeder"), missionsRS.getString("Status"));
                missions.add(mission);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return missions;
    }

    public static ArrayList<Mission> getMissionsToValidate() {
        ArrayList<Mission> missions = new ArrayList<Mission>();
        try {
            String getMissionsRequest = "SELECT * FROM MISSIONS WHERE Status = 'Pending'";
            ResultSet missionsRS = bdd.state.executeQuery(getMissionsRequest);
            while (missionsRS.next()) {
                Mission mission = new Mission(missionsRS.getInt("IdMission"), missionsRS.getString("Title"), missionsRS.getString("Description"), missionsRS.getString("Date"), missionsRS.getInt("IdNeeder"), missionsRS.getString("Status"));
                missions.add(mission);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return missions;
    }

    public static void acceptMission(int idMission) {
        try {
            String acceptMissionQuery = "UPDATE MISSIONS SET Status = 'Accepted' WHERE IdMission = '" + idMission + "'";
            bdd.state.executeUpdate(acceptMissionQuery);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void refuseMission(int idMission) {
        try {
            String refuseMissionQuery = "UPDATE MISSIONS SET Status = 'Refused' WHERE IdMission = '" + idMission + "'";
            bdd.state.executeUpdate(refuseMissionQuery);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
