package view;

import controller.Controller;

import javax.swing.*;

public class MissionView {

    public static void add(int idUser){
        JFrame frame = new JFrame("AddMissionFrame");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        JLabel topPanel = new JLabel("AddMission", JLabel.CENTER);
        JLabel labelTitle = new JLabel("Title", JLabel.CENTER);
        JTextField textFieldTitle = new JTextField();
        JLabel labelDescription = new JLabel("Description", JLabel.CENTER);
        JTextField textFieldDescription = new JTextField();
        JLabel labelDate = new JLabel("Date", JLabel.CENTER);
        JTextField textFieldDate = new JTextField();

        JButton buttonAddMission = new JButton("add mission");

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        frame.getContentPane().add(mainPanel);
        mainPanel.add(topPanel);
        mainPanel.add(labelTitle);
        mainPanel.add(textFieldTitle);
        mainPanel.add(labelDescription);
        mainPanel.add(textFieldDescription);
        mainPanel.add(labelDate);
        mainPanel.add(textFieldDate);
        mainPanel.add(buttonAddMission);

        buttonAddMission.addActionListener(e -> {
            Controller.createNewMission(textFieldTitle.getText(), textFieldDescription.getText(), textFieldDate.getText(), idUser);

        });

    }
}
