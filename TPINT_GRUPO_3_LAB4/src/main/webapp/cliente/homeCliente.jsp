<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="entidad.Usuario"%>
<%@ page import="entidad.Cuenta"%>
<%@ page import="java.util.List"%>

<%
Usuario usuario = (Usuario) request.getAttribute("usuarioDetalle");
List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("cuentasUsuario");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Cliente</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/homeCliente.css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
	rel="stylesheet">
	<link rel="stylesheet" 
    href="${pageContext.request.contextPath}/resources/css/nav.css">
</head>
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
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/HomeClienteServlet">Home</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/CuentasClienteServlet">Cuentas</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/PrestamosClienteServlet">Préstamos</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/TransferenciasHomeServlet">Transferir</a>
					</li>
				</ul>
				<span class="navbar-text d-flex flex-row align-items-center gap-2"> <a
					class="username-link"
					href="${pageContext.request.contextPath}/VerDatosClienteServlet">
						<%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null"%>
				</a> <a
					href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar"
					class="logout-btn">Salir</a>
				</span>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="row">
			<div class="col-12">
				<h4>
					<strong>MI INFORMACIÓN PERSONAL</strong>
				</h4>
			</div>
		</div>

		<div class="row mt-4">
			<div class="col-md-4">
				<div class="info-container">
					<h5 class="section-title">Datos Personales</h5>

					<div class="info-field">
						<label class="field-label">Nombre:</label> <span
							class="field-value"> <%=usuario.getNombre()%></span>
					</div>

					<div class="info-field mt-3">
						<label class="field-label">Apellido:</label> <span
							class="field-value"> <%=usuario.getApellido()%></span>
					</div>

					<div class="info-field mt-3">
						<label class="field-label">DNI:</label> <span class="field-value"> <%=usuario.getDni()%></span>
					</div>

					<div class="info-field mt-3">
						<label class="field-label">CUIL:</label> <span class="field-value"> <%=usuario.getCuil()%></span>
					</div>

					<div class="info-field mt-3">
						<label class="field-label">Sexo:</label> <span class="field-value"> <%=usuario.getSexo()%></span>
					</div>

					<div class="info-field mt-3">
						<label class="field-label">Nacionalidad:</label> <span
							class="field-value"> <%=usuario.getNacionalidad()%></span>
					</div>

					<div class="info-field mt-3">
						<label class="field-label">Fecha de Nacimiento:</label> <span
							class="field-value"> <%=usuario.getFechaDeNacimiento()%></span>
					</div>
				</div>
			</div>

			<div class="col-md-4">
				<div class="info-container">
					<h5 class="section-title">Información de Contacto</h5>

					<div class="info-field">
						<label class="field-label">Teléfono:</label> <span
							class="field-value">  <%=usuario.getTelefono()%></span>
					</div>

					<div class="info-field mt-3">
						<label class="field-label">Email:</label> <span
							class="field-value"> <%=usuario.getCorreoElectronico()%></span>
					</div>

					<div class="info-field mt-3">
						<label class="field-label">Dirección:</label> <span
							class="field-value"> <%=usuario.getDireccion()%></span>
					</div>

					<div class="info-field mt-3">
						<label class="field-label">Localidad:</label> <span
							class="field-value"> <%=usuario.getNombreLocalidad()%></span>
					</div>

					<div class="info-field mt-3">
						<label class="field-label">Provincia:</label> <span
							class="field-value"> <%=usuario.getNombreProvincia()%></span>
					</div>

				</div>
			</div>

			<div class="col-md-4">
				<div class="info-container">
					<h5 class="section-title">Información de Acceso</h5>

					<div class="info-field">
						<label class="field-label">Usuario:</label> <span
							class="field-value"> <%=session.getAttribute("idNombre")%></span>
					</div>

					<div class="info-field mt-3">
						<label class="field-label">Estado:</label> <span
							class="field-value status-active"> <%=usuario.isEstado() ? "Activo" : "Inactivo"%></span>
					</div>
				</div>
			</div>
		</div>

		<div class="row mt-4">
			<div class="col-12">
				<div class="info-container">
					<h5 class="section-title">Resumen de Cuentas</h5>

					<%
					for (Cuenta cuenta : cuentas) {
					%>
					<div class="cuenta-resumen">
						<div class="row align-items-center">
							<div class="col-md-2">
								<strong>Cuenta N°:</strong><br> <span><%=cuenta.getId()%></span>
							</div>
							<div class="col-md-3">
								<strong>Tipo:</strong><br> <span><%=cuenta.getDescripcionTipoDeCuenta()%></span>
							</div>
							<div class="col-md-3">
								<strong>Saldo Actual:</strong><br> <span
									class="saldo-positivo">$<%=cuenta.getSaldo()%></span>
							</div>
							<div class="col-md-4">
								<strong>CBU:</strong><br> <span class="cbu-text"><%=cuenta.getCbu()%></span>
							</div>
						</div>
					</div>
					<%
					}
					%>
				</div>
			</div>
		</div>
	</div>
	<footer>
	        <p>© 2025 Grupo 3 - Laboratorio 4</p>
	</footer>
</body>
</html>

