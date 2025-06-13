<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Cuentas - Cliente</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/cuentas.css">
</head>
<body>

<header>
    <div><strong>CLIENTE</strong></div>
    <nav>
        <a href="${pageContext.request.contextPath}/cliente/homeCliente.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/cliente/transferencias.jsp">Transferir</a>
        <a href="${pageContext.request.contextPath}/cliente/cuentas.jsp"><u>Cuentas</u></a>
        <a href="${pageContext.request.contextPath}/cliente/prestamos.jsp">Préstamo</a>
        <span style="margin-left: 20px;">Usuario</span>
        <button class="logout-btn">LOG OUT</button>
    </nav>
</header>

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

