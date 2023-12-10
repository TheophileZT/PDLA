package view;

import javax.swing.*;
import java.awt.*;

public class ViewStart {

    /**
     * Create the GUI for the start page.
     */
    public static void create() {
        // Create and set up the window.
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Mettre en plein écran
        frame.setLayout(new BorderLayout());

        // Panel for buttons
        JPanel panelButtons = new JPanel(new FlowLayout());
        JLabel labelText = new JLabel("Welcome to \"name of the app\"!");
        labelText.setHorizontalAlignment(JLabel.CENTER);
        labelText.setFont(labelText.getFont().deriveFont(20.0f)); // Ajuster la taille de la police
        labelText.setBorder(BorderFactory.createEmptyBorder(50, 10, 0, 10)); // Ajuster les marges
        JButton buttonLogIn = new JButton("Log In");
        JButton buttonSignUp = new JButton("Sign Up");

        // Adjust buttons size
        Dimension buttonSize = new Dimension(150, 40);
        buttonLogIn.setPreferredSize(buttonSize);
        buttonSignUp.setPreferredSize(buttonSize);

        // Add buttons to the panel
        panelButtons.add(buttonLogIn);
        panelButtons.add(buttonSignUp);
        panelButtons.setBorder(BorderFactory.createEmptyBorder(100, 10, 10, 10)); // Ajuster les marges

        // Add components to the frame
        frame.getContentPane().add(labelText, BorderLayout.NORTH);
        frame.getContentPane().add(panelButtons, BorderLayout.CENTER);

        // Style the buttons to match the provided style
        buttonLogIn.setBackground(Color.GREEN);
        buttonLogIn.setForeground(Color.WHITE);
        buttonSignUp.setBackground(Color.ORANGE); // Red-Orange
        buttonSignUp.setForeground(Color.WHITE);



        // Action listeners
        buttonSignUp.addActionListener(e -> {
            SignUpView.create();
            frame.dispose();
        });

        buttonLogIn.addActionListener(e -> {
            LogInView.create();
            frame.dispose();
        });

        // Display the window.
        frame.setVisible(true);
    }
}
