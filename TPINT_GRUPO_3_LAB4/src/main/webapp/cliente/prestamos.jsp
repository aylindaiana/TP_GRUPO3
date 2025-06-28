<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dao.PrestamoDao" %>
<%@ page import="daoImpl.PrestamoDaoImpl" %>
<%@ page import="entidad.Cuenta" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Prestamos</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/prestamos.css">

</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Bankame</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarText"
				aria-controls="navbarText" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarText">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/HomeClienteServlet">Home</a> 
					</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/cliente/cuentas.jsp">Cuentas</a>
					</li>
					<li class="nav-item"><a class="nav-link active"
						href="${pageContext.request.contextPath}/PrestamosClienteServlet">Prestamo</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/cliente/transferencias.jsp">Transferir</a>
					</li>
				</ul>
				<span class="navbar-text d-flex flex-row"> <a
					class="nav-link align-self-center justify-content-center"
					href="${pageContext.request.contextPath}/cliente/verUsuarioCliente.jsp">Nombre
						Usuario</a>
					<button class="btn btn-danger">Log Out</button>
				</span>
			</div>
		</div>
	</nav>

	<div class="container text-center" id="general-container">
		<div class="row">
			<div class="col-4" id="cuenta-destino-container">

				<form
					action="${pageContext.request.contextPath}/PrestamosClienteServlet"
					method="post">
					<div class="row">
						<b>CUENTA DESTINO</b>
					</div>

					
					
					<div class="row">
						<select class="form-select" name="cuentaSeleccionada">
						<%
							List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("listaCuentas");
    						for (Cuenta aux : cuentas) {
						%>
							 <option value="<%= aux.getId() %>"><%= aux.getCbu() %></option>								
						<%
							}
						%>
						</select>
					</div>
					
					<div class="row">
						<div class="col">Monto a solicitar</div>
						<div class="col">
							<%
							if (request.getAttribute("incompleto") != null) {
							%>
							<label style="color: red;">*</label>
							<%
							}
							%>
						</div>
					</div>
					<div class="row">
						<div class="col" id="montos-container">
							<div class="row">
								<input name="txtMontoSolicitado" type="number" required>

							</div>
						</div>
					</div>

					<br>
					<div class="row">
						<div class="cuotas-container">
					<div class="row">
						<div class="col">cantidad de cuotas:</div>
						<div class="col">
							<%
							if (request.getAttribute("incompleto") != null) {
							%>
							<label style="color: red;">*</label>
							<%
							}
							%>
						</div>
					</div>
							<div class="btn-group" role="group"
								aria-label="Basic radio toggle button group">
								<input type="radio" class="btn-check" name="btnradio"
									id="btnradio1" autocomplete="off" value="3" checked> <label
									class="btn btn-outline-primary" for="btnradio1">3</label> <input
									type="radio" class="btn-check" name="btnradio" id="btnradio2"
									autocomplete="off" value="6"> <label
									class="btn btn-outline-primary" for="btnradio2">6</label> <input
									type="radio" class="btn-check" name="btnradio" id="btnradio3"
									autocomplete="off" value="9"> <label
									class="btn btn-outline-primary" for="btnradio3">9</label>
							</div>
						</div>
					</div>


					<br>
					<button type="submit" class="btn btn-success"
						name="btn-solicitarPrestamo">Solicitar</button>
				</form>
			</div>

			<div class="col" id="historial-container">

				<div class="row">
					<b>HISTORIAL</b>
				</div>

				<div class="row">
					<table class="table table-hover">
						<tr class="table-info">
							<th>Numero de prestamo</th>
							<th>Estado</th>
						</tr>
						<tr>
							<td>prestamo 1</td>
							<td><a href="verPrestamo.jsp" class="btn btn-success">ver</a></td>
						</tr>

						<tr>
							<td>prestamo 2</td>
							<td><a href="" class="btn btn-warning">pendiente</a></td>
						</tr>

						<tr>
							<td>prestamo 3</td>
							<td><a href="" class="btn btn-warning">pendiente</a></td>
						</tr>

						<tr>
							<td>prestamo 4</td>
							<td><a href="verPrestamo.jsp" class="btn btn-danger">rechazado</a></td>
						</tr>
					</table>
				</div>

			</div>

		</div>
	</div>

</body>
</html>