<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<body>
		<header>
		<div>
			<strong>ADMINISTRADOR</strong>
		</div>
		<nav>
			<div>
				<a href="${pageContext.request.contextPath}/admin/homeAdmin.jsp">Home</a>
				<a
					href="${pageContext.request.contextPath}/admin/altaCliente.jsp">Alta cliente</a>
				<a href="${pageContext.request.contextPath}/admin/clientes.jsp">Lista Clientes</a>
				<a href="${pageContext.request.contextPath}/admin/cuentasAdmin.jsp">Cuentas</a>
				<a href="${pageContext.request.contextPath}/admin/prestamosAdmin.jsp"><u>Préstamos</u></a>
				<a href="${pageContext.request.contextPath}/admin/reportes.jsp">Reportes</a>
				<span style="margin-left: 20px;">Usuario</span>
				<button class="logout-btn">LOG OUT</button>
			</div>
		</nav>
	</header>
	
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
                <div class="search-container">
                    <div class="row align-items-end">
                        <div class="col-md-4">
                            <label for="busqueda" class="form-label"><strong>Búsqueda:</strong></label>
                            <input type="text" class="form-control" id="busqueda" placeholder="Buscar cliente...">
                        </div>
                        <div class="col-md-4">
                            <label for="cbu" class="form-label"><strong>CBU:</strong></label>
                            <input type="text" class="form-control" id="cbu" placeholder="Buscar por CBU...">
                        </div>
                        <div class="col-md-4">
                            <button type="button" class="btn btn-secondary">Buscar</button>
                            <button type="button" class="btn btn-outline-secondary ms-2">Limpiar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
    <div class="contenido">
	    <div class="titulo-seccion">Cuentas</div>
	    <div class="tabla-cuentas">
        <table>
            <thead>
                <tr>
                	<th>Cliente</th> 
                    <th>Nombre cuenta</th>
                    <th>Tipo de cuenta</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                	<td>Antonio Melino</td>
                    <td>..................X943</td>
                    <td>Cuenta corriente</td>
                    <td><button class="ver-btn" onclick="location.href='verCuentaAdmin.jsp'">ver</button></td>
                </tr>
                <tr>
                	<td>Antonio Melino</td>
                    <td>..................A421</td>
                    <td>Cuenta de ahorro</td>
                    <td><button class="ver-btn" onclick="location.href='verCuentaAdmin.jsp'">ver</button></td>
                </tr>
                <tr>
                	<td>Antonio Melino</td>
                    <td>..................X123</td>
                    <td>Cuenta de ahorro</td>
                    <td><button class="ver-btn" onclick="location.href='verCuentaAdmin.jsp'">ver</button></td>
                </tr>
            </tbody>
        </table>
    	</div>
	</div>
</div>
</body>
</html>