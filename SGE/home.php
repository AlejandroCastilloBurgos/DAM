<?php
session_start();

// Verificar si el usuario está autenticado
if (!isset($_SESSION['username'])) {
    exit();
}

// Obtener el nombre de usuario de la sesión
$username = isset($_SESSION['username']) ? $_SESSION['username'] : '';
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenido</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" integrity="sha384-w8i8xLqfVv7UblJrFwoGq0o4z3+JFcV/l5QPKOqH2rME79c7HAqDTOb/l5SL5diG" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h2>Bienvenido, <?php echo $username; ?>!</h2>
        <p>¡Hola <?php echo $username; ?>, bienvenido a tu página de inicio!</p>
        <a href="logout.php" class="btn btn-danger">Cerrar sesión</a>
    </div>
</body>
</html>
