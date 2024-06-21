/*
Desarrolla un programa en C que cree varios procesos hijo para ejecutar tareas de determinación de primalidad,
usando el estado de finalización para controlar el éxito o fallo de cada proceso hijo basado en si el número generado es primo o no.
*/ \
#include<stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>

#define NUMERO_MAXIMO 100
#define NUMERO_MINIMO 1

bool es_primo(int numero)
{
    if (numero <= 1)
    {
        return false;
    }
    for (int i = 2; i * i <= numero; i++)
    {
        if (numero % i == 0)
        {
            return false;
        }
    }
    return true;
}

int main(int argc, char *argv[])
{
    if (argc != 2)
    {
        printf("Uso: %s <Cantidad_procesos_a_crear>\n", argv[0]);
        return 1;
    }

    int CANTIDAD_PROCESOS_A_CREAR = atoi(argv[1]);
    if (CANTIDAD_PROCESOS_A_CREAR <= 0)
    {
        printf("Por favor, introduce un número entero positivo.\n");
        return 1;
    }

    int cantidad_primos = 0;
    int cantidad_no_primos = 0;

    for (int i = 0; i < CANTIDAD_PROCESOS_A_CREAR; i++)
    {
        pid_t pid = fork();

        if (pid < 0)
        {
            perror("fork failed");
            exit(EXIT_FAILURE);
        }
        else if (pid == 0)
        {
            srand(time(NULL) ^ (getpid() << 22)); // Inicializar el generador de números aleatorios con una semilla única
            int num_aleatorio = rand() % NUMERO_MAXIMO + NUMERO_MINIMO;

            // printf("Proceso hijo (%d) número aleatorio: %d\n", getpid(), num_aleatorio);

            if (es_primo(num_aleatorio))
            {
                printf("Proceso hijo (%d) número primo: %d\n", getpid(), num_aleatorio);
                exit(1);
            }
            else
            {
                printf("Proceso hijo (%d) número no primo: %d\n", getpid(), num_aleatorio);
                exit(0);
            }
        }
    }

    // El proceso padre espera a que todos los hijos terminen y verifica su estado de salida
    for (int i = 0; i < CANTIDAD_PROCESOS_A_CREAR; i++)
    {
        int status;
        pid_t pid = wait(&status);
        if (pid == -1)
        {
            perror("wait failed");
            exit(EXIT_FAILURE);
        }
        if (WIFEXITED(status))
        {
            int exit_status = WEXITSTATUS(status);
            if (exit_status == 1)
            {
                cantidad_primos++;
            }
            else
            {
                cantidad_no_primos++;
            }
        }
    }

    printf("Cantidad de hijos que NO encontraron primo: %d\n", cantidad_no_primos);
    printf("Cantidad de hijos que SI encontraron primo: %d\n", cantidad_primos);
    printf("Proceso padre (%d) ha terminado.\n", getpid());

    return 0;
}
