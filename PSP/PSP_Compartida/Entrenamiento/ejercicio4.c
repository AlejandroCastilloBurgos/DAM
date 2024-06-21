#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>

void manejar_senal(int signo)
{
    printf("Proceso hijo con PID %d ha recibido la señal SIGTERM\n", getpid());
    exit(EXIT_SUCCESS);
}

int main(int argc, char *argv[])
{
    if (argc != 2)
    {
        fprintf(stderr, "Uso: %s <numero de hijos>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    int n = atoi(argv[1]);

    if (n <= 0)
    {
        fprintf(stderr, "El número de hijos debe ser un entero positivo\n");
        exit(EXIT_FAILURE);
    }

    // Configurar el manejador de señales
    signal(SIGTERM, manejar_senal);

    for (int i = 0; i < n; i++)
    {
        pid_t pid = fork();

        if (pid < 0)
        {
            perror("Error al crear el proceso hijo");
            exit(EXIT_FAILURE);
        }

        if (pid == 0)
        {
            // Este es el proceso hijo
            printf("Proceso hijo con PID %d creado\n", getpid());

            // Esperar a la señal SIGTERM para terminar
            while (1)
            {
                sleep(1);
            }
        }
    }

    // Esperar a que todos los hijos terminen
    for (int i = 0; i < n; i++)
    {
        wait(NULL);
    }

    printf("Proceso padre ha terminado\n");

    return 0;
}
