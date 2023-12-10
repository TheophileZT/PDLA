package controller;

import model.Mission;
import model.Status;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ControllerTest {

    // Utilisez ces variables pour stocker les données de test
    private static final String testUserEmail = "testuser@example.com";
    private static final String testUserPassword = "testPassword";
    private static int testUserId;

    // Cette méthode s'exécute une fois avant le début des tests
    @BeforeAll
    public static void setUp() throws SQLException {
        // Ajoutez un utilisateur de test à la base de données
        if (Controller.bdd.conn != null) {
            String firstName = "Test";
            String lastName = "User";
            String email = testUserEmail;
            String password = testUserPassword;

            String result = Controller.createNewUser(firstName, lastName, email, password, true, false, false);

            assertEquals("User successfully created", result);

            // Récupérez l'ID de l'utilisateur de test
            String[] loginResult = Controller.logIn(email, password);
            testUserId = Integer.parseInt(loginResult[2]);
        }
    }

    // Cette méthode s'exécute une fois après la fin de tous les tests
    @AfterAll
    public static void tearDown() {
        if (Controller.bdd.conn != null) {
            Controller.deleteUser(testUserId);
        }
    }

    @Test
    public void testCreateNewUser() throws SQLException {
        if (Controller.bdd.conn != null) {
            String firstName = "Test";
            String lastName = "User";
            String email = "testuser1@example.com";
            String password = "testPassword";

            String result = Controller.createNewUser(firstName, lastName, email, password, true, false, false);

            assertEquals("User successfully created", result);

            String[] loginResult = Controller.logIn(email, password);
            int testId = Integer.parseInt(loginResult[2]);

            Controller.deleteUser(testId);
        }
    }

    @Test
    public void testLogIn() throws SQLException {
        // Assuming there is a test user in the database with known credentials
        if (Controller.bdd.conn != null) {
            String email = "testuser@example.com";
            String password = "testPassword";

            String[] result = Controller.logIn(email, password);

            assertEquals("Login successful", result[0]);
            assertEquals("Needer", result[1]);
            assertEquals(testUserId, Integer.parseInt(result[2]));
        }
    }

    @Test
    public void testCreateExistingUser() throws SQLException{
        if (Controller.bdd.conn != null) {
            String firstName = "Test";
            String lastName = "User";
            String email = "testuser@example.com";
            String password = "testPassword";

            String result = Controller.createNewUser(firstName, lastName, email, password, true, false, false);

            assertEquals("User already exists", result);
        }
    }

    @Test
    public void testCreateNewMission() {
        {
            // Use the test user's ID
            int idUser = testUserId;

            // Data for the test mission
            String title = "Test Mission";
            String description = "Test Description";
            String date = "2023-01-01 18:00:00";

            // Perform the mission creation
            String result = Controller.createNewMission(title, description, date, idUser);

            // Ensure the mission is created successfully
            assertEquals("Mission successfully created", result);

            // Retrieve the ID of the created mission (this assumes you have a method to get mission data)
            ArrayList<Mission> missions = Controller.getMissionsOfNeeder(idUser);
            assertFalse(missions.isEmpty());
            int testMissionId = missions.get(0).getIdMission();
            Controller.deleteMission(testMissionId);
        }
    }

    @Test
    public void testAcceptOrRefuseMission(){
        if (Controller.bdd.conn != null) {
            int idUser = testUserId;

            // Data for the test mission
            String title = "Test Mission";
            String description = "Test Description";
            String date = "2023-01-01 18:00:00";

            // Perform the mission creation
            String result = Controller.createNewMission(title, description, date, idUser);

            // Ensure the mission is created successfully
            assertEquals("Mission successfully created", result);

            // Retrieve the ID of the created mission (this assumes you have a method to get mission data)
            ArrayList<Mission> missions = Controller.getMissionsOfNeeder(idUser);
            assertFalse(missions.isEmpty());
            int testMissionId = missions.get(0).getIdMission();

            //accept
            Controller.acceptMission(testMissionId);
            missions.clear();
            missions = Controller.getMissionsOfNeeder(idUser);
            assertFalse(missions.isEmpty());
            assertEquals(Status.Accepted, missions.get(0).getStatus());

            //refuse
            Controller.refuseMission(testMissionId);
            missions.clear();
            missions = Controller.getMissionsOfNeeder(idUser);
            assertFalse(missions.isEmpty());
            assertEquals(Status.Refused, missions.get(0).getStatus());

            Controller.deleteMission(testMissionId);
        }
    }


}
