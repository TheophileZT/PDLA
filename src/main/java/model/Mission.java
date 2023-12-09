package model;

public class Mission {
    private int idMission;
    private String title;
    private String description;
    private String date;
    private int idNeeder;

    private int idHelper;

    private Status status;

    public Mission(int idMission, String title, String description, String date, int idNeeder, String status) {
        this.idMission = idMission;
        this.title = title;
        this.description = description;
        this.date = date;
        this.idNeeder = idNeeder;
        this.status = Status.valueOf(status);
    }

    public int getIdMission() {
        return idMission;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdNeeder() {
        return idNeeder;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getIdHelper() {
        return idHelper;
    }

    public void setIdHelper(int idHelper) {
        this.idHelper = idHelper;
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
