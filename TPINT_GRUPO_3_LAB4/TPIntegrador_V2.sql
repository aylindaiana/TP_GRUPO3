/*DROP DATABASE tpintegrador;*/

CREATE DATABASE `tpintegrador`;

USE `tpintegrador`;


CREATE TABLE Provincia (
    ID_Provincia INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_Provincia VARCHAR(100) NOT NULL
);

CREATE TABLE Localidad (
    ID_Localidad INT AUTO_INCREMENT PRIMARY KEY,
    Nombre_Localidad VARCHAR(100) NOT NULL,
    ID_Provincia INT NOT NULL,
    FOREIGN KEY (ID_Provincia) REFERENCES Provincia(ID_Provincia)
);

CREATE TABLE `usuario` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Apellido` varchar(45) DEFAULT NULL,
  `Dni` int DEFAULT NULL,
  `Cuil` long DEFAULT NULL,
  `Sexo` varchar(45) DEFAULT NULL,
  `Nacionalidad` varchar(45) DEFAULT NULL,
  `FechaDeNacimiento` date DEFAULT NULL,
  `Direccion` varchar(45) DEFAULT NULL,
  `ID_Localidad` int DEFAULT NULL,
  `ID_Provincia` int DEFAULT NULL,
  `CorreoElectronico` varchar(100) DEFAULT NULL,
  `Telefono` varchar(45) DEFAULT NULL,
  `IDUsuario` int DEFAULT NULL,
  `Estado` tinyint DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`ID_Localidad`) REFERENCES Localidad(ID_Localidad),
  FOREIGN KEY (`ID_Provincia`) REFERENCES Provincia(ID_Provincia)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `usuario_credenciales` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IDCliente` int NOT NULL,
  `IDTipo` int DEFAULT NULL,
  `Usuario` varchar(45) DEFAULT NULL,
  `Contraseña` varchar(45) DEFAULT NULL,
  `Estado` tinyint DEFAULT NULL,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`IDCliente`) REFERENCES `usuario`(`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `cuenta` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IDCliente` int NOT NULL,
  `IDTipoDeCuenta` int DEFAULT NULL,
  `FechaDeCreacion` date DEFAULT NULL,
  `CBU` varchar(45) DEFAULT NULL UNIQUE,
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
  `FechaRealizacionPago` date DEFAULT NULL,
  `IDMovimiento` varchar(45) DEFAULT NULL,
  `Estado` tinyint DEFAULT NULL,
  PRIMARY KEY (`ID`,`IDPrestamo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='		';

CREATE TABLE `transferencia` (
  `ID` INT NOT NULL AUTO_INCREMENT,

  -- Cuenta que envía dinero
  `IDCuentaOrigen` INT NOT NULL,

  -- Cuenta que recibe dinero
  `IDCuentaDestino` INT NOT NULL,

  -- Monto transferido
  `Monto` DOUBLE NOT NULL,

  -- Fecha y hora exacta de la transferencia
  `Fecha` DATETIME NOT NULL,

  -- Detalle o comentario opcional
  `Comentario` VARCHAR(200) DEFAULT NULL,

  -- Estado: 1 = realizada, 0 = anulada, etc.
  `Estado` TINYINT DEFAULT 1,

  PRIMARY KEY (`ID`),

  -- Claves foráneas que vinculan con cuentas
  FOREIGN KEY (`IDCuentaOrigen`) REFERENCES `cuenta`(`ID`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,

  FOREIGN KEY (`IDCuentaDestino`) REFERENCES `cuenta`(`ID`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


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
  `ID` int NOT NULL AUTO_INCREMENT,
  `IDPrestamo` int NOT NULL,
  `MotivoRechazo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


/* 
-----------------------------------------------------------------------------------------------------------------------------------
Precargado 
-----------------------------------------------------------------------------------------------------------------------------------*/

-- Provincias
INSERT INTO Provincia (ID_Provincia, Nombre_Provincia) VALUES
(1, 'Buenos Aires'),
(2, 'Buenos Aires-GBA'),
(3, 'Capital Federal'),
(4, 'Catamarca'),
(5, 'Chaco'),
(6, 'Chubut'),
(7, 'Córdoba'),
(8, 'Corrientes'),
(9, 'Entre Ríos'),
(10, 'Formosa'),
(11, 'Jujuy'),
(12, 'La Pampa'),
(13, 'La Rioja'),
(14, 'Mendoza'),
(15, 'Misiones'),
(16, 'Neuquén'),
(17, 'Río Negro'),
(18, 'Salta'),
(19, 'San Juan'),
(20, 'San Luis'),
(21, 'Santa Cruz'),
(22, 'Santa Fe'),
(23, 'Santiago del Estero'),
(24, 'Tierra del Fuego'),
(25, 'Tucumán');

-- Localidades
INSERT INTO Localidad (Nombre_Localidad, ID_Provincia) VALUES
('La Plata', 1),
('Mar del Plata', 1),
('Bahía Blanca', 1),
('Lanús', 2),
('Quilmes', 2),
('Morón', 2),
('Palermo', 3),
('Recoleta', 3),
('Belgrano', 3),
('San Fernando del Valle', 4),
('Belén', 4),
('Tinogasta', 4),
('Resistencia', 5),
('Sáenz Peña', 5),
('Villa Ángela', 5),
('Rawson', 6),
('Comodoro Rivadavia', 6),
('Trelew', 6),
('Córdoba Capital', 7),
('Villa Carlos Paz', 7),
('Río Cuarto', 7),
('Corrientes', 8),
('Goya', 8),
('Paso de los Libres', 8),
('Paraná', 9),
('Concordia', 9),
('Gualeguaychú', 9),
('Formosa', 10),
('Clorinda', 10),
('Pirané', 10),
('San Salvador de Jujuy', 11),
('Humahuaca', 11),
('La Quiaca', 11),
('Santa Rosa', 12),
('General Pico', 12),
('Toay', 12),
('La Rioja', 13),
('Chilecito', 13),
('Aimogasta', 13),
('Mendoza', 14),
('San Rafael', 14),
('Godoy Cruz', 14),
('Posadas', 15),
('Oberá', 15),
('Eldorado', 15),
('Neuquén', 16),
('San Martín de los Andes', 16),
('Cutral Có', 16),
('Viedma', 17),
('Bariloche', 17),
('General Roca', 17),
('Salta', 18),
('Tartagal', 18),
('Orán', 18),
('San Juan', 19),
('Caucete', 19),
('Rawson', 19),
('San Luis', 20),
('Villa Mercedes', 20),
('Merlo', 20),
('Río Gallegos', 21),
('Caleta Olivia', 21),
('El Calafate', 21),
('Santa Fe', 22),
('Rosario', 22),
('Rafaela', 22),
('Santiago del Estero', 23),
('La Banda', 23),
('Frías', 23),
('Ushuaia', 24),
('Río Grande', 24),
('Tolhuin', 24),
('San Miguel de Tucumán', 25),
('Tafí Viejo', 25),
('Concepción', 25);


/* Usuario admin */
INSERT INTO `tpintegrador`.`usuario` 
(`ID`, `Nombre`, `Apellido`, `Dni`, `Cuil`, `Sexo`, `Nacionalidad`, `FechaDeNacimiento`, 
 `Direccion`, `ID_Localidad`, `ID_Provincia`, `CorreoElectronico`, `Telefono`, `IDUsuario`, `Estado`) 
VALUES (1, 'admin', 'admin', 1, 1, 'M', 'Argentina', '2000-01-01', 'Casa admin 1', 2, 1, 'admin@correo.com', '123', 1, 1);


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

/*  
	INSERT INTO cuenta (IDCliente, IDTipoDeCuenta, FechaDeCreacion, CBU, Saldo, Estado)
	VALUES (1, 1, CURDATE(), '1234567890123456789012', 100000.00, 1);
*/
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
    where UC.Usuario = p_usuario and UC.Contraseña = p_pass and UC.Estado = 1;
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
	SELECT ID, Nombre, Apellido, Dni, Cuil, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, ID_Localidad, ID_Provincia, CorreoElectronico, Telefono, IDUsuario, Estado
    FROM usuario
    where ID = id;
END$$
DELIMITER ;

call buscar_cliente_id(1);

/* SP ABML Usuarios */

DROP PROCEDURE IF EXISTS SP_LISTAR_CLIENTES;

DELIMITER $$
CREATE PROCEDURE SP_LISTAR_CLIENTES()
BEGIN
	SELECT 
		U.ID, U.Nombre, U.Apellido, U.Dni, U.Cuil, 
        U.Sexo, U.Nacionalidad, U.FechaDeNacimiento, 
        U.Direccion, U.ID_Localidad, U.ID_Provincia, 
        U.CorreoElectronico, U.Telefono, U.IDUsuario, U.Estado
    FROM usuario as U
    INNER JOIN usuario_credenciales AS UC ON U.ID = UC.IDCliente 
    WHERE IDTipo = 2;
END$$
DELIMITER ;

CALL SP_LISTAR_CLIENTES();


/* SP ABML Usuarios */

DROP PROCEDURE IF EXISTS SP_BUSCAR_ID_CLIENTES_INACTIVOS;

DELIMITER $$
CREATE PROCEDURE SP_BUSCAR_ID_CLIENTES_INACTIVOS(
	IN usuario varchar(45),
    IN pass varchar(45)
)
BEGIN
	SELECT 
		UC.IDCliente
    FROM usuario_credenciales as UC
    WHERE UC.Usuario = usuario AND UC.Contraseña = pass AND UC.Estado = 0;
END$$
DELIMITER ;

CALL SP_BUSCAR_ID_CLIENTES_INACTIVOS("usuario3", "1234");


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


DELIMITER $$
DROP PROCEDURE IF EXISTS sp_buscar_cuentas_asignadas_a_cliente$$ 
CREATE PROCEDURE sp_buscar_cuentas_asignadas_a_cliente(
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
    
    SELECT cantidad AS resultado;
END$$
DELIMITER ;

CALL sp_buscar_cuentas_asignadas_a_cliente(1);

/* SP Asignacion cuenta a cliente */

/* SP Autorizacion prestamo (cambio estado) */

/* SP Informes */

/* SP Transferencia */

/* SP Pedir prestamo */

DELIMITER $$
CREATE PROCEDURE sp_solicitar_prestamo(
	IN I_ID INT,
	IN I_IDCLIENTE VARCHAR(45),
	IN I_IDCUENTA INT,
	IN I_FECHA DATE,
	IN I_IMPORTE DOUBLE,
	IN I_PLAZO INT,
	IN I_IMPORTEMENSUAL DOUBLE,
	IN I_CUOTAS INT,
	IN I_AUTORIZACION TINYINT
)
BEGIN
	INSERT INTO prestamos (ID, IDCliente, IDCuenta, FechaDeAlta, Importe, PlazoPago, ImporteMensual, CantidadCuotas, Autorizacion)
	VALUES (I_ID, I_IDCLIENTE, I_IDCUENTA, I_FECHA, I_IMPORTE, I_PLAZO, I_IMPORTEMENSUAL, I_CUOTAS, I_AUTORIZACION);
END$$
DELIMITER ;

/*SP recargar cuenta con prestamo*/

DROP PROCEDURE IF EXISTS sp_recargar_cuenta 

DELIMITER $$
CREATE PROCEDURE sp_recargar_cuenta(
	IN I_RECARGA DOUBLE,
    IN I_ID_CUENTA INT
)
BEGIN

	UPDATE cuenta
	SET Saldo = Saldo + I_RECARGA
	WHERE ID = I_ID_CUENTA;
END$$
DELIMITER $$

/* SP listar prestamos por id prestamo */

DROP PROCEDURE IF EXISTS SP_BUSCAR_PRESTAMO_POR_ID

DELIMITER $$
CREATE PROCEDURE SP_BUSCAR_PRESTAMO_POR_ID(
    IN I_ID INT
)
BEGIN
	SELECT ID, IDCliente, IDCuenta, FechaDeAlta, Importe, PlazoPago, ImporteMensual, CantidadCuotas, Autorizacion
	FROM prestamos
    WHERE ID = I_ID;
END$$
DELIMITER $$

CALL SP_BUSCAR_PRESTAMO_POR_ID(1);


/* SP listar prestamos por cliente */

DROP PROCEDURE IF EXISTS sp_listar_prestamos_por_cliente;

DELIMITER $$
CREATE PROCEDURE sp_listar_prestamos_por_cliente(
	IN I_IDCLIENTE VARCHAR(45)
)
BEGIN
	SELECT ID, IDCliente, IDCuenta, FechaDeAlta, Importe, PlazoPago, ImporteMensual, CantidadCuotas, Autorizacion
	FROM prestamos WHERE IDCliente = I_IDCLIENTE;
END$$
DELIMITER ;

CALL sp_listar_prestamos_por_cliente(2);

/* SP listar prestamos general*/

DELIMITER $$
CREATE PROCEDURE sp_listar_prestamos()
BEGIN
	SELECT ID, IDCliente, IDCuenta, FechaDeAlta, Importe, PlazoPago, ImporteMensual, CantidadCuotas, Autorizacion
	FROM prestamos;
END$$
DELIMITER ;


/* SP listar prestamos por cuenta*/

DROP PROCEDURE IF EXISTS SP_LISTAR_PRESTAMOS_ACTIVOS_POR_CUENTA;

DELIMITER $$
CREATE PROCEDURE SP_LISTAR_PRESTAMOS_POR_CUENTA(
	IN I_IDCuenta INT
)
BEGIN
    SELECT 
		ID, IDCliente, IDCuenta, 
        FechaDeAlta, Importe, PlazoPago, 
        ImporteMensual, CantidadCuotas, Autorizacion
    FROM prestamos
    WHERE IDCuenta = I_IDCuenta AND Autorizacion != 3 AND Autorizacion != 4;
END$$
DELIMITER ;

CALL SP_LISTAR_PRESTAMOS_POR_CUENTA(4);

/* SP actualizacion estado prestamo*/

DROP PROCEDURE IF EXISTS SP_ACTUALIZACION_DE_ESTADO_PRESTAMO 

DELIMITER $$
CREATE PROCEDURE SP_ACTUALIZACION_DE_ESTADO_PRESTAMO(
	IN I_IDPrestamo INT,
    IN I_Estado TINYINT
)
BEGIN
	UPDATE prestamos SET Autorizacion = I_Estado WHERE ID = I_IDPrestamo;
END$$
DELIMITER $$

/*CALL SP_ACTUALIZACION_DE_ESTADO_PRESTAMO(?, ?)*/



/* sp actualizacion de tablas relacionadas a prestamos, en base al cambio de estao en un prestamo */


DROP PROCEDURE IF EXISTS SP_ACTUALIZACION_TABLAS_DEPENDIENDO_DE_ESTADO_PRESTAMO 

DELIMITER $$
CREATE PROCEDURE SP_ACTUALIZACION_TABLAS_DEPENDIENDO_DE_ESTADO_PRESTAMO(
	/*CARGADOS (PRESTAMOS)*/
	IN I_IDPrestamo INT, 
    IN I_IDCuenta INT, /* SE USA COMO CUENTA DESTINO*/
    IN I_Estado TINYINT,		
    IN I_CantidadCuotas INT,
    
    /*AUTOCALCULADO .JAVA (CUENTA Y MOVIMIENTOS)*/
    IN I_Importe DOUBLE,
    
    /*AUTOCALCULADOS .JAVA (MOVIMIENTOS)*/
    IN I_CuentaOrigen INT, /*¿¿¿DE DONDE PROVIENE ESTE???*/
    IN I_Fecha DATE,
    IN I_Comentario VARCHAR(45),
    
    /*AUTOCALCULADOS .jAVA CUOTAS*/
    
    /*AUTOCALCULADOS PRESTAMO RECHAZADO*/
    IN I_MotivoRechazo VARCHAR(45)
)
BEGIN
	/*declaraciones necesarias para cargar en el apartado de cuotas*/
	DECLARE ultimo_id_movimiento INT;
    DECLARE i INT DEFAULT 1;
    DECLARE numeroCuota INT;
    DECLARE fecha DATE;
    SET fecha = I_Fecha;
    
    CALL SP_ACTUALIZACION_DE_ESTADO_PRESTAMO(I_IDPrestamo, I_Estado);
    
    IF(I_ESTADO = 1) THEN
    
		CALL sp_recargar_cuenta(I_Importe/1.5, I_IDCuenta);
    
		/*el tipo de movimiento siempre es 2 ya que se trata del alta de un prestamo*/
		CALL SP_INSERTAR_MOVIMIENTO(I_CuentaOrigen, I_IDCuenta, I_Importe/1.5, I_Fecha, I_Comentario, 2);
    
		SET ultimo_id_movimiento = FN_ULTIMO_ID_MOVIMIENTO_GENERADO();
        /*a fecha se le setea el valor del mes siguiente (por consecuente la primer cuota se paga al siguiente mes)*/
		SET fecha = DATE_ADD(fecha, INTERVAL 1 MONTH);
        
		WHILE i <= I_CantidadCuotas DO
            SET numeroCuota = i;
			CALL SP_INSERTAR_CUOTA(I_IDPrestamo, numeroCuota, (I_Importe / I_CantidadCuotas), fecha, null, ultimo_id_movimiento, 0);
			
            SET i = i + 1;
			SET fecha = DATE_ADD(fecha, INTERVAL 1 MONTH);
		END WHILE;
        
    ELSEIF(I_ESTADO = 3) THEN
		
		CALL SP_INSERTAR_PRESTAMO_RECHAZADO(I_IDPrestamo, I_MotivoRechazo);
        
    END IF;
END$$
DELIMITER $$


/*CALL SP_ACTUALIZACION_TABLAS_DEPENDIENDO_DE_ESTADO_PRESTAMO(?, ?, ?, ?, ?, ?, ?, ?, ?)*/



/* SP Pago prestamo */




/* SP movimiento (transferencia recibida, transferencia emitida, pago cuota, alta cuenta, alta prestamo, pago prestamo */



DROP PROCEDURE IF EXISTS SP_INSERTAR_MOVIMIENTO $$

DELIMITER $$
CREATE PROCEDURE SP_INSERTAR_MOVIMIENTO(
    IN I_IDCuentaOrigen INT,
    IN I_IDCuentaDestino INT,
    IN I_Monto INT,
    IN I_Fecha DATE,
    IN I_Comentario VARCHAR(45),
    IN I_IDTipodeMovimiento INT
)
BEGIN
	INSERT INTO movimientos ( IDCuentaOrigen, IDCuentaDestino, Monto, Fecha, Comentario, IDTipodeMovimiento)
    VALUES (I_IDCuentaOrigen, I_IDCuentaDestino, I_Monto, I_Fecha, I_Comentario, I_IDTipodeMovimiento);
END$$
DELIMITER $$

/*CALL SP_INSERTAR_MOVIMIENTO(?, ?, ?, ?, ?, ?);*/




DROP PROCEDURE IF EXISTS SP_LISTAR_MOVIMIENTOS_POR_CLIENTE;
DELIMITER $$

CREATE PROCEDURE SP_LISTAR_MOVIMIENTOS_POR_CLIENTE(
    IN I_IDCliente INT
)
BEGIN
    SELECT M.ID, M.IDCuentaOrigen, M.IDCuentaDestino, M.Monto, M.Fecha, M.Comentario, M.IDTipodeMovimiento, MT.Descripcion AS DescripcionTipodeMovimiento
    FROM movimientos M
    INNER JOIN movimientos_tipos MT
    ON M.IDTipodeMovimiento = MT.ID
    WHERE 
        M.IDCuentaOrigen IN (SELECT ID FROM cuenta WHERE IDCliente = I_IDCliente)
        OR
        M.IDCuentaDestino IN (SELECT ID FROM cuenta WHERE IDCliente = I_IDCliente)
    ORDER BY Fecha DESC
    LIMIT 5;
END$$
DELIMITER ;


CALL SP_LISTAR_MOVIMIENTOS_POR_CLIENTE(2);


DROP PROCEDURE IF EXISTS SP_LISTAR_MOVIMIENTOS_POR_CUENTA;
DELIMITER $$

CREATE PROCEDURE SP_LISTAR_MOVIMIENTOS_POR_CUENTA(
    IN I_IDCuenta INT
)
BEGIN
    SELECT M.ID, M.IDCuentaOrigen, M.IDCuentaDestino, M.Monto, M.Fecha, M.Comentario, M.IDTipodeMovimiento, MT.Descripcion AS DescripcionTipodeMovimiento
    FROM movimientos M
    INNER JOIN movimientos_tipos MT
    ON M.IDTipodeMovimiento = MT.ID
    WHERE 
        M.IDCuentaOrigen IN (SELECT ID FROM cuenta WHERE ID = I_IDCuenta)
        OR
        M.IDCuentaDestino IN (SELECT ID FROM cuenta WHERE ID = I_IDCuenta)
    ORDER BY Fecha DESC
    LIMIT 5;
END$$
DELIMITER ;


CALL SP_LISTAR_MOVIMIENTOS_POR_CUENTA(8);



DROP PROCEDURE IF EXISTS SP_LISTAR_MOVIMIENTOS$$

DELIMITER $$
CREATE PROCEDURE SP_LISTAR_MOVIMIENTOS(
)
BEGIN
    SELECT M.ID, M.IDCuentaOrigen, M.IDCuentaDestino, M.Monto, M.Fecha, M.Comentario, M.IDTipodeMovimiento, MT.Descripcion AS DescripcionTipodeMovimiento
    FROM movimientos M
    INNER JOIN movimientos_tipos MT
    ON M.IDTipodeMovimiento = MT.ID;
END$$
DELIMITER $$

CALL SP_LISTAR_MOVIMIENTOS();




DROP FUNCTION IF EXISTS FN_ULTIMO_ID_MOVIMIENTO_GENERADO;

DELIMITER $$

CREATE FUNCTION FN_ULTIMO_ID_MOVIMIENTO_GENERADO()
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE ultimoID INT;
    
    SELECT ID INTO ultimoID
    FROM movimientos ORDER BY ID DESC LIMIT 1;
    
    RETURN ultimoID;
END$$
DELIMITER ;

/*------------------------------------------------------------------------------------------------------------------------------------
cuotas */


DROP PROCEDURE IF EXISTS SP_INSERTAR_CUOTA


DELIMITER $$
CREATE PROCEDURE SP_INSERTAR_CUOTA(
	IN I_IDPrestamo INT,
	IN I_NumeroCuota INT,
	IN I_Monto INT,
	IN I_FechaPago DATE,
	IN I_FechaRealizacionPago DATE,
	IN I_IDMovimiento VARCHAR(45),
	IN I_Estado TINYINT
)
BEGIN
	INSERT INTO cuotas (IDPrestamo, NumeroCuota, Monto, FechaPago, FechaRealizacionPago, IDMovimiento, Estado)
	VALUES (I_IDPrestamo, I_NumeroCuota, I_Monto, I_FechaPago, I_FechaRealizacionPago, I_IDMovimiento, I_Estado);
END$$
DELIMITER $$

/*CALL SP_INSERTAR_CUOTA(?, ?, ?, ?, ?, ?, ?);*/



DROP PROCEDURE IF EXISTS SP_PAGAR_CUOTA

DELIMITER $$
CREATE PROCEDURE SP_PAGAR_CUOTA(
    IN I_ID INT
)
BEGIN
	UPDATE cuotas SET FechaRealizacionPago = curdate(), Estado = 1
    WHERE ID = I_ID;
END$$
DELIMITER $$

/*CALL SP_PAGAR_CUOTA(1);*/



DROP PROCEDURE IF EXISTS SP_LISTAR_CUOTAS_POR_PRESTAMO $$

DELIMITER $$
CREATE PROCEDURE SP_LISTAR_CUOTAS_POR_PRESTAMO(
    IN I_ID INT
)
BEGIN
	SELECT ID, IDPrestamo, NumeroCuota, Monto, FechaPago, FechaRealizacionPago, IDMovimiento, Estado FROM cuotas 
    WHERE IDPrestamo = I_ID;
END$$
DELIMITER $$

/*CALL SP_LISTAR_CUOTAS_POR_PRESTAMO(?);*/


/*------------------------------------------------------------------------------------------------------------------------------------
prestamo rechazado */



DROP PROCEDURE IF EXISTS SP_INSERTAR_PRESTAMO_RECHAZADO $$

DELIMITER $$
CREATE PROCEDURE SP_INSERTAR_PRESTAMO_RECHAZADO(
    IN I_IDPrestamo INT,
    IN I_MotivoRechazo VARCHAR(45)
)
BEGIN
	INSERT INTO prestamo_rechazado (IDPrestamo, MotivoRechazo)
    VALUES (I_IDPrestamo, I_MotivoRechazo);
END$$
DELIMITER $$

/*CALL SP_INSERTAR_PRESTAMO_RECHAZADO(?, ?);*/



DROP PROCEDURE IF EXISTS SP_SELECT_MOTIVO_PRESTAMO_RECHAZADO $$

DELIMITER $$
CREATE PROCEDURE SP_SELECT_MOTIVO_PRESTAMO_RECHAZADO(
    IN I_ID INT
)
BEGIN
	SELECT ID, IDPrestamo, MotivoRechazo FROM prestamo_rechazado 
    WHERE IDPrestamo = I_ID;
END$$
DELIMITER $$

/*CALL SP_SELECT_MOTIVO_PRESTAMO_RECHAZADO(?);*/


/* SP - Listar clientes completos en cuentas Admin*/

DROP PROCEDURE IF EXISTS SP_LISTAR_CUENTAS;
DELIMITER $$

CREATE PROCEDURE SP_LISTAR_CUENTAS()
BEGIN
	SELECT 
		CU.ID,
        CU.IDCliente,
		CONCAT(U.Nombre, ' ', U.Apellido) AS NombreCliente,
		CU.IDTipoDeCuenta,
		CU.FechaDeCreacion,
		CU.CBU,
		CU.Saldo,
		CU.Estado
	FROM cuenta CU
	INNER JOIN usuario U ON CU.IDCliente = U.ID
	INNER JOIN cuenta_tipos CT ON CU.IDTipoDeCuenta = CT.ID;
END$$

DELIMITER ;
/*CALL SP_LISTAR_CUENTAS();*/

DROP PROCEDURE IF EXISTS SP_OBTENER_CUENTA_POR_ID;
DELIMITER $$

CREATE PROCEDURE SP_OBTENER_CUENTA_POR_ID(IN cuentaId INT)
BEGIN
    SELECT 
        CU.ID,
        CU.IDCliente,
        CU.IDTipoDeCuenta,
        CU.FechaDeCreacion,
        CU.CBU,
        CU.Saldo,
        CU.Estado,
        CONCAT(U.Nombre, ' ', U.Apellido) AS NombreCliente
    FROM cuenta CU
    INNER JOIN usuario U ON CU.IDCliente = U.ID
    WHERE CU.ID = cuentaId;
END$$

DELIMITER ;

/*CALL SP_LISTAR_CUENTAS();*/



DROP PROCEDURE IF EXISTS SP_OBTENER_SALDO_CUENTA;

DELIMITER $$
CREATE PROCEDURE SP_OBTENER_SALDO_CUENTA(
	IN I_IDCUENTA INT
)
BEGIN
	SELECT Saldo
    FROM CUENTA
	WHERE ID = I_IDCUENTA;
END$$
DELIMITER ;

CALL SP_OBTENER_SALDO_CUENTA(5);




DROP PROCEDURE IF EXISTS SP_LISTAR_CUENTAS_ACTIVAS_POR_CLIENTE;

DELIMITER $$
CREATE PROCEDURE SP_LISTAR_CUENTAS_ACTIVAS_POR_CLIENTE(
	IN I_IDCLIENTE VARCHAR(45)
)
BEGIN
	SELECT ID, IDCliente, IDTipoDeCuenta, FechaDeCreacion, CBU, Saldo, Estado
    FROM CUENTA
	WHERE IDCliente = I_IDCLIENTE AND ESTADO = 1;
END$$
DELIMITER ;

CALL SP_LISTAR_CUENTAS_ACTIVAS_POR_CLIENTE(2);

/*CALL SP_OBTENER_CUENTA_POR_ID(1);*/

/*DROP PROCEDURE IF EXISTS SP_BUSCAR_FILTRO;*/
DELIMITER $$

CREATE PROCEDURE SP_BUSCAR_FILTRO (
    IN filtroCliente VARCHAR(255),
    IN filtroCBU VARCHAR(255)
)
BEGIN
    IF filtroCliente IS NOT NULL AND filtroCliente != '' THEN
        SELECT 
            c.ID,
            c.IDCliente,
            cli.Nombre,
            cli.Apellido,
            c.CBU,
            c.IDTipoDeCuenta,
            c.FechaDeCreacion,
            c.Saldo,
            c.Estado
        FROM cuenta c
        INNER JOIN usuario cli ON cli.ID = c.IDCliente
        WHERE CONCAT(cli.Nombre, ' ', cli.Apellido) LIKE CONCAT('%', filtroCliente, '%');

    ELSEIF filtroCBU IS NOT NULL AND filtroCBU != '' THEN
        SELECT 
            c.ID,
            c.IDCliente,
            cli.Nombre,
            cli.Apellido,
            c.CBU,
            c.IDTipoDeCuenta,
            c.FechaDeCreacion,
            c.Saldo,
            c.Estado
        FROM cuenta c
        INNER JOIN usuario cli ON cli.ID = c.IDCliente
        WHERE c.CBU LIKE CONCAT('%', filtroCBU, '%');
    END IF;
END $$

DELIMITER ;


DELIMITER $$

CREATE PROCEDURE SP_TRANSFERIR(
    IN p_CuentaOrigen INT,
    IN p_CuentaDestino INT,
    IN p_Monto DOUBLE,
    IN p_Fecha DATETIME,
    IN p_Comentario VARCHAR(200)
)
BEGIN
    DECLARE saldoOrigen DOUBLE;
    DECLARE idTransferencia INT;

    SELECT Saldo INTO saldoOrigen FROM cuenta WHERE ID = p_CuentaOrigen;

    IF saldoOrigen >= p_Monto THEN

        UPDATE cuenta
        SET Saldo = Saldo - p_Monto
        WHERE ID = p_CuentaOrigen;

        UPDATE cuenta
        SET Saldo = Saldo + p_Monto
        WHERE ID = p_CuentaDestino;

        INSERT INTO transferencia (IDCuentaOrigen, IDCuentaDestino, Monto, Fecha, Comentario, Estado)
        VALUES (p_CuentaOrigen, p_CuentaDestino, p_Monto, p_Fecha, p_Comentario, 1);

        SET idTransferencia = LAST_INSERT_ID();

        INSERT INTO movimientos (
            IDCuentaOrigen,
            IDCuentaDestino,
            Monto,
            Fecha,
            Comentario,
            IDTipodeMovimiento
        )
        VALUES (
            p_CuentaOrigen,
            p_CuentaDestino,
            p_Monto,
            p_Fecha,
            CONCAT('Transferencia: ', p_Comentario),
            4
        );

    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Saldo insuficiente para realizar la transferencia.';
    END IF;

END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS SP_LISTAR_TRANSFERENCIAS_POR_CUENTAS;

DELIMITER $$

CREATE PROCEDURE SP_LISTAR_TRANSFERENCIAS_POR_CUENTAS(
  IN p_Cuenta1 INT,
  IN p_Cuenta2 INT,
  IN p_Cuenta3 INT,
  IN p_FechaDesde DATETIME,
  IN p_FechaHasta DATETIME,
  IN p_MontoMin DOUBLE,
  IN p_MontoMax DOUBLE,
  IN p_Offset INT,
  IN p_Limite INT
)
BEGIN
  SELECT
    t.ID,
    t.IDCuentaOrigen,
    co.CBU AS CBUOrigen,
    uo.Nombre AS NombreOrigen,

    t.IDCuentaDestino,
    cd.CBU AS CBUDestino,
    ud.Nombre AS NombreDestino,

    t.Monto,
    t.Fecha,
    t.Comentario,
    CASE
      WHEN t.IDCuentaOrigen IN (p_Cuenta1, p_Cuenta2, p_Cuenta3) THEN 'EGRESO'
      WHEN t.IDCuentaDestino IN (p_Cuenta1, p_Cuenta2, p_Cuenta3) THEN 'INGRESO'
      ELSE 'N/A'
    END AS TipoMovimiento
  FROM transferencia t
  INNER JOIN cuenta co ON t.IDCuentaOrigen = co.ID
  INNER JOIN usuario uo ON co.IDCliente = uo.ID

  INNER JOIN cuenta cd ON t.IDCuentaDestino = cd.ID
  INNER JOIN usuario ud ON cd.IDCliente = ud.ID

  WHERE
    (t.IDCuentaOrigen IN (p_Cuenta1, p_Cuenta2, p_Cuenta3)
     OR t.IDCuentaDestino IN (p_Cuenta1, p_Cuenta2, p_Cuenta3))
    AND t.Fecha BETWEEN p_FechaDesde AND p_FechaHasta
    AND t.Monto BETWEEN p_MontoMin AND p_MontoMax
  ORDER BY t.Fecha DESC
  LIMIT p_Offset, p_Limite;
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS SP_LISTAR_MOVIMIENTOS_POR_CUENTA_Y_FILTROS $$

CREATE PROCEDURE SP_LISTAR_MOVIMIENTOS_POR_CUENTA_Y_FILTROS(
    IN p_idCuenta INT,
    IN p_busqueda VARCHAR(255),
    IN p_tipo VARCHAR(50),
    IN p_minMonto DOUBLE,
    IN p_maxMonto DOUBLE,
    IN p_fechaDesde DATE,
    IN p_fechaHasta DATE,
    IN p_offset INT,
    IN p_limit INT
)
BEGIN
    SELECT 
        m.Fecha,
        m.Comentario AS Detalle,
        m.Monto,
        mt.Descripcion AS TipoMovimiento
    FROM movimientos m
    INNER JOIN movimientos_tipos mt ON m.IDTipodeMovimiento = mt.ID
    WHERE (m.IDCuentaOrigen = p_idCuenta OR m.IDCuentaDestino = p_idCuenta)
      AND m.Comentario LIKE p_busqueda
      AND (p_tipo = '' OR mt.Descripcion = p_tipo)
      AND m.Monto BETWEEN p_minMonto AND p_maxMonto
      AND m.Fecha BETWEEN p_fechaDesde AND p_fechaHasta
    ORDER BY m.Fecha DESC
    LIMIT p_offset, p_limit;
END$$

DELIMITER ;

/*pruebas para los Reportes. */
DELIMITER $$
CREATE PROCEDURE SP_TOTAL_POR_TIPO(
    IN tipoMovimiento INT,
    IN fechaDesde DATE, IN fechaHasta DATE
)
BEGIN
    SELECT IFNULL(SUM(Monto), 0) AS total
    FROM movimientos
    WHERE IDTipodeMovimiento = tipoMovimiento
      AND Fecha BETWEEN fechaDesde AND fechaHasta; 
END $$

DELIMITER ;

DELIMITER $$
CREATE PROCEDURE SP_CONTAR_PRESTAMOS_APROBADOS(
    IN fechaDesde DATE,
    IN fechaHasta DATE
)
BEGIN
    SELECT COUNT(*) AS total 
    FROM prestamos
    WHERE Autorizacion = 1 
      AND FechaDeAlta BETWEEN fechaDesde AND fechaHasta;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE SP_CONTAR_PRESTAMOS_RECHAZADOS(
    IN fechaDesde DATE,
    IN fechaHasta DATE
)
BEGIN
    SELECT COUNT(*) AS total 
    FROM prestamo_rechazado
    WHERE IDPrestamo IN (
        SELECT ID FROM prestamos
        WHERE FechaDeAlta BETWEEN fechaDesde AND fechaHasta
    );
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE SP_CONTAR_CUOTAS_PAGADAS(
    IN fechaDesde DATE, IN fechaHasta DATE
)
BEGIN
    SELECT COUNT(*) AS total
    FROM cuotas
    WHERE Estado = 1
      AND FechaPago BETWEEN fechaDesde AND fechaHasta;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE SP_TOTAL_RECAUDADO_CUOTAS(
    IN fechaDesde DATE, IN fechaHasta DATE
)
BEGIN
    SELECT SUM(Monto) AS total
    FROM cuotas
    WHERE Estado = 1
      AND FechaPago BETWEEN fechaDesde AND fechaHasta;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE SP_CONTAR_CUENTAS_POR_TIPO(
    IN tipoCuenta INT,
    IN fechaDesde DATE,
    IN fechaHasta DATE
)
BEGIN
    SELECT COUNT(*) AS total
    FROM cuenta
    WHERE Estado = 1
      AND IDTipoDeCuenta = tipoCuenta
      AND FechaDeCreacion BETWEEN fechaDesde AND fechaHasta;
END$$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE SP_CONTAR_NUEVOS_CLIENTES(
    IN fechaDesde DATE, IN fechaHasta DATE
)
BEGIN
    SELECT COUNT(*) AS total
    FROM usuario
    WHERE FechaDeNacimiento IS NOT NULL
      AND FechaDeNacimiento BETWEEN fechaDesde AND fechaHasta;
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE SP_CONTAR_TRANSFERENCIAS(
    IN fechaDesde DATE, IN fechaHasta DATE
)
BEGIN
    SELECT COUNT(*) AS total
    FROM transferencia
    WHERE Fecha BETWEEN fechaDesde AND fechaHasta
      AND Estado = 1;
END $$
DELIMITER ;

DELIMITER $$

CREATE PROCEDURE SP_CONTAR_CUENTAS_CREADAS(
    IN fechaDesde DATE, IN fechaHasta DATE
)
BEGIN
    SELECT COUNT(*) AS total
    FROM cuenta
    WHERE Estado = 1 AND FechaDeCreacion BETWEEN fechaDesde AND fechaHasta;
END $$

DELIMITER ;

/*CALL SP_CONTAR_CUENTAS_CREADAS("2023-01-01", "2023-12-31");*/

/* Pruebas para los Reportes. */

SELECT * FROM usuario;
SELECT * FROM usuario_credenciales;
SELECT * FROM cuenta;
SELECT * FROM movimientos;



/*CALL SP_BUSCAR_FILTRO("admin", 324324231111111);*/
-- Cliente 1: Juan Pérez
/*
INSERT INTO usuario (nombre, apellido, dni)
VALUES ('Rami', 'Gomez', '12345678');

-- Cliente 2: María Gómez
INSERT INTO usuario (nombre, apellido, dni)
VALUES ('Nahuel', 'Ramos', '87654321');*/

/*********PRUEBAS PARA REPORTES
INSERT INTO usuario (Nombre, Apellido, Dni, Cuil, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, ID_Localidad, ID_Provincia, CorreoElectronico, Telefono, IDUsuario, Estado)
VALUES ('Juan', 'Perez', 12345678, 20312345678, 'Masculino', 'Argentina', '1990-01-15', 'Calle Ficticia 123', 1, 1, 'juan.perez@example.com', '1122334455', 1001, 1);

INSERT INTO usuario (Nombre, Apellido, Dni, Cuil, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, ID_Localidad, ID_Provincia, CorreoElectronico, Telefono, IDUsuario, Estado)
VALUES ('Maria', 'Gomez', 87654321, 20387654321, 'Femenino', 'Argentina', '1985-04-10', 'Calle Real 456', 2, 1, 'maria.gomez@example.com', '1166778899', 1002, 1);

INSERT INTO usuario_credenciales (IDCliente, Usuario, Contraseña, Estado) 
VALUES (2, 'juanperez', 'password123', 1);

INSERT INTO usuario_credenciales (IDCliente, Usuario, Contraseña, Estado) 
VALUES (3, 'mariagomez', 'mypassword456', 1);

INSERT INTO cuenta (IDCliente, IDTipoDeCuenta, FechaDeCreacion, CBU, Saldo, Estado) 
VALUES (2, 1, '2023-01-01', '5425345345345', 5000.0, 1);

INSERT INTO cuenta (IDCliente, IDTipoDeCuenta, FechaDeCreacion, CBU, Saldo, Estado) 
VALUES (3, 2, '2023-02-01', '65463542432152', 2000.0, 1);

INSERT INTO movimientos (IDCuentaOrigen, IDCuentaDestino, Monto, Fecha, Comentario, IDTipodeMovimiento)
VALUES (1, 2, 1000.0, '2023-06-15', 'Pago de servicio', 1);

INSERT INTO movimientos (IDCuentaOrigen, IDCuentaDestino, Monto, Fecha, Comentario, IDTipodeMovimiento)
VALUES (2, 1, 500.0, '2023-07-01', 'Transferencia a cuenta', 2);

INSERT INTO prestamos (IDCliente, IDCuenta, FechaDeAlta, Importe, PlazoPago, ImporteMensual, CantidadCuotas, Autorizacion)
VALUES ('1001', 4, '2023-05-10', 15000.0, 12, 1250.0, 12, 1);

INSERT INTO prestamos (IDCliente, IDCuenta, FechaDeAlta, Importe, PlazoPago, ImporteMensual, CantidadCuotas, Autorizacion)
VALUES ('1002', 5, '2023-04-20', 10000.0, 6, 1666.67, 6, 0);

INSERT INTO cuotas (IDPrestamo, NumeroCuota, Monto, FechaPago, IDMovimiento, Estado)
VALUES (1, 1, 1250.0, '2023-06-10', 'ABC123', 1);

INSERT INTO cuotas (IDPrestamo, NumeroCuota, Monto, FechaPago, IDMovimiento, Estado)
VALUES (1, 2, 1250.0, '2023-07-10', 'DEF456', 1);

INSERT INTO transferencia (IDCuentaOrigen, IDCuentaDestino, Monto, Fecha, Comentario, Estado)
VALUES (1, 2, 500.0, '2023-06-15', 'Pago a Maria', 1);
 
INSERT INTO transferencia (IDCuentaOrigen, IDCuentaDestino, Monto, Fecha, Comentario, Estado)
VALUES (2, 1, 300.0, '2023-07-01', 'Pago de deuda', 1);

 select * FROM movimientos

CALL SP_TOTAL_POR_TIPO(1, '2023-01-01', '2023-12-31');
SELECT ID, IDCuentaOrigen, IDCuentaDestino, Monto, Fecha, Comentario, IDTipodeMovimiento
FROM movimientos;
CALL SP_TOTAL_POR_TIPO(1, '2023-01-01', '2023-12-31');

SELECT * FROM movimientos
WHERE Fecha BETWEEN '2023-01-01' AND '2023-12-31';
*/

/*******************************************************/


/* Al crear usuario, trigger para crear automaticamente credenciales (en nulo pero que se cree el registro relacionado con su id) */

CALL SP_LISTAR_MOVIMIENTOS_POR_CLIENTE(3);

/* Trigger al crear usuario */
DELIMITER $$ 
DROP TRIGGER IF EXISTS crear_usuario_primera_cuenta;

DELIMITER $$ 
CREATE TRIGGER crear_usuario_primera_cuenta
AFTER INSERT
ON usuario
FOR EACH ROW
BEGIN
	DECLARE cbu VARCHAR(45);
	SET cbu = CONCAT(
    /* No lo toquen que se rompe, aca si o si devuelve un numero de 22 digitos c:*/
    LPAD(FLOOR(RAND() * 10000000000), 11, '0'),
    LPAD(FLOOR(RAND() * 10000000000), 11, '0')
	);
	/* creacion cuenta */
	INSERT INTO cuenta (IDCliente, IDTipoDeCuenta, FechaDeCreacion, CBU, Saldo, Estado)
	VALUES (NEW.ID, 2, NOW(), cbu, 10000, 1);
END$$;
DELIMITER ;

/* Trigger al crear cuenta nueva */
DELIMITER $$ 
DROP TRIGGER IF EXISTS crear_cuenta;

	DELIMITER $$ 
	CREATE TRIGGER crear_cuenta
	AFTER INSERT
	ON cuenta
	FOR EACH ROW
	BEGIN
		/* creacion movimiento */
        INSERT INTO movimientos (IDCuentaOrigen, IDCuentaDestino, Monto, Fecha, Comentario, IDTipodeMovimiento)
		VALUES (NEW.ID, NEW.ID, 10000, NOW(), "Se creo la cuenta", 1);
	END$$;

DELIMITER ;

/* Trigger al eliminr (logicamente) un usuario borrar sus cuentas anexadas*/
DELIMITER $$ 
DROP TRIGGER IF EXISTS tr_eliminar_cuentas;

DELIMITER $$ 
CREATE TRIGGER tr_eliminar_cuentas
AFTER UPDATE
ON usuario
FOR EACH ROW
BEGIN
IF OLD.Estado = 1 AND NEW.Estado = 0 THEN
        UPDATE cuenta c
        SET c.Estado = 0
        WHERE c.IDCliente = NEW.ID;
    END IF;
END
$$;

/* Trigger al activar un usuario volver a activar sus cuentas anexadas*/
DELIMITER $$ 
DROP TRIGGER IF EXISTS tr_activar_cuentas;

DELIMITER $$ 
CREATE TRIGGER tr_activar_cuentas
AFTER UPDATE
ON usuario
FOR EACH ROW
BEGIN
IF OLD.Estado = 0 AND NEW.Estado = 1 THEN
        UPDATE cuenta c
        SET c.Estado = 1
        WHERE c.IDCliente = NEW.ID;
    END IF;
END
$$;

DELIMITER ;

/* Trigger al eliminar logicamente un usuario, dar de baja sus credenciales */
DELIMITER $$ 
DROP TRIGGER IF EXISTS inactivar_credenciales;

DELIMITER $$ 
CREATE TRIGGER inactivar_credenciales
AFTER UPDATE
ON usuario
FOR EACH ROW
BEGIN
	IF
		OLD.Estado = 1 AND NEW.Estado = 0 THEN
		update usuario_credenciales uc
        set uc.Estado = 0
        WHERE uc.IDCliente = NEW.ID;
    END IF;
END$$;
DELIMITER ;


/*TRIGGER QUE SE ENCARGA DE ACTUALIZAR EL ESTADO DE LOS PRESTAMOS AL MOMENTO DE PAGADAS TODAS SUS CUOTAS*/

DROP TRIGGER IF EXISTS TR_ACTUALIZAR_ESTADO_PRESTAMO;

DELIMITER $$
CREATE TRIGGER TR_ACTUALIZAR_ESTADO_PRESTAMO
AFTER UPDATE ON cuotas
FOR EACH ROW
BEGIN
DECLARE CUOTAS_PENDIENTES INT;

SELECT COUNT(*) INTO CUOTAS_PENDIENTES
FROM Cuotas
WHERE IDPrestamo = NEW.IDPrestamo AND Estado = 0;

IF CUOTAS_PENDIENTES = 0 THEN
  UPDATE prestamos
  SET Autorizacion = 4
  WHERE ID = NEW.IDPrestamo;
END IF;
END$$

DELIMITER ;





/* SP tipo_movimiento_id */
DELIMITER $$
DROP PROCEDURE IF EXISTS tipo_movimiento_id$$

CREATE PROCEDURE tipo_movimiento_id(
	IN id_mov int
)
BEGIN 
	SELECT descripcion 
    FROM movimientos_tipos
    WHERE ID = id_mov;
END$$
DELIMITER ;

CALL tipo_movimiento_id(1)

/* SP Listar ultimos movimientos por clientes */
DELIMITER $$
DROP PROCEDURE IF EXISTS SP_listar_ultimos_cliente$$

CREATE PROCEDURE SP_listar_ultimos_cliente(
	IN id_cliente int,
    IN limite int
)
BEGIN 
    SELECT M.ID, M.IDCuentaOrigen, M.IDCuentaDestino, M.Monto, M.Fecha, M.Comentario, M.IDTipodeMovimiento, MT.Descripcion AS DescripcionTipodeMovimiento
    FROM movimientos M
    INNER JOIN movimientos_tipos MT 
    ON M.IDTipoDeMovimiento = MT.ID
    WHERE M.IDCuentaOrigen IN (SELECT ID FROM cuenta WHERE IDCliente = id_cliente)
	ORDER BY M.Fecha DESC LIMIT limite;
END$$
DELIMITER ;

CALL SP_listar_ultimos_cliente(5, 5);

/* SP Para calcular cantidad de prestamos pendientes de aprobacion, teniendo en cuenta si la cuenta esta activa o no*/

DELIMITER $$
DROP PROCEDURE IF EXISTS SP_cant_prestamos_pendientes_aprob$$

CREATE PROCEDURE SP_cant_prestamos_pendientes_aprob()
BEGIN
	SELECT COUNT(*) as total
    FROM prestamos p
    Inner join cuenta c
    ON p.IDCuenta = c.ID
    WHERE p.Autorizacion = 2 AND c.Estado = 1;
END$$
DELIMITER ;

CALL SP_cant_prestamos_pendientes_aprob();

/* Funcion listar cantidad prestamos activos por cliente */

DROP FUNCTION IF EXISTS listar_cant_prestamos_activos_por_cliente;

DELIMITER $$
CREATE FUNCTION listar_cant_prestamos_activos_por_cliente(
	I_IDCLIENTE VARCHAR(45)
)
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE CANTIDAD INT;
    
	SELECT COUNT(*) INTO CANTIDAD
	FROM prestamos 
    WHERE IDCliente = I_IDCLIENTE AND Autorizacion = 1;
    
    RETURN CANTIDAD;
END$$
DELIMITER ;

SELECT listar_cant_prestamos_activos_por_cliente(4); 

/* Corroborar si el usuario tiene prestamos activos antes de darlo de baja*/
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_baja_usuario$$

CREATE PROCEDURE sp_baja_usuario(
	IN id_usuario int
)
BEGIN
	DECLARE CantidadPrestamos INT DEFAULT 0;
    DECLARE FilasActualizadas INT;
    
	SET CantidadPrestamos = listar_cant_prestamos_activos_por_cliente(id_usuario);
    
	IF CantidadPrestamos = 0
		THEN 
		UPDATE usuario SET Estado = 0 WHERE id = id_usuario;
        SET FilasActualizadas = ROW_COUNT();
		SELECT FilasActualizadas AS FilasActualizadas;
	ELSE
		SELECT 0 as FilasActualizadas;
    END IF;
END$$
DELIMITER ;

SELECT listar_cant_prestamos_activos_por_cliente(4);
SELECT * FROM USUARIO; 
CALL sp_baja_usuario(6);
SELECT * FROM USUARIO; 
