package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.sql.SQLException;
import java.util.Arrays;

public class SignUpView {

    /**
     * Create the GUI and show it for the sign-up view.
     */
    public static void create() {
        JFrame frame = new JFrame("SignUpFrame");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
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
        JButton validateButton = new JButton("Sign Up");
        JLabel passwordMismatchLabel = new JLabel("Passwords do not match");
        passwordMismatchLabel.setForeground(Color.RED); // Couleur du texte en rouge
        passwordMismatchLabel.setVisible(false); // Cacher le message au départ
        JCheckBox checkBoxIsNeeder = new JCheckBox("Needer");
        JCheckBox checkBoxIsHelper = new JCheckBox("Helper");
        JCheckBox checkBoxIsValidator = new JCheckBox("Validator");

        // Component properties
        labelTitle.setFont(labelTitle.getFont().deriveFont(24.0f));
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.X_AXIS));
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        frame.getContentPane().add(formPanel, BorderLayout.CENTER);
        frame.getContentPane().add(validateButton, BorderLayout.SOUTH);
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        labelFirstName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        labelLastName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        checkBoxIsNeeder.setSelected(true);
        validateButton.setEnabled(false);

        // Add components to panels
        typePanel.add(checkBoxIsNeeder);
        typePanel.add(checkBoxIsHelper);
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
        formPanel.add(passwordMismatchLabel);

        // Add spacing between components
        formPanel.add(Box.createVerticalStrut(10));

        // Add a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(validateButton);

        formPanel.add(buttonPanel);

        // Event listeners
        checkBoxIsNeeder.addActionListener(actionEvent -> {
            if (checkBoxIsNeeder.isSelected()) {
                checkBoxIsHelper.setSelected(false);
                checkBoxIsValidator.setSelected(false);
            }
        });

        checkBoxIsHelper.addActionListener(actionEvent -> {
            if (checkBoxIsHelper.isSelected()) {
                checkBoxIsNeeder.setSelected(false);
                checkBoxIsValidator.setSelected(false);
            }
        });

        checkBoxIsValidator.addActionListener(actionEvent -> {
            if (checkBoxIsValidator.isSelected()) {
                checkBoxIsNeeder.setSelected(false);
                checkBoxIsHelper.setSelected(false);
            }
        });

        // Document listener that verify that all fields are filled
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkIfFieldsAreFilled();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkIfFieldsAreFilled();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkIfFieldsAreFilled();
            }

            // Check if all fields are filled and if the passwords match
            private void checkIfFieldsAreFilled() {
                String firstName = textFieldFirstName.getText();
                String lastName = textFieldLastName.getText();
                String email = textFieldEmail.getText();
                char[] password = passwordField.getPassword();
                char[] confirmPassword = confirmPasswordField.getPassword();

                boolean fieldsFilled = !firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() &&
                        password.length > 0 && confirmPassword.length > 0;

                validateButton.setEnabled(fieldsFilled && Arrays.equals(password, confirmPassword));
            }
        };

        // Add the document listener to all fields
        textFieldFirstName.getDocument().addDocumentListener(documentListener);
        textFieldLastName.getDocument().addDocumentListener(documentListener);
        textFieldEmail.getDocument().addDocumentListener(documentListener);
        passwordField.getDocument().addDocumentListener(documentListener);
        confirmPasswordField.getDocument().addDocumentListener(documentListener);

        validateButton.addActionListener(actionEvent -> {
            try {
                String ret = Controller.createNewUser(textFieldFirstName.getText(),
                        textFieldLastName.getText(),
                        textFieldEmail.getText(),
                        Arrays.toString(passwordField.getPassword()),
                        checkBoxIsNeeder.isSelected(),
                        checkBoxIsHelper.isSelected(),
                        checkBoxIsValidator.isSelected()
                );
                if (ret.equals("User successfully created")) {
                    JOptionPane.showMessageDialog(frame, "User created successfully");
                    frame.dispose();
                    LogInView.create();
                } else if (ret.equals("User already exists")) {
                    JOptionPane.showMessageDialog(frame, "User already exists. Redirecting to Login Page");
                    frame.dispose();
                    LogInView.create();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        // Afficher la fenêtre.
        frame.setVisible(true);
    }
}
