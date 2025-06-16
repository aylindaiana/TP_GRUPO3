<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalle Cliente</title>
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
        href="${pageContext.request.contextPath}/resources/css/detalleCliente.css">
</head>
<body>
    <header>
        <div>
            <strong>ADMINISTRADOR</strong>
        </div>
        <nav>
            <div>
                <a href="${pageContext.request.contextPath}/admin/homeCliente.jsp">Home</a>
                <a href="${pageContext.request.contextPath}/admin/altCliente.jsp">Alta cliente</a>
                <a href="${pageContext.request.contextPath}/admin/clientes.jsp"><u>Lista Clientes</u></a>
                <a href="${pageContext.request.contextPath}/admin/cuentasAdmin.jsp">Cuentas</a>
                <a href="${pageContext.request.contextPath}/admin/prestamosAdmin.jsp">Préstamos</a>
                <a href="${pageContext.request.contextPath}/admin/reportes.jsp">Reportes</a>
                <span style="margin-left: 20px;">Usuario</span>
                <button class="logout-btn">LOG OUT</button>
            </div>
        </nav>
    </header>

    <div class="container">
        <div class="row">
            <div class="col-12">
                <h4><strong>Detalle del Cliente</strong></h4>
            </div>
        </div>

        <form action="${pageContext.request.contextPath}/admin/detalleCliente" method="post">
            <div class="row mt-4">
                <div class="col-12">
                    <div class="cliente-container">
                        <h5 class="section-title">Información Personal</h5>
                        
                        <input type="hidden" name="clienteId" value="1">
                        
                        <div class="row">
                            <div class="col-md-6">
                                <label for="nombre" class="form-label"><strong>Nombre:</strong></label>
                                <input type="text" class="form-control" id="nombre" name="nombre" value="Juan" required>
                            </div>
                            <div class="col-md-6">
                                <label for="apellido" class="form-label"><strong>Apellido:</strong></label>
                                <input type="text" class="form-control" id="apellido" name="apellido" value="Pérez" required>
                            </div>
                        </div>
                        
                        <div class="row mt-3">
                            <div class="col-md-4">
                                <label for="dni" class="form-label"><strong>DNI:</strong></label>
                                <input type="text" class="form-control" id="dni" name="dni" value="12345678" required>
                            </div>
                            <div class="col-md-4">
                                <label for="cuil" class="form-label"><strong>CUIL:</strong></label>
                                <input type="text" class="form-control" id="cuil" name="cuil" value="20-12345678-9" required>
                            </div>
                            <div class="col-md-4">
                                <label for="sexo" class="form-label"><strong>Sexo:</strong></label>
                                <select class="form-select" id="sexo" name="sexo" required>
                                    <option value="">Seleccionar</option>
                                    <option value="M" selected>Masculino</option>
                                    <option value="F">Femenino</option>
                                    <option value="X">No binario</option>
                                </select>
                            </div>
                        </div>
                        
                        <div class="row mt-3">
                            <div class="col-md-6">
                                <label for="nacionalidad" class="form-label"><strong>Nacionalidad:</strong></label>
                                <input type="text" class="form-control" id="nacionalidad" name="nacionalidad" value="Argentina" required>
                            </div>
                            <div class="col-md-6">
                                <label for="fechaNacimiento" class="form-label"><strong>Fecha de Nacimiento:</strong></label>
                                <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" value="1985-03-15" required>
                            </div>
                        </div>
                        
                        <div class="row mt-3">
                            <div class="col-md-6">
                                <label for="telefono" class="form-label"><strong>Teléfono:</strong></label>
                                <input type="text" class="form-control" id="telefono" name="telefono" value="11-4567-8901">
                            </div>
                            <div class="col-md-6">
                                <label for="email" class="form-label"><strong>Email:</strong></label>
                                <input type="email" class="form-control" id="email" name="email" value="juan.perez@email.com">
                            </div>
                        </div>
                        
                        <div class="row mt-3">
                            <div class="col-12">
                                <label for="direccion" class="form-label"><strong>Dirección:</strong></label>
                                <textarea class="form-control" id="direccion" name="direccion" rows="2">Av. Corrientes 1234</textarea>
                            </div>
                        </div>
                        
                        <div class="row mt-3">
                            <div class="col-md-6">
                                <label for="localidad" class="form-label"><strong>Localidad:</strong></label>
                                <input type="text" class="form-control" id="localidad" name="localidad" value="Ciudad Autónoma de Buenos Aires" required>
                            </div>
                            <div class="col-md-6">
                                <label for="provincia" class="form-label"><strong>Provincia:</strong></label>
                                <input type="text" class="form-control" id="provincia" name="provincia" value="CABA" required>
                            </div>
                        </div>
                        
                        <div class="row mt-3">
                            <div class="col-md-4">
                                <label for="usuario" class="form-label"><strong>Usuario:</strong></label>
                                <input type="text" class="form-control" id="usuario" name="usuario" value="jperez123" required>
                            </div>
                            <div class="col-md-4">
                                <label for="password" class="form-label"><strong>Contraseña:</strong></label>
                                <input type="password" class="form-control" id="password" name="password" value="********" required>
                            </div>
                            <div class="col-md-4">
                                <label for="estado" class="form-label"><strong>Estado:</strong></label>
                                <select class="form-select" id="estado" name="estado" required>
                                    <option value="Activo" selected>Activo</option>
                                    <option value="Inactivo">Inactivo</option>
                                    <option value="Pendiente">Pendiente</option>
                                    <option value="Suspendido">Suspendido</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row mt-4">
                <div class="col-12">
                    <div class="cuentas-container">
                        <h5 class="section-title">Cuentas del Cliente </h5>

                        <div class="cuenta-item" id="cuenta1">
                            <div class="row align-items-center">
                                <div class="col-md-2">
                                    <label class="form-label"><strong>Número:</strong></label>
                                    <input type="text" class="form-control" name="numeroCuenta1" value="X123" readonly>
                                </div>
                                <div class="col-md-2">
                                    <label class="form-label"><strong>Tipo:</strong></label>
                                    <select class="form-select" name="tipoCuenta1">
                                        <option value="Caja de ahorro" selected>Caja de ahorro</option>
                                        <option value="Cuenta corriente">Cuenta corriente</option>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                    <label class="form-label"><strong>Fecha Creación:</strong></label>
                                    <input type="date" class="form-control" name="fechaCreacion1" value="2023-01-15" readonly>
                                </div>
                                <div class="col-md-2">
                                    <label class="form-label"><strong>Saldo:</strong></label>
                                    <input type="text" class="form-control" name="saldo1" value="$250.000,00" readonly>
                                </div>
                                <div class="col-md-3">
                                    <label class="form-label"><strong>CBU:</strong></label>
                                    <input type="text" class="form-control" name="cbu1" value="0170123456789012345678" readonly>
                                </div>
                                <div class="col-md-1 text-center">
                                    <label class="form-label"><strong>Acciones:</strong></label><br>
                                    <button type="button" class="btn btn-danger btn-sm">Eliminar</button>
                                </div>
                            </div>
                        </div>

                        <div class="cuenta-item" id="cuenta2">
                            <div class="row align-items-center">
                                <div class="col-md-2">
                                    <label class="form-label"><strong>Número:</strong></label>
                                    <input type="text" class="form-control" name="numeroCuenta2" value="X943" readonly>
                                </div>
                                <div class="col-md-2">
                                    <label class="form-label"><strong>Tipo:</strong></label>
                                    <select class="form-select" name="tipoCuenta2">
                                        <option value="Caja de ahorro">Caja de ahorro</option>
                                        <option value="Cuenta corriente" selected>Cuenta corriente</option>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                    <label class="form-label"><strong>Fecha Creación:</strong></label>
                                    <input type="date" class="form-control" name="fechaCreacion2" value="2023-06-10" readonly>
                                </div>
                                <div class="col-md-2">
                                    <label class="form-label"><strong>Saldo:</strong></label>
                                    <input type="text" class="form-control" name="saldo2" value="$180.500,50" readonly>
                                </div>
                                <div class="col-md-3">
                                    <label class="form-label"><strong>CBU:</strong></label>
                                    <input type="text" class="form-control" name="cbu2" value="0170987654321098765432" readonly>
                                </div>
                                <div class="col-md-1 text-center">
                                    <label class="form-label"><strong>Acciones:</strong></label><br>
                                    <button type="button" class="btn btn-danger btn-sm">Eliminar</button>
                                </div>
                            </div>
                        </div>
                        
                        <div class="cuenta-item" style="border-style: dashed; background-color: #e9ecef;">
                            <div class="row align-items-center">
                                <div class="col-md-8">
                                    <p class="mb-0"><strong>Agregar nueva cuenta</strong></p>
                                    <small class="text-muted">El cliente puede tener hasta 3 cuentas</small>
                                </div>
                                <div class="col-md-4 text-center">
                                    <button type="button" class="btn btn-success btn-sm">Agregar Cuenta</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row mt-4">
                <div class="col-12 text-center">
                    <button type="submit" class="btn btn-success btn-lg me-3">Guardar Cambios</button>
                    <a href="${pageContext.request.contextPath}/admin/clientes.jsp" class="btn btn-secondary btn-lg me-3">Volver</a>
                    <button type="button" class="btn btn-danger btn-lg">Eliminar Cliente</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>