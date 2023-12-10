package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.sql.SQLException;
import java.util.Arrays;

public class SignUpView {

    public static void create() {
        JFrame frame = new JFrame("SignUpFrame");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Mettre en plein écran
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Créer tous les composants
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
        JCheckBox checkBoxIsVolunteer = new JCheckBox("Volunteer");
        JCheckBox checkBoxIsValidator = new JCheckBox("Validator");

        // Propriétés des composants
        labelTitle.setFont(labelTitle.getFont().deriveFont(24.0f));
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.X_AXIS));
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        frame.getContentPane().add(formPanel, BorderLayout.CENTER);
        frame.getContentPane().add(validateButton, BorderLayout.SOUTH);
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        labelFirstName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        labelLastName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        checkBoxIsNeeder.setSelected(true); // Cocher par défaut
        validateButton.setEnabled(false); // Désactiver le bouton de validation

        // Ajouter les composants au panneau
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
        formPanel.add(passwordMismatchLabel); // Ajouter le label d'incohérence

        // Ajouter des espacements
        formPanel.add(Box.createVerticalStrut(10));

        // Ajouter un panneau pour aligner le bouton au centre
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(validateButton);

        // Ajouter le panneau de boutons à la fin du formulaire
        formPanel.add(buttonPanel);

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

        // Ajouter des écouteurs d'événements
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
                        checkBoxIsVolunteer.isSelected(),
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
