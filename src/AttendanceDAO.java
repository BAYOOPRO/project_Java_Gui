import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    private Connection getConnection() throws SQLException {
        // Replace with your database connection details
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance_system", "root", "");
    }

    public void checkIn(int employeeId) throws SQLException {
        String sql = "INSERT INTO attendance (employee_id, check_in) VALUES (?, NOW())";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
        }
    }

    public void checkOut(int employeeId) throws SQLException {
        String sql = "UPDATE attendance SET check_out = NOW() WHERE employee_id = ? AND check_out IS NULL";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
        }
    }

    public List<Attendance> getAllAttendance() throws SQLException {
        List<Attendance> attendanceList = new ArrayList<>();
        String sql = "SELECT employee_id, check_in, check_out FROM attendance";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int employeeId = rs.getInt("employee_id");
                Timestamp checkInTime = rs.getTimestamp("check_in");
                Timestamp checkOutTime = rs.getTimestamp("check_out");

                // Create an Attendance object and add it to the list
                Attendance attendance = new Attendance(employeeId, checkInTime, checkOutTime);
                attendanceList.add(attendance);
            }
        }
        return attendanceList;
    }
}
