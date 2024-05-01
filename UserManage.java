import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class UserManage extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JButton addUserButton;
    private JButton editUserButton;
    private JButton closeButton;

    public UserManage() {
        setTitle("User Manage");
        setSize(1040, 508);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220, 220, 220));
        setLocation(230, 140);

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(46, 139, 87));
        JLabel titleLabel = new JLabel("Members");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("ID");
        model.addColumn("Batch");
        model.addColumn("Department");
        model.addColumn("Phone");
        model.addColumn("Email");
        model.addColumn("Designation");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBackground(new Color(46, 139, 87));

        // Add User Button
        addUserButton = new JButton("Add User");
        addUserButton.setBackground(new Color(46, 139, 87));
        addUserButton.setForeground(Color.WHITE);
        addUserButton.setPreferredSize(new Dimension(120, 40));
        addUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });

        // Edit User Button
        editUserButton = new JButton("Edit User");
        editUserButton.setBackground(new Color(46, 139, 87));
        editUserButton.setForeground(Color.WHITE);
        editUserButton.setPreferredSize(new Dimension(120, 40));
        editUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editUser();
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

        buttonsPanel.add(addUserButton);
        buttonsPanel.add(editUserButton);
        buttonsPanel.add(closeButton);

        add(buttonsPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addUser() {
        // Create an array to hold user input
        String[] userData = new String[7];

        // Show input dialogs for user data
        userData[0] = JOptionPane.showInputDialog(this, "Enter Name:");
        userData[1] = JOptionPane.showInputDialog(this, "Enter ID:");
        userData[2] = JOptionPane.showInputDialog(this, "Enter Batch:");
        userData[3] = JOptionPane.showInputDialog(this, "Enter Department:");
        userData[4] = JOptionPane.showInputDialog(this, "Enter Phone:");
        userData[5] = JOptionPane.showInputDialog(this, "Enter Email:");
        userData[6] = JOptionPane.showInputDialog(this, "Enter Designation:");

        // Add the user data to the table
        model.addRow(userData);
    }

    private void editUser() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] options = {"Name", "ID", "Batch", "Department", "Phone", "Email", "Designation"};
        int choice = JOptionPane.showOptionDialog(this,
                "Which field do you want to edit?", "Edit User",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        if (choice == -1) // User closed the dialog
            return;

        String newValue = JOptionPane.showInputDialog(this, "Enter new value:");

        if (newValue != null && !newValue.isEmpty()) {
            model.setValueAt(newValue, selectedRow, choice);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UserManage();
            }
        });
    }
}
