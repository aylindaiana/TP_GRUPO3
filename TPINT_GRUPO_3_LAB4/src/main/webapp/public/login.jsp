<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css">
</head>
<body>
    <div class="login-container">
        <h2 class="login-title">Bienvenido</h2>
        <form action="${pageContext.request.contextPath}/ServletLogin" method="post">
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
</body>
</html>