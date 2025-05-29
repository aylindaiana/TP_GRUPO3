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

	
	<a href="Inicio.jsp">Inicio</a>
	<a href="#">Agregar seguro</a>
	<a href="servletSeguro?accion=listado">Listar seguros</a>
        

    <h2>Agregar seguros</h2>

    <% if (request.getAttribute("filasInsertadas") != null) { %>
        <p style="color: green;">✅ Seguro agregado correctamente.</p>
    <% } %>
	
	<%
		if(request.getAttribute("camposVacios") != null && (boolean)request.getAttribute("camposVacios") == true)
		{
	%>
			<script>
				alert("Todos los campos deben ser completados.");
			</script>
	<%
		}
	%>
	
	
	<%
		if(request.getAttribute("seguroRepetido") != null && (boolean)request.getAttribute("seguroRepetido") == true)
		{
	%>
			<script>
				alert("el registro que intenta agregar tiene los datos de un seguro existente, ingrese datos validos.");
			</script>
	<%
		}
	%>
	

    <form action="servletSeguro" method="post">
        <table>
            <tr>
                <td>Id Seguro</td>
                <td><input type="text" value="<%= request.getAttribute("proximoId") %>" readonly /></td>
            </tr>
            <tr>
                <td>Descripción</td>
                <td><input type="text" name="descripcion" value="<%= request.getParameter("descripcion") != null ? request.getParameter("descripcion") : "" %>" /></td> 
                <td><%
	       			if (request.getAttribute("camposVacios") != null && (boolean)request.getAttribute("camposVacios") == true) 
	       			{
	         	%>	
	         			<label for="descripcion" style="color: red;">* Campo obligatorio</label>
	       		<%	
	       			}
	    		%>
	    		<%
	       			if (request.getAttribute("seguroRepetido") != null && (boolean)request.getAttribute("seguroRepetido") == true) 
	       			{
	         	%>	
	         			<label for="descripcion" style="color: red;">* Modificar Campo</label>
	       		<%	
	       			}
	    		%></td> 
            </tr>
            <tr>
                <td>Tipo de seguro</td>
                <td>
                    <select name="tipoSeguro">
                        <%
                            ArrayList<TipoSeguro> lista = (ArrayList<TipoSeguro>) request.getAttribute("listaTipos");
                            if(lista!=null){
                        	for (TipoSeguro tipo : lista) {
                        %>
                            <option value="<%= tipo.getIdTipo() %>"><%= tipo.getDescripcion() %></option>
                        <%
                            }
                            }
                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Costo contratación</td>
                <td><input type="number" name="costoContratacion" value="<%= request.getParameter("costoContratacion") != null ? request.getParameter("costoContratacion") : "" %>" /></td> 
                <td><%
	       			if (request.getAttribute("camposVacios") != null && (boolean)request.getAttribute("camposVacios") == true) 
	       			{
	         	%>	
	         			<label for="costoContratacion" style="color: red;">* Campo obligatorio</label>
	       		<%	
	       			}
	    		%>
	    		<%
	       			if (request.getAttribute("seguroRepetido") != null && (boolean)request.getAttribute("seguroRepetido") == true) 
	       			{
	         	%>	
	         			<label for="descripcion" style="color: red;">* Modificar Campo</label>
	       		<%	
	       			}
	    		%></td> 
            </tr>
            <tr>
                <td>Costo máximo asegurado</td>
                <td><input type="number" name="costoMaximo" value="<%= request.getParameter("costoMaximo") != null ? request.getParameter("costoMaximo") : "" %>" /></td> 
                <td><%
	       			if (request.getAttribute("camposVacios") != null && (boolean)request.getAttribute("camposVacios") == true) 
	       			{
	         	%>	
	         			<label for="costoMaximo" style="color: red;">* Campo obligatorio</label>
	       		<%	
	       			}
	    		%>
	    		<%
	       			if (request.getAttribute("seguroRepetido") != null && (boolean)request.getAttribute("seguroRepetido") == true) 
	       			{
	         	%>	
	         			<label for="descripcion" style="color: red;">* Modificar Campo</label>
	       		<%	
	       			}
	    		%></td> 
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
