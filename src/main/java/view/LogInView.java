package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Arrays;

public class LogInView {

    /**
     * Create the GUI and show it for the login view.
     */
    public static void create() {
        // Create and set up the window.
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(200, 200, 200));

        // Panel for form components
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel labelTitle = new JLabel("Login", JLabel.CENTER);
        labelTitle.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(labelTitle, gbc);

        // Email
        JLabel labelEmail = new JLabel("Email:");
        JTextField textFieldEmail = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(labelEmail, gbc);
        gbc.gridx = 1;
        formPanel.add(textFieldEmail, gbc);

        // Password
        JLabel labelPassword = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(labelPassword, gbc);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(46, 139, 87)); // SeaGreen
        loginButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(loginButton, gbc);

        // Sign Up Button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(new Color(255, 69, 0)); // Red-Orange
        signUpButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(signUpButton, gbc);

        // Event listeners
        loginButton.addActionListener(e -> {
            actionLogin(textFieldEmail, passwordField, frame);
        });

        signUpButton.addActionListener(e -> {
            frame.dispose();
            SignUpView.create();
        });

        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    actionLogin(textFieldEmail, passwordField, frame);
                }
            }
        });

        // Add components to the frame
        frame.add(formPanel, BorderLayout.CENTER);

        // Display the window.
        frame.setVisible(true);
    }

    /**
     * Action performed when the login button is clicked or the enter key is pressed
     * @param textFieldEmail the email text field
     * @param passwordField the password text field
     * @param frame the frame
     */
    private static void actionLogin(JTextField textFieldEmail, JPasswordField passwordField, JFrame frame) {
        String email = textFieldEmail.getText();
        String password = Arrays.toString(passwordField.getPassword());
        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in both fields");
        } else {
            String[] ret;
            try {
                ret = Controller.logIn(email, password);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (ret[0].equals("Login successful")) {
                frame.dispose();
                switch (ret[1]) {
                    case "Needer" -> ViewNeeder.create(Integer.parseInt(ret[2]));
                    case "Helper" -> ViewHelper.create(Integer.parseInt(ret[2]));
                    case "Validator" -> ViewValidator.create();
                }
            } else {
                JOptionPane.showMessageDialog(frame, ret[0]);
            }
        }
    }
}
