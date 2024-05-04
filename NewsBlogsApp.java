import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class NewsBlogsApp extends JFrame {

    private JPanel titlePanel, contentPanel, buttonsPanel;
    private JLabel titleLabel;
    private JTextArea contentTextArea;
    private JButton saveButton, addButton, editButton, deleteButton, addTitleButton, showAllButton, closeButton;
    private ArrayList<String> newsBlogsList;

    public NewsBlogsApp() {
        setTitle("Notice Board");
        setSize(1040, 508);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220, 220, 220));
        setLocation(230, 140);

        newsBlogsList = new ArrayList<>();

        // Panel for Title
        titlePanel = new JPanel(new BorderLayout());
        titleLabel = new JLabel("News/Blogs");
        titlePanel.setBackground(new Color(46, 139, 87));
        titleLabel.setFont(new Font("Oswald", Font.BOLD | Font.ITALIC, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.setPreferredSize(new Dimension(1040, 60)); // Set the preferred size
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        // Panel for News/Blog Content
        contentPanel = new JPanel(new BorderLayout());
        contentTextArea = new JTextArea();
        contentTextArea.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase font size
        JScrollPane scrollPane = new JScrollPane(contentTextArea);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel for Buttons
        buttonsPanel = new JPanel();
        saveButton = new JButton("Save");
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        addTitleButton = new JButton("Add Title");
        showAllButton = new JButton("All News/Blogs");
        closeButton = new JButton("Close Window");
        
        // Set button properties
        JButton[] buttons = {saveButton, addButton, editButton, deleteButton, addTitleButton, showAllButton, closeButton};
        for (JButton button : buttons) {
            button.setBackground(new Color(46, 139, 87));
            button.setForeground(Color.WHITE);
            button.setPreferredSize(new Dimension(130, 30));
        }

        buttonsPanel.add(saveButton);
        buttonsPanel.add(addButton);
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(addTitleButton);
        buttonsPanel.add(showAllButton);
        buttonsPanel.add(closeButton);

        // Close Button Action
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Add Title Button Action
        addTitleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newTitle = JOptionPane.showInputDialog(NewsBlogsApp.this, "Enter new title:");
                if (newTitle != null && !newTitle.isEmpty()) {
                    titleLabel.setText(newTitle);
                }
            }
        });

        // Add Button Action
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(NewsBlogsApp.this, "Enter content:");
                if (input != null && !input.isEmpty()) {
                    contentTextArea.append(input + "\n");
                    newsBlogsList.add(input);
                }
            }
        });

        // Edit Button Action
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Title", "Content"};
                int choice = JOptionPane.showOptionDialog(NewsBlogsApp.this,
                        "What do you want to edit?", "Edit",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[0]);
                if (choice == 0) {
                    String newTitle = JOptionPane.showInputDialog(NewsBlogsApp.this, "Enter new title:");
                    if (newTitle != null && !newTitle.isEmpty()) {
                        titleLabel.setText(newTitle);
                    }
                } else if (choice == 1) {
                    String newContent = JOptionPane.showInputDialog(NewsBlogsApp.this, "Enter new content:");
                    if (newContent != null && !newContent.isEmpty()) {
                        contentTextArea.setText(newContent);
                    }
                }
            }
        });

        // Delete Button Action
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] options = new String[newsBlogsList.size()];
                for (int i = 0; i < newsBlogsList.size(); i++) {
                    options[i] = newsBlogsList.get(i);
                }
                String selectedContent = (String) JOptionPane.showInputDialog(NewsBlogsApp.this,
                        "Select content to delete:", "Delete",
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (selectedContent != null && !selectedContent.isEmpty()) {
                    contentTextArea.setText("");
                    newsBlogsList.remove(selectedContent);
                    for (String content : newsBlogsList) {
                        contentTextArea.append(content + "\n");
                    }
                }
            }
        });

        // Show All Button Action
        showAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder allContent = new StringBuilder();
                for (String content : newsBlogsList) {
                    allContent.append(content).append("\n");
                }
                JOptionPane.showMessageDialog(NewsBlogsApp.this, allContent.toString(), "All News/Blogs", JOptionPane.PLAIN_MESSAGE);
            }
        });

        // Set the layout for the main frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(titlePanel, BorderLayout.NORTH);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NewsBlogsApp();
            }
        }); 
    }*/
}
