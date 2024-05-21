import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EmployeePage extends JFrame {
    private JButton checkInButton;
    private JButton checkOutButton;

    public EmployeePage() {
        setTitle("Employee Page");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        checkInButton = new JButton("Check In");
        checkOutButton = new JButton("Check Out");

        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int employeeId = 1; // Replace with actual employee ID
                    AttendanceDAO attendanceDAO = new AttendanceDAO();
                    attendanceDAO.checkIn(employeeId);
                    JOptionPane.showMessageDialog(EmployeePage.this, "Check in successful!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(EmployeePage.this, "Error: Unable to check in!");
                }
            }
        });

        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int employeeId = 1; // Replace with actual employee ID
                    AttendanceDAO attendanceDAO = new AttendanceDAO();
                    attendanceDAO.checkOut(employeeId);
                    JOptionPane.showMessageDialog(EmployeePage.this, "Check out successful!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(EmployeePage.this, "Error: Unable to check out!");
                }
            }
        });

        setLayout(new GridLayout(2, 1));
        add(checkInButton);
        add(checkOutButton);

        setVisible(true);
    }
}
