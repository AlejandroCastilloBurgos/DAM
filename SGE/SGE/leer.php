<?php
   echo "<pre>";
//manejo de mysql con php, creacion de conexion
//para hacer lectura de la tabla
$nombreconexion = mysqli_connect( 'localhost', 'root', '', 'tesla');
$consulta = "select * from coches";
$respuestaconsulta = mysqli_query( $nombreconexion, $consulta);
//rows sirve para que nois devuleva las cosas en un array, (si no especificamos array lo devuelve como objeto) donde pone MYSQLI_NUM, PUEDE PONERSE MYSQLI_BOTH)
$rows = mysqli_fetch_array($respuestaconsulta, MYSQLI_ASSOC);
//Si hacemos un print normal del rows nos dara solo la 1 consulta por lo que hayq ue hacer un bucle 
do {
	$datos[] = $rows;
}while ( $rows = mysqli_fetch_array ($respuestaconsulta, MYSQLI_ASSOC));
print_r ($datos);

mysqli_close($nombreconexion);
