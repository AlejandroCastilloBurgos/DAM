#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <math.h>

// Función para verificar si un número es primo
int esPrimo(long long num)
{
    if (num < 2)
    {
        return 0;
    }
    for (long long i = 2; i <= sqrt(num); i++)
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
        fprintf(stderr, "Uso: %s <numero1> <numero2>\n", argv[0]);
        return 1;
    }

    long long numero1 = atoll(argv[1]);
    long long numero2 = atoll(argv[2]);

    if (numero1 <= 0 || numero2 <= 0)
    {
        fprintf(stderr, "Ambos números deben ser enteros positivos.\n");
        return 1;
    }

    // Crear procesos
    for (int i = 0; i < 2; i++)
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
            long long numero = (i == 0) ? numero1 : numero2;
            if (esPrimo(numero))
            {
                printf("Proceso hijo %d: El número %lld es primo.\n", i + 1, numero);
                exit(0);
            }
            else
            {
                printf("Proceso hijo %d: El número %lld no es primo.\n", i + 1, numero);
                exit(1);
            }
        }
    }

    // Esperar a que ambos hijos terminen
    int primosEncontrados = 0;
    for (int i = 0; i < 2; i++)
    {
        int estado;
        wait(&estado);

        if (WIFEXITED(estado) && WEXITSTATUS(estado) == 0)
        {
            primosEncontrados++;
        }
    }

    // Mostrar el total de números primos encontrados
    printf("Total de números primos encontrados: %d\n", primosEncontrados);

    return 0;
}
