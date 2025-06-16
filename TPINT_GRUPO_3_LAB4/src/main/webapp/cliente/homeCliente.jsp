<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Cliente</title>
    <link 
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" 
        crossorigin="anonymous">
    <link rel="stylesheet" 
        href="${pageContext.request.contextPath}/resources/css/homeCliente.css">
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

    <div class="container">
        <div class="row">
            <div class="col-12">
                <h4><strong>MI INFORMACIÓN PERSONAL</strong></h4>
            </div>
        </div>

       <div class="row mt-4">
            <div class="col-md-4">
                <div class="info-container">
                    <h5 class="section-title">Datos Personales</h5>
                    
                    <div class="info-field">
                        <label class="field-label">Nombre:</label>
                        <span class="field-value">Juan</span>
                    </div>
                    
                    <div class="info-field mt-3">
                        <label class="field-label">Apellido:</label>
                        <span class="field-value">Pérez</span>
                    </div>
                    
                    <div class="info-field mt-3">
                        <label class="field-label">DNI:</label>
                        <span class="field-value">12.345.678</span>
                    </div>
                    
                    <div class="info-field mt-3">
                        <label class="field-label">CUIL:</label>
                        <span class="field-value">20-12345678-9</span>
                    </div>
                    
                    <div class="info-field mt-3">
                        <label class="field-label">Sexo:</label>
                        <span class="field-value">Masculino</span>
                    </div>
                    
                    <div class="info-field mt-3">
                        <label class="field-label">Nacionalidad:</label>
                        <span class="field-value">Argentina</span>
                    </div>
                    
                    <div class="info-field mt-3">
                        <label class="field-label">Fecha de Nacimiento:</label>
                        <span class="field-value">15/03/1985</span>
                    </div>
                </div>
            </div>


            <div class="col-md-4">
                <div class="info-container">
                    <h5 class="section-title">Información de Contacto</h5>
                    
                    <div class="info-field">
                        <label class="field-label">Teléfono:</label>
                        <span class="field-value">11-4567-8901</span>
                    </div>
                    
                    <div class="info-field mt-3">
                        <label class="field-label">Email:</label>
                        <span class="field-value">juan.perez@email.com</span>
                    </div>
                    
                    <div class="info-field mt-3">
                        <label class="field-label">Dirección:</label>
                        <span class="field-value">Av. Corrientes 1234</span>
                    </div>
                    
                    <div class="info-field mt-3">
                        <label class="field-label">Localidad:</label>
                        <span class="field-value">Ciudad Autónoma de Buenos Aires</span>
                    </div>
                    
                    <div class="info-field mt-3">
                        <label class="field-label">Provincia:</label>
                        <span class="field-value">CABA</span>
                    </div>
                </div>
            </div>


            <div class="col-md-4">
                <div class="info-container">
                    <h5 class="section-title">Información de Acceso</h5>
                    
                    <div class="info-field">
                        <label class="field-label">Usuario:</label>
                        <span class="field-value">jperez123</span>
                    </div>
                    
                    <div class="info-field mt-3">
                        <label class="field-label">Estado de Cuenta:</label>
                        <span class="field-value status-active">Activa</span>
                    </div>
                </div>
            </div>
        </div>

        <div class="row mt-4">
            <div class="col-12">
                <div class="info-container">
                    <h5 class="section-title">Resumen de Cuentas</h5>
                    
                    <div class="cuenta-resumen">
                        <div class="row align-items-center">
                            <div class="col-md-2">
                                <strong>Cuenta N°:</strong><br>
                                <span>X123</span>
                            </div>
                            <div class="col-md-3">
                                <strong>Tipo:</strong><br>
                                <span>Caja de Ahorro</span>
                            </div>
                            <div class="col-md-3">
                                <strong>Saldo Actual:</strong><br>
                                <span class="saldo-positivo">$250.000,00</span>
                            </div>
                            <div class="col-md-4">
                                <strong>CBU:</strong><br>
                                <span class="cbu-text">0170123456789012345678</span>
                            </div>
                        </div>
                    </div>
                    
                    <div class="cuenta-resumen">
                        <div class="row align-items-center">
                            <div class="col-md-2">
                                <strong>Cuenta N°:</strong><br>
                                <span>X943</span>
                            </div>
                            <div class="col-md-3">
                                <strong>Tipo:</strong><br>
                                <span>Cuenta Corriente</span>
                            </div>
                            <div class="col-md-3">
                                <strong>Saldo Actual:</strong><br>
                                <span class="saldo-positivo">$180.500,50</span>
                            </div>
                            <div class="col-md-4">
                                <strong>CBU:</strong><br>
                                <span class="cbu-text">0170987654321098765432</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</body>
</html>