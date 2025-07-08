<%@ page contentType="text/html; charset=UTF-8" language="java"
	import="java.util.List, entidad.Cuenta"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transferir entre cuentas propias</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/homeCliente.css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="container">

	<div class="info-container mt-5">
		<h5 class="section-title">Transferir entre tus propias cuentas</h5>

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

			<input type="hidden" name="tipoTransferencia" value="propia">

			<label>Cuenta Origen:</label> <select name="cuentaOrigen"
				class="form-select" required>
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
			</select> <label class="mt-3">Cuenta Destino:</label> <select
				name="cuentaDestino" class="form-select" required>
				<%
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
			</select> <label class="mt-3">Monto:</label> <input type="text" name="monto"
				class="form-control" required> <label class="mt-3">Comentario:</label>
			<textarea name="comentario" class="form-control"></textarea>

			<div class="d-flex gap-2 mt-3">
				<button type="submit" class="btn btn-primary px-4">Transferir</button>
				<a href="<%=request.getContextPath()%>/TransferenciasHomeServlet"
					class="btn btn-secondary px-4">Volver</a>
			</div>

		</form>
	</div>
	<footer>
	        <p>© 2025 Grupo 3 - Laboratorio 4</p>
	</footer>
</body>
</html>

