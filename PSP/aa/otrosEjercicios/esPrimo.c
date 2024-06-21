#include <stdio.h>
#include <math.h>

// Función para verificar si un número es primo
int esPrimo(int numero) {
    // Caso especial: 0 y 1 no son primos
    if (numero <= 1) {
        return 0; // No es primo
    }

    // Verificar si el número es divisible por algún número entre 2 y la raíz cuadrada del número
    for (int i = 2; i <= sqrt(numero); i++) {
        if (numero % i == 0) {
            return 0; // No es primo
        }
    }

    return 1; // Es primo
}

int main() {
    // Declaración de variables
    int numero;

    // Solicitar al usuario que ingrese un número entero positivo
    printf("Ingrese un número entero positivo: ");

    // Utilizar scanf para leer el número
    if (scanf("%d", &numero) != 1 || numero < 0) {
        // Manejar un error si el usuario no ingresa un número entero positivo
        printf("Error: Ingrese un número entero positivo válido.\n");
        return 1; // Salir con un código de error
    }

    // Llamar a la función esPrimo para verificar si el número es primo
    if (esPrimo(numero)) {
        printf("%d es un número primo.\n", numero);
    } else {
        printf("%d no es un número primo.\n", numero);
    }

    return 0; // Salir con éxito
}
