import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class NoticeBoard extends JFrame {
    private JTextArea noticeTextArea;
    private ArrayList<String> notices;

    public NoticeBoard() {
        setTitle("Notice Board");
        setSize(1040, 508);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window, not the whole application
        getContentPane().setBackground(new Color(220, 220, 220)); // Set background color
        setLocationRelativeTo(null); // Center the window
        setLocation(230,140);

        // Initialize notices ArrayList
        notices = new ArrayList<>();

        // Notice Text Area
        noticeTextArea = new JTextArea();
        noticeTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(noticeTextArea);

        // Buttons
        JButton addButton = new JButton("Add Notice");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNotice();
            }
        });

        JButton editButton = new JButton("Edit Notice");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editNotice();
            }
        });

        JButton deleteButton = new JButton("Delete Notice");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteNotice();
            }
        });

        // Layout
        setLayout(new BorderLayout());

        // Button Panel at the Top
        JPanel topButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topButtonPanel.add(addButton);
        topButtonPanel.add(editButton);
        topButtonPanel.add(deleteButton);
        add(topButtonPanel, BorderLayout.NORTH);

        // Close Button at the Bottom
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });
        JPanel bottomButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomButtonPanel.add(closeButton);
        add(bottomButtonPanel, BorderLayout.SOUTH);

        // Notice Text Area
        add(scrollPane, BorderLayout.CENTER);

        // Display notices
        updateNoticeTextArea();
    }

    private void updateNoticeTextArea() {
        noticeTextArea.setText("");
        for (String notice : notices) {
            noticeTextArea.append(notice + "\n");
        }
    }

    private void addNotice() {
        String newNotice = JOptionPane.showInputDialog(this, "Enter new notice:");
        if (newNotice != null && !newNotice.trim().isEmpty()) {
            notices.add(newNotice);
            updateNoticeTextArea();
        }
    }

    private void editNotice() {
        String selectedNotice = noticeTextArea.getSelectedText();
        if (selectedNotice != null && !selectedNotice.isEmpty()) {
            String editedNotice = JOptionPane.showInputDialog(this, "Edit notice:", selectedNotice);
            if (editedNotice != null && !editedNotice.trim().isEmpty()) {
                notices.set(notices.indexOf(selectedNotice), editedNotice);
                updateNoticeTextArea();
            }
        }
    }

    private void deleteNotice() {
        String selectedNotice = noticeTextArea.getSelectedText();
        if (selectedNotice != null && !selectedNotice.isEmpty()) {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this notice?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                notices.remove(selectedNotice);
                updateNoticeTextArea();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NoticeBoard().setVisible(true);
            }
        });
    }
}
