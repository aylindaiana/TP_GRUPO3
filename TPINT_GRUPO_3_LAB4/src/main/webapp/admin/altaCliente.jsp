<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alta cliente</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/transferencias.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/altaCliente.css">
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
          <a class="nav-link active" href="${pageContext.request.contextPath}/admin/altaCliente.jsp">Alta Clientes</a>
        </li>
        <li class="nav-item">
		  <a class="nav-link" href="${pageContext.request.contextPath}/ListarUsuariosServlet">Clientes</a>
		</li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/admin/reportes.jsp">Reportes</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/CuentaAdminServlet">Cuentas</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/admin/prestamosAdmin.jsp">Prestamo</a>
        </li>
      </ul>		  
	  <span class="navbar-text d-flex flex-row">
		  <a class="nav-link align-self-center justify-content-center" href="${pageContext.request.contextPath}/admin/verUsuarioAdmin.jsp"><%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null" %></a>
		  <a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="btn btn-danger">Log Out</a>
	  </span>
    </div>
  </div>
</nav>

<%
    String status = request.getParameter("status");
    if ("success".equals(status)) {
%>
    <div class="alert alert-success alert-dismissible fade show text-center" role="alert" style="max-width: 600px; margin: 20px auto;">
        ✅ Cliente agregado exitosamente.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
<%
    } else if ("error".equals(status)) {
%>
    <div class="alert alert-danger alert-dismissible fade show text-center" role="alert" style="max-width: 600px; margin: 20px auto;">
        ❌ Hubo un error al agregar el cliente. Intente nuevamente.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
<%
    }
%>

<div class="main-container">
    <form action="${pageContext.request.contextPath}/procesarAltaCliente" method="post">
        <div class="form-columns">
            <!-- Columna Izquierda - Datos Personales -->
            <div class="form-column">
                <div class="form-section">
                    <h2>Datos personales</h2>
                    <div class="form-group">
                        <label for="dni">DNI:</label>
                        <input type="text" id="dni" name="dni" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="cuil">CUIL:</label>
                        <input type="text" id="cuil" name="cuil" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="nombre">Nombre:</label>
                        <input type="text" id="nombre" name="nombre" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="apellido">Apellido:</label>
                        <input type="text" id="apellido" name="apellido" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="sexo">Sexo:</label>
                        <select id="sexo" name="sexo" required>
                            <option value="">Seleccionar...</option>
                            <option value="M">Masculino</option>
                            <option value="F">Femenino</option>
                            <option value="O">Otro</option>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label for="nacionalidad">Nacionalidad:</label>
                        <input type="text" id="nacionalidad" name="nacionalidad" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="fechaNacimiento">Fecha de nacimiento:</label>
                        <input type="date" id="fechaNacimiento" name="fechaNacimiento" required>
                    </div>
                </div>
            </div>

            <!-- Columna Derecha - Datos de Contacto -->
            <div class="form-column">
                <div class="form-section">
                    <h2>Datos de contacto</h2>
                    <div class="form-group">
                        <label for="direccion">Dirección:</label>
                        <input type="text" id="direccion" name="direccion" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="localidad">Localidad:</label>
                        <input type="text" id="localidad" name="localidad" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="provincia">Provincia:</label>
                        <input type="text" id="provincia" name="provincia" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="email">Correo electrónico:</label>
                        <input type="email" id="email" name="email" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="telefono">Teléfono:</label>
                        <input type="tel" id="telefono" name="telefono" required>
                    </div>
                </div>
            </div>
        </div>

        <!-- Sección Inferior - Credenciales -->
        <div class="credentials-section">
            <h2>Credenciales de acceso</h2>
            <div class="form-group">
                <label for="usuario">Usuario:</label>
                <input type="text" id="usuario" name="usuario" required>
            </div>
            
            <div class="form-group">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>
            </div>
            
            <div class="form-group">
                <label for="confirmPassword">Confirmar contraseña:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
            </div>
        </div>

        <!-- Botones de acción -->
        <div class="action-buttons">
            <a href="${pageContext.request.contextPath}/admin/homeAdmin.jsp" class="cancel-button">Cancelar</a>
            <button type="submit" class="submit-button">Crear</button>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</body>
</html>
