package view;

import javax.swing.*;
public class ViewStart {

    public static void create() {
        //Create and set up the window.
        JFrame frame = new JFrame("LogInFrame");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panelButtons = new JPanel();
        JLabel labelText = new JLabel("Welcome to \"name of the app\"!");
        labelText.setHorizontalAlignment(JLabel.CENTER);
        labelText.setFont(labelText.getFont().deriveFont(15.0f));
        labelText.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 10));
        JButton buttonLogIn = new JButton("Log In");
        JButton buttonSignUp = new JButton("Sign Up");
        panelButtons.add(buttonLogIn);
        panelButtons.add(buttonSignUp);
        panelButtons.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
        frame.getContentPane().add(labelText, "North");
        frame.getContentPane().add(panelButtons);
        //Display the window.
        frame.setVisible(true);

        //ouverture du formulaire d'inscription
        buttonSignUp.addActionListener(actionEvent -> {
            SignUpView.create();
            frame.dispose();
        });

        buttonLogIn.addActionListener(actionEvent -> {
            LogInView.create();
            frame.dispose();
        });
    }

}