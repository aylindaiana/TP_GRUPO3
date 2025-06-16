<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transferencias</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
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
	          <a class="nav-link" aria-current="page" href="#">Home</a>
	        </li>
	        
	    <%
	    	// si es admin
	    	%>
	        <li class="nav-item">
	          <a class="nav-link" href="#">Clientes</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="#">Reportes</a>
	        </li>
	   <%
	    %>
	        <li class="nav-item">
	          <a class="nav-link" href="#">Cuentas</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="#">Prestamo</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" href="#">Transferir</a>
	        </li>
	      </ul>
	      <span class="navbar-text">
	      	logo
	        <button class="btn btn-danger">Log Out</button>
	      </span>
	    </div>
	  </div>
	</nav>
	
	<div class="container text-center pt mt-5">
	  <div class="row justify-content-start">
	    <div class="col-6">
	      <div class="container">
	      	<div class="row">
	      		<p class="fw-bold text-start">Cuenta</p>
	      		<form>
					<div class="input-group mb-3">
					  <span class="input-group-text">Cuenta</span>
					  <select id="formSelect" class="form-select">
					    <option>Seleccione cuenta</option>
					  </select>
					</div>
	      		</form>
	      	</div>
	      	<div class="row">
	      		<p class="fw-bold text-start">Saldo</p>
	      		<h1 class="w-50 text-success p-2 ms-3">$420.69</h1>
	      	</div>
	      </div>
	    </div>
	    <div class="col-6">
	      	<div class="row">
	      		<p class="fw-bold text-start">Transferencia</p>
	      		<form class="d-flex flex-column">
	      		
					<label for="formSelect" class="form-label fw-bold text-start">Destinatario</label>
					<div class="input-group mb-3">
					  <span class="input-group-text">Contacto</span>
					  <select id="formSelect" class="form-select">
					    <option>Seleccione contacto</option>
					  </select>
					</div>
				    
					<label for="formMonto" class="form-label fw-bold text-start">Monto</label>
					<div class="input-group mb-3">
					  <span class="input-group-text">$</span>
					  <input type="text" class="form-control" id="formMonto">
					  <span class="input-group-text">.00</span>
					</div>
				    
					<div class="input-group mb-3">
					  <span class="input-group-text fw-bold text-start">Mensaje</span>
					  <textarea class="form-control" aria-label="With textarea"></textarea>
					</div>
					
				    <button type="submit" class="btn btn-success w-100">Submit</button>
				</form>
	      	</div>
	    </div>
	    
	    <div class="col-6"></div>
	    
	    <div class="col-6 mt-5 p-2">
	      	<div class="row">
	      		<p class="fw-bold text-start">Movimientos</p>
	      		<table class="table table-striped-columns">
				  <thead>
				    <tr>
				      <th scope="col">Fecha</th>
				      <th scope="col">Operacion</th>
				      <th scope="col">Usuario</th>
				      <th scope="col">Monto</th>
				      <th scope="col">Comentario</th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <th scope="row">01/02/2025</th>
				      <td class="text-success">Ingreso</td>
				      <td>Otto 12</td>
				      <td>$3000.00</td>
				      <td>Pago repuestos</td>
				    </tr>
				    <tr>
				      <th scope="row">17/01/2025</th>
				      <td class="text-success">Ingreso</td>
				      <td>Banco LAB</td>
				      <td>$100.000,00</td>
				      <td>Prestamo</td>
				    </tr>
				    <tr>
				      <th scope="row">12/01/2025</th>
				      <td class="text-danger">Egreso</td>
				      <td>Edenor</td>
				      <td>$80.000,00</td>
				      <td>Pago cuentas</td>
				    </tr>
				    <tr>
				      <th scope="row">11/01/2025</th>
				      <td class="text-success">Ingreso</td>
				      <td>Laburo</td>
				      <td>$300.000,00</td>
				      <td>Sueldo</td>
				    </tr>
				    <tr>
				      <th scope="row">10/01/2025</th>
				      <td class="text-danger">Egreso</td>
				      <td>YPF</td>
				      <td>$7.500,00</td>
				      <td>Sin comentario</td>
				    </tr>
				  </tbody>
				</table>
				<button type="submit" class="btn btn-primary w-100">Ver todo</button>
	      	</div>
	    </div>
	  </div>
	</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</body>
</html>