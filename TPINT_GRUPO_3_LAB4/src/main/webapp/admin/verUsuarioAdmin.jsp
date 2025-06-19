<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ver datos usuario</title>
    <link rel="stylesheet" 
        href="${pageContext.request.contextPath}/resources/css/cuentasAdmin.css">	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
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
	          <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/admin/homeAdmin.jsp">Home</a>
	        </li>
	    <%
	    	// si es admin
	    	%>
	    	
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/altaCliente.jsp">Alta Clientes</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/clientes.jsp">Clientes</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/reportes.jsp">Reportes</a>
	        </li>
	   <%
	    %>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/cuentasAdmin.jsp">Cuentas</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/prestamosAdmin.jsp">Prestamo</a>
	        </li>
	      </ul>
	      <span class="navbar-text d-flex flex-row">
	      <a class="nav-link active align-self-center justify-content-center" href="${pageContext.request.contextPath}/admin/verUsuarioAdmin.jsp">Nombre Usuario</a>
	        <button class="btn btn-danger">Log Out</button>
	      </span>
	    </div>
	  </div>
	</nav>

</body>
</html>