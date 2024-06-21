#include <stdio.h>

// Función para calcular el factorial de un número
unsigned long long calcularFactorial(int numero) {
    // Manejar caso especial: factorial de 0 es 1
    if (numero == 0) {
        return 1;
    }

    // Inicializar el resultado del factorial
    unsigned long long factorial = 1;

    // Calcular el factorial usando un bucle for
    for (int i = 1; i <= numero; ++i) {
        factorial *= i;
    }

    return factorial;
}

int main() {
    // Declaración de variables
    int numero;

    // Solicitar al usuario que ingrese un número entero no negativo
    printf("Ingrese un número entero no negativo: ");

    // Utilizar scanf para leer el número
    if (scanf("%d", &numero) != 1 || numero < 0) {
        // Manejar un error si el usuario no ingresa un número entero no negativo
        printf("Error: Ingrese un número entero no negativo válido.\n");
        return 1; // Salir con un código de error
    }

    // Llamar a la función calcularFactorial para calcular el factorial del número ingresado
    unsigned long long factorial = calcularFactorial(numero);

    // Mostrar el resultado del cálculo del factorial
    printf("El factorial de %d es: %llu\n", numero, factorial);

    return 0; // Salir con éxito
}
