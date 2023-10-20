package view;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;

import controller.Controller;
public class Vue {

    public static void createAndShowGui() {
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
        buttonSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                createAndShowSignUpForm();
            }
        });
    }

    private static void createAndShowSignUpForm() {
        JFrame frame = new JFrame("SignUpFrame");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create all the components.
        JPanel typePanel = new JPanel();
        JPanel formPanel = new JPanel();
        JLabel labelTitle = new JLabel("Sign Up", JLabel.CENTER);
        JLabel labelName = new JLabel("Name");
        JTextField textFieldName = new JTextField();
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

        //Add the components to the panel.
        typePanel.add(checkBoxIsNeeder);
        typePanel.add(checkBoxIsVolunteer);
        typePanel.add(checkBoxIsValidator);

        formPanel.add(labelTitle);
        formPanel.add(labelName);
        formPanel.add(textFieldName);
        formPanel.add(typePanel);
        formPanel.add(labelEmail);
        formPanel.add(textFieldEmail);
        formPanel.add(labelPassword);
        formPanel.add(passwordField);
        formPanel.add(labelConfirmPassword);
        formPanel.add(confirmPasswordField);

        //Add event listeners.
        checkBoxIsNeeder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (checkBoxIsNeeder.isSelected()) {
                    checkBoxIsVolunteer.setSelected(false);
                    checkBoxIsValidator.setSelected(false);
                }
            }
        });

        checkBoxIsVolunteer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (checkBoxIsVolunteer.isSelected()) {
                    checkBoxIsNeeder.setSelected(false);
                    checkBoxIsValidator.setSelected(false);
                }
            }
        });

        checkBoxIsValidator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (checkBoxIsValidator.isSelected()) {
                    checkBoxIsNeeder.setSelected(false);
                    checkBoxIsVolunteer.setSelected(false);
                }
            }
        });

        confirmPasswordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!Objects.equals(Arrays.toString(passwordField.getPassword()), Arrays.toString(confirmPasswordField.getPassword()))) {
                    JOptionPane.showMessageDialog(frame, "Passwords don't match");
                }
            }
        });

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Controller.createNewUser(labelTitle.getText(),
                        textFieldName.getText(),
                        textFieldEmail.getText(),
                        Arrays.toString(passwordField.getPassword()),
                        checkBoxIsNeeder.isSelected(),
                        checkBoxIsVolunteer.isSelected(),
                        checkBoxIsValidator.isSelected()
                );
            }
        });



        //Display the window.
        frame.setVisible(true);
    }
}
