package view;

import controller.Controller;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

public class SignUpView {
    public static void create() {
        JFrame frame = new JFrame("SignUpFrame");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create all the components.
        JPanel typePanel = new JPanel();
        JPanel formPanel = new JPanel();
        JPanel namePanel = new JPanel();
        JLabel labelTitle = new JLabel("Sign Up", JLabel.CENTER);
        JLabel labelFirstName = new JLabel("First Name");
        JTextField textFieldFirstName = new JTextField();
        JLabel labelLastName = new JLabel("Last Name");
        JTextField textFieldLastName = new JTextField();
        JLabel labelEmail = new JLabel("Email");
        JTextField textFieldEmail = new JTextField();
        JLabel labelPassword = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField();
        JLabel labelConfirmPassword = new JLabel("Confirm Password");
        JPasswordField confirmPasswordField = new JPasswordField();
        JButton validateButton = new JButton("Validate");
        JCheckBox checkBoxIsNeeder = new JCheckBox("Needer");
        JCheckBox checkBoxIsVolunteer = new JCheckBox("Volunteer");
        JCheckBox checkBoxIsValidator = new JCheckBox("Validator");

        //Set the components properties.
        labelTitle.setFont(labelTitle.getFont().deriveFont(20.0f));
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.X_AXIS));
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        frame.getContentPane().add(formPanel, "North");
        frame.getContentPane().add(validateButton, "South");
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        labelFirstName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        labelLastName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));


        //Add the components to the panel.
        typePanel.add(checkBoxIsNeeder);
        typePanel.add(checkBoxIsVolunteer);
        typePanel.add(checkBoxIsValidator);

        namePanel.add(labelFirstName);
        namePanel.add(textFieldFirstName);
        namePanel.add(labelLastName);
        namePanel.add(textFieldLastName);


        formPanel.add(labelTitle);
        formPanel.add(namePanel);
        formPanel.add(typePanel);
        formPanel.add(labelEmail);
        formPanel.add(textFieldEmail);
        formPanel.add(labelPassword);
        formPanel.add(passwordField);
        formPanel.add(labelConfirmPassword);
        formPanel.add(confirmPasswordField);

        //Add event listeners.
        checkBoxIsNeeder.addActionListener(actionEvent -> {
            if (checkBoxIsNeeder.isSelected()) {
                checkBoxIsVolunteer.setSelected(false);
                checkBoxIsValidator.setSelected(false);
            }
        });

        checkBoxIsVolunteer.addActionListener(actionEvent -> {
            if (checkBoxIsVolunteer.isSelected()) {
                checkBoxIsNeeder.setSelected(false);
                checkBoxIsValidator.setSelected(false);
            }
        });

        checkBoxIsValidator.addActionListener(actionEvent -> {
            if (checkBoxIsValidator.isSelected()) {
                checkBoxIsNeeder.setSelected(false);
                checkBoxIsVolunteer.setSelected(false);
            }
        });

        confirmPasswordField.addActionListener(actionEvent -> {
            if (!Objects.equals(Arrays.toString(passwordField.getPassword()), Arrays.toString(confirmPasswordField.getPassword()))) {
                JOptionPane.showMessageDialog(frame, "Passwords don't match");
            }
        });

        validateButton.addActionListener( actionEvent -> {
            try {
                String ret = Controller.createNewUser(textFieldFirstName.getText(),
                        textFieldLastName.getText(),
                        null,
                        textFieldEmail.getText(),
                        Arrays.toString(passwordField.getPassword()),
                        checkBoxIsNeeder.isSelected(),
                        checkBoxIsVolunteer.isSelected(),
                        checkBoxIsValidator.isSelected()
                );
                if (ret.equals("User created")) {
                    JOptionPane.showMessageDialog(frame, ret);
                    frame.dispose();
                    LogInView.create();
                } else if (ret.equals("User already exists")) {
                    JOptionPane.showMessageDialog(frame, "Travel to Login Page", ret,1);
                    frame.dispose();
                    LogInView.create();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });



        //Display the window.
        frame.setVisible(true);
    }
}
