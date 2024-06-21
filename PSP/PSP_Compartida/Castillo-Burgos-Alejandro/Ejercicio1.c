#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdbool.h>
#include <time.h>
#define NUMERO_MAXIMO 25
#define NUMERO_MINIMO 0
#define CANTIDAD_ALEATORIOS 100

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
    // argumentos
    if (argc != 1)
    {
        printf("error");
        return 1;
    }

    // int CANTIDAD_NUMEROS_ALEATORIOS = CANTIDAD_ALEATORIOS;

    int pipefd[2];
    // pid_t pid;

    // crear el pipe
    if (pipe(pipefd) == -1)
    {
        perror("pipe failed");
        exit(EXIT_FAILURE);
    }

    // crear el proceso hijo
    pid_t pid = fork();

    if (pid == 0)
    {
        // codigo proceso hijo

        close(pipefd[1]); // cerrar extremo escritura
        int num;
        long long suma = 0;
        int menor, mayor;
        read(pipefd[0], &num, sizeof(int));
        menor = mayor = num;
        suma += num;
        // para sumar mediciones
        for (int i = 1; i < CANTIDAD_ALEATORIOS; i++)
        {
            read(pipefd[0], &num, sizeof(int));
            if (num < menor)
                menor = num;
            suma += num;
            if (num > mayor)
                mayor = num;
        }

        double media = (double)suma;
        printf("La suma de las mediciones es: %.2f\n", media);
        // ahora primalidad
        if (es_primo(media))
        {
            printf("Proceso hijo: número primo: %.2f\n", media);
            exit(1);
        }
        else
        {
            printf("Proceso hijo:  número no primo: %.2f\n", media);
            exit(0);
        }
        close(pipefd[0]); // cerrar el extremo de lectura del pipe
    }
    else
    {
        // codigo papa
        close(pipefd[0]);  // cerrar el extremo lectura del pipe
        srand(time(NULL)); // inicia generador aleatorios
        for (int i = 0; i < CANTIDAD_ALEATORIOS; i++)
        {
            int num_aleatorio = rand() % NUMERO_MAXIMO + NUMERO_MINIMO;
            write(pipefd[1], &num_aleatorio, sizeof(int));
            printf("Número aleatorio %d: %d\n", i + 1, num_aleatorio);
        }
        close(pipefd[1]); // cerrar el extremo de escritura del pipe
        // El proceso padre espera a que todos los hijos terminen y verifica su estado de salida
        int status;
        pid_t pid = wait(&status);
        if (pid == -1)
        {
            printf("Errorcito");
            exit(EXIT_FAILURE);
        }
        if (WIFEXITED(status))
        {
            int exit_status = WEXITSTATUS(status);
            if (exit_status == 1)
            {
                printf("Padre: El planeta es habitable \n");
            }
            else
            {
                printf("Padre: El planeta no es habitable \n");
            }
        }

        printf("Proceso padre (%d) ha terminado.\n", getpid());
    }

    return 0;
}