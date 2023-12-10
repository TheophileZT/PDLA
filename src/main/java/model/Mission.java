package model;

/**
 * Mission class
 */
public class Mission {
    private final int idMission;
    private final String title;
    private final String description;
    private final String date;
    private final int idNeeder;

    private final int idHelper;

    private final Status status;

    /**
     * Constructor of the Mission class
     * @param idMission the id of the mission
     * @param title the title of the mission
     * @param description the description of the mission
     * @param date the date of the mission
     * @param idNeeder the id of the needer
     * @param status the status of the mission
     * @param idHelper the id of the helper
     */
    public Mission(int idMission, String title, String description, String date, int idNeeder, String status, int idHelper) {
        this.idMission = idMission;
        this.title = title;
        this.description = description;
        this.date = date;
        this.idNeeder = idNeeder;
        this.status = Status.valueOf(status);
        this.idHelper = idHelper;
    }

    /**
     * Getter of the id of the mission
     * @return the id of the mission
     */
    public int getIdMission() {
        return idMission;
    }

    /**
     * Getter of the title of the mission
     * @return the title of the mission
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter of the description of the mission
     * @return the description of the mission
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter of the status of the mission
     * @return the status of the mission
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Getter of the id of the helper
     * @return the id of the helper
     */
    public int getIdHelper() {
        return idHelper;
    }

    /**
     * Getter of the id of the needer
     * @return the id of the needer
     */
    public int getIdNeeder() {
        return idNeeder;
    }

    /**
     * Getter of the date and hour of the mission
     * @return the date of the mission
     */
    public String getDateTime() {
        return date;
    }

    /**
     * toString method of the Mission class
     * @return the string of the mission
     */
    public String toString() {
        return "Mission{" +
                "idMission=" + idMission +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", idNeeder=" + idNeeder +
                ", idHelper=" + idHelper +
                ", status=" + status +
                '}';
    }


}
