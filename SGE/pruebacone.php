<?php

    //localhost o lugar donde se guarde la bbdd, nombre usuario, contraseña, nombre base
    $conn = mysqli_connect('localhost', 'root', '', 'tesla');
    //por algun motivo el pre ordena para que todo salga mucho mas ordenado, le da sentido a todo la verdad
    echo "<pre>";
    //vemos si la conexion es correcta
   // print_r( $conn);
    //escribimos la sentencia para hacer un insert, como si fuese sql, con esto haremos que entre directamente a la base de datos lo que escribamos aqui
   $insert = "insert into coches(modelo, potencia, autonomia, precio)  
              values('zafira', 555, 545, 9695)";

    //Aqui ponemos el return para que en caso de ser correcta la conexion, nos de 1, pero no haria falta
    $return = mysqli_query ( $conn, $insert);

    print_r(( $return));
    //muy importante cerrar la conexion para no comer memoria por la cara
    mysqli_close( $conn);

    //



