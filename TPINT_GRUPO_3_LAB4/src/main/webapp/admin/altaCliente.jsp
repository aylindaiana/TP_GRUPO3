<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alta cliente</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/altaCliente.css">
</head>
<body>

<header>
    <div><strong>ADMINISTRADOR</strong></div>
    <nav>
        <a href="${pageContext.request.contextPath}/admin/homeAdmin.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/admin/altaCliente.jsp"><u>Alta cliente</u></a>
        <a href="${pageContext.request.contextPath}/admin/clientes.jsp">Lista Clientes</a>
        <a href="${pageContext.request.contextPath}/admin/cuentasAdmin.jsp">Cuentas</a>
        <a href="${pageContext.request.contextPath}/admin/prestamosAdmin.jsp">Préstamos</a>
        <a href="${pageContext.request.contextPath}/admin/reportes.jsp">Reportes</a>
        <span style="margin-left: 20px;">Usuario</span>
        <button class="logout-btn">LOG OUT</button>
    </nav>
</header>

<div class="main-container">
    <form action="procesarAltaCliente" method="post">
        <div class="form-columns">
            <!-- Columna Izquierda - Datos Personales -->
            <div class="form-column">
                <div class="form-section">
                    <h2>Datos personales</h2>
                    <div class="form-group">
                        <label for="dni">DNI:</label>
                        <input type="text" id="dni" name="dni" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="cuil">CUIL:</label>
                        <input type="text" id="cuil" name="cuil" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="nombre">Nombre:</label>
                        <input type="text" id="nombre" name="nombre" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="apellido">Apellido:</label>
                        <input type="text" id="apellido" name="apellido" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="sexo">Sexo:</label>
                        <select id="sexo" name="sexo" required>
                            <option value="">Seleccionar...</option>
                            <option value="M">Masculino</option>
                            <option value="F">Femenino</option>
                            <option value="O">Otro</option>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label for="nacionalidad">Nacionalidad:</label>
                        <input type="text" id="nacionalidad" name="nacionalidad" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="fechaNacimiento">Fecha de nacimiento:</label>
                        <input type="date" id="fechaNacimiento" name="fechaNacimiento" required>
                    </div>
                </div>
            </div>

            <!-- Columna Derecha - Datos de Contacto -->
            <div class="form-column">
                <div class="form-section">
                    <h2>Datos de contacto</h2>
                    <div class="form-group">
                        <label for="direccion">Dirección:</label>
                        <input type="text" id="direccion" name="direccion" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="localidad">Localidad:</label>
                        <input type="text" id="localidad" name="localidad" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="provincia">Provincia:</label>
                        <input type="text" id="provincia" name="provincia" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="email">Correo electrónico:</label>
                        <input type="email" id="email" name="email" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="telefono">Teléfono:</label>
                        <input type="tel" id="telefono" name="telefono" required>
                    </div>
                </div>
            </div>
        </div>

        <!-- Sección Inferior - Credenciales -->
        <div class="credentials-section">
            <h2>Credenciales de acceso</h2>
            <div class="form-group">
                <label for="usuario">Usuario:</label>
                <input type="text" id="usuario" name="usuario" required>
            </div>
            
            <div class="form-group">
                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>
            </div>
            
            <div class="form-group">
                <label for="confirmPassword">Confirmar contraseña:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
            </div>
        </div>

        <!-- Botones de acción -->
        <div class="action-buttons">
            <a href="${pageContext.request.contextPath}/admin/homeAdmin.jsp" class="cancel-button">Cancelar</a>
            <button type="submit" class="submit-button">Crear</button>
        </div>
    </form>
</div>

</body>
</html>