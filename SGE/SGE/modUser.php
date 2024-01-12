<?php
    
    require("db.php");
  
    try{

    $stmt = $conn->prepare("UPDATE users  SET pass=?  where username = ? and pass = ?");
    $pwOk = hash("sha256",$_POST['passNew']);  
    $stmt->execute([$pwOk,$_POST['user'],$_POST['passOld']]);    

      
    $result = $stmt->fetchAll();

    
    print_r ($result);
 
    
    if (count($result)>0){
        header("Location: index.php");
    }else
        header("Location: cambiarPass.php");
  } catch(PDOException $e) {
    echo "Error: " . $e->getMessage();
  }
  $conn = null;
 
?>