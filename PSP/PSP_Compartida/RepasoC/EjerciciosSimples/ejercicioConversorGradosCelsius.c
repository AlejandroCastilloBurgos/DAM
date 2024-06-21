#include <stdio.h>

int main()
{
    float celsius, fahrenheit;

    // Solicitar al usuario ingresar la temperatura en grados Celsius
    printf("Ingrese la temperatura en grados Celsius: ");
    scanf("%f", &celsius);

    // Aplicar la fórmula de conversión a grados Fahrenheit
    fahrenheit = (celsius * 9 / 5) + 32;

    // Mostrar el resultado en grados Fahrenheit
    printf("%.2f grados Celsius son equivalentes a %.2f grados Fahrenheit.\n", celsius, fahrenheit);

    return 0;
}
