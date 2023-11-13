package view;
import javax.swing.*;
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
        buttonSignUp.addActionListener(actionEvent -> createAndShowSignUpForm());
    }

    private static void createAndShowSignUpForm() {
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

        validateButton.addActionListener(actionEvent -> Controller.createNewUser(textFieldFirstName.getText(),
                textFieldLastName.getText(),
                textFieldEmail.getText(),
                Arrays.toString(passwordField.getPassword()),
                checkBoxIsNeeder.isSelected(),
                checkBoxIsVolunteer.isSelected(),
                checkBoxIsValidator.isSelected()
        ));

        validateButton.addActionListener(actionEvent -> frame.dispose());



        //Display the window.
        frame.setVisible(true);
    }

    private static void createAndShowLogInForm() {
        JFrame frame = new JFrame("LogInFrame");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create all the components.
        JPanel formPanel = new JPanel();
        JLabel labelTitle = new JLabel("Log In", JLabel.CENTER);
        JLabel emailLabel = new JLabel("Email");
        JTextField emailTextField = new JTextField();
        JLabel passwordLabel = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField();
        JButton connectButton = new JButton("Connection");

        // Set the components properties.
        labelTitle.setFont(labelTitle.getFont().deriveFont(20.0f));
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        frame.getContentPane().add(formPanel, "North");
        frame.getContentPane().add(connectButton, "South");

        // Add the components to the panel.
        formPanel.add(labelTitle);
        formPanel.add(emailLabel);
        formPanel.add(emailTextField);
        formPanel.add(passwordLabel);

        // Add event listeners.
        connectButton.addActionListener(actionEvent -> Controller.logIn(emailTextField.getText(), passwordField.getPassword()));

    }
}
