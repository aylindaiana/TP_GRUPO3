<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entidad.Cuenta" %>
<%@ page import="entidad.Movimiento" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalle Cuenta</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/detalleCuenta.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" 
    href="${pageContext.request.contextPath}/resources/css/nav.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/footer.css">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/HomeAdminServlet">Banco G3</a>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/HomeAdminServlet">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/FormularioClienteServlet">Alta Clientes</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}//ListarUsuariosServlet">Clientes</a></li>
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/CuentaAdminServlet">Cuentas</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/PrestamosAdminServlet">Préstamos</a></li>
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/reportes.jsp">Reportes</a></li>
            </ul>
            <span class="navbar-text d-flex flex-row align-items-center gap-2">
                <a class="nav-link align-self-center" href="${pageContext.request.contextPath}/VerDatosAdminServlet">
                    <%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre") : "Admin"%>
                </a>
                <a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="logout-btn">Salir</a>
            </span>

        </div>
    </div>
</nav>

<div class="container mt-4">

    <%
        Cuenta cuenta = (Cuenta) request.getAttribute("cuenta");
	    String modo = (String) request.getAttribute("modo");
	    if (modo == null) modo = "ver";
	
	    if ("crear".equals(modo)) {
	        cuenta = new Cuenta(); 

	        String idClienteStr = (String) request.getAttribute("idClienteCrear");
	        if(idClienteStr != null){
			    int idCliente = Integer.parseInt(idClienteStr);
		        cuenta.setIdCliente(idCliente);
	        }
	        cuenta.setCbu((String)request.getAttribute("cbu"));
	    }

        String error = (String) request.getAttribute("error");
    %>
    <% if ("crear".equals(modo)) { %>
	    <h3>Creación de nueva cuenta</h3>
	<% } else { %>
	    <h3>Detalle de cuenta</h3>
	<% } %>

    <% if (error != null) { %>
        <div class="alert alert-danger"><%= error %></div>
    <% } %>

    <% if (cuenta != null || "crear".equals(modo)) { %>
        <form action="${pageContext.request.contextPath}/DetalleCuentaServlet" method="post">
			<input type="hidden" name="idCuenta" value="<%= cuenta != null ? cuenta.getId() : 0 %>">
			<input type="hidden" name="modo" value="<%= modo %>">


					
			<div class="mb-3">
				<label for="idCliente" class="form-label">ID Cliente</label>
				<input type="number" class="form-control" name="idCliente" id="idCliente" required 
					value="<%= cuenta != null ? cuenta.getIdCliente() : "" %>">
			</div>

            <div class="mb-3">
                <label for="tipoCuenta" class="form-label">Tipo de cuenta</label>
                <select class="form-select" name="tipoCuenta" id="tipoCuenta">
                    <option value="1" <%= cuenta != null && cuenta.getIdTipoDeCuenta() == 1 ? "selected" : "" %>>Caja de ahorro</option>
                    <option value="2" <%= cuenta != null && cuenta.getIdTipoDeCuenta() == 2 ? "selected" : "" %>>Cuenta corriente</option>
                </select>
            </div>

            <div class="mb-3">
                <label for="cbu" class="form-label">CBU</label>
                <input type="text" class="form-control disabled" name="cbu" id="cbu" required readonly
                       value="<%= cuenta != null ? cuenta.getCbu() : " "%>">
            </div>
            <div class="mb-3">
        		<label for="fechaCreacion" class="form-label">Fecha de creación</label>
        		<input type="date" class="form-control disabled" name="fechaCreacion" id="fechaCreacion"
               value="<%= cuenta != null && cuenta.getFechaDeCreacion() != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(cuenta.getFechaDeCreacion()) : new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" 
               required readonly>
    		</div>

            <% if ("editar".equals(modo)) { %>
                <div class="mb-3">
                    <label for="saldo" class="form-label">Saldo</label>
                    <input type="number" class="form-control" name="saldo" id="saldo" step="0.01"
                           value="<%= cuenta != null ? cuenta.getSaldo() : 0.0 %>" required>
                </div>
            <% } else { %>
                <input type="hidden" name="saldo" value="10000.0">
            <% } %>
			
            <button type="submit" class="btn btn-primary">
                <%= "editar".equals(modo) ? "Guardar cambios" : "Crear cuenta" %>
            </button>
        </form>

        <% if (cuenta != null && "editar".equals(modo)) { %>
            <form action="${pageContext.request.contextPath}/InactivarCuentaServlet" method="post" class="d-inline" onsubmit="return confirmarEliminacion();">
                <input type="hidden" name="id" value="<%= cuenta.getId() %>">
                <% if (cuenta.isEstado()) { %>
                    <button type="submit" class="btn btn-danger">Inactivar</button>
                <% } %>
            </form>

            <form action="${pageContext.request.contextPath}/ActivarCuentaServlet" method="post" class="d-inline" onsubmit="return confirmarActivacion();">
                <input type="hidden" name="id" value="<%= cuenta.getId() %>">
                <input type="hidden" name="idCliente" value="<%= cuenta.getIdCliente() %>">
                <% if (!cuenta.isEstado()) { %>
                    <button type="submit" class="btn btn-success">Activar</button>
                <% } %>
            </form>
            <div class="tabla-movimientos mt-4">
	        <h4>Movimientos</h4>
		        <table class="table">
		            <thead>
		                <tr>
		                	<th>Fecha</th>
		                    <th>Tipo de Movimiento</th>
		                    <th>Monto</th>
		                </tr>
		            </thead>
		            <tbody>
					    <%
					        List<Movimiento> movimientos = (List<Movimiento>) request.getAttribute("movimientos");
					        if (movimientos != null && !movimientos.isEmpty()) {
					            for (Movimiento m : movimientos) {
					    %>
					        <tr>
					            <td><%= new java.text.SimpleDateFormat("dd/MM/yyyy").format(m.getFecha()) %></td>
					            <td><%= m.getDescripcionTipoDeMovimiento() %> </td>
					            
					            <% if(m.getIDCuentaOrigen() != cuenta.getId() || m.getIDTipoDeMovimiento() == 1){ %>
						            <td class="text-success">
						            	+ $<%= Math.abs(m.getMonto()) %>
						            </td>
						        <% } else { %>
						            <td class="text-danger">
						            	- $<%= Math.abs(m.getMonto()) %>
						            </td>
					            <%}%>
					        </tr>
					    <%
					            }
					        } else {
					    %>
					        <tr><td colspan="3">No hay movimientos en esta cuenta.</td></tr>
					    <%
					        }
					    %>
		            </tbody>
		        </table>
    		</div>
        <% } %>

        <div class="mt-3">
            <a href="${pageContext.request.contextPath}/CuentaAdminServlet" class="btn btn-secondary">Volver</a>
        </div>
    <% } else { %>
        <div class="alert alert-warning">No se encontró la cuenta.</div>
    <% } %>

    
</div>
	<footer>
	        <p>© 2025 Grupo 3 - Laboratorio 4</p>
	</footer>
<script>
    function confirmarEliminacion() {
        return confirm("¿Estás seguro que deseas inactivar la cuenta?");
    }
    function confirmarActivacion() {
        return confirm("¿Estás seguro que deseas activar la cuenta?");
    }
</script>
</body>
</html>
