<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Ver cuentas</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/cuentas.css">
        
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
	<link rel="stylesheet" 
    href="${pageContext.request.contextPath}/resources/css/nav.css">
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="${pageContext.request.contextPath}/HomeClienteServlet">Banco G3</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarText">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/HomeClienteServlet">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/CuentasClienteServlet">Cuentas</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/PrestamosClienteServlet">Préstamos</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/TransferenciasHomeServlet">Transferir</a>
	        </li>
	      </ul>	  
	      <span class="navbar-text d-flex flex-row align-items-center gap-2">
		    <a class="username-link" href="${pageContext.request.contextPath}/VerDatosClienteServlet"><%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null" %></a>
		    <a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="logout-btn">Salir</a>
		  </span>
	    </div>
	  </div>
	</nav>

<div class="contenido">
    <div class="titulo-seccion">Cuenta</div>

    <div class="tabla-cuentas">
        <div style="display: flex; justify-content: space-between; align-items: center;">
            <div>
                <p><strong>nombre cuenta:</strong> .................X123</p>
                <p><strong>tipo de cuenta:</strong> Cuenta de ahorro</p>
            </div>
            <button class="volver-btn" onclick="location.href='cuentas.jsp'">VOLVER</button>
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

