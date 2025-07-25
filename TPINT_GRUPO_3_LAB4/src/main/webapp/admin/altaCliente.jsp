<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alta cliente</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/transferencias.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/altaCliente.css">
	<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
	<link rel="stylesheet" 
    href="${pageContext.request.contextPath}/resources/css/nav.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css">
	
</head>
<body> 

	<nav class="navbar navbar-expand-lg bg-body-tertiary">
	<div class="container-fluid">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/HomeAdminServlet">Banco G3</a>
		<button class="navbar-toggler" type="button"
			data-bs-toggle="collapse" data-bs-target="#navbarText"
			aria-controls="navbarText" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/HomeAdminServlet">Home</a>
				</li>
				<li class="nav-item">
					<a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/FormularioClienteServlet">Alta Clientes</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/ListarUsuariosServlet">Clientes</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/CuentaAdminServlet">Cuentas</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/PrestamosAdminServlet">Préstamos</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${pageContext.request.contextPath}/ReportesServlet">Reportes</a>
				</li>
			</ul>
			<span class="navbar-text d-flex flex-row align-items-center gap-2">
				<a class="username-link" href="${pageContext.request.contextPath}/VerDatosAdminServlet">
					<%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null"%>
				</a>
				<a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="logout-btn">Salir</a>
			</span>
		</div>
	</div>
</nav>


	<%
	String status = request.getParameter("status");
	if ("success".equals(status)) {
	%>
	<div
		class="alert alert-success alert-dismissible fade show text-center"
		role="alert" style="max-width: 600px; margin: 20px auto;">
		✅ Cliente agregado exitosamente.
		<button type="button" class="btn-close" data-bs-dismiss="alert"
			aria-label="Close" onclick="window.location.href='ListarUsuariosServlet'"></button>
	</div>
	<%
	} else if ("error".equals(status)) {
	%>
	<div class="alert alert-danger alert-dismissible fade show text-center"
		role="alert" style="max-width: 600px; margin: 20px auto;">
		❌ Hubo un error al agregar el cliente. Intente nuevamente.
		<button type="button" class="btn-close" data-bs-dismiss="alert"
			aria-label="Close"></button>
	</div>
	<%
	}
	%>


	<div class="main-container">
		<form action="${pageContext.request.contextPath}/procesarAltaCliente"
			method="post">
			<div class="form-columns">
				<!-- Columna Izquierda - Datos Personales -->
				<div class="form-column">
					<div class="form-section">
						<h2>Datos personales</h2>
						<div class="form-group">
							<label for="dni">DNI:</label> <input type="text" id="dni"
								name="dni" required
								value="<%=request.getAttribute("dni") != null ? request.getAttribute("dni") : ""%>">
							<small class="text-danger"> <%=request.getAttribute("errorDni") != null ? request.getAttribute("errorDni") : ""%>
							</small>
						</div>

						<div class="form-group">
						    <label for="cuil">CUIL:</label>
						    <input type="text" id="cuil" name="cuil" required
						        value="<%=request.getAttribute("cuil") != null ? request.getAttribute("cuil") : ""%>">
						    <small class="text-danger">
						        <%=request.getAttribute("errorCuil") != null ? request.getAttribute("errorCuil") : ""%>
						    </small>
						</div>


						<div class="form-group">
							<label for="nombre">Nombre:</label> <input type="text"
								id="nombre" name="nombre" required
								value="<%=request.getAttribute("nombre") != null ? request.getAttribute("nombre") : ""%>">
						</div>

						<div class="form-group">
							<label for="apellido">Apellido:</label> <input type="text"
								id="apellido" name="apellido" required
								value="<%=request.getAttribute("apellido") != null ? request.getAttribute("apellido") : ""%>">
						</div>

						<div class="form-group">
							<label for="sexo">Sexo:</label> <select id="sexo" name="sexo"
								required>
								<option value="">Seleccionar...</option>
								<option value="M"
									<%="M".equals(request.getAttribute("sexo")) ? "selected" : ""%>>Masculino</option>
								<option value="F"
									<%="F".equals(request.getAttribute("sexo")) ? "selected" : ""%>>Femenino</option>
								<option value="O"
									<%="O".equals(request.getAttribute("sexo")) ? "selected" : ""%>>Otro</option>
							</select>
						</div>

						<div class="form-group">
							<label for="nacionalidad">Nacionalidad:</label> <input
								type="text" id="nacionalidad" name="nacionalidad" required
								value="<%=request.getAttribute("nacionalidad") != null ? request.getAttribute("nacionalidad") : ""%>">
						</div>

						<div class="form-group">
						    <label for="fechaNacimiento">Fecha de nacimiento:</label> 
						    <input type="date" id="fechaNacimiento" name="fechaNacimiento" required
						        value="<%=request.getAttribute("fechaNacimiento") != null ? request.getAttribute("fechaNacimiento") : ""%>">
						    <small class="text-danger">
						        <%=request.getAttribute("errorEdad") != null ? request.getAttribute("errorEdad") : "" %>
						    </small>
						</div>

					</div>
				</div>

				<!-- Columna Derecha - Datos de Contacto -->
				<div class="form-column">
					<div class="form-section">
						<h2>Datos de contacto</h2>
						<div class="form-group">
							<label for="direccion">Dirección:</label> <input type="text"
								id="direccion" name="direccion" required
								value="<%=request.getAttribute("direccion") != null ? request.getAttribute("direccion") : ""%>">
						</div>

						<div class="form-group mb-3">
						    <label for="provincia">Provincia:</label>
						    <select id="provincia" name="provincia" required class="form-select">
						        <option value="">Seleccionar provincia...</option>
						        <%
						        List<entidad.Provincia> provincias = (List<entidad.Provincia>) request.getAttribute("provincias");
						        String provinciaSeleccionada = String.valueOf(request.getAttribute("provincia"));
						        if (provincias != null) {
						            for (entidad.Provincia p : provincias) {
						                String selected = String.valueOf(p.getIdProvincia()).equals(provinciaSeleccionada) ? "selected" : "";
						        %>
						            <option value="<%=p.getIdProvincia()%>" <%=selected%>><%=p.getNombreProvincia()%></option>
						        <%
						            }
						        }
						        %>
						    </select>
						</div>


						<div class="form-group mb-3">
						    <label for="localidad">Localidad:</label>
						    <select id="localidad" name="localidad" required class="form-select">
						        <option value="">Seleccionar localidad...</option>
						        <%
						        List<entidad.Localidad> localidades = (List<entidad.Localidad>) request.getAttribute("localidades");
						        String localidadSeleccionada = String.valueOf(request.getAttribute("localidad"));
						        if (localidades != null) {
						            for (entidad.Localidad l : localidades) {
						                String selected = String.valueOf(l.getIdLocalidad()).equals(localidadSeleccionada) ? "selected" : "";
						        %>
						            <option value="<%=l.getIdLocalidad()%>" <%=selected%>><%=l.getNombreLocalidad()%></option>
						        <%
						            }
						        }
						        %>
						    </select>
						</div>




						<div class="form-group">
							<label for="email">Correo electrónico:</label> <input
								type="email" id="email" name="email" required
								value="<%=request.getAttribute("email") != null ? request.getAttribute("email") : ""%>">
						</div>

						<div class="form-group">
							<label for="telefono">Teléfono:</label> <input type="text"
								id="telefono" name="telefono" required
								value="<%=request.getAttribute("telefono") != null ? request.getAttribute("telefono") : ""%>">
							<small class="text-danger"> <%=request.getAttribute("errorTelefono") != null ? request.getAttribute("errorTelefono") : ""%>
							</small>
						</div>
					</div>
				</div>
			</div>

			<!-- Sección Inferior - Credenciales -->
			<div class="credentials-section">
				<h2>Credenciales de acceso</h2>
				<div class="form-group">
					<label for="usuario">Usuario:</label> <input type="text"
						id="usuario" name="usuario" required
						value="<%=request.getAttribute("usuario") != null ? request.getAttribute("usuario") : ""%>">
					<small class="text-danger"> <%=request.getAttribute("errorNombreUsuario") != null ? request.getAttribute("errorNombreUsuario") : ""%>
					</small>
				</div>


				<div class="form-group">
					<label for="password">Contraseña:</label> <input type="password"
						id="password" name="password" required>
				</div>

				<div class="form-group">
					<label for="confirmPassword">Confirmar contraseña:</label> <input
						type="password" id="confirmPassword" name="confirmPassword"
						required> <small class="text-danger"> <%=request.getAttribute("errorPassword") != null ? request.getAttribute("errorPassword") : ""%>
					</small>
				</div>
			</div>

			<!-- Botones de acción -->
			<div class="action-buttons">
				<a href="${pageContext.request.contextPath}/HomeAdminServlet"
					class="cancel-button">Cancelar</a>
				<button type="submit" class="submit-button">Crear</button>
			</div>
		</form>
	</div>
	<footer>
	        <p>© 2025 Grupo 3 - Laboratorio 4</p>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
		crossorigin="anonymous"></script>
</body>
</html>
