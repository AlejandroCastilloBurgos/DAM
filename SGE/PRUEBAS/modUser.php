<?php

var_dump($_POST);

require("db.php");

try{

    $stmt = $conn ->prepare("UPDATE users SET pass = ? WHERE username = ?");
    $pwOK = hash("sha256", $_POST['pw']);
    $stmt->execute([$pwOK, $_POST['user']]);

    $result = $stmt->fetchAll();

    print_r($result);
    echo "el tamaño del array es ". count($result);

} catch(PDOException $e) {
    echo "Error: " . $e->getMessage();

}
$conn = null; 
?>