<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Ver cuentas</title>
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

