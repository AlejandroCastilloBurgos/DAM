<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/sge/js/main.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <form action="comprobarLogin.php" method="post"> 
            <div class="mb-3">
                <label for="user" class="form-label">Usuario</label>
                <input type="text" name="user" id="user" class="form-control">
                <div id="nombre" class="form-text text-danger"></div>
            </div>
            <div class="mb-3">
                <label for="pw" class="form-label">Contraseña</label>
                <input type="password" name="pw" id="pw" class="form-control">
                <div id="contrasena" class="form-text text-danger"></div>
            </div>
            <button type="submit" id="Login" class="btn btn-success">Iniciar Sesión</button>
            <a href="crearUser.php" class="ms-2">Regístrate</a>
        </form>
    </div>
</body>
</html>
