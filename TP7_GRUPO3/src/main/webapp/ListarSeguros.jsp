<%@page import="Dominio.TipoSeguro"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Dominio.Seguro" %>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>Seguros</title>
<style>
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }
    th, td {
        border: 1px solid black;
        padding: 8px;
        vertical-align: top;
        text-align: left;
    }
</style>
</head>
<body>


<a href="Inicio.jsp">Inicio</a>
<a href="servletSeguro?accion=nuevo">Agregar seguro</a>
<a href="#	">Listar seguros</a>
        

<h2>Listado de seguros:</h2>
<br><br>
<%

	ArrayList<Seguro> lista = (ArrayList<Seguro>) request.getAttribute("listaSeguros");
	ArrayList<TipoSeguro> listaTipos = (ArrayList<TipoSeguro>) request.getAttribute("listaTipos");
%>

	<form method="get" action="servletSeguro">
		Busqueda por tipo de Seguros: 
		<select name="idTipoSeguro"> 
			<% 
			for(TipoSeguro tipo : listaTipos){
				
			%>
				<option value=<%=tipo.getIdTipo()%> ><%=tipo.getDescripcion() %></option>
			<%
			}
			%>
		</select> <br> 
		<input type="submit" name="btnFiltrar" value="Filtra"/>
	</form>

<%
    if (lista != null && !lista.isEmpty()) {
%>
    <table>
        <thead>
            <tr>
                <th>ID seguro</th>
                <th>Descripción seguro</th>
                <th>Descripción tipo seguro</th>
                <th>Costo contratación</th>
                <th>Costo máximo asegurado</th>
            </tr>
        </thead>
        <tbody>
        <% for (Seguro s : lista) { %>
            <tr>
                <td><%= s.getIdSeguro()%></td>
                <td><%= s.getDescripcion()%></td>
                <td><%= s.getTipoDescripcion()%></td>
                <td><%= s.getCostoContratacion() %></td>
                <td><%= s.getCostoAsegurado() %></td>
            </tr>
        <% } %>
        </tbody>
    </table>
<%
    }
%>
<br><br>
</body>
</html>
