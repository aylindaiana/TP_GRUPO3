<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidad.Usuario" %>
<%
    Usuario usuario = (Usuario) request.getAttribute("usuarioModificar");
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

    <form action="${pageContext.request.contextPath}/GuardarModificacionUsuarioServlet" method="post">
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
            </div>
            <div class="col-md-4">
                <label>CUIL</label>
                <input type="text" name="cuil" class="form-control" value="<%= usuario.getCuil() %>" required>
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
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <label>Dirección</label>
                <input type="text" name="direccion" class="form-control" value="<%= usuario.getDireccion() %>">
            </div>
            <div class="col-md-3">
                <label>Localidad</label>
                <input type="text" name="localidad" class="form-control" value="<%= usuario.getLocalidad() %>">
            </div>
            <div class="col-md-3">
                <label>Provincia</label>
                <input type="text" name="provincia" class="form-control" value="<%= usuario.getProvincia() %>">
            </div>
        </div>

        <div class="text-center mt-4">
            <a href="${pageContext.request.contextPath}/ListarUsuariosServlet" class="btn btn-secondary">Cancelar</a>
            <button type="submit" class="btn btn-success">Guardar Cambios</button>
        </div>
    </form>
</div>

</body>
</html>
