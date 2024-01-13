#include <stdio.h>
#include <ctype.h>

// Función para cifrar un carácter usando el cifrado César
char cifrarCesar(char caracter, int desplazamiento) {
    if (isalpha(caracter)) {
        char base = isupper(caracter) ? 'A' : 'a';
        return (caracter - base + desplazamiento) % 26 + base;
    } else {
        return caracter; // Mantener caracteres no alfabéticos sin cambios
    }
}

// Función para cifrar una cadena usando el cifrado César
void cifrarCadena(char *cadena, int desplazamiento) {
    for (int i = 0; cadena[i] != '\0'; ++i) {
        cadena[i] = cifrarCesar(cadena[i], desplazamiento);
    }
}

int main() {
    // Declaración de variables
    char cadena[100]; // Tamaño arbitrario para la cadena
    int desplazamiento;

    // Solicitar al usuario que ingrese una cadena de texto
    printf("Ingrese una cadena de texto: ");
    scanf("%99[^\n]", cadena); // Leer hasta 99 caracteres, excluyendo el salto de línea

    // Solicitar al usuario que ingrese un valor de desplazamiento
    printf("Ingrese un valor de desplazamiento: ");
    scanf("%d", &desplazamiento);

    // Llamar a la función cifrarCadena para cifrar la cadena ingresada
    cifrarCadena(cadena, desplazamiento);

    // Mostrar el resultado del cifrado
    printf("Cadena cifrada: %s\n", cadena);

    return 0; // Salir con éxito
}
