<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entidad.Usuario" %>
<%@ page import="java.util.Map" %>
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
        <link rel="stylesheet" 
    href="${pageContext.request.contextPath}/resources/css/nav.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
      <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/HomeAdminServlet">Banco G3</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link"  href="${pageContext.request.contextPath}/HomeAdminServlet">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/FormularioClienteServlet">Alta Clientes</a></li>
					<li class="nav-item"><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/ListarUsuariosServlet">Clientes</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/CuentaAdminServlet">Cuentas</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/PrestamosAdminServlet">Préstamos</a></li>
					<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/ReportesServlet">Reportes</a></li>
          </ul>         
          <span class="navbar-text d-flex flex-row align-items-center gap-2">
              <a class="nav-link align-self-center justify-content-center" href="${pageContext.request.contextPath}/VerDatosAdminServlet"><%=(session.getAttribute("idNombre") != null) ? session.getAttribute("idNombre").toString() : "null" %></a>
              <a href="${pageContext.request.contextPath}/ServletLogin?accion=cerrar" class="logout-btn">Salir</a>
          </span>
        </div>
      </div>
    </nav>
    
    <%
    String status = request.getParameter("status");
    if ("modificado".equals(status)) {
%>
    <div class="alert alert-success alert-dismissible fade show text-center" role="alert" style="max-width: 600px; margin: 20px auto;">
        ✅ Se modificó con éxito.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
<%
    } else if ("error".equals(status)) {
%>
    <div class="alert alert-danger alert-dismissible fade show text-center" role="alert" style="max-width: 600px; margin: 20px auto;">
        ❌ Ocurrió un error al modificar al cliente.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
<%
    }
%>
    

  <div class="container" style="margin-top: 30px;">
        <div class="row">
            <div class="col-12">
                <h4><strong>Buscar Clientes:</strong></h4>
            </div>
        </div>
        
        <div class="row mt-3">
            <div class="col-12">
                <div class="search-container">
                    <form method="get" action="ListarUsuariosServlet">
					  <div class="row align-items-end">
					    <div class="col-md-4">
					        <label for="busqueda" class="form-label"><strong>Búsqueda por nombre, apellido o DNI:</strong></label>
					        <input type="text" class="form-control" id="busqueda" name="busqueda" placeholder="Buscar cliente..." value="${busqueda}">
					    </div>
					    <div class="col-md-4">
					        <button type="submit" class="btn btn-secondary">Buscar</button>
					        <a href="ListarUsuariosServlet" class="btn btn-outline-secondary ms-2">Limpiar</a>
					    </div>
					  </div>
					</form>

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
								    Map<Integer, Integer> cuentasPorUsuario = (Map<Integer, Integer>) request.getAttribute("cuentasPorUsuario");
								    int cantidadCuentas = cuentasPorUsuario.get(usuario.getId());
								  %>
								  <%= cantidadCuentas %>
								</td>
                               <td>
                                    <%
                                        if(usuario.isEstado()) {
                                    %>
                                        <span class="badge bg-success">Activo</span>
                                    <%
                                        } else {
                                    %>
                                        <span class="badge bg-danger">Inactivo</span>
                                    <%
                                        }
                                    %>
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
                    
                    <% String busqueda = request.getAttribute("busqueda") != null ? request.getAttribute("busqueda").toString() : ""; %>
                    
                    
                    <%-- PAGINACION --%>
                    <%
                        int paginaActual = (request.getAttribute("paginaActual") != null) ? (Integer)request.getAttribute("paginaActual") : 1;
                        int totalPaginas = (request.getAttribute("totalPaginas") != null) ? (Integer)request.getAttribute("totalPaginas") : 1;
                    %>
                    <nav aria-label="Paginación">
					  <ul class="pagination justify-content-center">
					    <li class="page-item <%= (paginaActual == 1) ? "disabled" : "" %>">
					      <a class="page-link" href="ListarUsuariosServlet?page=<%= paginaActual - 1 %>&busqueda=<%= busqueda %>" tabindex="-1">Anterior</a>
					    </li>
					    <% for (int i = 1; i <= totalPaginas; i++) { %>
					      <li class="page-item <%= (i == paginaActual) ? "active" : "" %>">
					        <a class="page-link" href="ListarUsuariosServlet?page=<%= i %>&busqueda=<%= busqueda %>"><%= i %></a>
					      </li>
					    <% } %>
					    <li class="page-item <%= (paginaActual == totalPaginas) ? "disabled" : "" %>">
					      <a class="page-link" href="ListarUsuariosServlet?page=<%= paginaActual + 1 %>&busqueda=<%= busqueda %>">Siguiente</a>
					    </li>
					  </ul>
					</nav>

                    
                </div>
            </div>
        </div>
    </div>
	<footer>
	        <p>© 2025 Grupo 3 - Laboratorio 4</p>
	</footer>
</body>
</html>
