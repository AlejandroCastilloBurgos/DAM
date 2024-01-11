<?php
session_start();

// Verifica si se han enviado datos por el formulario
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Recupera los valores del formulario
    $username = $_POST["username"];
    $password = $_POST["password"];

    // Verifica las credenciales (esto es solo un ejemplo, no es seguro)
    if ($username === "alex" && $password === "alex") {
        // Credenciales válidas, inicia sesión y redirige a home.php
        $_SESSION['username'] = $username;
        header("Location: home.php");
        exit();
    } else {
        // Credenciales incorrectas, muestra un mensaje de error
        $error_message = "Usuario o contraseña incorrectos";
    }
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h2>Iniciar Sesión</h2>
        <form action="login.php" method="post">
            <label for="username">Usuario:</label>
            <input type="text" id="username" name="username" required>
            <br>
            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" required>
            <br>
            <button type="submit" class="btn btn-primary">Acceder</button>
        </form>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

        
        <?php if (isset($error_message)) : ?>
            <p class="text-danger"><?php echo $error_message; ?></p>
        <?php endif; ?>
    </div>
</body>
</html>
