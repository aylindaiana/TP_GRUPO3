<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entidad.Usuario" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Clientes</title>
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT"
		crossorigin="anonymous">
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO"
		crossorigin="anonymous"></script>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/listaClientes.css">
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
	          <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/admin/homeAdmin.jsp">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/altaCliente.jsp">Alta Clientes</a>
	        </li>
	        <li class="nav-item">
  <a class="nav-link" href="${pageContext.request.contextPath}/ListarUsuariosServlet">Clientes</a>
</li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/reportes.jsp">Reportes</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/cuentasAdmin.jsp">Cuentas</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="${pageContext.request.contextPath}/admin/prestamosAdmin.jsp">Prestamo</a>
	        </li>
	      </ul>
	      <span class="navbar-text d-flex flex-row">
	        <a class="nav-link align-self-center justify-content-center" href="${pageContext.request.contextPath}/admin/verUsuarioAdmin.jsp">Nombre Usuario</a>
	        <button class="btn btn-danger">Log Out</button>
	      </span>
	    </div>
	  </div>
	</nav>

  <div class="container" style="margin-top: 30px;">
        <div class="row">
            <div class="col-12">
                <h4><strong>Lista Clientes</strong></h4>
            </div>
        </div>
        
        <div class="row mt-3">
            <div class="col-12">
                <div class="search-container">
                    <div class="row align-items-end">
                        <div class="col-md-4">
                            <label for="busqueda" class="form-label"><strong>Búsqueda:</strong></label>
                            <input type="text" class="form-control" id="busqueda" placeholder="Buscar cliente...">
                        </div>
                        <div class="col-md-4">
                            <label for="cbu" class="form-label"><strong>CBU:</strong></label>
                            <input type="text" class="form-control" id="cbu" placeholder="Buscar por CBU...">
                        </div>
                        <div class="col-md-4">
                            <button type="button" class="btn btn-secondary">Buscar</button>
                            <button type="button" class="btn btn-outline-secondary ms-2">Limpiar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row mt-4">
            <div class="col-12">
                <div class="table-container">
                    <table class="table table-hover">
                        <thead class="table-secondary">
                            <tr>
                                <th>Nombre</th>
                                <th>Apellido</th>
                                <th>DNI</th>
                                <th>CBU</th>
                                <th>Nro de cuentas</th>
                                <th>Estado</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                            @SuppressWarnings("unchecked")
                                List<Usuario> obtenerTodos = (List<Usuario>) request.getAttribute("obtenerTodos");
                                if (obtenerTodos != null) {
                                    for (Usuario usuario : obtenerTodos) {
                            %>
                            <tr>
                                <td><%= usuario.getNombre() %></td>
                                <td><%= usuario.getApellido() %></td>
                                <td><%= usuario.getDni() %></td>
                                <td>
                                    <%
                                        // Acá se debería obtener el CBU y cantidad de cuentas del usuario desde la base
                                        // Por ahora hay valores de ejemplo:
                                    %>
                                    0170123456789012345678
                                </td>
                                <td>
                                    <%
                                        // Ejemplo: mostrar cantidad de cuentas
                                    %>
                                    2
                                </td>
                                <td>
                                    <%
                                        // Mostrar estado
                                    %>
                                    <span class="badge bg-success">Activo</span>
                                </td>
                                <td>
                                    <form method="get" action="${pageContext.request.contextPath}/DetalleUsuarioServlet">
									    <input type="hidden" name="id" value="<%= usuario.getId() %>" />
									    <button type="submit" class="btn btn-primary btn-sm">Ver</button>
									</form>



                                </td>
                            </tr>
                            <%
                                    }
                                } else {
                            %>
                            <tr>
                                <td colspan="7" class="text-center">No hay clientes para mostrar.</td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
