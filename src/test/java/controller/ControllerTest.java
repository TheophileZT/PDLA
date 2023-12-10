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
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class ControllerTest {


    private static final String testUserEmail = "testuser@example.com";
    private static final String testUserPassword = "testPassword";
    private static int testUserId;


    @BeforeAll
    public static void setUp() throws SQLException {
        String firstName = "Test";
        String lastName = "User";
        String email = testUserEmail;
        String password = testUserPassword;

        String result = Controller.createNewUser(firstName, lastName, email, password, true, false, false);

        assertEquals("User successfully created", result);

        String[] loginResult = Controller.logIn(email, password);
        testUserId = Integer.parseInt(loginResult[2]);
    }

    @AfterAll
    public static void tearDown() {
        Controller.deleteUser(testUserId);
    }

    @Test
    public void testCreateNewUser() throws SQLException {
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

    @Test
    public void testLogIn() throws SQLException {
        String email = "testuser@example.com";
        String password = "testPassword";

        String[] result = Controller.logIn(email, password);

        assertEquals("Login successful", result[0]);
        assertEquals("Needer", result[1]);
        assertEquals(testUserId, Integer.parseInt(result[2]));
    }

    @Test
    public void testCreateExistingUser() throws SQLException{
        String firstName = "Test";
        String lastName = "User";
        String email = "testuser@example.com";
        String password = "testPassword";

        String result = Controller.createNewUser(firstName, lastName, email, password, true, false, false);

        assertEquals("User already exists", result);
    }

    @Test
    public void testCreateNewMission() {
        assumeTrue(Controller.bdd.conn != null);
        int idUser = testUserId;

        String title = "Test Mission";
        String description = "Test Description";
        String date = "2023-01-01 18:00:00";

        String result = Controller.createNewMission(title, description, date, idUser);

        assertEquals("Mission successfully created", result);

        ArrayList<Mission> missions = Controller.getMissionsOfNeeder(idUser);
        assertFalse(missions.isEmpty());
        int testMissionId = missions.get(0).getIdMission();
        Controller.deleteMission(testMissionId);
}

    @Test
    public void testAcceptOrRefuseMission(){
        assumeTrue(Controller.bdd.conn != null);
        int idUser = testUserId;

        String title = "Test Mission";
        String description = "Test Description";
        String date = "2023-01-01 18:00:00";

        String result = Controller.createNewMission(title, description, date, idUser);

        assertEquals("Mission successfully created", result);

        ArrayList<Mission> missions = Controller.getMissionsOfNeeder(idUser);
        assertFalse(missions.isEmpty());
        int testMissionId = missions.get(0).getIdMission();

        Controller.acceptMission(testMissionId);
        missions.clear();
        missions = Controller.getMissionsOfNeeder(idUser);
        assertFalse(missions.isEmpty());
        assertEquals(Status.Accepted, missions.get(0).getStatus());

        Controller.refuseMission(testMissionId);
        missions.clear();
        missions = Controller.getMissionsOfNeeder(idUser);
        assertFalse(missions.isEmpty());
        assertEquals(Status.Refused, missions.get(0).getStatus());

        Controller.deleteMission(testMissionId);
    }


}
