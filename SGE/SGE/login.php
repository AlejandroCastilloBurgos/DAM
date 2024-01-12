<!DOCTYPE html>

<html lang="en">

<head>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

    <form action="" method="post">
    User<input type="text" name="user" id="cajaUser">
    Password<input type="text" name="pass" id="cajaPassword">
    <input type="submit" value="Enviar">
    </form>
    
</body>
</html>
<?php

  
    session_start();
    $combinacion = "pw";
    if (count($_POST)!=0)
   { 
       if ($combinacion==$_POST["pass"]){
       header("Location:index.php");
       die();
        }else{
       echo "contraseña incorrecta";
    }
}
  
    
    
   
?>