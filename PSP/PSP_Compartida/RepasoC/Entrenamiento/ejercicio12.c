#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <math.h>

// Función para verificar si un número es primo
int esPrimo(int num)
{
    if (num < 2)
    {
        return 0;
    }
    for (int i = 2; i <= sqrt(num); i++)
    {
        if (num % i == 0)
        {
            return 0;
        }
    }
    return 1;
}

int main(int argc, char *argv[])
{
    if (argc != 3)
    {
        fprintf(stderr, "Uso: %s <longitud> <num_procesos>\n", argv[0]);
        return 1;
    }

    int longitud = atoi(argv[1]);
    int numProcesos = atoi(argv[2]);

    if (longitud <= 0 || numProcesos <= 0)
    {
        fprintf(stderr, "Ambos números deben ser enteros positivos.\n");
        return 1;
    }

    // Crear procesos
    for (int i = 0; i < numProcesos; i++)
    {
        pid_t pid = fork();

        if (pid < 0)
        {
            perror("Error al crear el proceso hijo");
            return 1;
        }

        if (pid == 0)
        {
            // Este es el proceso hijo
            printf("Proceso hijo %d: ", i + 1);

            for (int j = i + 1; j <= pow(10, longitud); j += numProcesos)
            {
                if (esPrimo(j))
                {
                    printf("%d ", j);
                }
            }

            printf("\n");

            // El hijo termina su ejecución
            exit(0);
        }
    }

    // Esperar a que todos los hijos terminen
    for (int i = 0; i < numProcesos; i++)
    {
        wait(NULL);
    }

    return 0;
}
