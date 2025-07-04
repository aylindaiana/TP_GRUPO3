<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidad.Usuario" %>
<%@ page import="entidad.Provincia" %>
<%@ page import="entidad.Localidad" %>
<%@ page import="java.util.List" %>
<%
Usuario usuario = (Usuario) request.getAttribute("usuarioModificar");
List<Provincia> provincias = (List<Provincia>) request.getAttribute("provincias");
List<Localidad> localidades = (List<Localidad>) request.getAttribute("localidades");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar Cliente</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2>Modificar Cliente</h2>

    <form id="formModificarCliente" action="${pageContext.request.contextPath}/GuardarModificacionUsuarioServlet" method="post">

        <input type="hidden" name="id" value="<%= usuario.getId() %>">

        <div class="row mb-3">
            <div class="col-md-6">
                <label>Nombre</label>
                <input type="text" name="nombre" class="form-control" value="<%= usuario.getNombre() %>" required>
            </div>
            <div class="col-md-6">
                <label>Apellido</label>
                <input type="text" name="apellido" class="form-control" value="<%= usuario.getApellido() %>" required>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-4">
                <label>DNI</label>
                <input type="text" name="dni" class="form-control" value="<%= usuario.getDni() %>" required>
                <% if (request.getAttribute("errorDni") != null) { %>
				    <div class="text-danger"><%= request.getAttribute("errorDni") %></div>
				<% } %>
                
            </div>
            <div class="col-md-4">
                <label>CUIL</label>
                <input type="text" name="cuil" class="form-control" value="<%= usuario.getCuil() %>" required>
                <% if (request.getAttribute("errorCuil") != null) { %>
				    <div class="text-danger"><%= request.getAttribute("errorCuil") %></div>
				<% } %>
                
            </div>
            <div class="col-md-4">
                <label>Sexo</label>
                <input type="text" name="sexo" class="form-control" value="<%= usuario.getSexo() %>">
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <label>Nacionalidad</label>
                <input type="text" name="nacionalidad" class="form-control" value="<%= usuario.getNacionalidad() %>">
            </div>
            <div class="col-md-6">
                <label>Fecha de Nacimiento</label>
                <input type="date" name="fechaNacimiento" class="form-control" value="<%= usuario.getFechaDeNacimiento() %>">
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <label>Email</label>
                <input type="email" name="correo" class="form-control" value="<%= usuario.getCorreoElectronico() %>">
            </div>
            <div class="col-md-6">
                <label>Teléfono</label>
                <input type="text" name="telefono" class="form-control" value="<%= usuario.getTelefono() %>">
                <% if (request.getAttribute("errorTelefono") != null) { %>
				    <div class="text-danger"><%= request.getAttribute("errorTelefono") %></div>
				<% } %>
                
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <label>Dirección</label>
                <input type="text" name="direccion" class="form-control" value="<%= usuario.getDireccion() %>">
            </div>

            <div class="col-md-3">
                <label>Localidad</label>
                <select name="localidad" class="form-select">
                    <option value="">Seleccionar localidad...</option>
                    <% for (Localidad loc : localidades) { %>
                        <option value="<%= loc.getIdLocalidad() %>"
                        <%= (loc.getIdLocalidad() == usuario.getIdLocalidad()) ? "selected" : "" %>>
                        <%= loc.getNombreLocalidad() %></option>
                    <% } %>
                </select>
            </div>

            <div class="col-md-3">
                <label>Provincia</label>
                <select name="provincia" class="form-select">
                    <option value="">Seleccionar provincia...</option>
                    <% for (Provincia prov : provincias) { %>
                        <option value="<%= prov.getIdProvincia() %>"
                        <%= (prov.getIdProvincia() == usuario.getIdProvincia()) ? "selected" : "" %>>
                        <%= prov.getNombreProvincia() %></option>
                    <% } %>
                </select>
            </div>
        </div>

        <div class="text-center mt-4">
            <a href="${pageContext.request.contextPath}/ListarUsuariosServlet" class="btn btn-secondary">Cancelar</a>
            <button type="submit" class="btn btn-success">Guardar Cambios</button>
        </div>
    </form>
</div>

<script>
document.getElementById("formModificarCliente").addEventListener("submit", function(event) {
    const confirmado = confirm("¿Estás seguro de que deseas guardar los cambios?");
    if (!confirmado) {
        event.preventDefault();
    }
});
</script>


</body>
</html>
