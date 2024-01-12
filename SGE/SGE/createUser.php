<?php
    
    require("db.php");
  
try{

    $stmt = $conn->prepare("INSERT INTO users (username,pass) VALUES (? ,?)");
    $pwOk = hash("sha256",$_POST['pass']);  
    $stmt->execute([$_POST['user'],$pwOk]);  
    
    $rowCount = $stmt->rowCount();  
    
    
    echo "el tamaño del array es " .  $rowCount;
    
    if ( $rowCount>0){
        header("Location: loginbd.php");
    }else{
        header("Location: crearUser.php");
    }
  } catch(PDOException $e) {
    echo "Error: " . $e->getMessage();
  }
  $conn = null;
 
?>