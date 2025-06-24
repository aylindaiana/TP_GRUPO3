<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cuentas - Cliente</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/cuentas.css">
        
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
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
	          <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/cliente/homeCliente.jsp">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" href="${pageContext.request.contextPath}/cliente/cuentas.jsp">Cuentas</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/cliente/prestamos.jsp">Prestamo</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/cliente/transferencias.jsp">Transferir</a>
	        </li>
	      </ul>	  
	      <span class="navbar-text d-flex flex-row">
		    <a class="nav-link align-self-center justify-content-center" href="${pageContext.request.contextPath}/cliente/verUsuarioCliente.jsp"><%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null" %></a>
		    <a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="btn btn-danger">Log Out</a>
		  </span>
	    </div>
	  </div>
	</nav>

<div class="contenido">
    <div class="titulo-seccion">Cuentas</div>

    <div class="tabla-cuentas">
        <table>
            <thead>
                <tr>
                    <th>nombre cuenta</th>
                    <th>tipo de cuenta</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>..................X123</td>
                    <td>Cuenta de ahorro</td>
                    <td><button class="ver-btn" onclick="location.href='verCuenta.jsp'">ver</button></td>
                </tr>
                <tr>
                    <td>..................X943</td>
                    <td>Cuenta corriente</td>
                    <td><button class="ver-btn" onclick="location.href='verCuenta.jsp'">ver</button></td>
                </tr>
                <tr>
                    <td>..................A421</td>
                    <td>Cuenta de ahorro</td>
                    <td><button class="ver-btn" onclick="location.href='verCuenta.jsp'">ver</button></td>
                </tr>
                <tr>
                    <td>..................X123</td>
                    <td>Cuenta de ahorro</td>
                    <td><button class="ver-btn" onclick="location.href='verCuenta.jsp'">ver</button></td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>

