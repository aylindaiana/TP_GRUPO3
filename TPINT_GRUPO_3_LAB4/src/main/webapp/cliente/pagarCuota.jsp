<%@page import="java.util.List"%>
<%@page import="entidad.Cuenta"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagar Cuota</title>

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
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarText">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/HomeClienteServlet">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/CuentasClienteServlet">Cuentas</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" href="${pageContext.request.contextPath}/PrestamosClienteServlet">Prestamo</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/cliente/transferencias.jsp">Transferir</a>
	        </li>
	      </ul>	  
	      <span class="navbar-text d-flex flex-row">
		    <a class="nav-link align-self-center justify-content-center" href="${pageContext.request.contextPath}/cliente/verUsuarioCliente.jsp"><%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null" %></a>
		    <a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="btn btn-danger">Log Out</a>
		  </span>
	    </div>
	  </div>
	</nav>

	<%
	
	List<Cuenta> cuentas = (List<Cuenta>)request.getAttribute("listaCuentas");
	int IDCuota = (int)request.getAttribute("IDCuota");
	double montoCuota = (double)request.getAttribute("montoCuota");
	
	%>

	<div class="container text-center" id="general-container">
		<div class="col-md-6 mx-auto" id="historial-container">
			<form method="post" action="${pageContext.request.contextPath}/PagarCuotaServlet">
				<div class="row">
					<b>Pagar cuota </b> Seleccione cuenta 
					
					<select class="btn btn-secondary btn-lg dropdown-toggle"  name="cuentaSeleccionada">
						<%
						
						for(Cuenta aux : cuentas){
							%>
							<option value="<%= aux.getId() %>">Cuenta <%=aux.getCbu() %></option>
							<%
						} 
						%>
					</select>
				
				</div>
	
				<br>
				<div class="row">
					<div class="col" id="montos-container">Monto: $<%=montoCuota %></div>
				</div>
	
	
				<br>
				<div class="row">
					
					<input type="hidden" name="IDCuota" value="<%=IDCuota%>">
					<input type="hidden" name="montoCuota" value="<%=montoCuota%>">
				
					<button type="submit" class="btn btn-success">Confirmar</button>
				</div>
				<br>
				<div class="row">
					<a href="verPrestamo.jsp" class="btn btn-danger">Cancelar</a>
				</div>
			</form>
		</div>
	</div>

</body>
</html>





















