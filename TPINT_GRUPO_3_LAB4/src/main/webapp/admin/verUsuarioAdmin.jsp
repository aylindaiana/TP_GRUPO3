<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mi Perfil</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/verUsuario.css">
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
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/FormularioClienteServlet">Alta Clientes</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/ListarUsuariosServlet">Clientes</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/CuentaAdminServlet">Cuentas</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/prestamosAdmin.jsp">Prestamos</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/reportes.jsp">Reportes</a>
	        </li>
	      </ul>				  
		  <span class="navbar-text d-flex flex-row">
			  <a class="nav-link align-self-center justify-content-center" href="${pageContext.request.contextPath}/admin/verUsuarioAdmin.jsp"><%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null" %></a>
			  <a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="btn btn-danger">Log Out</a>
		  </span>
	    </div>
	  </div>
	</nav>

	<div class="container mt-5">
	    <div class="card shadow p-4">
	        <div class="d-flex align-items-center mb-4">
	            <div class="perfil-foto me-4"></div>
	            <div>
	                <h4 class="fw-bold text-uppercase">GÓMEZ, LAURA</h4>
	                <p class="text-muted mb-0">Administrador del banco</p>
	            </div>
	        </div>

	        <h5 class="mt-3">Datos Personales</h5>
	        <div class="row">
	            <div class="col-md-4">
	                <p><strong>DNI:</strong> 34.567.890</p>
	                <p><strong>CUIL:</strong> 27-34567890-1</p>
	                <p><strong>Sexo:</strong> Femenino</p>
	            </div>
	            <div class="col-md-4">
	                <p><strong>Nacionalidad:</strong> Argentina</p>
	                <p><strong>Fecha de Nacimiento:</strong> 20/08/1990</p>
	            </div>
	        </div>


	        <h5 class="mt-4">Datos de Contacto</h5>
	        <div class="row">
	            <div class="col-md-4">
	                <p><strong>Teléfono:</strong> 11-7890-1234</p>
	                <p><strong>Email:</strong> laura.gomez@bankame.com</p>
	            </div>
	            <div class="col-md-4">
	                <p><strong>Dirección:</strong> Calle Falsa 123</p>
	                <p><strong>Localidad:</strong> La Plata</p>
	                <p><strong>Provincia:</strong> Buenos Aires</p>
	            </div>
	        </div>

	        <h5 class="mt-4">Datos de Acceso</h5>
	        <div class="row">
	            <div class="col-md-4">
	                <p><strong>Usuario:</strong> adminlaura</p>
	            </div>
	        </div>
	    </div>
	</div>

</body>
</html>

