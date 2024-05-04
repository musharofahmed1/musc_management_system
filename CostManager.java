import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class CostManager extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JButton addCostButton;
    private JButton addEventButton;
    private JButton closeButton;

    public CostManager() {
        setTitle("Cost Manager");
        setSize(1040, 508);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220, 220, 220));
        setLocation(230, 140);

        // Title Panel
        //JPanel titlePanel = new JPanel();
        //titlePanel.setBackground(new Color(46, 139, 87));
        //JLabel titleLabel = new JLabel("COST MANAGER");
        //titleLabel.setForeground(Color.WHITE);
       // titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        //titlePanel.add(titleLabel);
        //add(titlePanel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel();
        model.addColumn("Event Name");
        model.addColumn("Year");
        model.addColumn("Date");
        model.addColumn("AOC");
        model.addColumn("Amount");
        model.addColumn("Author");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.setBackground(new Color(46, 139, 87));

        // Add Cost Button
        addCostButton = new JButton("Add Cost");
        addCostButton.setBackground(new Color(46, 139, 87));
        addCostButton.setForeground(Color.WHITE);
        addCostButton.setPreferredSize(new Dimension(120, 40));
        addCostButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCost();
            }
        });

        // Add Event Button
        addEventButton = new JButton("Add New Event");
        addEventButton.setBackground(new Color(46, 139, 87));
        addEventButton.setForeground(Color.WHITE);
        addEventButton.setPreferredSize(new Dimension(150, 40));
        addEventButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addEvent();
            }
        });

        // Close Button
        closeButton = new JButton("Close Window");
        closeButton.setBackground(new Color(46, 139, 87));
        closeButton.setForeground(Color.WHITE);
        closeButton.setPreferredSize(new Dimension(150, 40));
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });

        buttonsPanel.add(addCostButton);
        buttonsPanel.add(addEventButton);
        buttonsPanel.add(closeButton);

        add(buttonsPanel, BorderLayout.SOUTH);

        // Search Bar Panel
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.setBackground(new Color(46, 139, 87));
        JLabel searchLabel = new JLabel("Search Event Cost:");
        searchLabel.setForeground(new Color(255,255,255));
        Font font = new Font("Oswald", Font.PLAIN, 16);
        searchLabel.setFont(font);
        JTextField searchTextField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(46, 139, 87));
        searchButton.setForeground(new Color(255,255,255));
        searchButton.setFont(font);

        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // Search Button Action
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String eventName = searchTextField.getText();
                searchEventCost(eventName);
            }
        });

        setVisible(true);
    }

    private void addCost() {
        // Create an array to hold cost input
        String[] costData = new String[6];

        // Show input dialogs for cost data
        costData[0] = JOptionPane.showInputDialog(this, "Enter Event Name:");
        costData[1] = JOptionPane.showInputDialog(this, "Enter Year:");
        costData[2] = JOptionPane.showInputDialog(this, "Enter Date:");
        costData[3] = JOptionPane.showInputDialog(this, "Enter AOC:");
        costData[4] = JOptionPane.showInputDialog(this, "Enter Amount:");
        costData[5] = JOptionPane.showInputDialog(this, "Enter Author:");

        // Add the cost data to the table
        model.addRow(costData);
    }

    private void addEvent() {
        // Dummy method for adding a new event
        JOptionPane.showMessageDialog(this, "Functionality for adding a new event will be implemented soon.");
    }

    private void searchEventCost(String eventName) {
        // Dummy method for searching event cost
        JOptionPane.showMessageDialog(this, "Functionality for searching event cost will be implemented soon.");
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CostManager();
            }
        });
    } */
}
