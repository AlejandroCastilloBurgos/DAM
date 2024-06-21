#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>

void manejar_senal(int signo)
{
    printf("Recibido y terminando el proceso hijo con PID %d\n", getpid());
    exit(EXIT_SUCCESS);
}

int main()
{
    pid_t pid;

    // Configurar el manejador de señales para el hijo
    signal(SIGUSR1, manejar_senal);

    // Crear un nuevo proceso
    pid = fork();

    // Verificar si la creación del proceso fue exitosa
    if (pid < 0)
    {
        perror("Error al crear el proceso hijo");
        exit(EXIT_FAILURE);
    }

    if (pid == 0)
    {
        // Este es el proceso hijo
        printf("Proceso hijo con PID %d creado\n", getpid());

        // Esperar a la señal SIGUSR1 para terminar
        while (1)
        {
            sleep(1);
        }
    }
    else
    {
        // Este es el proceso padre
        printf("Proceso padre con PID %d\n", getpid());

        // Esperar un momento antes de enviar la señal al hijo
        sleep(2);

        // Enviar la señal SIGUSR1 al hijo
        kill(pid, SIGUSR1);

        // Esperar a que el hijo termine
        wait(NULL);

        printf("Proceso padre ha terminado\n");
    }

    return 0;
}
