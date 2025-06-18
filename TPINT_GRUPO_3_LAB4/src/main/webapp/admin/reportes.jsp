<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reportes</title>
<link 
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" 
        crossorigin="anonymous">
    <link rel="stylesheet" 
        href="${pageContext.request.contextPath}/resources/css/reportesAdmin.css">
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
				<a href="${pageContext.request.contextPath}/admin/prestamosAdmin.jsp">Préstamos</a>
				<a href="${pageContext.request.contextPath}/admin/reportes.jsp"><u>Reportes</u></a>
				<span style="margin-left: 20px;">Usuario</span>
				<button class="logout-btn">LOG OUT</button>
			</div>
		</nav>
	</header>

    <div class="container mt-4">
        <h1 class="page-title">Reportes del Sistema</h1>

        <div class="summary-grid">
            <div class="info-card">
                <div class="info-card-header">
                    <h6>📊 Reporte Ingresos / Egresos</h6>
                </div>
                <div class="metric-row">
                    <span class="metric-label">✓ Ingresos:</span>
                    <span class="metric-value metric-positive">$50,000</span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">✗ Egresos:</span>
                    <span class="metric-value metric-negative">$28,000</span>
                </div>
                <hr>
                <div class="metric-row">
                    <span class="metric-label">📊 Diferencia:</span>
                    <span class="metric-value metric-primary">$22,000</span>
                </div>
            </div>

            <div class="info-card">
                <div class="info-card-header">
                    <h6>💳 Préstamos</h6>
                </div>
                <div class="metric-row">
                    <span class="metric-label">✓ Aprobados:</span>
                    <span class="metric-value metric-positive">12 ($120,000)</span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">✗ Rechazados:</span>
                    <span class="metric-value metric-negative">4 ($40,000)</span>
                </div>
            </div>

            <div class="info-card">
                <div class="info-card-header">
                    <h6>📅 Cuotas pagadas</h6>
                </div>
                <div class="metric-row">
                    <span class="metric-label">Total cuotas:</span>
                    <span class="metric-value">25</span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">Total recaudado:</span>
                    <span class="metric-value metric-positive">$12,500</span>
                </div>
            </div>

            <div class="info-card">
                <div class="info-card-header">
                    <h6>🏦 Cuentas activas</h6>
                </div>
                <div class="metric-row">
                    <span class="metric-label">📦 Caja de ahorro:</span>
                    <span class="metric-value">28</span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">🏛️ Cuenta corriente:</span>
                    <span class="metric-value">16</span>
                </div>
                <hr>
                <div class="metric-row">
                    <span class="metric-label">📋 Total cuentas activas:</span>
                    <span class="metric-value metric-primary">44</span>
                </div>
            </div>

            <div class="info-card">
                <div class="info-card-header">
                    <h6>👥 Nuevos clientes</h6>
                </div>
                <div class="metric-row">
                    <span class="metric-label">Total:</span>
                    <span class="metric-value">12</span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">🏙️ Provincia con más altas:</span>
                    <span class="metric-value">Buenos Aires (4)</span>
                </div>
            </div>

            <div class="info-card">
                <div class="info-card-header">
                    <h6>↔️ Movimientos por tipo</h6>
                </div>
                <div class="metric-row">
                    <span class="metric-label">➡️ Transferencias:</span>
                    <span class="metric-value">45</span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">⬆️ Altas de cuenta:</span>
                    <span class="metric-value">18</span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">💰 Pagos de préstamo:</span>
                    <span class="metric-value">25</span>
                </div>
                <div class="metric-row">
                    <span class="metric-label">📈 Altas de préstamos:</span>
                    <span class="metric-value">12</span>
                </div>
            </div>
        </div>
	</div>
</body>
</html>