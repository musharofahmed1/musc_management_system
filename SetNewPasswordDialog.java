import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SetNewPasswordDialog extends JDialog {
    public SetNewPasswordDialog(Frame parent) {
        super(parent, "Set New Password", true);
        setSize(400, 200);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel message = new JLabel("Enter your new password:");
        panel.add(message, BorderLayout.NORTH);

        JPasswordField newPasswordField = new JPasswordField();
        newPasswordField.setPreferredSize(new Dimension(200, 25));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Here you can add your logic to save the new password
                JOptionPane.showMessageDialog(SetNewPasswordDialog.this, "Password changed successfully!");
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        panel.add(newPasswordField, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Here you can add your logic to save the new password
                String newPassword = new String(newPasswordField.getPassword());
                JOptionPane.showMessageDialog(SetNewPasswordDialog.this, "Password changed successfully!");
                
                // Setting the new password in the LoginInterface
                if (getParent() instanceof FrontPage) {
                    FrontPage parent = (FrontPage) getParent();
                    parent.getLoginInterface().setPassword(newPassword);
                }
                
                dispose();
            }
        });
        
    }
    
}
