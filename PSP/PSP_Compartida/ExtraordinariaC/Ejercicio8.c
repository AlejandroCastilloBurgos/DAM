/*Ejercicio 2: Generación de Números Fibonacci
Escribe un programa que reciba un número como parámetro de línea de comandos. Este número indicará cuántos procesos hijo deben crearse.

Cada hijo calculará un número en la secuencia de Fibonacci, desde el 0 hasta el número total de procesos hijo - 1.
Cada hijo escribirá su número de Fibonacci y su PID.
El padre esperará a que todos los hijos terminen.*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

// Función para calcular el número de Fibonacci
int fibonacci(int n)
{
    if (n <= 1)
        return n;
    return fibonacci(n - 1) + fibonacci(n - 2);
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
            // Cada hijo calcula su número de Fibonacci basado en su ID
            int numero_fibonacci = fibonacci(i);
            printf("Soy el hijo (%d) mi numero de la sucesion de Fibonacci es (%d)\n", getpid(), numero_fibonacci);
            exit(0); // Termina el proceso hijo después de imprimir su número de Fibonacci
        }
    }

    // El proceso padre espera a que todos los hijos terminen
    for (int i = 0; i < CANTIDAD_PROCESOS_A_CREAR; i++)
    {
        wait(NULL);
    }

    printf("Todos los procesos han terminado\n");

    return 0;
}

/*Codigo que lo saca ordenado GPT

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>

#define COMIENZO 0
#define UNO 1

// Función para calcular el número de Fibonacci
int fibonacci(int n)
{
    if (n <= 1)
        return n;
    return fibonacci(n - 1) + fibonacci(n - 2);
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

    // Arreglo para almacenar los PIDs de los hijos
    pid_t pid[CANTIDAD_PROCESOS_A_CREAR];

    // Crear los procesos hijo
    for (int i = 0; i < CANTIDAD_PROCESOS_A_CREAR; i++)
    {
        pid[i] = fork();

        if (pid[i] < 0)
        {
            perror("fork failed");
            exit(EXIT_FAILURE);
        }
        else if (pid[i] == 0)
        {
            // Cada hijo calcula su número de Fibonacci basado en su ID
            int numero_fibonacci = fibonacci(i);
            sleep(1);
            printf("Soy el hijo (%d) mi numero de la sucesion de Fibonacci es (%d)\n", getpid(), numero_fibonacci);
            exit(0); // Termina el proceso hijo después de imprimir su número de Fibonacci
        }
        else
        {
            // El proceso padre espera a que este hijo termine antes de crear el siguiente
            waitpid(pid[i], NULL, 0);
        }
    }

    printf("Todos los procesos han terminado\n");

    return 0;
}
*/
