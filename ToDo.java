import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class ToDo extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JButton addTaskButton;
    private JButton markCompleteButton;
    private JButton closeButton;

    private JTextField taskNameField;
    private JTextField taskDeadlineField;

    public ToDo() {
        setTitle("To Do");
        setSize(1040, 508);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220, 220, 220));
        setLocation(230, 140);

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(46, 139, 87));
        JLabel titleLabel = new JLabel("To-Do List");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel();
        model.addColumn("Task");
        model.addColumn("Deadline");
        model.addColumn("Complete");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBackground(new Color(46, 139, 87));

        // Add Task Button
        addTaskButton = new JButton("Add Task");
        addTaskButton.setBackground(new Color(46, 139, 87));
        addTaskButton.setForeground(Color.WHITE);
        addTaskButton.setPreferredSize(new Dimension(120, 40));
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddTaskDialog();
            }
        });

        // Mark Complete Button
        markCompleteButton = new JButton("Mark Complete");
        markCompleteButton.setBackground(new Color(46, 139, 87));
        markCompleteButton.setForeground(Color.WHITE);
        markCompleteButton.setPreferredSize(new Dimension(150, 40));
        markCompleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                markTaskComplete();
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

        buttonsPanel.add(addTaskButton);
        buttonsPanel.add(markCompleteButton);
        buttonsPanel.add(closeButton);

        add(buttonsPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void showAddTaskDialog() {
        // Create the panel to hold input fields
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 5));

        // Task Name
        inputPanel.add(new JLabel("Task Name:"));
        taskNameField = new JTextField();
        inputPanel.add(taskNameField);

        // Task Deadline
        inputPanel.add(new JLabel("Deadline:"));
        taskDeadlineField = new JTextField();
        inputPanel.add(taskDeadlineField);

        // Task Complete Checkbox
        JCheckBox completeCheckBox = new JCheckBox("Complete");
        inputPanel.add(completeCheckBox);

        // Show input dialog
        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Add Task",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            // Add the task data to the table
            String[] taskData = new String[3];
            taskData[0] = taskNameField.getText();
            taskData[1] = taskDeadlineField.getText();
            taskData[2] = completeCheckBox.isSelected() ? "Yes" : "No";

            model.addRow(taskData);
        }
    }

    private void markTaskComplete() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a task to mark as complete.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        model.setValueAt("Yes", selectedRow, 2); // Mark task as complete
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ToDo();
            }
        });
    }
}
