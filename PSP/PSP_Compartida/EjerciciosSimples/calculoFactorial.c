#include <stdio.h>

int main()
{
    int numero;
    long long factorial = 1; // Usamos un tipo de dato largo para manejar factoriales grandes

    // Solicitar al usuario ingresar un número entero no negativo
    printf("Ingrese un número entero no negativo: ");
    scanf("%d", &numero);

    // Verificar si el número es negativo
    if (numero < 0)
    {
        printf("El número ingresado es negativo. Por favor, ingrese un número no negativo.\n");
        return 1; // Terminar el programa con código de error
    }

    // Calcular el factorial utilizando un bucle for
    for (int i = 1; i <= numero; i++)
    {
        factorial *= i;
    }

    // Mostrar el resultado
    printf("El factorial de %d es %lld.\n", numero, factorial);

    return 0;
}
