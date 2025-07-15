<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<!-- Bootstrap 5 CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
</head>
<body>


    <div class="login-container">
    
    <%
    String status = request.getParameter("status");
    if (status != null) status = status.trim();

    if ("errorLogin".equalsIgnoreCase(status)) {
%>
    <div class="alert alert-danger alert-dismissible fade show text-center mx-auto mt-4" role="alert" style="max-width: 500px;">
        ❌ Usuario o contraseña incorrectos.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
<%
    } else if ("inactivo".equalsIgnoreCase(status)) {
%>
    <div class="alert alert-warning alert-dismissible fade show text-center mx-auto mt-4" role="alert" style="max-width: 500px;">
        ⚠️ Tu usuario está inactivo. Por favor, comunícate con el administrador.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
<%
    }
%>

        <h2 class="login-title">Bienvenido</h2>
        <form action="${pageContext.request.contextPath}/ServletLogin?accion=iniciar" method="post">
            <div class="input-group">
                <label for="username">Usuario</label>
                <input type="text" id="username" name="username" required value="<%= request.getParameter("username") != null ? request.getParameter("username") : "" %>">
            </div> 
            <div class="input-group"> 
                <label for="password">Contraseña</label>
                <input type="password" id="password" name="password" required value="<%= request.getParameter("password") != null ? request.getParameter("password") : "" %>">
            </div>
            <input type="submit" class="login-button" value="Entrar" />
        </form>
    </div>
    
    <!-- Bootstrap JS para funcionalidad del botón close -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    
</body>	
</html>