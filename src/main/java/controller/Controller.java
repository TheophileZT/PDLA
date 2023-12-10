package controller;
import model.Mission;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {

    static SingletonBDD bdd = SingletonBDD.getInstance();

    /**
     * Try to log in a user with the given email and password
     * @param email email of the user
     * @param password password of the user
     * @return String[] containing the login status, the user type and the user id
     * @throws SQLException if the SQL request fails
     */
    public static String[] logIn(String email, String password) throws SQLException {
        String logStatus = "";
        String userType = "Not defined";
        String idUser = "";
        ResultSet login = getUserData(email);
        // Check if the user exists and if the password is correct
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

    /**
     * Create a new user in the database with the given information
     * @param firstName first name of the user
     * @param lastName last name of the user
     * @param email email of the user
     * @param password password of the user
     * @param isNeeder true if the user is a needer
     * @param isHelper true if the user is a helper
     * @param isValidator true if the user is a validator
     * @return String containing the user creation status
     * @throws SQLException if the SQL request fails
     */
    public static String createNewUser(String firstName, String lastName, String email, String password, boolean isNeeder, boolean isHelper, boolean isValidator) throws SQLException{
        String userCreationStatus = "";
        // Check if the user already exists
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
        // Create the user
        try {
            String userCreationQuery = "INSERT INTO USER (UserFirstName, UserLastName, Email, UserType, Password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = bdd.conn.prepareStatement(userCreationQuery);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
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

    /**
     * Get the data of a user with the given email
     * @param email email of the user
     * @return ResultSet containing the data of the user
     */
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

    /**
     * Create a new mission in the database with the given information
     * @param title title of the mission
     * @param description description of the mission
     * @param date date and hour of the mission
     * @param idUser id of the user who created the mission
     * @return String containing the mission creation status
     */
    public static String createNewMission(String title, String description, String date, int idUser) {
        String missionCreationStatus = "";
        // Create the mission
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

    /**
     * Get all the missions of a needer
     * @param idNeeder id of the needer
     * @return ArrayList of Mission containing all the missions of the needer
     */
    public static ArrayList<Mission> getMissionsOfNeeder(int idNeeder){
        ArrayList<Mission> missions = new ArrayList<Mission>();
        // Get the missions
        try {
            String getMissionsRequest = "SELECT * FROM MISSIONS WHERE IdNeeder = '" + idNeeder + "'";
            ResultSet missionsRS = bdd.state.executeQuery(getMissionsRequest);
            // Add the missions to the ArrayList
            while (missionsRS.next()) {
                Mission mission = new Mission(missionsRS.getInt("IdMission"), missionsRS.getString("Title"), missionsRS.getString("Description"), missionsRS.getString("Date"), missionsRS.getInt("IdNeeder"), missionsRS.getString("Status"), missionsRS.getInt("IdHelper"));
                missions.add(mission);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return missions;
    }

    /**
     * Get all the pending missions
     * @return ArrayList of Mission containing all the pending missions
     */
    public static ArrayList<Mission> getMissionsToValidate() {
        ArrayList<Mission> missions = new ArrayList<Mission>();
        try {
            String getMissionsRequest = "SELECT * FROM MISSIONS WHERE Status = 'Pending'";
            ResultSet missionsRS = bdd.state.executeQuery(getMissionsRequest);
            while (missionsRS.next()) {
                Mission mission = new Mission(missionsRS.getInt("IdMission"), missionsRS.getString("Title"), missionsRS.getString("Description"), missionsRS.getString("Date"), missionsRS.getInt("IdNeeder"), missionsRS.getString("Status"), missionsRS.getInt("IdHelper"));
                missions.add(mission);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return missions;
    }

    /**
     * Accept a mission
     * @param idMission id of the mission to accept
     */
    public static void acceptMission(int idMission) {
        try {
            String acceptMissionQuery = "UPDATE MISSIONS SET Status = 'Accepted' WHERE IdMission = '" + idMission + "'";
            bdd.state.executeUpdate(acceptMissionQuery);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Refuse a mission
     * @param idMission id of the mission to refuse
     */
    public static void refuseMission(int idMission) {
        try {
            String refuseMissionQuery = "UPDATE MISSIONS SET Status = 'Refused' WHERE IdMission = '" + idMission + "'";
            bdd.state.executeUpdate(refuseMissionQuery);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all the accepted or assigned missions of a helper
     * @param idHelper id of the helper
     * @return ArrayList of Mission containing all the accepted or assigned missions of the helper
     */
    public static ArrayList<Mission> getAcceptedOrAssignedMission(int idHelper) {
        ArrayList<Mission> missions = new ArrayList<Mission>();
        try {
            String getMissionsRequest = "SELECT * FROM MISSIONS WHERE Status = 'Accepted' OR Status = 'Assigned' AND IdHelper = '" + idHelper + "'";
            ResultSet missionsRS = bdd.state.executeQuery(getMissionsRequest);
            while (missionsRS.next()) {
                Mission mission = new Mission(missionsRS.getInt("IdMission"), missionsRS.getString("Title"), missionsRS.getString("Description"), missionsRS.getString("Date"), missionsRS.getInt("IdNeeder"), missionsRS.getString("Status"), missionsRS.getInt("IdHelper"));
                missions.add(mission);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return missions;
    }

    /**
     * Assign a mission to a helper
     * @param idMission id of the mission to assign
     * @param idHelper  id of the helper to assign the mission to
     * @return String containing the mission assignation status
     */
    public static String assignMission(int idMission, int idHelper) {
        String assignMissionStatus = "";
        try {
            String assignMissionQuery = "UPDATE MISSIONS SET IdHelper = '" + idHelper + "', Status = 'Assigned' WHERE IdMission = '" + idMission + "'";
            bdd.state.executeUpdate(assignMissionQuery);
            assignMissionStatus = "Mission successfully assigned";
        }
        catch (SQLException e) {
            e.printStackTrace();
            assignMissionStatus = "Mission assignation failed";
        }
        return assignMissionStatus;
    }

    /**
     * Get the firstname and lastname of a user
     * @param idHelper id of the user
     * @return String containing the name of the user
     */
    public static String getNameOfUser(int idHelper) {
        String name = "";
        try {
            String getNameRequest = "SELECT UserFirstName, UserLastName FROM USER WHERE IdUser = '" + idHelper + "'";
            ResultSet nameRS = bdd.state.executeQuery(getNameRequest);
            while (nameRS.next()) {
                name = nameRS.getString("UserFirstName") + " " + nameRS.getString("UserLastName");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * Complete a mission
     * @param idMission id of the mission to complete
     * @return String containing the mission update status
     */
    public static String completeMission(int idMission) {
        String completeMissionStatus = "";
        try {
            String completeMissionQuery = "UPDATE MISSIONS SET Status = 'Done' WHERE IdMission = '" + idMission + "'";
            bdd.state.executeUpdate(completeMissionQuery);
            completeMissionStatus = "Mission successfully completed";
        }
        catch (SQLException e) {
            e.printStackTrace();
            completeMissionStatus = "Mission completion failed";
        }
        return completeMissionStatus;
    }

    /**
     * Get the id of a user from his email
     * @param email email of the user
     * @return int containing the id of the user
     */
    public static int getIdOfUser(String email) {
        int id = 0;
        try {
            String getIdRequest = "SELECT IdUser FROM USER WHERE Email = '" + email + "'";
            ResultSet idRS = bdd.state.executeQuery(getIdRequest);
            while (idRS.next()) {
                id = idRS.getInt("IdUser");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * Delete a user from his name
     * @param name name of the user
     */
    public static void deleteUserByName(String name) {
        try {
            String deleteUserQuery = "DELETE FROM USER WHERE UserFirstName = '" + name + "'";
            bdd.state.executeUpdate(deleteUserQuery);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a user from its id
     * @param userId id of the user
     */
    public static void deleteUser(int userId) {
        try {
            String deleteUserQuery = "DELETE FROM USER WHERE IdUser = '" + userId + "'";
            bdd.state.executeUpdate(deleteUserQuery);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a mission from its id
     * @param missionId id of the mission
     */
    public static void deleteMission(int missionId) {
        try {
            String deleteMissionQuery = "DELETE FROM MISSIONS WHERE IdMission = '" + missionId + "'";
            bdd.state.executeUpdate(deleteMissionQuery);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
