<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Admin</title>
<link 
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" 
        rel="stylesheet" 
        integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" 
        crossorigin="anonymous">
    <link rel="stylesheet" 
        href="${pageContext.request.contextPath}/resources/css/homeAdmin.css">
</head>
<body>
		<header>
		<div>
			<strong>ADMINISTRADOR</strong>
		</div>
		<nav>
			<div>
				<a href="${pageContext.request.contextPath}/admin/homeAdmin.jsp"><u>Home</u></a>
				<a href="${pageContext.request.contextPath}/admin/altaCliente.jsp">Alta cliente</a>
				<a href="${pageContext.request.contextPath}/admin/clientes.jsp">Lista Clientes</a>
				<a href="${pageContext.request.contextPath}/admin/cuentasAdmin.jsp">Cuentas</a>
				<a href="${pageContext.request.contextPath}/admin/prestamosAdmin.jsp">Préstamos</a>
				<a href="${pageContext.request.contextPath}/admin/reportes.jsp">Reportes</a>
				<span style="margin-left: 20px;">Usuario</span>
				<button class="logout-btn">LOG OUT</button>
			</div>
		</nav>
	</header>
	
	<div class="container" style="margin-top: 30px;">
		<div class="row">
            <div class="col-12">
                <h4><strong>Buscar clientes</strong></h4>
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
                            <tr>
                                <td>Braian</td>
                                <td>Pirelli</td>
                                <td>12.345.678</td>
                                <td>0170123456789012345678</td>
                                <td>2</td>
                                <td><span class="badge bg-success">Activo</span></td>
                                <td><a href="${pageContext.request.contextPath}/admin/detalleCliente.jsp?id=1" class="btn btn-primary btn-sm">VER</a></td>
                            </tr>
                            <tr>
                                <td>Alex</td>
                                <td>Carvajal</td>
                                <td>23.456.789</td>
                                <td>0170987654321098765432</td>
                                <td>1</td>
                                <td><span class="badge bg-success">Activo</span></td>
                                <td><a href="${pageContext.request.contextPath}/admin/detalleCliente.jsp?id=2" class="btn btn-primary btn-sm">VER</a></td>
                            </tr>
                            <tr>
                                <td>Alex</td>
                                <td>Nass</td>
                                <td>34.567.890</td>
                                <td>0170555666777888999000</td>
                                <td>3</td>
                                <td><span class="badge bg-warning">Pendiente</span></td>
                                <td><a href="${pageContext.request.contextPath}/admin/detalleCliente.jsp?id=3" class="btn btn-primary btn-sm">VER</a></td>
                            </tr>
                            <tr>
                                <td>Antonio</td>
                                <td>Melino</td>
                                <td>45.678.901</td>
                                <td>0170111222333444555666</td>
                                <td>1</td>
                                <td><span class="badge bg-danger">Inactivo</span></td>
                                <td><a href="${pageContext.request.contextPath}/admin/detalleCliente.jsp?id=4" class="btn btn-primary btn-sm">VER</a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
     </div>
</body>
</html>