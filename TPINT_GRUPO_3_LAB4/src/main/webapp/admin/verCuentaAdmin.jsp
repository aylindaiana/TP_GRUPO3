<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<body>
		<header>
		<div>
			<strong>ADMINISTRADOR</strong>
		</div>
		<nav>
			<div>
				<a href="${pageContext.request.contextPath}/admin/homeAdmin.jsp">Home</a>
				<a href="${pageContext.request.contextPath}/admin/altaCliente.jsp">Alta cliente</a>
				<a href="${pageContext.request.contextPath}/admin/clientes.jsp">Lista Clientes</a>
				<a href="${pageContext.request.contextPath}/admin/cuentasAdmin.jsp">Cuentas</a>
				<a href="${pageContext.request.contextPath}/admin/prestamosAdmin.jsp"><u>Préstamos</u></a>
				<a href="${pageContext.request.contextPath}/admin/reportes.jsp">Reportes</a>
				<span style="margin-left: 20px;">Usuario</span>
				<button class="logout-btn">LOG OUT</button>
			</div>
		</nav>
	</header>
	<div class="contenido">
	    <div class="titulo-seccion">Cuenta</div>
	
	    <div class="tabla-cuentas">
	        <div style="display: flex; justify-content: space-between; align-items: center;">
	            <div>
	                <p><strong>nombre cuenta:</strong> .................X123</p>
	                <p><strong>tipo de cuenta:</strong> Cuenta de ahorro</p>
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