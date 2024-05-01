import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class EnterPreviousPasswordDialog extends JDialog {
    public EnterPreviousPasswordDialog(Frame parent) {
        super(parent, "Enter Previous Password", true);
        setSize(400, 150);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel message = new JLabel("Enter your previous password:");
        panel.add(message, BorderLayout.NORTH);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 25));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton nextButton = new JButton("Next");
        JButton cancelButton = new JButton("Cancel");

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                // Here you can add your logic to check if the password is correct
                if (password.equals("admin123")) {
                    dispose();
                    new SetNewPasswordDialog(parent).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(EnterPreviousPasswordDialog.this, "Incorrect password. Please try again.");
                    passwordField.setText("");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel.add(nextButton);
        buttonPanel.add(cancelButton);

        panel.add(passwordField, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
    }
}
