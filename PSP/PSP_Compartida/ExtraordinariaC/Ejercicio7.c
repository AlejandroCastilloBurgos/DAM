/*
Ejercicio 1: Procesamiento de Números Pares e Impares
Escribe un programa en C que acepte un número como parámetro de línea de comandos
 Este número indicará la cantidad de números aleatorios generados.
El programa creará otro proceso usando fork.
El proceso padre generará n números aleatorios y los enviará al proceso hijo a través de un pipe.
El proceso hijo procesará los números recibidos para contar cuántos son pares y cuántos impares.
El hijo escribirá el resultado en pantalla.
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
#define NUMERO_MAXIMO 1000
#define NUMERO_MINIMO 1

int main(int argc, char *argv[])
{
    // verificamos n de argumentos
    if (argc != 2)
    {
        printf("Introduce el parametro adecuado");
        return 1;
    }

    int CANTIDAD_NUMEROS_ALEATORIOS = atoi(argv[1]);

    if (CANTIDAD_NUMEROS_ALEATORIOS <= 0)
    {

        printf("Por favor, introduce un número entero positivo.\n");
        return 1;
    }

    int pipefd[2];
    pid_t pid;
    // Crear el pipe
    if (pipe(pipefd) == -1)
    {
        perror("pipe failed");
        exit(EXIT_FAILURE);
    }

    // Crear el proceso hijo
    pid = fork();

    if (pid < 0)
    {
        // Error al crear el proceso
        perror("fork failed");
        exit(EXIT_FAILURE);
    }
    else if (pid == 0)
    {
        // Código del proceso hijo
        int numero_recibido;
        int par;
        int impar;
        // read(pipefd[0], &numero_recibido, sizeof(int));
        for (int i = 0; i < CANTIDAD_NUMEROS_ALEATORIOS; i++)
        {
            read(pipefd[0], &numero_recibido, sizeof(int));
            if (numero_recibido % 2 == 0)
            {
                printf("Hijo (%d) encontró un número Par(%d) \n", getpid(), numero_recibido);
                par++;
            }
            else
            {
                printf("Hijo (%d) encontro un numero Impar(%d) \n", getpid(), numero_recibido);
                impar++;
            }
        }
        printf("Hijo (%d) encontró esta cantidad de pares: (%d) \n", getpid(), par);
        printf("Hijo (%d) encontró esta cantidad de impares: (%d) \n", getpid(), impar);
        close(pipefd[1]); // Cerrar el extremo de escritura del pipe
    }
    else
    {
        // Código del proceso padre
        close(pipefd[0]); // Cerrar el extremo de lectura del pipe

        srand(time(NULL)); // Inicializar el generador de números aleatorios

        for (int i = 0; i < CANTIDAD_NUMEROS_ALEATORIOS; i++)
        {
            int num_aleatorio = rand();
            write(pipefd[1], &num_aleatorio, sizeof(int));
            printf("Número aleatorio %d: %d\n", i + 1, num_aleatorio);
        }

        close(pipefd[1]); // Cerrar el extremo de escritura del pipe
        wait(NULL);       // Esperar a que el hijo termine

        printf("Proceso padre (%d) ha terminado.\n", getpid());
    }

    return 0;
}
