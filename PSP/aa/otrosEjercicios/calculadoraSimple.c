#include <stdio.h>

int main() {
    // Declaración de variables
    int num1, num2, suma;

    // Solicitar al usuario que ingrese el primer número
    printf("Ingrese el primer número entero: ");
    
    // Utilizar scanf para leer el primer número
    if (scanf("%d", &num1) != 1) {
        // Manejar un error si el usuario no ingresa un número
        printf("Error: Ingrese un número entero válido.\n");
        return 1; // Salir con un código de error
    }

    // Solicitar al usuario que ingrese el segundo número
    printf("Ingrese el segundo número entero: ");
    
    // Utilizar scanf para leer el segundo número
    if (scanf("%d", &num2) != 1) {
        // Manejar un error si el usuario no ingresa un número
        printf("Error: Ingrese un número entero válido.\n");
        return 1; // Salir con un código de error
    }

    // Calcular la suma de los dos números
    suma = num1 + num2;

    // Mostrar la suma en pantalla
    printf("La suma de %d y %d es: %d\n", num1, num2, suma);

    return 0; // Salir con éxito
}
