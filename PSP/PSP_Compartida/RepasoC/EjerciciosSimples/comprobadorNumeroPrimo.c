#include <stdio.h>
#include <math.h>

int main()
{
    int numero, esPrimo = 1; // Suponemos que el número es primo inicialmente

    // Solicitar al usuario ingresar un número entero positivo
    printf("Ingrese un número entero positivo: ");
    scanf("%d", &numero);

    // Verificar si el número es menor o igual a 1
    if (numero <= 1)
    {
        printf("El número no es primo.\n");
        return 0;
    }

    // Verificar si el número es divisible por algún número entre 2 y la raíz cuadrada del número
    for (int i = 2; i <= sqrt(numero); i++)
    {
        if (numero % i == 0)
        {
            // Si es divisible, entonces no es un número primo
            esPrimo = 0;
            break;
        }
    }

    // Mostrar el resultado
    if (esPrimo)
    {
        printf("%d es un número primo.\n", numero);
    }
    else
    {
        printf("%d no es un número primo.\n", numero);
    }

    return 0;
}

// PARA COMPILAR TENIENDO SQRT gcc -o comprobadorNumeroPrimo comprobadorNumeroPrimo.c -lm