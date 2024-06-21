<?php
//esta linea la ponemos siempre porque formatea y hace que salga mas bonito
echo"<pre>";

$conn = mysqli_connect('localhost', 'root', '', 'tesla');

//para hacer una sentencia que haga update podemos hacer
$update = "update modelo set modelo = 'zafia2',
             potencia = 431, where potencia in (555)";

//para hacer una sentancia delete

$delete = "delete from modelo where modelo in ('zafia2')";
//esta movida del return lo hacemos para ver que funcione, siempre que devuelva 1 es que todo esta correcto
$return = mysqli_query ($conn, $delete);

print_r($return);

mysqli_close($conn);
