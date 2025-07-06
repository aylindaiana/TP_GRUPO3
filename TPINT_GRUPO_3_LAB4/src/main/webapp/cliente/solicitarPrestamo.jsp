<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Solicitar Prestamo</title>

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
	          <a class="nav-link" href="${pageContext.request.contextPath}/cliente/transferencias.jsp">Transferir</a>
	        </li>
	      </ul>	  
	      <span class="navbar-text d-flex flex-row align-items-center gap-2">
		    <a class="username-link" href="${pageContext.request.contextPath}/cliente/verUsuarioCliente.jsp"><%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null" %></a>
		    <a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="logout-btn">Salir</a>
		  </span>
	    </div>
	  </div>
	</nav>


	<%
	int cuotas = Integer.parseInt(request.getAttribute("cuotas").toString());
	double solicitado = Double.parseDouble(request.getAttribute("montoSolicitado").toString());
	double total = Double.parseDouble(request.getAttribute("montoTotal").toString());
	int cuenta = Integer.parseInt(request.getAttribute("cuentaSeleccionada").toString());
	%>

	<div class="container text-center" id="general-container">
		<div class="col" id="historial-container">
			<div class="col">
				<form
					action="${pageContext.request.contextPath}/SolicitarPrestamoServlet"
					method="post">


					<input type="hidden" value="<%=cuotas%>" name="cuotas"> 
					<input type="hidden" value="<%=solicitado%>" name="solicitado">
					<input type="hidden" value="<%=total%>" name="total"> 
					<input type="hidden" value="<%=cuenta%>" name="cuenta">

					<div class="row">
						<table class="table table-hover">
							<tr class="table-info">
								<th>Cuenta</th>
								<th>Cantidad de cuotas</th>
								<th>Tasa de interés</th>
								<th>Monto solicitado</th>
								<th>Monto final a pagar</th>
							</tr>

							<tr>
								<th>Numero cuenta: <%=cuenta%> <% //agregar para que se muestre el numero de cuenta seleccionada de la ddl %></th>
								<th><%=cuotas%></th>
								<th>50%</th>
								<th>$<%=solicitado%></th>
								<th>$<%=total%></th>
							</tr>
						</table>
					</div>
					<div class="row">
						<div class="col">
							<div class="row">
								<button name="btn-confirmar" type="submit"
									class="btn btn-success">Confirmar</button>
							</div>
						</div>
						<div class="col">
							<div class="row">
								<a href="prestamos.jsp" class="btn btn-danger">Cancelar</a>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>