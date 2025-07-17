<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.util.List, entidad.Cuenta"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transferir a otro cliente (CBU)</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/homeCliente.css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/nav.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/footer.css">
</head>
<body>

	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand"
				href="${pageContext.request.contextPath}/HomeClienteServlet">Banco
				G3</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarText"
				aria-controls="navbarText" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarText">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/HomeClienteServlet">Home</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/CuentasClienteServlet">Cuentas</a>
					</li>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/PrestamosClienteServlet">Préstamos</a>
					</li>
					<li class="nav-item active"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.request.contextPath}/TransferenciasHomeServlet">Transferir</a>
					</li>
				</ul>
				<span class="navbar-text d-flex flex-row align-items-center gap-2">
					<a class="username-link"
						href="${pageContext.request.contextPath}/VerDatosClienteServlet">
						<%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null"%>
					</a> <a
						href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar"
						class="logout-btn">Salir</a>
				</span>
			</div>
		</div>
	</nav>

	<div class="container pt mt-5">
		<div class="row">
			<div class="col-md-8 mx-auto">
				<div class="info-container">
					<h5 class="section-title text-center">Transferir a otro cliente (CBU)</h5>

					<%
					String errorGeneral = (String) request.getAttribute("errorGeneral");
					String success = (String) request.getAttribute("success");
					if (errorGeneral != null) {
					%>
					<div class="alert alert-danger"><%=errorGeneral%></div>
					<%
					} else if (success != null) {
					%>
					<div class="alert alert-success"><%=success%></div>
					<%
					}
					%>

					<form method="post"
						action="<%=request.getContextPath()%>/TransferenciaServlet">

						<input type="hidden" name="tipoTransferencia" value="cbu">

						<label class="mt-3">Cuenta Origen:</label>
						<select name="cuentaOrigen" class="form-select" required>
							<%
							List<entidad.Cuenta> cuentasPropias = (List<entidad.Cuenta>) request.getAttribute("cuentasPropias");
							if (cuentasPropias != null) {
								for (entidad.Cuenta cuenta : cuentasPropias) {
							%>
							<option value="<%=cuenta.getId()%>">
								<%=cuenta.getCbu()%> - Saldo: $<%=cuenta.getSaldo()%>
							</option>
							<%
								}
							}
							%>
						</select>

						<label class="mt-3">CBU Destino:</label>
						<input type="text" name="cbuDestino" class="form-control" required>

						<label class="mt-3">Monto:</label>
						<input type="text" name="monto" class="form-control" required>

						<label class="mt-3">Comentario:</label>
						<textarea name="comentario" class="form-control"></textarea>

						<div class="d-flex gap-2 mt-3 justify-content-center">
							<button type="submit" class="btn btn-primary px-4">Transferir</button>
							<a href="<%=request.getContextPath()%>/TransferenciasHomeServlet"
								class="btn btn-secondary px-4">Volver</a>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>

	<footer>
		<p>© 2025 Grupo 3 - Laboratorio 4</p>
	</footer>
</body>
</html>
