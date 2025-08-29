package IA;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    static Connection conn = null;

    public static Connection connectDB() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://Localhost: 8889/IATest",
                "root", "root");
        System.out.println("Connection successful");
        return conn;
    }

    public static void main(String[] args) {
        try {
            connectDB();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
