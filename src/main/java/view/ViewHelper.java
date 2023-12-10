package view;

import controller.Controller;
import model.Mission;
import model.Status;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewHelper {
    public static void create(int idHelper) {
        JFrame frame = new JFrame("HelperFrame");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel labelTitle = new JLabel("Accepted Missions", JLabel.CENTER);
        topPanel.add(labelTitle);

        JPanel missionPanel = new JPanel();
        missionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        missionPanel.setLayout(new GridLayout(0, 2, 10, 10)); // Utilisation de GridLayout avec espacement

        ArrayList<Mission> missions = Controller.getAcceptedOrAssignedMission(idHelper);

        for (Mission mission : missions) {
            JPanel missionCard = new JPanel();
            missionCard.setLayout(new BorderLayout());
            missionCard.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            JPanel infoMissionPanel = new JPanel();
            infoMissionPanel.setLayout(new BoxLayout(infoMissionPanel, BoxLayout.Y_AXIS));

            JLabel labelID = new JLabel("ID: " + mission.getIdMission());
            JLabel labelName = new JLabel("Needer: " + Controller.getNameOfUser(mission.getIdNeeder()));
            JLabel labelTitleM = new JLabel("Title: " + mission.getTitle());
            JLabel descriptionLabel = new JLabel("Desc: " + mission.getDescription());
            if (descriptionLabel.getText().length() > 100) {
                descriptionLabel.setText(descriptionLabel.getText().substring(0, 100) + "...");
            }
            JLabel labelDateTime = new JLabel("Date: " + mission.getDateTime());
            JLabel statusLabel = new JLabel("Status: " + mission.getStatus());

            infoMissionPanel.add(labelID);
            infoMissionPanel.add(labelName);
            infoMissionPanel.add(labelTitleM);
            infoMissionPanel.add(descriptionLabel);
            infoMissionPanel.add(labelDateTime);
            infoMissionPanel.add(statusLabel);

            missionCard.add(infoMissionPanel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            if (mission.getStatus()== Status.Accepted) {

                JButton buttonAccept = new JButton("Accept");
                buttonAccept.setBackground(Color.BLUE);
                buttonPanel.add(buttonAccept);

                missionCard.add(buttonPanel, BorderLayout.SOUTH);

                buttonAccept.addActionListener(e -> {
                    String ret = Controller.assignMission(mission.getIdMission(), idHelper);
                    JOptionPane.showMessageDialog(frame, ret);
                    frame.dispose();
                    ViewHelper.create(idHelper);
                });
            } else if (mission.getStatus()== Status.Assigned) {
                JButton buttonComplete = new JButton("Complete");
                buttonComplete.setBackground(Color.GREEN);
                buttonPanel.add(buttonComplete);

                missionCard.add(buttonPanel, BorderLayout.SOUTH);

                buttonComplete.addActionListener(e -> {
                    String ret = Controller.completeMission(mission.getIdMission());
                    JOptionPane.showMessageDialog(frame, ret);
                    frame.dispose();
                    ViewHelper.create(idHelper);
                });
            }

            missionPanel.add(missionCard);
        }

        JScrollPane scrollPane = new JScrollPane(missionPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton buttonLogout = new JButton("Logout");
        buttonLogout.addActionListener(e -> {
            LogInView.create();
            frame.dispose();
        });
        bottomPanel.add(buttonLogout);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(mainPanel);

        frame.setVisible(true);
    }
}
