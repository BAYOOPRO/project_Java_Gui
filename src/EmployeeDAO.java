import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private Connection getConnection() throws SQLException {
        // Replace with your database connection details
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance_system", "root", "");
    }

    public void addEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (name, email, phone) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getEmail());
            stmt.setString(3, employee.getPhone());
            stmt.executeUpdate();
        }
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT id, name, email, phone FROM employees";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Employee employee = new Employee(rs.getString("name"), rs.getString("email"), rs.getString("phone"));
                employee.setId(rs.getInt("id"));
                employees.add(employee);
            }
        }
        return employees;
    }
}
