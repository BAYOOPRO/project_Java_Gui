import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class AdminPage extends JFrame {
    private JButton addEmployeeButton;
    private JButton viewEmployeesButton;
    private JButton viewAttendanceButton;

    public AdminPage() {
        setTitle("Admin Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addEmployeeButton = new JButton("Add Employee");
        viewEmployeesButton = new JButton("View Employees");
        viewAttendanceButton = new JButton("View Attendance");

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });

        viewEmployeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewEmployees();
            }
        });

        viewAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAttendance();
            }
        });

        setLayout(new GridLayout(3, 1));
        add(addEmployeeButton);
        add(viewEmployeesButton);
        add(viewAttendanceButton);

        setVisible(true);
    }

    private void addEmployee() {
        JFrame frame = new JFrame("Add Employee");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(this);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField(20);
        JButton addButton = new JButton("Add");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields");
                    return;
                }

                try {
                    EmployeeDAO employeeDAO = new EmployeeDAO();
                    Employee employee = new Employee(name, email, phone);
                    employeeDAO.addEmployee(employee);
                    JOptionPane.showMessageDialog(frame, "Employee added successfully");
                    frame.dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error: Unable to add employee");
                }
            }
        });

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(new JLabel());
        panel.add(addButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void viewEmployees() {
        JFrame frame = new JFrame("View Employees");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(this);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);

        try {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            List<Employee> employees = employeeDAO.getAllEmployees();
            StringBuilder sb = new StringBuilder();
            for (Employee employee : employees) {
                sb.append("ID: ").append(employee.getId()).append(", ")
                        .append("Name: ").append(employee.getName()).append(", ")
                        .append("Email: ").append(employee.getEmail()).append(", ")
                        .append("Phone: ").append(employee.getPhone()).append("\n");
            }
            textArea.setText(sb.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error: Unable to fetch employees");
        }

        frame.add(scrollPane);
        frame.setVisible(true);
    }

    private void viewAttendance() {
        JFrame frame = new JFrame("View Attendance");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(this);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);

        try {
            AttendanceDAO attendanceDAO = new AttendanceDAO();
            List<Attendance> attendanceList = attendanceDAO.getAllAttendance();
            StringBuilder sb = new StringBuilder();
            for (Attendance attendance : attendanceList) {
                sb.append("Employee ID: ").append(attendance.getEmployeeId()).append(", ")
                        .append("Check In Time: ").append(attendance.getCheckInTime()).append(", ")
                        .append("Check Out Time: ").append(attendance.getCheckOutTime()).append("\n");
            }
            textArea.setText(sb.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error: Unable to fetch attendance records");
        }

        frame.add(scrollPane);
        frame.setVisible(true);
    }
}
