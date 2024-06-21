#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>
#include <time.h>

void manejar_senal(int signo)
{
    printf("Soy el proceso %d y he recibido la señal %d.\n", getpid(), signo);
}

int main()
{
    // Semilla para la generación de números aleatorios
    srand(time(NULL));

    // Número de procesos hijos
    int N = 5;

    // Arreglo para almacenar los PID de los hijos
    pid_t hijos[N];

    // Crear N procesos hijos
    for (int i = 0; i < N; i++)
    {
        pid_t pid = fork();

        if (pid == 0)
        {
            // Código del proceso hijo
            signal(SIGUSR1, manejar_senal);
            while (1)
            {
                // Esperar señales continuamente
                pause();
            }
        }
        else if (pid > 0)
        {
            // Código del proceso padre
            hijos[i] = pid; // Proceso padre envía señales aleatorias a los hijos
            for (int i = 0; i < 10; i++)
            {
                int hijo_indice = rand() % N;                                         // Índice aleatorio del hijo
                int signal_aleatoria = rand() % (SIGRTMAX - SIGRTMIN + 1) + SIGRTMIN; // Señal aleatoria

                printf("Padre envía la señal %d al hijo %d (PID: %d).\n", signal_aleatoria, hijo_indice, hijos[hijo_indice]);
                kill(hijos[hijo_indice], signal_aleatoria);

                // Esperar un poco antes de enviar la siguiente señal
                sleep(1);
            }
        }
        else
        {
            // Manejo de error en fork
            perror("Error en fork");
            exit(EXIT_FAILURE);
        }
    }

    // Esperar que los hijos terminen
    for (int i = 0; i < N; i++)
    {
        kill(hijos[i], SIGTERM);
        waitpid(hijos[i], NULL, 0);
    }

    return 0;
}
