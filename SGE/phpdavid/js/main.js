window.onload = iniciar;

function iniciar() {
    var log = document.getElementById('Login');
    log.addEventListener('click', botonLogear);
    var registrar = document.getElementById('Registrar');
    log.addEventListener('click', comprobarEmail);
}

function botonLogear(event) {
    var contenedorNombre = document.getElementById("nombre");
    var contenedorContrasena = document.getElementById("contrasena");
    var user = document.getElementById('user').value;
    var pass = document.getElementById('pw').value;


    if (user == "") {
        contenedorNombre.innerText = "Introduce un username";
        event.preventDefault();
    }
    if (pass == "") {
        contenedorContrasena.innerText = "Introduce una contraseña";
        event.preventDefault();
    }
}

function comprobarPass(event) {
    let regexEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var email = document.getElementById('email').value;

    // Utilizar .test(email) para evaluar si el email cumple con la expresión regular
    if (regexEmail.test(email)) {
        // El email cumple con la expresión regular
        console.log("Email válido");
        // Aquí puedes seguir con la lógica para un email válido
    } else {
        // El email no cumple con la expresión regular
        console.log("Email no válido");
        event.preventDefault(); // Evitar que se ejecute la acción predeterminada
        // Aquí puedes mostrar un mensaje al usuario indicando que el email no es válido
    }
}

