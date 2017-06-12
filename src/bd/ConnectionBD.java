package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBD {
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final String DATABASE_URL = "jdbc:mysql://localhost/KookWS"; // conferir essa url
	private Connection connection;

	public Connection getConnection() {
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void main(String[] args) {
		ConnectionBD c = new ConnectionBD();
		c.getConnection();
	}
}