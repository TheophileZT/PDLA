package view;

import com.toedter.calendar.JDateChooser;
import controller.Controller;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MissionView {

    /**
     * Create the GUI for create mission view
     */
    public static void add(int idUser) {
        JFrame frame = new JFrame("AddMissionFrame");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Components
        JPanel mainPanel = new JPanel();
        JLabel topPanel = new JLabel("AddMission", JLabel.CENTER);
        JLabel labelTitle = new JLabel("Title", JLabel.CENTER);
        JTextField textFieldTitle = new JTextField();
        JLabel labelDescription = new JLabel("Description", JLabel.CENTER);
        JTextField textFieldDescription = new JTextField();
        JLabel labelDate = new JLabel("Date", JLabel.CENTER);
        JDateChooser date = new JDateChooser();
        JLabel labelHour = new JLabel("Time", JLabel.CENTER);
        SpinnerDateModel spinnerDateModel = new SpinnerDateModel();
        JSpinner timeSpinner = new JSpinner(spinnerDateModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        JButton buttonAddMission = new JButton("add mission");

        // Components properties
        timeSpinner.setEditor(timeEditor);
        date.setMinSelectableDate(new Date());
        date.setDateFormatString("yyyy-MM-dd");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Components positions
        frame.getContentPane().add(mainPanel);
        mainPanel.add(topPanel);
        mainPanel.add(labelTitle);
        mainPanel.add(textFieldTitle);
        mainPanel.add(labelDescription);
        mainPanel.add(textFieldDescription);
        mainPanel.add(labelDate);
        mainPanel.add(date);
        mainPanel.add(labelHour);
        mainPanel.add(timeSpinner);
        mainPanel.add(buttonAddMission);

        // Events listeners
        buttonAddMission.addActionListener(e -> {
            // Get selected date and time with proper format
            Date selectedDate = date.getDate();
            Date selectedTime = (Date) timeSpinner.getValue();

            Calendar calendarDate = Calendar.getInstance();
            calendarDate.setTime(selectedDate);

            Calendar calendarTime = Calendar.getInstance();
            calendarTime.setTime(selectedTime);

            calendarDate.set(Calendar.HOUR_OF_DAY, calendarTime.get(Calendar.HOUR_OF_DAY));
            calendarDate.set(Calendar.MINUTE, calendarTime.get(Calendar.MINUTE));
            calendarDate.set(Calendar.SECOND, calendarTime.get(Calendar.SECOND));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = dateFormat.format(calendarDate.getTime());
            System.out.println("Selected DateTime: " + dateTime);

            // Create mission
            String ret = Controller.createNewMission(textFieldTitle.getText(), textFieldDescription.getText(), dateTime, idUser);
            if (ret.equals("Mission successfully created")) {
                frame.dispose();
                ViewNeeder.create(idUser);
            } else {
                JOptionPane.showMessageDialog(frame, ret);
            }
        });

        frame.setVisible(true);
    }
}
