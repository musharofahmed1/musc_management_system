import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChangePasswordConfirmationDialog extends JDialog {
    public ChangePasswordConfirmationDialog(Frame parent) {
        super(parent, "Change Password", true);
        setSize(400, 150);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel message = new JLabel("Are you sure you want to change your password?");
        panel.add(message, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new EnterPreviousPasswordDialog(parent).setVisible(true);
            }
        });

        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
    }
}
