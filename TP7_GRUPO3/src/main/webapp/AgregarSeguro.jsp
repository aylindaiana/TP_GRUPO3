<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Dominio.TipoSeguro" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar seguro</title> 
</head>
<body>
    <h2>Agregar seguros</h2>

    <% if (request.getAttribute("filasInsertadas") != null) { %>
        <p style="color: green;">✅ Seguro agregado correctamente.</p>
    <% } %>

    <form action="servletSeguro" method="post">
        <table>
            <tr>
                <td>Id Seguro</td>
                <td><input type="text" value="<%= request.getAttribute("proximoId") %>" readonly /></td>
            </tr>
            <tr>
                <td>Descripción</td>
                <td><input type="text" name="descripcion" /></td>
            </tr>
            <tr>
                <td>Tipo de seguro</td>
                <td>
                    <select name="tipoSeguro">
                        <%
                            ArrayList<TipoSeguro> lista = (ArrayList<TipoSeguro>) request.getAttribute("listaTipos");
                            for (TipoSeguro tipo : lista) {
                        %>
                            <option value="<%= tipo.getIdTipo() %>"><%= tipo.getDescripcion() %></option>
                        <%
                            }
                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Costo contratación</td>
                <td><input type="number" name="costoContratacion" /></td>
            </tr>
            <tr>
                <td>Costo máximo asegurado</td>
                <td><input type="number" name="costoMaximo" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Aceptar" />
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
