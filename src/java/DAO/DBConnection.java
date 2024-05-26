package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    protected Connection connection;

    public DBConnection() {
        try {
            String dbURL = "jdbc:sqlserver://localhost;databaseName=SWP391_DBv3;user=sa;password=123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(dbURL);

            if (connection != null && !connection.isClosed()) {
                System.out.println("Kết nối thành công đến cơ sở dữ liệu.");
            } else {
                System.out.println("Không thể kết nối đến cơ sở dữ liệu.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Lỗi kết nối cơ sở dữ liệu: " + ex.getMessage());
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            System.out.println("Lỗi kiểm tra kết nối: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
        if (dbConnection.isConnected()) {
            System.out.println("Kết nối đang hoạt động.");
        } else {
            System.out.println("Kết nối không hoạt động.");
        }
    }
}
