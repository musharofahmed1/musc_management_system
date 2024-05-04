import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class NoticeBoard extends JFrame {
    private JTextArea noticeTextArea;
    private ArrayList<String> notices;
    private int currentIndex;

    public NoticeBoard() {
        setTitle("Notice Board");
        setSize(1040, 508);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220, 220, 220));
        setLocation(230, 140);

        // Initialize notices ArrayList
        notices = new ArrayList<>();
        currentIndex = -1;

        // Text Panel
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBackground(new Color(46, 139, 87));

        // Title Label
        JLabel titleLabel = new JLabel("NOTICE");
        titleLabel.setFont(new Font("Oswald", Font.BOLD | Font.ITALIC, 36));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        textPanel.add(titleLabel, BorderLayout.NORTH);

        // Notice Text Area
        noticeTextArea = new JTextArea(20, 40);
        noticeTextArea.setEditable(false);
        noticeTextArea.setFont(new Font("Arial", Font.BOLD, 24));
        noticeTextArea.setForeground(Color.BLACK);
        noticeTextArea.setLineWrap(true);
        noticeTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(noticeTextArea);

        textPanel.add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBackground(new Color(46, 139, 87));

        JButton previousButton = new JButton("Previous Notice");
        previousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPreviousNotice();
            }
        });
        

        JButton addButton = new JButton("Add Notice");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNotice();
            }
        });
        

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });

        Dimension buttonSize = new Dimension(220, 40); // Button size
        addButton.setPreferredSize(buttonSize);
        previousButton.setPreferredSize(buttonSize);
        closeButton.setPreferredSize(buttonSize);
        
        
        // Set font for buttons
        Font buttonFont = new Font("Garamond", Font.BOLD, 18);
        addButton.setFont(buttonFont);
        previousButton.setFont(buttonFont);
        closeButton.setFont(buttonFont);
        
        
        // Set font color for buttons
        Color fontColor = Color.WHITE;
        addButton.setForeground(fontColor);
        previousButton.setForeground(fontColor);
        closeButton.setForeground(fontColor);
        
        
        // Set background color for buttons
        Color buttonColor = new Color(46,139,87);
        addButton.setBackground(buttonColor);
        previousButton.setBackground(buttonColor);
        closeButton.setBackground(buttonColor);
        


        buttonPanel.add(previousButton);
        buttonPanel.add(addButton);
        buttonPanel.add(closeButton);

        // Add panels to frame
        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Display notices
        updateNoticeTextArea();
    }

    private void updateNoticeTextArea() {
        if (notices.isEmpty()) {
            noticeTextArea.setText("No notices available.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String notice : notices) {
                sb.append(notice).append("\n");
            }
            noticeTextArea.setText(sb.toString());
        }
    }

    private void addNotice() {
        String newNotice = JOptionPane.showInputDialog(this, "Enter new notice:");
        if (newNotice != null && !newNotice.trim().isEmpty()) {
            notices.add(newNotice);
            currentIndex = notices.size() - 1;
            updateNoticeTextArea();
        }
    }

    private void showPreviousNotice() {
        if (currentIndex >= 0 && currentIndex < notices.size()) {
            JOptionPane.showMessageDialog(this, notices.get(currentIndex), "Previous Notice", JOptionPane.INFORMATION_MESSAGE);
            currentIndex--;
            if (currentIndex < 0) currentIndex = notices.size() - 1;
        } else {
            JOptionPane.showMessageDialog(this, "No previous notice available.", "Previous Notice", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NoticeBoard().setVisible(true);
            }
        });
    }*/
}
