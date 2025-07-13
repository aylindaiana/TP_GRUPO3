<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entidad.Cuenta" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cuentas</title>
<link 
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" 
        crossorigin="anonymous">
    <link rel="stylesheet" 
        href="${pageContext.request.contextPath}/resources/css/cuentasAdmin.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" 
    href="${pageContext.request.contextPath}/resources/css/nav.css">
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="${pageContext.request.contextPath}/HomeAdminServlet">Banco G3</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarText">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/HomeAdminServlet">Home</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/FormularioClienteServlet">Alta Clientes</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/ListarUsuariosServlet">Clientes</a></li>
			<li class="nav-item"><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/CuentaAdminServlet">Cuentas</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/PrestamosAdminServlet">Préstamos</a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/ReportesServlet">Reportes</a></li>
	      </ul>		  
		  <span class="navbar-text d-flex flex-row align-items-center gap-2">
			  <a class="nav-link align-self-center justify-content-center" href="${pageContext.request.contextPath}/VerDatosAdminServlet"><%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null" %></a>
			  <a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="logout-btn">Salir</a>
		  </span>
	    </div>
	  </div>
	</nav>
	
	<div class="container" style="margin-top: 30px;">
		<div class="row">
            <div class="col-12">
                <h4><strong>Buscar cuentas:</strong></h4>
                <label>En el caso que sea la busqueda por cliente, aparecen las cuentas en su totalidad, si la busqueda es por CBU, aparece la cuenta en cuestion.</label>
                <label>SE BUSCA UN CLIENTE A LA VEZ</label>
            </div>
    	</div>
		<div class="row mt-3">
		    <div class="col-12">
		        <form method="get" action="${pageContext.request.contextPath}/CuentaAdminServlet">
		            <div class="row align-items-end">
		                <div class="col-md-4">
		                    <label for="filtroCliente" class="form-label"><strong>Búsqueda:</strong></label>
		                    <input type="text" class="form-control" id="filtroCliente" name="filtroCliente"
		                        placeholder="Buscar cliente..." 
		                        value="<%= request.getAttribute("busquedaAnterior") != null ? request.getAttribute("busquedaAnterior") : "" %>">
		                </div>
		                <div class="col-md-4">
		                    <label for="filtroCBU" class="form-label"><strong>CBU:</strong></label>
		                    <input type="text" class="form-control" id="filtroCBU" name="filtroCBU"
		                        placeholder="Buscar por CBU..." 
		                        value="<%= request.getAttribute("cbuAnterior") != null ? request.getAttribute("cbuAnterior") : "" %>">
		                </div>
		                <div class="col-md-4 d-flex align-items-end">
		                    <button type="submit" class="btn btn-secondary">Buscar</button>
		                    <a href="${pageContext.request.contextPath}/CuentaAdminServlet" class="btn btn-outline-secondary ms-2">Limpiar</a>
		                </div>
		            </div>
		        </form>
		    </div>
		</div>

       
        <div class="row mt-3">
		    <div class="col-12 text-end">
				<a href="${pageContext.request.contextPath}/DetalleCuentaServlet?modo=crear" class="btn btn-primary">
				    Crear Nueva Cuenta
				</a>

		    </div>
		</div>
        
    <div class="contenido">
	    <div class="titulo-seccion">Cuentas</div>
	    <div class="tabla-cuentas">
        <table>
            <thead>
                <tr>
                	<th>Nro Cuenta</th>
                	<th>ID cliente</th>
                	<th>Cliente</th> 
                    <th>CBU</th>
                    <th>Tipo de cuenta</th>
                    <th>Fecha de Creacion</th>
                    <th>Saldo</th>
                    <th>Estado</th>
                    <th>Acción</th>
                </tr>
            </thead>
			<tbody>
			<%
			    List<Cuenta> cuentas = (List<Cuenta>) request.getAttribute("listaCuentas");
			    if (cuentas != null) {
			        for (Cuenta cuenta : cuentas) {
			%>
			    <tr>
			    	<td><%= cuenta.getId() %></td>
			    	<td><%= cuenta.getIdCliente() %></td>
			        <td><%= cuenta.getNombreCliente() %></td>
			        <td>..................<%= cuenta.getCbu().substring(cuenta.getCbu().length() - 3) %></td>
			        <td><%= cuenta.getIdTipoDeCuenta() == 1 ? "Cuenta corriente" : "Caja de ahorro" %></td>
			        <td><%= cuenta.getFechaDeCreacion() %></td>
			        <td>$<%= String.format("%.2f", cuenta.getSaldo()) %></td>
			        <td>
			            <span class="badge <%= cuenta.isEstado() ? "bg-success" : "bg-danger" %>">
			                <%= cuenta.isEstado() ? "Activa" : "Inactiva" %>
			            </span>
			        </td>
			        <td>
			            <form method="get" action="DetalleCuentaServlet">
			                <input type="hidden" name="idCuenta" value="<%= cuenta.getId() %>"/>
			                <input type="hidden" name="modo" value="editar"/>
			                <button type="submit" class="ver-btn">ver</button>
			            </form>
			        </td>
			    </tr>
			<%
			        }
			    } else {
			%>
			    <tr><td colspan="7">No hay cuentas disponibles.</td></tr>
			<%
			    }
			%>
			</tbody>

        </table>
    	</div>
	</div>
</div>
	<footer>
	        <p>© 2025 Grupo 3 - Laboratorio 4</p>
	</footer>
</body>
</html>