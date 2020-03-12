package dojoservlets.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dojoservlets.domain.Client;

public class ClientDaoJDBC implements ClientDao {

	private static final String SQL_SELECT_ALL = "SELECT id_cliente, nombre, apellido, email,"
			+ "telefono, saldo FROM cliente";

	private static final String SQL_SELECT_BY_ID = "SELECT id_cliente, nombre, apellido, email,"
			+ "telefono, saldo FROM cliente WHERE id_cliente = ?";

	private static final String SQL_INSERT = "INSERT INTO cliente(nombre, apellido, email, telefono,"
			+ "saldo) VALUES(?,?,?,?,?)";

	private static final String SQL_UPDATE_BY_ID = "UPDATE cliente "
			+ " SET nombre= ?, apellido = ?, email = ?, telefono = ?, saldo = ? " + " WHERE id_cliente = ?";

	private static final String SQL_DELETE_BY_ID = "DELETE FROM cliente WHERE id_cliente = ?";

	@Override
	public List<Client> findAll() {
		Connection connectionDB = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Client client = null;
		List<Client> clients = new ArrayList<>();

		try {
			connectionDB = ConnectionMySQL.getConnection();
			stmt = connectionDB.prepareStatement(SQL_SELECT_ALL);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int idCliente = rs.getInt("id_cliente");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String email = rs.getString("email");
				String telefono = rs.getString("telefono");
				double saldo = rs.getDouble("saldo");

				client = new Client(idCliente, nombre, apellido, email, telefono, saldo);

				clients.add(client);
			}
		} catch (SQLException|ClassNotFoundException e) {
			e.printStackTrace(System.out);
		} finally {
			ConnectionMySQL.close(connectionDB);
			ConnectionMySQL.close(rs);
			ConnectionMySQL.close(stmt);
		}

		return clients;
	}

	@Override
	public Client find(Client client) {
		Connection connectionDB = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			connectionDB = ConnectionMySQL.getConnection();
			stmt = connectionDB.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setInt(1, client.getIdCliente());
			rs = stmt.executeQuery();
			rs.absolute(1);

			String nombre = rs.getString("nombre");
			String apellido = rs.getString("apellido");
			String email = rs.getString("email");
			String telefono = rs.getString("telefono");
			double saldo = rs.getDouble("saldo");
			
			client.setNombre(nombre);
			client.setApellido(apellido);
			client.setEmail(email);
			client.setTelefono(telefono);
			client.setSaldo(saldo);

		} catch (SQLException|ClassNotFoundException e) {
			e.printStackTrace(System.out);
		} finally {
			ConnectionMySQL.close(connectionDB);
			ConnectionMySQL.close(rs);
			ConnectionMySQL.close(stmt);
		}
		return client;
	}

	@Override
	public int insert(Client client) {
		Connection connectionDB = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {
			connectionDB = ConnectionMySQL.getConnection();
			stmt = connectionDB.prepareStatement(SQL_INSERT);
			stmt.setString(1, client.getNombre());
			stmt.setString(2, client.getApellido());
			stmt.setString(3, client.getEmail());
			stmt.setString(4, client.getTelefono());
			stmt.setDouble(5, client.getSaldo());
			
			rows = stmt.executeUpdate();
		} catch (SQLException|ClassNotFoundException e) {
			e.printStackTrace(System.out);
		} finally {
			ConnectionMySQL.close(connectionDB);
			ConnectionMySQL.close(stmt);
		}
		return rows;
	}

	@Override
	public int update(Client client) {
		Connection connectionDB = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {
			connectionDB = ConnectionMySQL.getConnection();
			stmt = connectionDB.prepareStatement(SQL_UPDATE_BY_ID);
			stmt.setString(1, client.getNombre());
			stmt.setString(2, client.getApellido());
			stmt.setString(3, client.getEmail());
			stmt.setString(4, client.getTelefono());
			stmt.setDouble(5, client.getSaldo());
			stmt.setInt(6, client.getIdCliente());
			
			rows = stmt.executeUpdate();
		} catch (SQLException|ClassNotFoundException e) {
			e.printStackTrace(System.out);
		} finally {
			ConnectionMySQL.close(connectionDB);
			ConnectionMySQL.close(stmt);
		}
		return rows;
	}

	@Override
	public int delete(Client client) {
		Connection connectionDB = null;
		PreparedStatement stmt = null;
		int rows = 0;

		try {
			connectionDB = ConnectionMySQL.getConnection();
			stmt = connectionDB.prepareStatement(SQL_DELETE_BY_ID);
			stmt.setInt(1, client.getIdCliente());
			
			rows = stmt.executeUpdate();
		} catch (SQLException|ClassNotFoundException e) {
			e.printStackTrace(System.out);
		} finally {
			ConnectionMySQL.close(connectionDB);
			ConnectionMySQL.close(stmt);
		}
		return rows;
	}

}
