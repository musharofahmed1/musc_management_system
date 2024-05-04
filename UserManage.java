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
        addUserButton = new JButton("Add Member");
        addUserButton.setBackground(new Color(46, 139, 87));
        addUserButton.setForeground(Color.WHITE);
        addUserButton.setPreferredSize(new Dimension(120, 40));
        addUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addMember();
            }
        });

        // Edit User Button
        editUserButton = new JButton("Edit Member");
        editUserButton.setBackground(new Color(46, 139, 87));
        editUserButton.setForeground(Color.WHITE);
        editUserButton.setPreferredSize(new Dimension(120, 40));
        editUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editMember();
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

    private void addMember() {
        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        panel.add(nameField);
        panel.add(new JLabel("ID (Digits and '-' only):"));
        JTextField idField = new JTextField();
        panel.add(idField);
        panel.add(new JLabel("Batch:"));
        JTextField batchField = new JTextField();
        panel.add(batchField);
        panel.add(new JLabel("Department:"));
        JTextField deptField = new JTextField();
        panel.add(deptField);
        panel.add(new JLabel("Phone (Digits only):"));
        JTextField phoneField = new JTextField();
        panel.add(phoneField);
        panel.add(new JLabel("Email (@ required):"));
        JTextField emailField = new JTextField();
        panel.add(emailField);
        panel.add(new JLabel("Designation:"));
        JTextField designationField = new JTextField();
        panel.add(designationField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Member", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String[] rowData = {
                nameField.getText(),
                idField.getText(),
                batchField.getText(),
                deptField.getText(),
                phoneField.getText(),
                emailField.getText(),
                designationField.getText()
            };

            // Check uniqueness of ID, Phone, and Email
            if (isUnique(rowData[1], 1) && isUnique(rowData[4], 4) && isEmailValid(rowData[5])) {
                model.addRow(rowData);
            } else {
                JOptionPane.showMessageDialog(this, "ID, Phone, or Email is not unique, or Email is not valid.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editMember() {
        String id = JOptionPane.showInputDialog(this, "Enter ID of the member to edit:");
        if (id == null || id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int selectedRow = -1;
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 1).equals(id)) {
                selectedRow = i;
                break;
            }
        }

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Member with ID " + id + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] options = {"Name", "ID", "Batch", "Department", "Phone", "Email", "Designation"};
        int choice = JOptionPane.showOptionDialog(this,
                "Which field do you want to edit?", "Edit Member",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        if (choice == JOptionPane.CANCEL_OPTION) // User closed the dialog
            return;

        String newValue = JOptionPane.showInputDialog(this, "Enter new value for " + options[choice] + ":");

        if (newValue != null && !newValue.isEmpty()) {
            if (choice == 1 && !newValue.matches("\\d+-?\\d*")) { // Check ID format
                JOptionPane.showMessageDialog(this, "ID must contain digits and '-' only.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (choice == 4 && !newValue.matches("\\d*")) { // Check Phone format
                JOptionPane.showMessageDialog(this, "Phone must contain digits only.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (choice == 5 && !isEmailValid(newValue)) { // Check Email format
                JOptionPane.showMessageDialog(this, "Email format is invalid.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (isUnique(newValue, choice)) { // Check uniqueness of ID, Phone, and Email
                model.setValueAt(newValue, selectedRow, choice);
            } else {
                JOptionPane.showMessageDialog(this, options[choice] + " is not unique.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean isUnique(String value, int column) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (i != table.getSelectedRow() && model.getValueAt(i, column).equals(value)) {
                return false;
            }
        }
        return true;
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

   /* public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UserManage();
            }
        });
    }*/ 
}
