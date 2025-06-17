<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Banco Grupo 3</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css">
</head>
<body>
    <div class="page-container">
        <header class="main-header">
            <div class="logo-container">
                <h1>Banco Grupo 3</h1>
            </div>
            <div class="login-container">
                <a href="${pageContext.request.contextPath}/public/login.jsp" class="login-button">Iniciar Sesión</a>
            </div>
        </header>

        <main class="main-content">
            <section class="hero-section">
                <div class="hero-text">
                    <h2>Bienvenido a su banco de confianza</h2>
                    <p>Soluciones financieras para todos sus proyectos</p>
                </div>
            </section>

            <section class="features-section">
                <div class="feature-card">
                    <div class="feature-icon">💳</div>
                    <h3>Cuentas Bancarias</h3>
                    <p>Variedad de opciones adaptadas a sus necesidades</p>
                </div>
                <div class="feature-card">
                    <div class="feature-icon">🏠</div>
                    <h3>Préstamos</h3>
                    <p>Realice sus sueños con nuestras opciones de crédito</p>
                </div>
                <div class="feature-card">
                    <div class="feature-icon">📈</div>
                    <h3>Inversiones</h3>
                    <p>Haga crecer su dinero con nuestros productos</p>
                </div>
            </section>
        </main>

        <footer class="main-footer">
            <div class="footer-content">
                <p>© 2023 Banco Grupo 3. Todos los derechos reservados.</p>
                <div class="footer-links">
                    <a href="#">Términos y condiciones</a>
                    <a href="#">Política de privacidad</a>
                    <a href="#">Contacto</a>
                </div>
            </div>
        </footer>
    </div>
</body>
</html>