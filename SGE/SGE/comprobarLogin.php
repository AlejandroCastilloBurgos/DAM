<?php
    var_dump($_POST);
    require("db.php");
    $sql = "SELECT * FROM users";
    $result = $conn -> query ($sql);
    try{
    $stmt = $conn->prepare("SELECT id, username,pass password FROM users where username = :user and pass = :pw");
    $stmt->bindParam(':user',$_POST['user']);
    $stmt->bindParam(':pw',hash("sha256",$_POST['pw']));

    $stmt->execute();    
    $result = $stmt->fetchAll();

    
    print_r ($result);
    echo "el tamaño del array es " . count($result);
    
    if (count($result)>0){
        header("Location: index.php");
    }else
        header("Location: loginbd.php");
  } catch(PDOException $e) {
    echo "Error: " . $e->getMessage();
  }
  $conn = null;
 
?>