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

            if (connection != null) {
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


}
