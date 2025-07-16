<%@page import="entidad.Prestamo"%>
<%@page import="entidad.PrestamoRechazado"%>
<%@page import="entidad.Cuota"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ver prestamo</title>
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
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarText">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/HomeClienteServlet">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/CuentasClienteServlet">Cuentas</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/PrestamosClienteServlet">Préstamos</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/TransferenciasHomeServlet">Transferir</a>
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
		
<%
	if((List<Cuota>)request.getAttribute("listaCuotas") != null){
%>
	
		<div class="col" id="historial-container">
			<div class="row">
				<b>PRESTAMO </b>
			</div>

			<div class="row">

				<form action="${pageContext.request.contextPath}/PagarCuotaServlet" method="get">
					<table class="table table-hover">
						<tr class="table-info">
							<th>Numero de Cuota</th>
							<th>Monto</th>
							<th>Fecha limite</th>
							<th>Fecha de pago</th>
							<th>Estado cuota</th>
						</tr>
						<%
						List<Cuota> cuotas = (List<Cuota>)request.getAttribute("listaCuotas");
						for(Cuota aux : cuotas)
						{
						%>
							
							<tr>
								<td><%=aux.getNumeroCuota() %></td>
								<td><%=aux.getMonto() %></td>
								<td><%=aux.getFechaPago() %></td>
								<td><%=aux.getFechaRealizacionPago() == null ? "impaga" :  aux.getFechaRealizacionPago()%></td>
								<%
								if(aux.getEstado() == 1){
								%>
								<td><button type="submit" class="btn btn-success" disabled>Pagado</button></td>									
								<%
								}
								else
								{
								%>
								<td>
								<input type="hidden" name="IDPrestamo" value="<%=aux.getIDPrestamo()%>">
								<input type="hidden" name="IDCuota" value="<%=aux.getID() %>">
								<input type="hidden" name="montoCuota" value="<%=aux.getMonto() %>">
								<button type="submit" class="btn btn-warning">Pagar</button>
								</td>
								<%
								}
								%>	
							</tr>
						<%
						}
						%>
					</table>
				</form>
			</div>

		</div>
	
	
<%
	}
%>
		
		
		
		
<%
	if((PrestamoRechazado)request.getAttribute("motivoRechazo") != null){
	
	//agregar el desarrollo de mostrar los datos reales de cada prestamo y sus motivos de rechazo.
	Prestamo prestamo = (Prestamo)request.getAttribute("datosPrestamo");
	PrestamoRechazado rechazo = (PrestamoRechazado)request.getAttribute("motivoRechazo");
	
	
%>
	
		<div class="col" id="historial-container">

			<div class="row">
				<b>PRESTAMO PRESTAMO RECHAZADO</b>
			</div>

			<div class="row">
					<table class="table table-hover">
						<tr class="table-info">
							<th>Cantidad de cuotas</th>
							<th>Monto solicitado</th>
							<th>Fecha de solicitud</th>
							<th>Motivo de rechazo</th>
						</tr>
						<tr>
							<td><%= prestamo.getCantidadCuotas() %></td>
							<td>$<%= prestamo.getImporte()/1.5 %></td>
							<td><%= prestamo.getFechaDeAlta() %></td>
							<td>
								<textarea rows="8" cols="50" readonly style="resize: none;"><%=rechazo.getMotivoRechazo() %>
								</textarea>
							</td>
						</tr>

					</table>
					<a href="${pageContext.request.contextPath}/PrestamosClienteServlet" class="btn btn-primary">Regresar</a>
			</div>

		</div>
	
<%
	}
%>		

	</div>
	<footer>
	        <p>© 2025 Grupo 3 - Laboratorio 4</p>
	</footer>
</body>
</html>


















