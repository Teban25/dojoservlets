package dojoservlets.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dojoservlets.data.ClientDao;
import dojoservlets.data.ClientDaoJDBC;
import dojoservlets.domain.Client;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ClientDao clientDao;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		elegirOpcion(request, response, accion);
	}

	private void accionDefault(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Client> clientes = null;
		clientDao = new ClientDaoJDBC();
		clientes = clientDao.findAll();
		System.out.println("clientes= " + clientes);
		session.setAttribute("clientes", clientes);
		session.setAttribute("totalClientes", clientes.size());
		session.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));

		// request.getRequestDispatcher("clientes.jsp").forward(request,response);
		response.sendRedirect("clientes.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		elegirOpcion(request, response, accion);
	}

	private void elegirOpcion(HttpServletRequest request, HttpServletResponse response, String accion)
			throws ServletException, IOException {
		if (accion != null) {
			switch (accion) {

			case "insertar":
				this.insertarCliente(request, response);
				break;

			case "editar":
				this.editarCliente(request, response);
				break;

			case "modificar":
				this.modificarCliente(request, response);
				break;

			case "eliminar":
				this.eliminarCliente(request, response);
				break;

			default:
				this.accionDefault(request, response);
			}
		} else {
			this.accionDefault(request, response);
		}
	}

	private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		int idCliente = Integer.parseInt(request.getParameter("idCliente"));

		Client client = new Client(idCliente);

		clientDao = new ClientDaoJDBC();
		int registrosModificados = clientDao.delete(client);
		System.out.println("Registros modificiados = " + registrosModificados);

		this.accionDefault(request, response);

	}

	private void modificarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idCliente = Integer.parseInt(request.getParameter("idCliente"));
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String telefono = request.getParameter("telefono");
		double saldo = request.getParameter("saldo") == null || request.getParameter("saldo").isEmpty() ? 0.0
				: Double.parseDouble(request.getParameter("saldo"));

		Client client = new Client(idCliente, nombre, apellido, email, telefono, saldo);

		clientDao = new ClientDaoJDBC();
		int registrosModificados = clientDao.update(client);
		System.out.println("Registros modificiados = " + registrosModificados);

		this.accionDefault(request, response);

	}

	private void editarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int idCliente = Integer.parseInt(request.getParameter("idCliente"));

		Client cliente = new Client(idCliente);
		clientDao = new ClientDaoJDBC();
		clientDao.find(cliente);
		request.setAttribute("cliente", cliente);

		String jspEditar = "/WEB-INF/pages/cliente/editarCliente.jsp";

		request.getRequestDispatcher(jspEditar).forward(request, response);
	}

	private double calcularSaldoTotal(List<Client> clientes) {
		double saldoTotal = 0;
		for (Client cliente : clientes) {
			saldoTotal += cliente.getSaldo();
		}
		return saldoTotal;
	}

	private void insertarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String telefono = request.getParameter("telefono");
		double saldo = request.getParameter("saldo") == null || request.getParameter("saldo").isEmpty() ? 0.0
				: Double.parseDouble(request.getParameter("saldo"));

		Client client = new Client(nombre, apellido, email, telefono, saldo);

		clientDao = new ClientDaoJDBC();
		int registrosModificados = clientDao.insert(client);
		System.out.println("Registros modificiados = " + registrosModificados);

		this.accionDefault(request, response);
	}

}
