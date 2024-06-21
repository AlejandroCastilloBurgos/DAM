#include <stdio.h>
#include <ctype.h>
#include <string.h>

// Función para verificar si una cadena es un palíndromo
int esPalindromo(const char *cadena) {
    // Declaración de variables
    int longitud = strlen(cadena);
    int i = 0;
    int j = longitud - 1;

    // Recorrer la cadena desde los extremos hacia el centro
    while (i < j) {
        // Ignorar espacios y signos de puntuación
        while (i < longitud && !isalpha(cadena[i])) {
            ++i;
        }
        while (j >= 0 && !isalpha(cadena[j])) {
            --j;
        }

        // Comparar caracteres ignorando mayúsculas/minúsculas
        if (i < longitud && j >= 0 && tolower(cadena[i]) != tolower(cadena[j])) {
            return 0; // No es un palíndromo
        }

        ++i;
        --j;
    }

    return 1; // Es un palíndromo
}

int main() {
    // Declaración de variables
    char cadena[100]; // Tamaño arbitrario para la cadena

    // Solicitar al usuario que ingrese una palabra o frase
    printf("Ingrese una palabra o frase: ");
    scanf("%99[^\n]", cadena); // Leer hasta 99 caracteres, excluyendo el salto de línea

    // Llamar a la función esPalindromo para verificar si es un palíndromo
    if (esPalindromo(cadena)) {
        printf("Es un palíndromo.\n");
    } else {
        printf("No es un palíndromo.\n");
    }

    return 0; // Salir con éxito
}
