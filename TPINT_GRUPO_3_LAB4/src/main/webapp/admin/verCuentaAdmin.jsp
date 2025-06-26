<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entidad.Cuenta" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalle cuenta</title>
<link 
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" 
        crossorigin="anonymous">
    <link rel="stylesheet" 
        href="${pageContext.request.contextPath}/resources/css/cuentasAdmin.css">
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
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/altaCliente.jsp">Alta Clientes</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/clientes.jsp">Clientes</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/reportes.jsp">Reportes</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" href="${pageContext.request.contextPath}/admin/cuentasAdmin.jsp">Cuentas</a>
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
	<div class="contenido">
	    <div class="titulo-seccion">Cuenta</div>
	
	    <div class="tabla-cuentas">
	        <div style="display: flex; justify-content: space-between; align-items: center;">
	            <div>
	                <%
					    Cuenta cuenta = (Cuenta) request.getAttribute("cuenta");
					    String modo = (String) request.getAttribute("modo");
					%>
					<p><strong>CBU:</strong> <%= cuenta != null ? cuenta.getCbu() : "N/A" %></p>
					<p><strong>Tipo de cuenta:</strong> 
					    <%= (cuenta != null && cuenta.getIdTipoDeCuenta() == 1) ? "Cuenta corriente" : "Caja de ahorro" %>
					</p>
					<% String error = (String) request.getAttribute("error"); %>
						<% if (error != null) { %>
						    <div class="alert alert-danger"><%= error %></div>
					<% } %>
					<form method="post" action="${pageContext.request.contextPath}/DetalleCuentaServlet">
					    <input type="hidden" name="idCuenta" value="<%= cuenta != null ? cuenta.getId() : 0 %>">
					
					    <div class="mb-3">
					        <label for="idCliente" class="form-label">ID Cliente</label>
					        <input type="number" class="form-control" name="idCliente" id="idCliente" required 
					               value="<%= cuenta != null ? cuenta.getIdCliente() : "" %>">
					    </div>
					
					    <div class="mb-3"> 
					        <label for="tipoCuenta" class="form-label">Tipo de cuenta</label>
					        <select class="form-select" name="tipoCuenta" id="tipoCuenta">
					            <option value="1" <%= cuenta != null && cuenta.getIdTipoDeCuenta() == 1 ? "selected" : "" %>>Cuenta corriente</option>
					            <option value="2" <%= cuenta != null && cuenta.getIdTipoDeCuenta() == 2 ? "selected" : "" %>>Caja de ahorro</option>
					        </select>
					    </div>
					
					    <div class="mb-3">
					        <label for="cbu" class="form-label">CBU</label>
					        <input type="text" class="form-control" name="cbu" id="cbu" required 
					               value="<%= cuenta != null ? cuenta.getCbu() : "" %>">
					    </div>
					
					    <% if ("editar".equals(modo)) { %>
						    <div class="mb-3">
						        <label for="saldo" class="form-label">Saldo</label>
						        <input type="number" class="form-control" name="saldo" id="saldo" step="0.01" required
						               value="<%= cuenta != null ? String.format("%.2f", cuenta.getSaldo()) : 0 %>">
						    </div>
						<% } else { %>
						    <input type="hidden" name="saldo" value="10.000">
						<% } %>
					
					    <button type="submit" class="btn btn-primary">
					        <%= "editar".equals(modo) ? "Guardar cambios" : "Crear cuenta" %>
					    </button>
					</form>
					
	            </div>
	            <button class="Prestamos-btn" onclick="location.href='prestamosAdmin.jsp'">Prestamos</button>
	            <button class="volver-btn" onclick="location.href='cuentasAdmin.jsp'">VOLVER</button>
	            
	        </div>
	    </div>
	
	    <div class="tabla-movimientos">
	        <table>
	            <thead>
	                <tr>
	                    <th>Movimiento</th>
	                    <th>Monto</th>
	                </tr>
	            </thead>
	            <tbody>
	                <tr>
	                    <td>IN</td>
	                    <td class="positivo">3000</td>
	                </tr>
	                <tr>
	                    <td>TE</td>
	                    <td class="negativo">2000</td>
	                </tr>
	                <tr>
	                    <td>TR</td>
	                    <td class="negativo">13000</td>
	                </tr>
	                <tr>
	                    <td>PC</td>
	                    <td class="negativo">14000</td>
	                </tr>
	            </tbody>
	        </table>
	    </div>
	</div>

</body>
</html>