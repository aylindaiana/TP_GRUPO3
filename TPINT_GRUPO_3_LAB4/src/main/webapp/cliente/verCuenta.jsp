<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, entidad.Movimiento"%>
<%
List<Movimiento> movimientos = (List<Movimiento>) request.getAttribute("movimientos");
int idCuenta = (request.getAttribute("idCuenta") != null) ? (int) request.getAttribute("idCuenta") : 0;
int currentPage = (request.getAttribute("currentPage") != null) ? (int) request.getAttribute("currentPage") : 1;
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Cuentas - Cliente</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/homeCliente.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/nav.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/footer.css">
</head>
<body>

	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/HomeClienteServlet">Banco
				G3</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarText"
				aria-controls="navbarText" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarText">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/HomeClienteServlet">Home</a>
					</li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/CuentasClienteServlet">Cuentas</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/PrestamosClienteServlet">Préstamos</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/TransferenciasHomeServlet">Transferir</a>
					</li>
				</ul>
				<span class="navbar-text d-flex flex-row align-items-center gap-2">
					<a class="username-link"
					href="${pageContext.request.contextPath}/VerDatosClienteServlet">
						<%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null"%>
				</a> <a
					href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar"
					class="logout-btn">Salir</a>
				</span>
			</div>
		</div>
	</nav>

	<div class="container mt-4">

		<div class="d-flex justify-content-between align-items-center my-3">
			<a href="${pageContext.request.contextPath}/CuentasClienteServlet"
				class="btn btn-secondary"> ← Volver a Mis Cuentas </a>
		</div>
		<h2 class="mb-4">
			Historial de Movimientos - Cuenta N°
			<%=idCuenta%></h2>

		<form method="get"
			action="${pageContext.request.contextPath}/VerTodoCuentaServlet"
			class="row g-3">
			<input type="hidden" name="idCuenta" value="<%=idCuenta%>" />

			<div class="col-md-3">
				<input type="text" class="form-control" name="texto"
					placeholder="Buscar por detalle...">
			</div>
			<div class="col-md-2">
				<select name="tipo" class="form-select">
					<option value="">Todos los tipos</option>
					<option value="Alta de cuenta">Alta de cuenta</option>
					<option value="Alta de préstamo">Alta de préstamo</option>
					<option value="Pago de préstamo">Pago de préstamo</option>
					<option value="Transferencia">Transferencia</option>
				</select>
			</div>
			<div class="col-md-2">
				<input type="number" step="0.01" class="form-control"
					name="minMonto" placeholder="Monto mínimo">
			</div>
			<div class="col-md-2">
				<input type="number" step="0.01" class="form-control"
					name="maxMonto" placeholder="Monto máximo">
			</div>
			<div class="col-md-2">
				<input type="date" class="form-control" name="fechaDesde"
					placeholder="Desde">
			</div>
			<div class="col-md-2">
				<input type="date" class="form-control" name="fechaHasta"
					placeholder="Hasta">
			</div>
			<div class="col-md-2">
				<button type="submit" class="btn btn-primary">Buscar</button>
			</div>
		</form>

		<hr class="my-4">

		<div class="table-responsive">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Fecha</th>
						<th>Detalle</th>
						<th>Tipo</th>
						<th>Monto</th>
					</tr>
				</thead>
				<tbody>
					<%
					if (movimientos != null && !movimientos.isEmpty()) {
						for (Movimiento mov : movimientos) {
					%>
					<tr>
						<td><%=mov.getFecha()%></td>
						<td><%=mov.getComentario()%></td>
						<td><%=mov.getDescripcionTipoDeMovimiento()%></td>
						<td
							class="<%=mov.getDescripcionTipoDeMovimiento().toLowerCase().contains("alta") ? "text-success" : "text-danger"%>">
							$<%=mov.getMonto()%>
						</td>
					</tr>
					<%
					}
					} else {
					%>
					<tr>
						<td colspan="4" class="text-center">No se encontraron
							movimientos.</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>


		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<li class="page-item <%=(currentPage <= 1) ? "disabled" : ""%>">
					<a class="page-link"
					href="${pageContext.request.contextPath}/VerTodoCuentaServlet?idCuenta=<%= idCuenta %>&page=<%= currentPage - 1 %>">Anterior</a>
				</li>
				<li class="page-item"><a class="page-link"
					href="${pageContext.request.contextPath}/VerTodoCuentaServlet?idCuenta=<%= idCuenta %>&page=<%= currentPage + 1 %>">Siguiente</a>
				</li>
			</ul>
		</nav>
	</div>

	<footer class="text-center mt-5">
		<p>© 2025 Grupo 3 - Laboratorio 4</p>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
