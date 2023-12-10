package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MissionTest {

    @Test
    public void testGetters() {
        Mission mission = new Mission(1, "Test Mission", "Test Description", "2023-01-01 18:00:00", 2, "Pending", 3);

        assertEquals(1, mission.getIdMission());
        assertEquals("Test Mission", mission.getTitle());
        assertEquals("Test Description", mission.getDescription());
        assertEquals("2023-01-01 18:00:00", mission.getDateTime());
        assertEquals(2, mission.getIdNeeder());
        assertEquals("Pending", mission.getStatus().name());
        assertEquals(3, mission.getIdHelper());
    }

    @Test
    public void testMissionConstructor() {
        Mission mission = new Mission(1, "Test Mission", "Test Description", "2023-01-01 18:00:00", 2, "Pending", 3);

        assertEquals(1, mission.getIdMission());
        assertEquals("Test Mission", mission.getTitle());
        assertEquals("Test Description", mission.getDescription());
        assertEquals("2023-01-01 18:00:00", mission.getDateTime());
        assertEquals(2, mission.getIdNeeder());
        assertEquals("Pending", mission.getStatus().name());
        assertEquals(3, mission.getIdHelper());
    }

    @Test
    public void testToString() {
        Mission mission = new Mission(1, "Test Mission", "Test Description", "2023-01-01 18:00:00", 2, "Pending", 3);

        String expectedToString = "Mission{idMission=1, title='Test Mission', description='Test Description', date='2023-01-01 18:00:00', idNeeder=2, idHelper=3, status=Pending}";
        assertEquals(expectedToString, mission.toString());
    }

}
