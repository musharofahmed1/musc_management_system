import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Resource extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JButton addResourceButton;
    private JButton removeResourceButton;
    private JButton closeButton;

    public Resource() {
        setTitle("Club Resources");
        setSize(1040, 508);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220, 220, 220));
        setLocation(230, 140);

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(46, 139, 87));
        JLabel titleLabel = new JLabel("Club Resources");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel();
        model.addColumn("Resource Name");
        model.addColumn("Quantity");
        model.addColumn("Condition");
        model.addColumn("Location");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBackground(new Color(46, 139, 87));

        // Add Resource Button
        addResourceButton = new JButton("Add Resource");
        addResourceButton.setBackground(new Color(46, 139, 87));
        addResourceButton.setForeground(Color.WHITE);
        addResourceButton.setPreferredSize(new Dimension(120, 40));
        addResourceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addResource();
            }
        });

        // Remove Resource Button
        removeResourceButton = new JButton("Remove Resource");
        removeResourceButton.setBackground(new Color(46, 139, 87));
        removeResourceButton.setForeground(Color.WHITE);
        removeResourceButton.setPreferredSize(new Dimension(150, 40));
        removeResourceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeResource();
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

        buttonsPanel.add(addResourceButton);
        buttonsPanel.add(removeResourceButton);
        buttonsPanel.add(closeButton);

        add(buttonsPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addResource() {
        // Create an array to hold resource input
        String[] resourceData = new String[4];

        // Show input dialogs for resource data
        resourceData[0] = JOptionPane.showInputDialog(this, "Enter Resource Name:");
        resourceData[1] = JOptionPane.showInputDialog(this, "Enter Quantity:");
        resourceData[2] = JOptionPane.showInputDialog(this, "Enter Condition:");
        resourceData[3] = JOptionPane.showInputDialog(this, "Enter Location:");

        // Add the resource data to the table
        model.addRow(resourceData);
    }

    private void removeResource() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this resource?", "Confirm Removal", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            model.removeRow(selectedRow);
        }
    }

    /* public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Resource();
            }
        });
    }*/
}
