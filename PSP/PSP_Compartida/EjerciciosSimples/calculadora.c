#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[])
{
    // Verificar que haya al menos 3 parámetros
    if (argc < 4)
    {
        printf("Error: Se necesitan al menos 3 parámetros.\n");
        return 1;
    }

    // Obtener la operación y los operandos
    char *operacion = argv[1];
    float operando1 = atof(argv[2]);
    float operando2 = atof(argv[3]);

    // Realizar la operación según el primer parámetro
    if (strcmp(operacion, "suma") == 0)
    {
        printf("%.2f + %.2f = %.2f\n", operando1, operando2, operando1 + operando2);
    }
    else if (strcmp(operacion, "resta") == 0)
    {
        printf("%.2f - %.2f = %.2f\n", operando1, operando2, operando1 - operando2);
    }
    else if (strcmp(operacion, "multiplicacion") == 0)
    {
        printf("%.2f * %.2f = %.2f\n", operando1, operando2, operando1 * operando2);
    }
    else if (strcmp(operacion, "division") == 0)
    {
        // Verificar que el segundo operador no sea cero
        if (operando2 == 0)
        {
            printf("Error: El segundo operador no puede ser 0 en la división.\n");
            return 1;
        }
        printf("%.2f / %.2f = %.2f\n", operando1, operando2, operando1 / operando2);
    }
    else
    {
        printf("Error: Operación no válida. Use suma, resta, multiplicacion o division.\n");
        return 1;
    }

    return 0;
}
