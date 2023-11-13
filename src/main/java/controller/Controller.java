package controller;

public class Controller {

    Connection connect = Controller.getConnection(srv-bdens.insa-toulouse.fr, projet_gei_011, shu6AeNg);
    Statement statement = connect.createStatement();
    String query = "SELECT * FROM User";
    public static void logIn(String text, char[] password) {

    }
    public static void createNewUser(String firstName, String lastName, String email, String password, boolean isNeeder, boolean isVolunteer, boolean isValidator) {

    }
}
