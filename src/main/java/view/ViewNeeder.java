package view;

import controller.Controller;
import model.Mission;
import model.Status;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewNeeder {
    public static void create(int userId) {
        JFrame frame = new JFrame("NeederFrame");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel missionPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        JLabel labelTitle = new JLabel("My Missions", JLabel.CENTER);
        JButton buttonAddMission = new JButton("Add Mission");

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        ArrayList<Mission> missions = Controller.getMissionsOfNeeder(userId);

        missionPanel.setLayout(new GridLayout(0, 2));

        for (Mission mission : missions) {
            JPanel missionCard = new JPanel();
            missionCard.setLayout(new BoxLayout(missionCard, BoxLayout.Y_AXIS));
            missionCard.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            JLabel labelID = new JLabel("ID: " + mission.getIdMission());
            JLabel labelTitleM = new JLabel("Title: " + mission.getTitle());
            JLabel descriptionLabel = new JLabel("Desc: " + mission.getDescription());
            if (descriptionLabel.getText().length() > 100) {
                descriptionLabel.setText(descriptionLabel.getText().substring(0, 100) + "...");
            }
            JLabel labelDateTime = new JLabel("Date: " + mission.getDateTime());
            JLabel labelStatus = new JLabel("Status: " + mission.getStatus());
            JLabel labelIDHelper= new JLabel();

            if (mission.getStatus()==Status.Assigned) {
                labelStatus.setForeground(Color.BLUE);
            } else if (mission.getStatus()== Status.Done) {
                labelStatus.setForeground(Color.GREEN);
            } else if (mission.getStatus()==Status.Refused) {
                labelStatus.setForeground(Color.RED);
            } else if (mission.getStatus() == Status.Pending) {
                labelStatus.setForeground(Color.ORANGE);
            }

            if (mission.getIdHelper() == 0) {
                labelIDHelper.setText("Helper: Not assigned");
            } else {
                String name = Controller.getNameOfUser(mission.getIdHelper());
                labelIDHelper.setText("Helper: " + name);
            }

            missionCard.add(labelID);
            missionCard.add(labelTitleM);
            missionCard.add(descriptionLabel);
            missionCard.add(labelDateTime);
            missionCard.add(labelStatus);
            missionCard.add(labelIDHelper);

            missionPanel.add(missionCard);
        }

        missionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        missionPanel.setMaximumSize(new Dimension(1000, 1000));

        JScrollPane scrollPane = new JScrollPane(missionPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        JButton buttonLogout = new JButton("Logout");
        buttonLogout.addActionListener(e -> {
            LogInView.create();
            frame.dispose();
        });

        frame.getContentPane().add(mainPanel);
        mainPanel.add(topPanel);
        mainPanel.add(scrollPane);
        mainPanel.add(bottomPanel);
        topPanel.add(labelTitle);
        topPanel.add(buttonAddMission);
        bottomPanel.add(buttonLogout);

        // Gestionnaire d'événements pour le bouton "Add Mission"
        buttonAddMission.addActionListener(e -> {
            MissionView.add(userId);
            frame.dispose();
        });

        frame.setVisible(true);
    }
}
