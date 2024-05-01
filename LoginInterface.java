import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginInterface extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginInterface() {
        setTitle("Admin Login");
        setSize(500, 250); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10)); // Added gap between components
        setLocationRelativeTo(null);
        setResizable(false);

        JLabel welcomeLabel = new JLabel("Welcome Admin", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Garamond", Font.BOLD, 25)); 
        welcomeLabel.setForeground(new Color(46, 139, 87)); 
        welcomeLabel.setBackground(new Color(0,128,128));// Changed font color to orange
        welcomeLabel.setPreferredSize(new Dimension(150, 30)); 
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Garamond", Font.BOLD, 25));
        usernameLabel.setForeground(Color.BLACK); // Changed font color to black
        usernameLabel.setPreferredSize(new Dimension(150, 30)); 

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Garamond", Font.BOLD, 25));
        passwordLabel.setForeground(Color.BLACK); // Changed font color to black
        passwordLabel.setPreferredSize(new Dimension(150, 30));

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(150, 30)); 
        Font font = new Font("Oswald", Font.PLAIN, 22);
        usernameField.setForeground(new Color(46, 139, 87));
        usernameField.setFont(font);
        
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(150, 30)); 

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(46, 139, 87));
        loginButton.setFont(new Font("Arial", Font.BOLD, 20)); // Setting font size and style
        loginButton.setForeground(Color.WHITE); // Setting font color
        loginButton.addActionListener(this); 

        add(welcomeLabel);
        add(new JLabel()); 
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); 
        add(loginButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.equals("admin") && password.equals("admin123")) {
            // Open FrontPage upon successful login
            new FrontPage().setVisible(true);
            // Close the login interface window
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
        }

    }
    public void setPassword(String newPassword) {
        passwordField.setText(newPassword);
    }
    

    public static void main(String[] args) {
        new LoginInterface();
    }
}
