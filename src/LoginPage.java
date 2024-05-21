import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JButton adminButton;
    private JButton employeeButton;

    public LoginPage() {
        setTitle("Login Page");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        adminButton = new JButton("Admin");
        employeeButton = new JButton("Employee");

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminLoginDialog(LoginPage.this);
            }
        });

        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeePage();
                dispose();
            }
        });

        setLayout(new GridLayout(2, 1));
        add(adminButton);
        add(employeeButton);

        setVisible(true);
    }
}

class AdminLoginDialog extends JDialog {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public AdminLoginDialog(JFrame parent) {
        super(parent, "Admin Login", true);
        setLayout(new GridLayout(3, 2));
        setSize(300, 150);
        setLocationRelativeTo(parent);

        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel());
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Placeholder check for email and password. Replace with actual check.
                if (email.equals("admin@gmail.com") && password.equals("123")) {
                    new AdminPage();
                    dispose();
                    parent.dispose();
                } else {
                    JOptionPane.showMessageDialog(AdminLoginDialog.this, "Invalid email or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }
}
