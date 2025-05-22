<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agregar seguro</title> 
</head>
<body>
    <h2>Agregar seguros</h2>

    <form action="AgregarSeguroServlet" method="post">
        <table>
            <tr>
                <td>Id Seguro </td>
                <td><input type="text" name="idSeguro" value="3" readonly /></td>
            </tr>
            <tr>
                <td>Descripción </td>
                <td><input type="text" name="descripcion" /></td>
            </tr>
            <tr>
                <td>Tipo de seguro </td>
                <td>
                    <select name="tipoSeguro">
                        <option value="1">Seguro de casas</option>
                        <option value="2">Seguro de autos</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Costo contratación </td>
                <td><input type="number" name="costoContratacion" /></td>
            </tr>
            <tr>
                <td>Costo máximo asegurado </td>
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