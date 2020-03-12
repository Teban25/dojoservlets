<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<script src="https://kit.fontawesome.com/4738674618.js"
	crossorigin="anonymous"></script>

<title>Editar Clientes</title>
</head>
<body>

	<!-- Cabecero -->
	<jsp:include page="/WEB-INF/pages/commons/header.jsp" />

	<form
		action="${pageContext.request.contextPath}/ServletControlador?accion=modificar&idCliente=${cliente.idCliente}"
		method="POST" class="was-validated">

		<!-- Botones de navegacion -->
		<jsp:include
			page="/WEB-INF/pages/commons/botonesNavegacionEdicion.jsp" />

		<section id="details">
			<div class="container">
				<div class="row">
					<div class="col">
						<div class="card">
							<div class="car2d-header">
								<h4 class="text-center">Editar</h4>
							</div>
							<div class="card-body">
								<div class="form-group">
									<label for="nombre">Nombre</label> 
									<input type="text" class="form-control" name="nombre" required value="${cliente.nombre}" />
								</div>

								<div class="form-group">
									<label for="apellido">Apellido</label> 
									<input type="text" class="form-control" name="apellido" required value="${cliente.apellido}" />
								</div>

								<div class="form-group">
									<label for="email">Email</label> 
									<input type="email" class="form-control" name="email" required value="${cliente.email}" />
								</div>

								<div class="form-group">
									<label for="telefono">Telefono (Ej:###-####)</label> 
									<input type="tel" pattern="[0-9]{3}-[0-9]{4}" class="form-control"
										name="telefono" required value="${cliente.telefono}" />
								</div>

								<div class="form-group">
									<label for="saldo">Saldo</label> 
									<input type="number" class="form-control" name="saldo" required value="${cliente.saldo}"
										step="any" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</form>

	<!-- Pie de pagina -->
	<jsp:include page="/WEB-INF/pages/commons/footer.jsp" />

	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
</body>
</html>