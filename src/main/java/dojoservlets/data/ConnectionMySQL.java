package dojoservlets.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionMySQL implements ConnectionDB {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/control_clientes?"
			+ "useSSL=false&useTimezone=true&" + "serverTimeone=UTC&allowPublicKeyRetrieval=true";

	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWORD = "root";

	private static BasicDataSource dataSource;

	public static DataSource getDataSource() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		if (dataSource == null) {
			dataSource = new BasicDataSource();
			dataSource.setUrl(JDBC_URL);
			dataSource.setUsername(JDBC_USER);
			dataSource.setPassword(JDBC_PASSWORD);

			dataSource.setInitialSize(50);
		}

		return dataSource;
	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		return getDataSource().getConnection();
	}

	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		}
	}

	public static void close(PreparedStatement ps) {
		try {
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		}
	}

	public static void close(Connection connection) {
		try {
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		}
	}
}
