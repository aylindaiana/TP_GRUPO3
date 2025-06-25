<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidad.Cuenta" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalle Cuenta</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Bankame</a>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/homeAdmin.jsp">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/altaCliente.jsp">Alta Clientes</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/clientes.jsp">Clientes</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/reportes.jsp">Reportes</a></li>
                <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/admin/cuentasAdmin.jsp">Cuentas</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/prestamosAdmin.jsp">Prestamo</a></li>
            </ul>
            <span class="navbar-text d-flex flex-row">
                <a class="nav-link align-self-center" href="${pageContext.request.contextPath}/admin/verUsuarioAdmin.jsp">
                    <%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre") : "Admin"%>
                </a>
                <a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="btn btn-danger ms-2">Log Out</a>
            </span>
        </div>
    </div>
</nav>

<div class="container mt-4">
    <h3>Detalle de Cuenta</h3>

    <% 
        String error = (String) request.getAttribute("error"); 
        Cuenta cuenta = (Cuenta) request.getAttribute("cuenta"); 
    %>

    <% if (error != null) { %>
        <div class="alert alert-danger"><%= error %></div>
    <% } %>

    <% if (cuenta != null) { %>
        <div class="mb-3">
            <label class="form-label">ID Cuenta</label>
            <input type="text" class="form-control" value="<%= cuenta.getId() %>" readonly>
        </div>

        <div class="mb-3">
            <label class="form-label">ID Cliente</label>
            <input type="text" class="form-control" value="<%= cuenta.getIdCliente() %>" readonly>
        </div>

        <div class="mb-3">
            <label class="form-label">Tipo de Cuenta</label>
            <input type="text" class="form-control" 
                   value="<%= cuenta.getIdTipoDeCuenta() == 1 ? "Cuenta Corriente" : "Caja de Ahorro" %>" 
                   readonly>
        </div>

        <div class="mb-3">
            <label class="form-label">CBU</label>
            <input type="text" class="form-control" value="<%= cuenta.getCbu() %>" readonly>
        </div>

        <div class="mb-3">
            <label class="form-label">Saldo</label>
            <input type="text" class="form-control" value="<%= cuenta.getSaldo() %>" readonly>
        </div>
		
        <a href="${pageContext.request.contextPath}/CuentaAdminServlet" class="btn btn-secondary">Volver</a>
    <% } else { %>
        <div class="alert alert-warning">No se encontró la cuenta.</div>
    <% } %>
    	<form
					action="${pageContext.request.contextPath}/InactivarCuentaServlet"
					method="post" style="display: inline;"
					onsubmit="return confirmarEliminacion();">
					<input type="hidden" name="id" value="<%=cuenta.getId()%>">
					<%
						if(cuenta.isEstado()) {
					%>
						<button type="submit" class="btn btn-danger btn-lg">Inactivar</button>
					<%
					} 
					%>
		</form>
		<form
					action="${pageContext.request.contextPath}/ActivarCuentaServlet"
					method="post" style="display: inline;"
					onsubmit="return confirmarActivacion();">
					<input type="hidden" name="id" value="<%=cuenta.getId()%>">
					<%
						if(!cuenta.isEstado()) {
					%>
						<button type="submit" class="btn btn-primary btn-lg">Activar</button>
					<%
					} 
					%>
		</form>
		<script>
			function confirmarEliminacion() {
				return confirm("¿Estás seguro que deseas inactivar la cuenta?");
			}
			function confirmarActivacion() {
				return confirm("¿Estás seguro que deseas activar la cuenta?");
			}
			
		</script>
</div>
</body>
</html>
