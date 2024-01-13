#include <stdio.h>

// Función para contar el número de vocales en una cadena
int contarVocales(const char *cadena) {
    int contador = 0;

    // Recorrer la cadena
    for (int i = 0; cadena[i] != '\0'; ++i) {
        // Verificar si el carácter actual es una vocal (mayúscula o minúscula)
        if (cadena[i] == 'a' || cadena[i] == 'e' || cadena[i] == 'i' || cadena[i] == 'o' || cadena[i] == 'u' ||
            cadena[i] == 'A' || cadena[i] == 'E' || cadena[i] == 'I' || cadena[i] == 'O' || cadena[i] == 'U') {
            ++contador;
        }
    }

    return contador;
}

int main() {
    // Declaración de variables
    char cadena[100]; // Tamaño arbitrario para la cadena

    // Solicitar al usuario que ingrese una cadena de texto
    printf("Ingrese una cadena de texto: ");
    scanf("%99[^\n]", cadena); // Leer hasta 99 caracteres, excluyendo el salto de línea

    // Llamar a la función contarVocales para contar el número de vocales en la cadena ingresada
    int numeroVocales = contarVocales(cadena);

    // Mostrar el resultado
    printf("El número de vocales en la cadena es: %d\n", numeroVocales);

    return 0; // Salir con éxito
}