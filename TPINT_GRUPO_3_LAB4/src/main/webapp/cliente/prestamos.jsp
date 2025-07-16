<%@page import="entidad.Prestamo"%>
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
<link rel="stylesheet" 
    href="${pageContext.request.contextPath}/resources/css/nav.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css">
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/HomeClienteServlet">Banco G3</a>
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
					<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/CuentasClienteServlet">Cuentas</a>
					</li>
					<li class="nav-item"><a class="nav-link active"
						href="${pageContext.request.contextPath}/PrestamosClienteServlet">Préstamos</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/TransferenciasHomeServlet">Transferir</a>
					</li>
				</ul>
				<span class="navbar-text d-flex flex-row align-items-center gap-2">
		    <a class="username-link" href="${pageContext.request.contextPath}/VerDatosClienteServlet"><%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null" %></a>
		    <a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="logout-btn">Salir</a>
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
							
							<%
							if (request.getAttribute("valorErroneo") != null) {
							%>
							<label style="color: red;">Ingrese un valor valido</label>
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

			<form action="${pageContext.request.contextPath}/VerPrestamoServlet" method="post">
				<div class="row">
					<b>HISTORIAL</b>
				</div>

				<div class="row">
					<table class="table table-hover">
						<tr class="table-info">
							<th>Numero de prestamo</th>
							<th>Monto solicitado</th>
							<th>Total a pagar</th>
							<th>Cantidad de cuotas</th>
							<th>Estado</th>
						</tr>
						
						
						<%
						List<Prestamo> prestamos = (List<Prestamo>)request.getAttribute("listaPrestamos");
						for(Prestamo aux : prestamos)
						{
						%>
						
						<tr>
							<td>identificacion: <%= aux.getID() %></td>
							<td>$<%= aux.getImporte()/1.5 %></td>
							<td>$<%= aux.getImporte() %></td>
							<td>Nº<%= aux.getCantidadCuotas() %></td>
							
							<%
							switch(aux.getAutorizacion())
							{
								case 1:
									%>
									<td>
										<button name="verCuotas" value="<%=aux.getID() %>" class="btn btn-success">ver</button>
									</td>
									<%
									break;
								case 2:
									%>
									<td><button class="btn btn-warning" disabled>pendiente</button></td>
									<%
									break;
								case 3:
									%>
									<td>
										<button name="verRechazo" value="<%=aux.getID() %>" class="btn btn-danger">rechazado</button>
									</td>
									<%
									break;
								case 4:
									%>
									<td>
										<button disabled class="btn btn-success">Saldado</button>
									</td>
									<%
									break;
							}
							%>
							
						</tr>

						<%
						}
						%>
					</table>
				</div>

			</form>
			</div>

		</div>
	</div>
	<footer>
	        <p>© 2025 Grupo 3 - Laboratorio 4</p>
	</footer>
</body>
</html>