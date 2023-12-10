package model;

public class Mission {
    private final int idMission;
    private String title;
    private String description;
    private String date;
    private final int idNeeder;

    private int idHelper;

    private Status status;

    public Mission(int idMission, String title, String description, String date, int idNeeder, String status, int idHelper) {
        this.idMission = idMission;
        this.title = title;
        this.description = description;
        this.date = date;
        this.idNeeder = idNeeder;
        this.status = Status.valueOf(status);
        this.idHelper = idHelper;
    }

    public int getIdMission() {
        return idMission;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public int getIdHelper() {
        return idHelper;
    }

    public int getIdNeeder() {
        return idNeeder;
    }
    public String getDateTime() {
        return date;
    }

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
