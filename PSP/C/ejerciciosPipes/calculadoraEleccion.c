#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]) {
    // Verificar si se proporcionan al menos 3 parámetros
    if (argc < 4) {
        printf("Error: Se requieren al menos 3 parámetros.\n");
        return 1; // Salir con un código de error
    }

    // Obtener la operación y los operandos desde los parámetros
    char *operacion = argv[1];
    float operando1 = atof(argv[2]);
    float operando2 = atof(argv[3]);

    // Realizar la operación seleccionada
    if (strcmp(operacion, "suma") == 0) {
        printf("%.2f\n", operando1 + operando2);
    } else if (strcmp(operacion, "resta") == 0) {
        printf("%.2f\n", operando1 - operando2);
    } else if (strcmp(operacion, "multiplicacion") == 0) {
        printf("%.2f\n", operando1 * operando2);
    } else if (strcmp(operacion, "division") == 0) {
        // Verificar que el segundo operando no sea cero
        if (operando2 != 0) {
            printf("%.2f\n", operando1 / operando2);
        } else {
            printf("Error: No se puede dividir por cero.\n");
            return 1; // Salir con un código de error
        }
    } else {
        printf("Error: Operación no válida. Las operaciones válidas son suma, resta, multiplicacion, y division.\n");
        return 1; // Salir con un código de error
    }

    return 0; // Salir con éxito
}
