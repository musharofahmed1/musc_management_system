import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class BloodBank extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JButton addDonorButton;
    private JButton editDonorButton;
    private JButton closeButton;

    public BloodBank() {
        setTitle("Blood Bank");
        setSize(1040, 508);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220, 220, 220));
        setLocation(230, 140);

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(46, 139, 87));
        JLabel titleLabel = new JLabel("Blood Bank");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBackground(new Color(46, 139, 87));
        JLabel bloodGroupLabel = new JLabel("Blood Group:");
        bloodGroupLabel.setForeground(new Color(255, 255, 255));
        Font font = new Font("Oswald", Font.PLAIN, 16);
        bloodGroupLabel.setFont(font);
        JComboBox<String> bloodGroupCombo = new JComboBox<>(new String[]{"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"});
        bloodGroupCombo.setBackground(new Color(46, 139, 87));
        bloodGroupCombo.setForeground(new Color(255, 255, 255));
        JLabel locationLabel = new JLabel("Search Location:");
        locationLabel.setForeground(new Color(255, 255, 255));
        locationLabel.setFont(font);

        JTextField locationTextField = new JTextField(15);
        JButton searchButton = new JButton("Search");
        Dimension buttonSize = new Dimension(90, 35); // Button size
        searchButton.setPreferredSize(buttonSize);
        Font buttonFont = new Font("Garamond", Font.BOLD, 18);
        searchButton.setFont(buttonFont);
        Color fontColor = Color.WHITE;
        searchButton.setForeground(fontColor);
        Color buttonColor = new Color(46, 139, 87);
        searchButton.setBackground(buttonColor);

        searchPanel.add(bloodGroupLabel);
        searchPanel.add(bloodGroupCombo);
        searchPanel.add(locationLabel);
        searchPanel.add(locationTextField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel();
        model.addColumn("Donor Name");
        model.addColumn("Age");
        model.addColumn("Blood Group");
        model.addColumn("Phone Number");
        model.addColumn("Address");
        model.addColumn("Last Donate(Date)");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBackground(new Color(46, 139, 87));

        // Add Donor Button
        addDonorButton = new JButton("Add Donor");
        addDonorButton.setBackground(new Color(46, 139, 87));
        addDonorButton.setForeground(Color.WHITE);
        addDonorButton.setPreferredSize(new Dimension(120, 40));
        addDonorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addDonor();
            }
        });

        // Edit Donor Button
        editDonorButton = new JButton("Edit Donor");
        editDonorButton.setBackground(new Color(46, 139, 87));
        editDonorButton.setForeground(Color.WHITE);
        editDonorButton.setPreferredSize(new Dimension(120, 40));
        editDonorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editDonor();
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

        buttonsPanel.add(addDonorButton);
        buttonsPanel.add(editDonorButton);
        buttonsPanel.add(closeButton);

        add(buttonsPanel, BorderLayout.SOUTH);

        // Search Button Action
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bloodGroup = (String) bloodGroupCombo.getSelectedItem();
                String location = locationTextField.getText();
                showSearchResults(bloodGroup, location);
            }
        });

        setVisible(true);
    }

    private void addDonor() {
        // Create an array to hold donor input
        String[] donorData = new String[6];

        // Show input dialogs for donor data
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        JTextField nameField = new JTextField(10);
        JTextField ageField = new JTextField(10);
        JComboBox<String> bloodGroupCombo = new JComboBox<>(new String[]{"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"});
        JTextField phoneField = new JTextField(10);
        JTextField addressField = new JTextField(10);
        JTextField lastDonateField = new JTextField(10);

        inputPanel.add(new JLabel("Donor Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Blood Group:"));
        inputPanel.add(bloodGroupCombo);
        inputPanel.add(new JLabel("Phone Number:"));
        inputPanel.add(phoneField);
        inputPanel.add(new JLabel("Address:"));
        inputPanel.add(addressField);
        inputPanel.add(new JLabel("Last Donate(Date):"));
        inputPanel.add(lastDonateField);

        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Add Donor", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        // Validate phone number
        String phoneNumber = phoneField.getText();
        if (!phoneNumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Phone number must contain only digits.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the phone number is unique
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 3).equals(phoneNumber)) {
                JOptionPane.showMessageDialog(this, "Phone number must be unique.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Add the donor data to the table
        donorData[0] = nameField.getText();
        donorData[1] = ageField.getText();
        donorData[2] = (String) bloodGroupCombo.getSelectedItem();
        donorData[3] = phoneNumber;
        donorData[4] = addressField.getText();
        donorData[5] = lastDonateField.getText();

        model.addRow(donorData);
    }

    private void editDonor() {
        String phoneNumber = JOptionPane.showInputDialog(this, "Enter Donor Phone Number:");
        if (phoneNumber == null) {
            return;
        }

        int rowToEdit = -1;
        for (int i = 0; i < table.getRowCount(); i++) {
            if (table.getValueAt(i, 3).equals(phoneNumber)) {
                rowToEdit = i;
                break;
            }
        }

        if (rowToEdit == -1) {
            JOptionPane.showMessageDialog(this, "Donor with phone number " + phoneNumber + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] options = {"Donor Name", "Age", "Blood Group", "Phone Number", "Address", "Last Donate(Date)"};
        int choice = JOptionPane.showOptionDialog(this,
                "Which field do you want to edit?", "Edit Donor",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        if (choice == -1) // User closed the dialog
            return;

        String newValue = JOptionPane.showInputDialog(this, "Enter new value:");
        if (newValue == null || newValue.isEmpty()) {
            return;
        }

        if (choice == 1) { // Age field
            try {
                int age = Integer.parseInt(newValue);
                model.setValueAt(age, rowToEdit, choice);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Age must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (choice == 3) { // Phone Number field
            if (!newValue.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Phone number must contain only digits.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the phone number is unique
            for (int i = 0; i < model.getRowCount(); i++) {
                if (i != rowToEdit && model.getValueAt(i, 3).equals(newValue)) {
                    JOptionPane.showMessageDialog(this, "Phone number must be unique.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            model.setValueAt(newValue, rowToEdit, choice);
        } else { // Other fields
            model.setValueAt(newValue, rowToEdit, choice);
        }
    }

    private void showSearchResults(String bloodGroup, String location) {
        JFrame searchResultsFrame = new JFrame("Search Results");
        searchResultsFrame.setSize(600, 400);
        searchResultsFrame.setLocationRelativeTo(this);

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(46, 139, 87));
        JLabel titleLabel = new JLabel("Search Results");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        searchResultsFrame.add(titlePanel, BorderLayout.NORTH);

        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);

        DefaultTableModel searchModel = new DefaultTableModel();
        searchModel.addColumn("Donor Name");
        searchModel.addColumn("Age");
        searchModel.addColumn("Blood Group");
        searchModel.addColumn("Phone Number");
        searchModel.addColumn("Address");
        searchModel.addColumn("Last Donate(Date)");

        // Mock data for demonstration
        //searchModel.addRow(new Object[]{"John Doe", 30, "O+", "1234567890", "123 Main St", "2024-04-01"});
        //searchModel.addRow(new Object[]{"Jane Smith", 25, "A-", "9876543210", "456 Elm St", "2024-03-15"});

        JTable searchTable = new JTable(searchModel);
        JScrollPane scrollPane = new JScrollPane(searchTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Close Button
        JButton closeSearchButton = new JButton("Close");
        closeSearchButton.setBackground(new Color(46, 139, 87));
        closeSearchButton.setForeground(Color.WHITE);
        closeSearchButton.setPreferredSize(new Dimension(120, 40));
        closeSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchResultsFrame.dispose(); // Close the window
            }
        });
        tablePanel.add(closeSearchButton, BorderLayout.SOUTH);

        searchResultsFrame.add(tablePanel, BorderLayout.CENTER);
        searchResultsFrame.setVisible(true);
    }

   /*  public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BloodBank();
            }
        });
    }*/
}
