package view;

import controller.Controller;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Arrays;

public class LogInView {

    public static void create() {
        //Create and set up the window.
        JFrame frame = new JFrame("LogInFrame");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel formPanel = new JPanel();
        JLabel labelTitle = new JLabel("Log In", JLabel.CENTER);
        JLabel labelEmail = new JLabel("Email");
        JTextField textFieldEmail = new JTextField();
        JLabel labelPassword = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField();
        JButton validateButton = new JButton("Validate");

        //Set the components properties.
        labelTitle.setFont(labelTitle.getFont().deriveFont(20.0f));
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        frame.getContentPane().add(formPanel, "North");
        frame.getContentPane().add(validateButton, "South");
        labelEmail.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        labelPassword.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        //Add the components to the panel.
        formPanel.add(labelTitle);
        formPanel.add(labelEmail);
        formPanel.add(textFieldEmail);
        formPanel.add(labelPassword);
        formPanel.add(passwordField);

        //Event listener
        validateButton.addActionListener(actionEvent -> {
                    String email = textFieldEmail.getText();
                    String password = Arrays.toString(passwordField.getPassword());
                    if (email.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please fill all the fields");
                    } else {
                        String[] ret;
                        try {
                            ret = Controller.logIn(email, password);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        if (ret[0].equals("Login successful")) {
                            frame.dispose();
                            switch (ret[1]) {
                                case "Needer" -> ViewNeeder.create(Integer.parseInt(ret[2]));
                                case "Helper" -> ViewHelper.create();
                                case "Validator" -> ViewValidator.create();
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, ret[0]);
                        }
                    }
                });

        //Display the window.
        frame.setVisible(true);
    }
}
