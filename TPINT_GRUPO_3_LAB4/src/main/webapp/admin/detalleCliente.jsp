<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidad.Usuario"%>
<%@ page import="entidad.Cuenta"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalle Cliente</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/detalleCliente.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/nav.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css">
</head>
<body>
<%
	Usuario usuario = (Usuario) request.getAttribute("usuarioDetalle");
%>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg bg-body-tertiary shadow-sm">
	<div class="container-fluid">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/HomeAdminServlet">Banco G3</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/HomeAdminServlet">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/FormularioClienteServlet">Alta Clientes</a></li>
				<li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/ListarUsuariosServlet">Clientes</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/CuentaAdminServlet">Cuentas</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/PrestamosAdminServlet">Préstamos</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/ReportesServlet">Reportes</a></li>
			</ul>
			<span class="navbar-text d-flex flex-row align-items-center gap-2">
				<a class="nav-link" href="${pageContext.request.contextPath}/VerDatosAdminServlet"><%= session.getAttribute("idNombre") != null ? session.getAttribute("idNombre") : "null" %></a>
				<a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="logout-btn">Salir</a>
			</span>
		</div>
	</div>
</nav>

<%
    String status = request.getParameter("status");
    if ("activado".equals(status)) {
%>
    <div class="alert alert-success alert-dismissible fade show text-center" role="alert" style="max-width: 600px; margin: 20px auto;">
        ✅ Usuario activado con éxito.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
<%
    } else if ("inactivado".equals(status)) {
%>
    <div class="alert alert-warning alert-dismissible fade show text-center" role="alert" style="max-width: 600px; margin: 20px auto;">
        ⚠️ Usuario inactivado con éxito.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
<%
    } else if ("errorActivacion".equals(status)) {
%>
    <div class="alert alert-danger alert-dismissible fade show text-center" role="alert" style="max-width: 600px; margin: 20px auto;">
        ❌ Ocurrió un error al activar al usuario.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
<%
    } else if ("errorInactivacion".equals(status)) {
%>
    <div class="alert alert-danger alert-dismissible fade show text-center" role="alert" style="max-width: 600px; margin: 20px auto;">
        ❌ Ocurrió un error al inactivar al usuario.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
<%
    }
%>


<!-- Contenido -->
<div class="container mt-4">

	<h2 class="mb-4">Detalle del Cliente</h2>
	<div class="card shadow-sm p-4 mb-4">

		<input type="hidden" name="id" value="<%=usuario.getId()%>">

		<div class="row mb-3">
			<div class="col-md-6">
				<label class="form-label">Nombre</label>
				<input type="text" class="form-control" value="<%=usuario.getNombre()%>" readonly>
			</div>
			<div class="col-md-6">
				<label class="form-label">Apellido</label>
				<input type="text" class="form-control" value="<%=usuario.getApellido()%>" readonly>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-md-4">
				<label class="form-label">DNI</label>
				<input type="text" class="form-control" value="<%=usuario.getDni()%>" readonly>
			</div>
			<div class="col-md-4">
				<label class="form-label">CUIL</label>
				<input type="text" class="form-control" value="<%=usuario.getCuil()%>" readonly>
			</div>
			<div class="col-md-4">
				<label class="form-label">Sexo</label>
				<input type="text" class="form-control" value="<%=usuario.getSexo()%>" readonly>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-md-6">
				<label class="form-label">Nacionalidad</label>
				<input type="text" class="form-control" value="<%=usuario.getNacionalidad()%>" readonly>
			</div>
			<div class="col-md-6">
				<label class="form-label">Fecha de Nacimiento</label>
				<input type="date" class="form-control" value="<%=usuario.getFechaDeNacimiento()%>" readonly>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-md-6">
				<label class="form-label">Email</label>
				<input type="email" class="form-control" value="<%=usuario.getCorreoElectronico()%>" readonly>
			</div>
			<div class="col-md-6">
				<label class="form-label">Teléfono</label>
				<input type="text" class="form-control" value="<%=usuario.getTelefono()%>" readonly>
			</div>
		</div>

		<div class="row mb-3">
			<div class="col-md-6">
				<label class="form-label">Dirección</label>
				<input type="text" class="form-control" value="<%=usuario.getDireccion()%>" readonly>
			</div>
			<div class="col-md-3">
				<label class="form-label">Localidad</label>
				<input type="text" class="form-control" value="<%=usuario.getNombreLocalidad()%>" readonly>
			</div>
			<div class="col-md-3">
				<label class="form-label">Provincia</label>
				<input type="text" class="form-control" value="<%=usuario.getNombreProvincia()%>" readonly>
			</div>
		</div>
	</div>

	<!-- Credenciales del Usuario -->
	<div class="card shadow-sm p-4 mb-4">
		<h4 class="mb-3">Credenciales de Acceso</h4>
		<div class="row mb-3">
			<div class="col-md-6">
				<label class="form-label">Nombre de Usuario</label>
				<input type="text" class="form-control" readonly value="<%= ((entidad.UsuarioCredenciales)request.getAttribute("credencialesUsuario")) != null ? ((entidad.UsuarioCredenciales)request.getAttribute("credencialesUsuario")).getUsuario() : "" %>">
			</div>
			<div class="col-md-6">
				<label class="form-label">Tipo de Usuario</label>
				<input type="text" class="form-control" readonly
					value="<%
						entidad.UsuarioCredenciales cred = (entidad.UsuarioCredenciales)request.getAttribute("credencialesUsuario");
						String tipo = "Desconocido";
						if (cred != null) {
							if (cred.getIDTipo() == 1) tipo = "Administrador";
							else if (cred.getIDTipo() == 2) tipo = "Cliente bancario";
						}
						out.print(tipo);
					%>">
			</div>
		</div>
	</div>

	<!-- Cuentas del Cliente -->
	<div class="mb-4">
		<h4 class="mb-3">Cuentas del Cliente</h4>

		<%
		List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentasUsuario");
		for (Cuenta aux : cuentas) {
			if (aux.isEstado()) {
		%>
		<form method="post" action="DetalleUsuarioServlet">
			<div class="card mb-3 shadow-sm p-3">
				<div class="row g-3">
					<div class="col-md-2">
						<label class="form-label">Nro de cuenta</label>
						<input type="text" class="form-control" value="<%=aux.getId()%>" readonly name="numeroCuenta">
					</div>
					<%
					    String tipoDescripcion = "";
					    if (aux.getIdTipoDeCuenta() == 1) {
					        tipoDescripcion = "Caja de Ahorro";
					    } else if (aux.getIdTipoDeCuenta() == 2) {
					        tipoDescripcion = "Cuenta Corriente";
					    } else {
					        tipoDescripcion = "Desconocido";
					    }
					%>
					
					<div class="col-md-2">
					    <label class="form-label">Tipo de cuenta</label>
					    <input type="text" class="form-control" value="<%= tipoDescripcion %>" readonly>
					</div>

					<div class="col-md-2">
						<label class="form-label">Fecha</label>
						<input type="date" class="form-control" value="<%=aux.getFechaDeCreacion()%>" readonly>
					</div>
					<div class="col-md-2">
						<label class="form-label">Saldo</label>
						<input type="text" class="form-control" value="<%=aux.getSaldo()%>" readonly>
					</div>
					<div class="col-md-2">
						<label class="form-label">CBU</label>
						<input type="text" class="form-control" value="<%=aux.getCbu()%>" readonly>
					</div>
					<div class="col-md-2 d-flex flex-column justify-content-center align-items-center">
						<button type="submit" class="btn btn-primary btn-sm w-100 mb-1" name="verCuenta">Ver</button>
						<button type="submit" class="btn btn-danger btn-sm w-100" name="eliminarCuenta">Eliminar</button>
						<%
							if (request.getAttribute("eliminar") != null && (boolean) request.getAttribute("eliminar") && aux.getId() == (int) request.getAttribute("cuentaEliminar")) {
						%>
						<button type="submit" class="btn btn-warning btn-sm mt-2 w-100" name="confirmarEliminar">Confirmar</button>
						<% } %>
					</div>
				</div>
			</div>
		</form>
		<% }} %>
	</div>

	<!-- Agregar Cuenta -->
	<form method="post" action="DetalleCuentaServlet?modo=crear">
		<input type="hidden" name="idUser" value="<%=usuario.getId()%>">
		<div class="alert alert-light border shadow-sm d-flex justify-content-between align-items-center">
			<div>
				<strong>Agregar nueva cuenta</strong><br>
				<small>El cliente puede tener hasta 3 cuentas</small>
			</div>
			<a href="${pageContext.request.contextPath}/DetalleCuentaServlet?modo=crear" class="btn btn-secondary btn-lg">Agregar cuenta</a>
		</div>
	</form>

	<!-- Botones -->
	<div class="text-center mt-4">
		<a href="${pageContext.request.contextPath}/ListarUsuariosServlet" class="btn btn-secondary btn-lg me-2">Volver</a>
		<a href="${pageContext.request.contextPath}/ModificarUsuarioServlet?id=<%= usuario.getId() %>" class="btn btn-warning btn-lg me-2">Modificar</a>

		<form action="${pageContext.request.contextPath}/EliminarUsuarioServlet" method="post" style="display: inline;" onsubmit="return confirmarEliminacion();">
			<input type="hidden" name="id" value="<%=usuario.getId()%>">
			<% if (usuario.isEstado()) { %>
				<button type="submit" class="btn btn-danger btn-lg me-2">Eliminar</button>
			<% } %>
		</form>

		<form action="${pageContext.request.contextPath}/ActivarUsuarioServlet" method="post" style="display: inline;" onsubmit="return confirmarActivacion();">
			<input type="hidden" name="id" value="<%=usuario.getId()%>">
			<% if (!usuario.isEstado()) { %>
				<button type="submit" class="btn btn-primary btn-lg">Activar</button>
			<% } %>
		</form>
	</div>
</div>

<!-- Scripts JS -->
<script>
	function confirmarEliminacion() {
		return confirm("¿Estás seguro que deseas eliminar este usuario?");
	}
	function confirmarActivacion() {
		return confirm("¿Estás seguro que deseas activar este usuario?");
	}
</script>

<% if (request.getAttribute("permitirAgregar") != null && !(boolean) request.getAttribute("permitirAgregar")) { %>
<script>
	window.onload = function() {
		alert("No es posible agregar más cuentas, se alcanzó el límite de cuentas activas (3).");
	};
</script>
<% } %>

<!-- Footer -->
<footer>
	<p>© 2025 Grupo 3 - Laboratorio 4</p>
</footer>





</body>
</html>

