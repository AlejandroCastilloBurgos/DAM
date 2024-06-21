#include <stdio.h>

int main()
{
    int num1, num2;

    // Solicitar al usuario ingresar dos números
    printf("Ingrese el primer número entero: ");
    scanf("%d", &num1);

    printf("Ingrese el segundo número entero: ");
    scanf("%d", &num2);

    // Calcular la suma de los números
    int suma = num1 + num2;

    // Mostrar la suma en pantalla
    printf("La suma de %d y %d es: %d\n", num1, num2, suma);

    return 0;
}
