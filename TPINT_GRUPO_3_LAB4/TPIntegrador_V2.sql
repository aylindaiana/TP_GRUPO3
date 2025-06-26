CREATE DATABASE `tpintegrador`;

USE `tpintegrador`;

CREATE TABLE `usuario` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Apellido` varchar(45) DEFAULT NULL,
  `Dni` int DEFAULT NULL,
  `Cuil` int DEFAULT NULL,
  `Sexo` varchar(45) DEFAULT NULL,
  `Nacionalidad` varchar(45) DEFAULT NULL,
  `FechaDeNacimiento` date DEFAULT NULL,
  `Direccion` varchar(45) DEFAULT NULL,
  `Localidad` varchar(45) DEFAULT NULL,
  `Provincia` varchar(45) DEFAULT NULL,
  `CorreoElectronico` varchar(100) DEFAULT NULL,
  `Telefono` varchar(45) DEFAULT NULL,
  `IDUsuario` int DEFAULT NULL,
  `Estado` tinyint DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `usuario_credenciales` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IDCliente` int DEFAULT NULL,
  `IDTipo` int DEFAULT NULL,
  `Usuario` varchar(45) DEFAULT NULL,
  `Contraseña` varchar(45) DEFAULT NULL,
  `Estado` tinyint DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `cuenta` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IDCliente` int NOT NULL,
  `IDTipoDeCuenta` int DEFAULT NULL,
  `FechaDeCreacion` date DEFAULT NULL,
  `CBU` varchar(45) DEFAULT NULL,
  `Saldo` double DEFAULT NULL,
  `Estado` tinyint DEFAULT NULL,
  PRIMARY KEY (`ID`,`IDCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `cuenta_tipos` (
  `ID` int NOT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='		';

CREATE TABLE `cuotas` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IDPrestamo` int NOT NULL,
  `NumeroCuota` int DEFAULT NULL,
  `Monto` int DEFAULT NULL,
  `FechaPago` date DEFAULT NULL,
  `IDMovimiento` varchar(45) DEFAULT NULL,
  `Estado` tinyint DEFAULT NULL,
  PRIMARY KEY (`ID`,`IDPrestamo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='		';

CREATE TABLE `movimientos` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IDCuentaOrigen` int NOT NULL,
  `IDCuentaDestino` int NOT NULL,
  `Monto` double DEFAULT NULL,
  `Fecha` date DEFAULT NULL,
  `Comentario` varchar(45) DEFAULT NULL,
  `IDTipodeMovimiento` int DEFAULT NULL,
  PRIMARY KEY (`ID`,`IDCuentaOrigen`,`IDCuentaDestino`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='				';

CREATE TABLE `movimientos_tipos` (
  `ID` int NOT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `prestamos` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IDCliente` varchar(45) NOT NULL,
  `IDCuenta` int NOT NULL,
  `FechaDeAlta` date DEFAULT NULL,
  `Importe` double DEFAULT NULL,
  `PlazoPago` int DEFAULT NULL,
  `ImporteMensual` double DEFAULT NULL,
  `CantidadCuotas` int DEFAULT NULL,
  `Autorizacion` tinyint DEFAULT NULL,
  PRIMARY KEY (`ID`,`IDCliente`,`IDCuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `usuario_tipos` (
  `ID` int NOT NULL,
  `Descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `prestamo_rechazado` (
  `ID` int NOT NULL,
  `IDPrestamo` int NOT NULL,
  `MotivoRechazo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


/* 
-----------------------------------------------------------------------------------------------------------------------------------
Precargado 
-----------------------------------------------------------------------------------------------------------------------------------*/
/* Usuario admin */
INSERT INTO `tpintegrador`.`usuario` (`ID`, `Nombre`, `Apellido`, `Dni`, `Cuil`, `Sexo`, `Nacionalidad`, `FechaDeNacimiento`, `Direccion`, `Localidad`, `Provincia`, `CorreoElectronico`, `Telefono`, `IDUsuario`, `Estado`) VALUES ('1', 'admin', 'admin', '1', '1', 'M', 'Argentina', '2000-01-01', 'Casa admin 1', 'Tigre', 'Buenos Aires', 'admin@correo.com', '123', '1', 1);

/* Credenciales admin */
INSERT INTO `tpintegrador`.`usuario_credenciales` (`ID`, `IDCliente`, `IDTipo`, `Usuario`, `Contraseña`, `Estado`) VALUES ('1', '1', '1', 'admin', '1234', '1');

/* Tipos de Usuario*/
INSERT INTO `tpintegrador`.`usuario_tipos` (`ID`, `Descripcion`) VALUES ('1', 'Administrador');
INSERT INTO `tpintegrador`.`usuario_tipos` (`ID`, `Descripcion`) VALUES ('2', 'Cliente');

/* Tipos de Movimientos */
INSERT INTO `tpintegrador`.`movimientos_tipos` (`ID`, `Descripcion`) VALUES ('1', 'Alta de Cuenta');
INSERT INTO `tpintegrador`.`movimientos_tipos` (`ID`, `Descripcion`) VALUES ('2', 'Alta de prestamo');
INSERT INTO `tpintegrador`.`movimientos_tipos` (`ID`, `Descripcion`) VALUES ('3', 'Pago de prestamo');
INSERT INTO `tpintegrador`.`movimientos_tipos` (`ID`, `Descripcion`) VALUES ('4', 'Transferencia');

/* Tipos de Cuenta */
INSERT INTO `tpintegrador`.`cuenta_tipos` (`ID`, `Descripcion`) VALUES ('1', 'Caja de Ahorro');
INSERT INTO `tpintegrador`.`cuenta_tipos` (`ID`, `Descripcion`) VALUES ('2', 'Cuenta corriente');

INSERT INTO cuenta (IDCliente, IDTipoDeCuenta, FechaDeCreacion, CBU, Saldo, Estado)
VALUES (1, 1, CURDATE(), '1234567890123456789012', 15000.00, 1);

/* 
-----------------------------------------------------------------------------------------------------------------------------------
Triggers - Structured Procedures 
-----------------------------------------------------------------------------------------------------------------------------------*/

/* SP IniciarSesion */
DELIMITER $$
DROP PROCEDURE IF EXISTS iniciar_sesion$$

CREATE PROCEDURE iniciar_sesion(
	IN p_usuario  VARCHAR(45),
    IN p_pass  VARCHAR(45)
)
BEGIN 
	SELECT IDCliente
    FROM usuario_credenciales UC
    where UC.Usuario = p_usuario and UC.Contraseña = p_pass ;
END$$
DELIMITER ;

/* call iniciar_sesion("admin", "1234"); */


/* SP ABML Clientes (Usuarios) */
/* SP tipo_clente_ID */
DELIMITER $$
DROP PROCEDURE IF EXISTS tipo_cliente_id$$

CREATE PROCEDURE tipo_cliente_id(
	IN id int
)
BEGIN 
	SELECT UT.ID AS id
    FROM usuario_tipos UT
    Inner JOIN usuario_credenciales UC
    ON UT.ID = UC.IDTipo
    WHERE UC.ID = id;
END$$
DELIMITER ;

call tipo_cliente_id(1);

/* SP Buscar_Cliente_ID */
DELIMITER $$
DROP PROCEDURE IF EXISTS buscar_cliente_id$$

CREATE PROCEDURE buscar_cliente_id(
	IN id int
)
BEGIN 
	SELECT ID, Nombre, Apellido, Dni, Cuil, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, Localidad, Provincia, CorreoElectronico, Telefono, IDUsuario, Estado
    FROM usuario
    where ID = id;
END$$
DELIMITER ;

call buscar_cliente_id(1);

/* SP ABML Usuarios (Credenciales) */

/* SP ABML Cuentas */

/* comando sql para que si el usuario tiene 3 o mas cuentas, devuelva false y no permita la creacion ni asignacion de otras cuentas */
/* SP buscar_cuentas_asignadas */
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_buscar_cuentas_asignadas$$

CREATE PROCEDURE sp_buscar_cuentas_asignadas(
	IN id int
)
BEGIN 
	DECLARE cantidad INT;

	SELECT count(CU.ID) 
    INTO cantidad
    FROM cuenta CU
    INNER JOIN usuario US
    ON CU.IDCliente = US.ID
    where US.ID = id AND CU.Estado = 1;
    
    IF cantidad >= 3 THEN
     SELECT 0 AS resultado;
	ELSE
	 SELECT 1 AS resultado;
	END IF;
    
END$$
DELIMITER ;

CALL sp_buscar_cuentas_asignadas(1);


/* SP Asignacion cuenta a cliente */

/* SP Autorizacion prestamo (cambio estado) */

/* SP Informes */

/* SP Transferencia */

/* SP Pedir prestamo */

DELIMITER $$
CREATE PROCEDURE sp_solicitar_prestamo(
	IN id INT,
	IN idCliente VARCHAR(45),
	IN idCuenta INT,
	IN fecha DATE,
	IN importe DOUBLE,
	IN plazo INT,
	IN importeMensual DOUBLE,
	IN cuotas INT,
	IN autorizacion TINYINT
)
BEGIN
	INSERT INTO prestamos (ID, IDCliente, IDCuenta, FechaDeAlta, Importe, PlazoPago, ImporteMensual, CantidadCuotas, Autorizacion)
	VALUES (id, idCliente, idCuenta, fecha, importe, plazo, importeMensual, cuotas, autorizacion);
END$$
DELIMITER ;

/*SP recargar cuenta con prestamo*/

DELIMITER $$
CREATE PROCEDURE sp_recargar_cuenta(
	IN recarga DOUBLE,
    IN id INT
)
BEGIN
	UPDATE cuenta
	SET Saldo = Saldo + recarga
	WHERE ID = id;
END$$
DELIMITER $$

/* SP listar prestamos por cliente */

DELIMITER $$
CREATE PROCEDURE sp_listar_prestamos_por_cliente(
	IN idCliente VARCHAR(45)
)
BEGIN
	SELECT ID, IDCliente, IDCuenta, FechaDeAlta, Importe, PlazoPago, ImporteMensual, CantidadCuotas, Autorizacion
	FROM prestamos WHERE IDCliente = idCliente;
END$$
DELIMITER ;

/* SP listar prestamos general*/

DELIMITER $$
CREATE PROCEDURE sp_listar_prestamos()
BEGIN
	SELECT ID, IDCliente, IDCuenta, FechaDeAlta, Importe, PlazoPago, ImporteMensual, CantidadCuotas, Autorizacion
	FROM prestamos;
END$$
DELIMITER ;

/* SP Pago prestamo */




/* SP movimiento (transferencia recibida, transferencia emitida, pago cuota, alta cuenta, alta prestamo, pago prestamo */

/* Al crear usuario, trigger para crear automaticamente credenciales (en nulo pero que se cree el registro relacionado con su id) */

