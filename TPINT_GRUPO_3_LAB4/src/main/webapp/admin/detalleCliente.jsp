<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="entidad.Usuario"%>
<%@ page import="entidad.Cuenta"%>
<%@ page import="java.util.List"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalle Cliente</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/detalleCliente.css">
</head>
<body>
	<%
	Usuario usuario = (Usuario) request.getAttribute("usuarioDetalle");
	%>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Banco G3</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarText"
				aria-controls="navbarText" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarText">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="${pageContext.request.contextPath}/HomeAdminServlet">Home</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/FormularioClienteServlet">Alta
							Clientes</a></li>
					<li class="nav-item"><a class="nav-link active"
						href="${pageContext.request.contextPath}/ListarUsuariosServlet">Clientes</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/CuentaAdminServlet">Cuentas</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/admin/prestamosAdmin.jsp">Prestamos</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/admin/reportes.jsp">Reportes</a>
					</li>
				</ul>
				<span class="navbar-text d-flex flex-row align-items-center gap-2"> <a
					class="nav-link align-self-center justify-content-center"
					href="${pageContext.request.contextPath}/admin/verUsuarioAdmin.jsp"><%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null"%></a>
					<a
					href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar"
					class="logout-btn">Salir</a>
				</span>
			</div>
		</div>
	</nav>

	<div class="container mt-4">
		<h2>Detalle del Cliente</h2>

		<div>
			<input type="hidden" name="id" value="<%=usuario.getId()%>">

			<div class="row mb-3">
				<div class="col-md-6">
					<label class="form-label">Nombre</label> <input type="text"
						class="form-control" value="<%=usuario.getNombre()%>" readonly>
				</div>
				<div class="col-md-6">
					<label class="form-label">Apellido</label> <input type="text"
						class="form-control" value="<%=usuario.getApellido()%>" readonly>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-md-4">
					<label class="form-label">DNI</label> <input type="text"
						class="form-control" value="<%=usuario.getDni()%>" readonly>
				</div>
				<div class="col-md-4">
					<label class="form-label">CUIL</label> <input type="text"
						class="form-control" value="<%=usuario.getCuil()%>" readonly>
				</div>
				<div class="col-md-4">
					<label class="form-label">Sexo</label> <input type="text"
						class="form-control" value="<%=usuario.getSexo()%>" readonly>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-md-6">
					<label class="form-label">Nacionalidad</label> <input type="text"
						class="form-control" value="<%=usuario.getNacionalidad()%>"
						readonly>
				</div>
				<div class="col-md-6">
					<label class="form-label">Fecha de Nacimiento</label> <input
						type="date" class="form-control"
						value="<%=usuario.getFechaDeNacimiento()%>" readonly>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-md-6">
					<label class="form-label">Email</label> <input type="email"
						class="form-control" value="<%=usuario.getCorreoElectronico()%>"
						readonly>
				</div>
				<div class="col-md-6">
					<label class="form-label">Teléfono</label> <input type="text"
						class="form-control" value="<%=usuario.getTelefono()%>" readonly>
				</div>
			</div>

			<div class="row mb-3">
				<div class="col-md-6">
					<label class="form-label">Dirección</label> <input type="text"
						class="form-control" value="<%=usuario.getDireccion()%>" readonly>
				</div>
				<div class="col-md-3">
					<label class="form-label">Localidad</label> <input type="text"
						class="form-control" value="<%=usuario.getNombreLocalidad()%>"
						readonly>
				</div>
				<div class="col-md-3">
					<label class="form-label">Provincia</label> <input type="text"
						class="form-control" value="<%=usuario.getNombreProvincia()%>"
						readonly>
				</div>
			</div>


			<hr class="mt-4 mb-3">

			<h4>Cuentas del Cliente</h4>


			<%
			List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentasUsuario");

			for (Cuenta aux : cuentas) {
				if (aux.isEstado()) {
			%>

			<form method="post" action="DetalleUsuarioServlet">
				<div class="cuenta-item border rounded p-3 mb-3">
					<div class="row">
						<div class="col-md-2">
							<label class="form-label">Número</label> <input type="text"
								class="form-control" value="<%=aux.getId()%>" readonly
								name="numeroCuenta">
						</div>
						<div class="col-md-2">
							<label class="form-label">Tipo</label> <input type="text"
								class="form-control" value="<%=aux.getIdTipoDeCuenta()%>"
								readonly>
						</div>
						<div class="col-md-2">
							<label class="form-label">Fecha Creación</label> <input
								type="date" class="form-control"
								value="<%=aux.getFechaDeCreacion()%>" readonly>
						</div>
						<div class="col-md-2">
							<label class="form-label">Saldo</label> <input type="text"
								class="form-control" value="<%=aux.getSaldo()%>" readonly>
						</div>
						<div class="col-md-2">
							<label class="form-label">CBU</label> <input type="text"
								class="form-control" value="<%=aux.getCbu()%>" readonly>
						</div>
						<div class="col-md-2 text-center">
							<label class="form-label">Acciones</label><br>
							<button type="submit" class="btn btn-primary btn-sm me-1"
								name="verCuenta">Ver</button>
							<button type="submit" class="btn btn-danger btn-sm"
								name="eliminarCuenta">Eliminar</button>
							<%
							if (request.getAttribute("eliminar") != null && (boolean) request.getAttribute("eliminar") == true
									&& aux.getId() == (int) request.getAttribute("cuentaEliminar")) {
							%>
							<br>
							<button type="submit" class="btn btn-warning btn-sm"
								name="confirmarEliminar">Confirmar eliminacion</button>

							<%
							}
							%>

						</div>
					</div>
				</div>

			</form>
			<%
			}
			}
			%>



			<form method="post" action=DetalleCuentaServlet?modo=crear>
				<input type="hidden" name="modo" value="crear">
    			<input type="hidden" name="idUser" value="<%=usuario.getId()%>">
				<div
					class="cuenta-item border border-dashed p-3 text-muted bg-light">
					<div class="row align-items-center">
						<div class="col-md-8">
							<strong>Agregar nueva cuenta</strong><br> <small>El
								cliente puede tener hasta 3 cuentas</small>
						</div>
						<div class="col-md-4 text-end">
							<input type="hidden" name="idUser" value="<%=usuario.getId()%>">
							<a href="${pageContext.request.contextPath}/DetalleCuentaServlet?modo=crear"
								class="btn btn-secondary btn-lg me-3">Agregar cuenta</a>
						</div>
					</div>
				</div>
			</form>

			<div class="text-center mt-4">
				<a href="${pageContext.request.contextPath}/ListarUsuariosServlet"
					class="btn btn-secondary btn-lg me-3">Volver</a> <a
					href="${pageContext.request.contextPath}/ModificarUsuarioServlet?id=<%= usuario.getId() %>"
					class="btn btn-warning btn-lg me-3">Modificar</a>
				<form
					action="${pageContext.request.contextPath}/EliminarUsuarioServlet"
					method="post" style="display: inline;"
					onsubmit="return confirmarEliminacion();">
					<input type="hidden" name="id" value="<%=usuario.getId()%>">
					<%
					if (usuario.isEstado()) {
					%>
					<button type="submit" class="btn btn-danger btn-lg">Eliminar</button>
					<%
					}
					%>
				</form>
				<form
					action="${pageContext.request.contextPath}/ActivarUsuarioServlet"
					method="post" style="display: inline;"
					onsubmit="return confirmarActivacion();">
					<input type="hidden" name="id" value="<%=usuario.getId()%>">
					<%
					if (!usuario.isEstado()) {
					%>
					<button type="submit" class="btn btn-primary btn-lg">Activar</button>
					<%
					}
					%>
				</form>
			</div>
		</div>

		<script>
			function confirmarEliminacion() {
				return confirm("¿Estás seguro que deseas eliminar este usuario?");
			}
			function confirmarActivacion() {
				return confirm("¿Estás seguro que deseas activar este usuario?");
			}
		</script>


		<%
		if (request.getAttribute("permitirAgregar") != null && !(boolean) request.getAttribute("permitirAgregar")) {
		%>
		<script>
			window.onload = function() {
				alert("No es posible agregar más cuentas, se alcanzó el límite de cuentas activas (3).");
			};
		</script>
		<%
		}
		%>
	
</body>
</html>

