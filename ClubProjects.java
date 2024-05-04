import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ClubProjects extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JButton addEventButton;
    private JButton closeButton;

    private JTextField eventNameField;
    private JTextField eventDateField;
    private JTextField eventBudgetField;

    public ClubProjects() {
        setTitle("Club Projects");
        setSize(1040, 508);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220, 220, 220));
        setLocation(230, 140);

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(46, 139, 87));
        JLabel titleLabel = new JLabel("Club Projects");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel();
        model.addColumn("Event");
        model.addColumn("Date");
        model.addColumn("Budget");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBackground(new Color(46, 139, 87));

        // Add Event Button
        addEventButton = new JButton("Add Event");
        addEventButton.setBackground(new Color(46, 139, 87));
        addEventButton.setForeground(Color.WHITE);
        addEventButton.setPreferredSize(new Dimension(120, 40));
        addEventButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddEventDialog();
            }
        });

        // Close Button
        closeButton = new JButton("Close");
        closeButton.setBackground(new Color(46, 139, 87));
        closeButton.setForeground(Color.WHITE);
        closeButton.setPreferredSize(new Dimension(120, 40));
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });

        buttonsPanel.add(addEventButton);
        buttonsPanel.add(closeButton);

        add(buttonsPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void showAddEventDialog() {
        // Create the panel to hold input fields
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 5));

        // Event Name
        inputPanel.add(new JLabel("Event Name:"));
        eventNameField = new JTextField();
        inputPanel.add(eventNameField);

        // Event Date
        inputPanel.add(new JLabel("Event Date:"));
        eventDateField = new JTextField();
        inputPanel.add(eventDateField);

        // Event Budget
        inputPanel.add(new JLabel("Event Budget:"));
        eventBudgetField = new JTextField();
        inputPanel.add(eventBudgetField);

        // Show input dialog
        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Add Event",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Add the event data to the table
            String[] eventData = new String[3];
            eventData[0] = eventNameField.getText();
            eventData[1] = eventDateField.getText();
            eventData[2] = eventBudgetField.getText();

            model.addRow(eventData);
        }
    }

   /*  public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ClubProjects();
            }
        });
    }*/
}
