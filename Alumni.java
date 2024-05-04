import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Alumni extends JFrame {
    private JTextField nameField, idField, deptField, designationField, exCommitteeField, phoneField, addressField, presentAddressField, bloodGroupField, mailField;
    private JTextArea searchTextArea;
    private JButton addAlumniButton, editAlumniButton, searchButton, closeButton;
    private JTable table;
    private DefaultTableModel model;

    public Alumni() {
        setTitle("Alumni");
        setSize(1040, 508);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220, 220, 220));
        setLocation(230, 140);
        // Centre Panel
        JPanel centrePanel = new JPanel(new BorderLayout());
        centrePanel.setBackground(Color.WHITE);

        // Table
        String[] columnNames = {"Name", "ID", "Dept", "Designation", "Ex Committee", "Phone", "Address", "Present Address", "Blood Group", "Mail"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        centrePanel.add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(46, 139, 87));

        Font font = new Font("Oswald", Font.PLAIN, 16);
        addAlumniButton = new JButton("Add Alumni");
        addAlumniButton.setBackground(new Color(46,139,87));
        addAlumniButton.setForeground(new Color(255,255,255)); 
        addAlumniButton.setFont(font);
        editAlumniButton = new JButton("Edit Alumni");
        editAlumniButton.setBackground(new Color(46,139,87));
        editAlumniButton.setForeground(new Color(255,255,255)); 
        editAlumniButton.setFont(font);
        searchButton = new JButton("Search");
        searchButton.setBackground(new Color(46,139,87));
        searchButton.setForeground(new Color(255,255,255)); 
        searchButton.setFont(font);
        closeButton = new JButton("Close Window");
        closeButton.setBackground(new Color(46,139,87));
        closeButton.setForeground(new Color(255,255,255)); 
        closeButton.setFont(font);

        // Button Actions
        addAlumniButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddAlumniPanel();
            }
        });

        editAlumniButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editAlumni();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchAlumni();
            }
        });

        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel.add(addAlumniButton);
        buttonPanel.add(editAlumniButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(closeButton);

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(46, 139, 87));
        searchTextArea = new JTextArea(1, 20);
        searchPanel.add(searchTextArea);
        searchPanel.add(searchButton);

        // Add components to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(centrePanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(searchPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    private void openAddAlumniPanel() {
        // Add alumni functionality here
        // Panel opens to input alumni details
        JPanel panel = new JPanel(new GridLayout(11, 2));
        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);
        panel.add(new JLabel("ID:"));
        idField = new JTextField();
        panel.add(idField);
        panel.add(new JLabel("Dept:"));
        deptField = new JTextField();
        panel.add(deptField);
        panel.add(new JLabel("Designation:"));
        designationField = new JTextField();
        panel.add(designationField);
        panel.add(new JLabel("Ex Committee:"));
        exCommitteeField = new JTextField();
        panel.add(exCommitteeField);
        panel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        panel.add(phoneField);
        panel.add(new JLabel("Address:"));
        addressField = new JTextField();
        panel.add(addressField);
        panel.add(new JLabel("Present Address:"));
        presentAddressField = new JTextField();
        panel.add(presentAddressField);
        panel.add(new JLabel("Blood Group:"));
        bloodGroupField = new JTextField();
        panel.add(bloodGroupField);
        panel.add(new JLabel("Mail:"));
        mailField = new JTextField();
        panel.add(mailField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Alumni", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String[] rowData = {
                nameField.getText(),
                idField.getText(),
                deptField.getText(),
                designationField.getText(),
                exCommitteeField.getText(),
                phoneField.getText(),
                addressField.getText(),
                presentAddressField.getText(),
                bloodGroupField.getText(),
                mailField.getText()
            };
            model.addRow(rowData);
        }
    }

    private void editAlumni() {
        String id = JOptionPane.showInputDialog(null, "Enter ID to edit:");
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 1).equals(id)) {
                String[] options = {"Name", "ID", "Dept", "Designation", "Ex Committee", "Phone", "Address", "Present Address", "Blood Group", "Mail"};
                String choice = (String) JOptionPane.showInputDialog(null, "Select attribute to edit:", "Edit Alumni", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                if (choice != null) {
                    String newValue = JOptionPane.showInputDialog(null, "Enter new value for " + choice + ":");
                    model.setValueAt(newValue, i, getIndexForColumnName(choice));
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Alumni not found", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private int getIndexForColumnName(String columnName) {
        for (int i = 0; i < table.getColumnCount(); i++) {
            if (table.getColumnName(i).equals(columnName)) {
                return i;
            }
        }
        return -1;
    }

    private void searchAlumni() {
        String id = searchTextArea.getText();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 1).equals(id)) {
                String rowData = "";
                for (int j = 0; j < model.getColumnCount(); j++) {
                    rowData += model.getColumnName(j) + ": " + model.getValueAt(i, j) + "\n";
                }
                JOptionPane.showMessageDialog(null, rowData, "Alumni Details", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Alumni not found", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Alumni();
            }
        });
    }
}

