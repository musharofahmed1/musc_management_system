import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class EventHandle extends JFrame {
    private JTextField nameField, idField, deptField, aowField, eventNameField, dateField, timeField, dayNoField, supervisorField;
    private JTextArea searchTextArea;
    private JButton addVolunteerButton, removeVolunteerButton, searchButton, closeButton;
    private JTable table;
    private DefaultTableModel model;

    public EventHandle() {
        setTitle("Event Handle");
        setSize(1040, 508);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220, 220, 220));
        setLocation(230, 140);

        // Centre Panel
        JPanel centrePanel = new JPanel(new BorderLayout());
        centrePanel.setBackground(Color.WHITE);

        // Table
        String[] columnNames = {"Name", "ID", "Dept", "AOW", "Event Name", "Date", "Time", "Day No", "Supervisor"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        centrePanel.add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(46, 139, 87));

        addVolunteerButton = new JButton("Add Volunteer");
        removeVolunteerButton = new JButton("Remove Volunteer");
        closeButton = new JButton("Close Window");

        // Button Actions
        addVolunteerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddVolunteerPanel();
            }
        });

        removeVolunteerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeVolunteer();
            }
        });

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel.add(addVolunteerButton);
        buttonPanel.add(removeVolunteerButton);
        buttonPanel.add(closeButton);

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(46, 139, 87));
        searchTextArea = new JTextArea(1, 20);
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchVolunteer();
            }
        });
        searchPanel.add(searchTextArea);
        searchPanel.add(searchButton);

        // Add components to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(centrePanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(searchPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void openAddVolunteerPanel() {
        // Add volunteer functionality here
        // Panel opens to input volunteer details
        JPanel panel = new JPanel(new GridLayout(9, 2));
        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);
        panel.add(new JLabel("ID:"));
        idField = new JTextField();
        panel.add(idField);
        panel.add(new JLabel("Dept:"));
        deptField = new JTextField();
        panel.add(deptField);
        panel.add(new JLabel("AOW:"));
        aowField = new JTextField();
        panel.add(aowField);
        panel.add(new JLabel("Event Name:"));
        eventNameField = new JTextField();
        panel.add(eventNameField);
        panel.add(new JLabel("Date:"));
        dateField = new JTextField();
        panel.add(dateField);
        panel.add(new JLabel("Time:"));
        timeField = new JTextField();
        panel.add(timeField);
        panel.add(new JLabel("Day No:"));
        dayNoField = new JTextField();
        panel.add(dayNoField);
        panel.add(new JLabel("Supervisor:"));
        supervisorField = new JTextField();
        panel.add(supervisorField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Volunteer", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String[] rowData = {
                nameField.getText(),
                idField.getText(),
                deptField.getText(),
                aowField.getText(),
                eventNameField.getText(),
                dateField.getText(),
                timeField.getText(),
                dayNoField.getText(),
                supervisorField.getText()
            };
            model.addRow(rowData);
        }
    }

    private void removeVolunteer() {
        String id = JOptionPane.showInputDialog(null, "Enter ID to remove:");
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 1).equals(id)) {
                model.removeRow(i);
                break;
            }
        }
    }

    private void searchVolunteer() {
        String id = searchTextArea.getText();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 1).equals(id)) {
                String rowData = "";
                for (int j = 0; j < model.getColumnCount(); j++) {
                    rowData += model.getColumnName(j) + ": " + model.getValueAt(i, j) + "\n";
                }
                JOptionPane.showMessageDialog(null, rowData, "Volunteer Details", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Volunteer not found", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EventHandle();
            }
        });
    }
}
