<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <form action="index.php" method="post">
    Combinación <input type="text" name="combinacion" id="cajaNombre">
    <input type="submit" value="Enviar">
    </form>
</body>
</html>







<?php

    /*para escribir en html también podemos usar print
    echo "Hola mundo!\n1";
    //variables no deben ser especificadas y empiezan con $_COOKIE

    $contador = 1;
    $nombre = "Daniel";
    $altura = 1.70;
    //con => hacemos parejas clave valor
    $dias = array("lunes"=>"laborable","martes","miercoles")
    
    $cadena = "perro"
    echo $nombre;
    //esto te saca el array
    var_dump($dias);
    echo $dias["lunes"];
    /*al ser lenguaje de bajo nivel no tiene objetos usamos funciones
     strlen strtoupper strtolower strcmp str para concatenar se usa
     el . o ,
    strlen($cadena);
    echo $cadena;    

    echo $cadena , " " , "snoopy";
    for($i=0;$i<4 $i++){
        echo $i . <br>;
    }
    */
    session_start();
    $combinacion = "pw";
    if(count($_SESSION)<5){  
        if(count($_POST)>0){
            $_SESSION["intento".count($_SESSION)]= $_POST["combinacion"];

            if (strcmp($combinacion,$_POST["combinacion"])==0){
            echo "Acertaste!";
            }else{
                echo "intentos restantes ".(5-count($_SESSION));
            }
        } 
    
    
    }else{
        echo "no quedan intentos";
    }
    var_dump ($_SESSION);
   
?>
