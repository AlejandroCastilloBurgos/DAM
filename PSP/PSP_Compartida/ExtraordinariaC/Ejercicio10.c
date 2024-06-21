/*Ejercicio 4: Sistema de Notificaciones con Señales Mejorado
Escribe un programa en C que implemente un sistema de notificaciones usando señales entre procesos. El programa tendrá un proceso padre que actúa como un monitor de eventos y varios procesos hijo que simulan eventos aleatorios.

Cada hijo enviará una señal al padre después de un tiempo aleatorio entre 1 y 10 segundos.
El padre debe manejar las señales SIGUSR1 y SIGUSR2, imprimiendo un mensaje descriptivo del evento detectado por cada hijo.
El padre espera a que se ejecuten todos los hijos.*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <time.h>

#define MAX_HIJOS 10

// Manejador de señales para SIGUSR1
void signal_handler(int signum)
{
    printf(" Padre: evento detectado sig1 por proceso hijo con PID: %d\n", getpid());
}
// Manejadro de señales para SIGUSR2
void signal_handler2(int signum)
{
    printf(" Padre: evento detectado sig2 por proceso hijo con PID: %d\n", getpid());
}

int main(int argc, char *argv[])
{
    if (argc != 2)
    {
        printf("Uso: %s <Cantidad_procesos_hijo>\n", argv[0]);
        return 1;
    }

    int cantidad_hijos = atoi(argv[1]);

    if (cantidad_hijos <= 0 || cantidad_hijos > MAX_HIJOS)
    {
        printf("La cantidad de procesos hijo debe ser un número entre 1 y %d.\n", MAX_HIJOS);
        return 1;
    }

    // Configurar el manejador de señales para SIGUSR1
    signal(SIGUSR1, signal_handler);
    signal(SIGUSR2, signal_handler2);

    // Crear los procesos hijo
    for (int i = 0; i < cantidad_hijos; i++)
    {
        pid_t pid = fork();
        // Semilla para la generación de números aleatorios
        srand(time(NULL) ^ (getpid() << 22));

        if (pid < 0)
        {
            perror("fork failed");
            return 1;
        }
        else if (pid == 0)
        {
            // Código del proceso hijo
            // Dormir un tiempo aleatorio entre 1 y 5 segundos
            int tiempo_aleatorio = rand() % 10 + 1;
            int n_señal = rand() % 5 + 1;
            sleep(tiempo_aleatorio);
            if (n_señal == 1)
            {
                // Enviar una señal SIGUSR1 al proceso padre
                printf("Hijo (%d) temperatura perfecta \n", getpid());

                kill(getpid(), SIGUSR1);
                exit(0); // Terminar la ejecución del hijo después de enviar la señal
            }
            else if (n_señal == 2)
            { // Enviar una señal SIGUSR1 al proceso padre
                printf("Hijo (%d) tiene frio \n", getpid());
                kill(getpid(), SIGUSR1);
                exit(0); // Terminar la ejecución del hijo después de enviar la señal
            }
            else if (n_señal == 3)
            { // Enviar una señal SIGUSR1 al proceso padre
                printf("Hijo (%d) tiene calor \n", getpid());
                kill(getpid(), SIGUSR1);
                exit(0); // Terminar la ejecución del hijo después de enviar la señal
            }
            else if (n_señal == 4)
            {
                // Enviar una señal SIGUSR1 al proceso padre
                printf("Hijo (%d) tiene muchisimo calor \n", getpid());
                kill(getpid(), SIGUSR2);
                exit(0); // Terminar la ejecución del hijo después de enviar la señal
            }
            else if (n_señal == 5)
            { // Enviar una señal SIGUSR1 al proceso padre
                printf("Hijo (%d) tiene muchisimo frio \n", getpid());
                kill(getpid(), SIGUSR2);
                exit(0); // Terminar la ejecución del hijo después de enviar la señal
            }
            else
            {
                // Enviar una señal SIGUSR1 al proceso padre
                printf("Hijo (%d) esta mongolo \n", getpid());
                kill(getpid(), SIGUSR2);
                exit(0); // Terminar la ejecución del hijo después de enviar la señal
            }
        }
    }

    // El proceso padre espera a que todos los hijos terminen
    for (int i = 0; i < cantidad_hijos; i++)
    {
        wait(NULL);
    }

    printf("Todos los procesos hijo han terminado.\n");

    return 0;
}
