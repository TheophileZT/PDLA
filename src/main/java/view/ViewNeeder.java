package view;

import javax.swing.*;
import java.awt.*;

public class ViewNeeder {
    public static void create(int idUser) {

        JFrame frame = new JFrame("NeederFrame");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel missionPanel = new JPanel();

        JLabel labelTitle = new JLabel("MesMissions", JLabel.CENTER);
        JButton buttonAddMission = new JButton("add mission");

        mainPanel.setLayout(new BorderLayout());
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        missionPanel.setLayout(new BoxLayout(missionPanel, BoxLayout.Y_AXIS));

        frame.getContentPane().add(mainPanel, "North");
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(missionPanel, BorderLayout.CENTER);
        topPanel.add(labelTitle);
        topPanel.add(buttonAddMission);

        buttonAddMission.addActionListener(e -> {
            MissionView.add(idUser);

        });

        frame.setVisible(true);
    }
}
