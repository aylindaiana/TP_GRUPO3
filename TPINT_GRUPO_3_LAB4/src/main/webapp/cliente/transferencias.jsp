<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List, entidad.Cuenta, entidad.Transferencia"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transferencias</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/homeCliente.css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Bankame</a>
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
						href="${pageContext.request.contextPath}/PrestamosClienteServlet">Préstamo</a>
					</li>
					<li class="nav-item active"><a class="nav-link"
						href="${pageContext.request.contextPath}/TransferenciasHomeServlet">Transferir</a>
					</li>
				</ul>
				<span class="navbar-text d-flex flex-row"> <a
					class="nav-link align-self-center justify-content-center"
					href="${pageContext.request.contextPath}/cliente/verUsuarioCliente.jsp">
						<%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null"%>
				</a> <a
					href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar"
					class="btn btn-danger">Log Out</a>
				</span>
			</div>
		</div>
	</nav>

	<div class="container pt mt-5">
		<div class="row">
			<!-- === TRANSFERENCIA (arriba) === -->
			<div class="col-md-6 mx-auto mb-4">
				<div class="info-container transferencia text-center">
					<h5 class="section-title">Transferencia</h5>

					<p class="fw-bold">Seleccione tipo de transferencia</p>

					<form method="get"
						action="${pageContext.request.contextPath}/TransferenciaServlet">

						<div class="transfer-buttons">
							<button type="submit" name="tipo" value="propia"
								class="btn btn-primary">Transferir entre cuentas
								propias</button>

							<button type="submit" name="tipo" value="cbu"
								class="btn btn-secondary">Transferir a otro cliente
								(CBU)</button>
						</div>

					</form>
				</div>
			</div>

			<!-- === TRANSFERENCIAS RECIENTES (abajo) === -->
			<div class="col-12 mb-4">
				<div class="info-container">
					<h5 class="section-title">Transferencias recientes</h5>

					<table class="table table-striped-columns">
						<thead>
							<tr>
								<th scope="col">Fecha</th>
								<th scope="col">Tipo</th>
								<th scope="col">Cuenta Origen</th>
								<th scope="col">Cuenta Destino</th>
								<th scope="col">Monto</th>
								<th scope="col">Comentario</th>
							</tr>
						</thead>
						<tbody>
							<%
							List<entidad.Transferencia> ultimasTransferencias = (List<entidad.Transferencia>) request
									.getAttribute("ultimasTransferencias");
							if (ultimasTransferencias != null && !ultimasTransferencias.isEmpty()) {
								for (entidad.Transferencia t : ultimasTransferencias) {
									String claseOperacion = t.getTipoMovimiento().equals("EGRESO") ? "text-danger" : "text-success";
									String signo = t.getTipoMovimiento().equals("EGRESO") ? "-" : "+";
							%>
							<tr>
								<td><%=t.getFecha()%></td>
								<td class="<%=claseOperacion%>"><%=t.getTipoMovimiento()%></td>
								<td><%=t.getIdCuentaOrigen()%></td>
								<td><%=t.getIdCuentaDestino()%></td>
								<td><span class="<%=claseOperacion%>"><%=signo%>$<%=t.getMonto()%></span></td>
								<td><%=t.getComentario()%></td>
							</tr>
							<%
							}
							} else {
							%>
							<tr>
								<td colspan="6">No hay transferencias recientes.</td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
					<div class="d-flex justify-content-center mt-2">
						<a href="#" class="btn btn-primary px-4">Ver todo</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

