package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	
	private static DBConnect instance;
    private static Connection connection;
    
    //database details
    private final String url = "jdbc:mysql://localhost:3306/spare_parts_shop_db";
    private final String user = "root";
    private final String pass = "uthsara29";

    //private constructor 
    private DBConnect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, user, pass);
    }

    //provide access to the singleton instance
    public static DBConnect getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new DBConnect();
        }
        return instance;
    }

    //method to get the connection object
    public Connection getConnection() {
        return connection;
    }

}
