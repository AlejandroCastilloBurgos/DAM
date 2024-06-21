#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

void proceso_hijo(int numero);
int generarNumero(int longitud)
{
    int rangoInicial = 1;
    int rangoFinal = 1;

    for (int i = 1; i < longitud; ++i)
    {
        rangoInicial *= 10;
        rangoFinal *= 10;
    }
    rangoFinal = rangoFinal * 10 - 1;

    return rand() % (rangoFinal - rangoInicial + 1) + rangoInicial;
}

int esPrimo(int numero2)
{
    if (numero2 < 2)
    {
        return 0; // No es primo
    }

    for (long long i = 2; i * i <= numero2; i++)
    {
        if (numero2 % i == 0)
        {
            return 0; // No es primo
        }
    }

    return 1; // Es primo
}

int main()
{
    // Número de procesos hijos a crear

    int num_hijos;
    printf("Ingrese el número de números: ");
    scanf("%d", &num_hijos);

    for (int i = 1; i <= num_hijos; ++i)
    {
        pid_t pid = fork();

        // Verificar si la creación del proceso fue exitosa
        if (pid < 0)
        {
            fprintf(stderr, "Error al crear el proceso hijo\n");
            return 1;
        }

        if (pid == 0)
        {

            // Este es el proceso hijo

            proceso_hijo(i);
            if (esPrimo)
            {
                printf("el primer primo es: %d");
            }
            exit(0);
        }
    }

    // Este es el proceso padre
    printf("Soy el proceso padre\n");

    // Esperar a que todos los hijos terminen
    for (int i = 0; i < num_hijos; ++i)
    {
        int status;
        wait(&status);

        if (WIFEXITED(status))
        {
            printf("El proceso hijo %d terminó con estado: %d\n", WEXITSTATUS(status), i + 1);
        }
        else
        {
            printf("El proceso hijo %d terminó de manera anormal\n", i + 1);
        }
    }

    printf("El proceso padre ha terminado\n");

    return 0;
}

void proceso_hijo(int numero)
{
    printf("Soy el proceso hijo %d\n", numero);
    // Realizar las acciones específicas del proceso hijo
    sleep(2);
    printf("El proceso hijo %d ha terminado\n", numero);
}
