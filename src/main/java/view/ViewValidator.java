package view;

import controller.Controller;
import model.Mission;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewValidator {

    /**
     * Create the GUI for the validator view
     */
    public static void create() {
        JFrame frame = new JFrame("ValidatorFrame");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel labelTitle = new JLabel("Missions to Validate", JLabel.CENTER);
        topPanel.add(labelTitle);

        // Mission Panel
        JPanel missionPanel = new JPanel();
        missionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        missionPanel.setLayout(new GridLayout(0, 2, 10, 10)); // Utilisation de GridLayout avec espacement

        // Get missions
        ArrayList<Mission> missions = Controller.getMissionsToValidate();

        // Create mission cards
        for (Mission mission : missions) {
            JPanel missionCard = new JPanel();
            missionCard.setLayout(new BorderLayout());
            missionCard.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            JPanel infoMissionPanel = new JPanel();
            infoMissionPanel.setLayout(new BoxLayout(infoMissionPanel, BoxLayout.Y_AXIS));

            JLabel labelID = new JLabel("ID: " + mission.getIdMission());
            JLabel labelNeeder = new JLabel("Needer: " + Controller.getNameOfUser(mission.getIdNeeder()));
            JLabel labelTitleM = new JLabel("Title: " + mission.getTitle());
            JLabel descriptionLabel = new JLabel("Desc: " + mission.getDescription());
            if (descriptionLabel.getText().length() > 100) {
                descriptionLabel.setText(descriptionLabel.getText().substring(0, 100) + "...");
            }
            JLabel labelDateTime = new JLabel("Date: " + mission.getDateTime());

            infoMissionPanel.add(labelID);
            infoMissionPanel.add(labelNeeder);
            infoMissionPanel.add(labelTitleM);
            infoMissionPanel.add(descriptionLabel);
            infoMissionPanel.add(labelDateTime);

            missionCard.add(infoMissionPanel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            JButton buttonAccept = new JButton("Accept");
            JButton buttonRefuse = new JButton("Refuse");
            buttonAccept.setBackground(Color.GREEN);
            buttonRefuse.setBackground(Color.RED);
            buttonPanel.add(buttonAccept);
            buttonPanel.add(buttonRefuse);

            missionCard.add(buttonPanel, BorderLayout.SOUTH);

            // Add action listeners
            buttonAccept.addActionListener(e -> {
                Controller.acceptMission(mission.getIdMission());
                frame.dispose();
                ViewValidator.create();
            });

            buttonRefuse.addActionListener(e -> {
                Controller.refuseMission(mission.getIdMission());
                frame.dispose();
                ViewValidator.create();
            });

            missionPanel.add(missionCard);
        }

        // Create scroll pane if there are too many missions
        JScrollPane scrollPane = new JScrollPane(missionPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton buttonLogout = new JButton("Logout");
        buttonLogout.addActionListener(e -> {
            LogInView.create();
            frame.dispose();
        });
        bottomPanel.add(buttonLogout);

        // Add panels to main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);

        frame.setVisible(true);
    }
}
