/*Tenemos n procesos hijos creados con un fork el proceso padre enviara señales aleatorias a sus hijos el numero de señales lo recibe
el padre por parametro de linea de comandos, el padre almacena en un array los ids de sus hijosl el padre enviara cada señal a un hijo aleatorio,
a lo largo del juego el padre puede enviar varias señales a un mismo hijo, el hijo tiene un bucle infinito en el que no hace nada mas que sleep
el hijo al recibir la priemera señal USER1 escribe soy el dijo XXXX he sido elimniado, si se envia de nuevo al mismo hijo dice soy el hijo XXXX
he vuelto a ser eliminado, los hijos, al recibir la señal SIGINT escriben por patanlla si han sido eliniados y finalizan
En resumen, el padre envia n señales user1 y luego una sigint a cada hijo, el padre espera a que sus hijos terminen, los hijos escriben si han sido
eliminados*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <time.h>

void manejar_senal(int signo)
{
    // Imprimir un mensaje al recibir la señal
}

int main(int argc, char *argv[])
{
    if (argc != 2)
    {
        fprintf(stderr, "Uso: %s <numero_de_hijos>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    int num_hijos = atoi(argv[1]);

    if (num_hijos <= 0)
    {
        fprintf(stderr, "El número de hijos debe ser un entero positivo.\n");
        exit(EXIT_FAILURE);
    }

    int numseñales;
    printf("Ingrese el número de señales: ");
    scanf("%d", &numseñales);

    // Semilla para la generación de números aleatorios
    srand(time(NULL));

    // manejador
    signal(SIGINT, manejar_senal);

    printf("Creando %d procesos hijos...\n", num_hijos);

    pid_t *hijos = malloc(num_hijos * sizeof(pid_t)); // Arreglo para almacenar los PID de los hijos

    for (int i = 0; i < num_hijos; i++)
    {
        pid_t pid = fork();

        if (pid < 0)
        {
            perror("Error al crear el proceso hijo");
            exit(EXIT_FAILURE);
        }
        else if (pid == 0)
        {
            // Este es el código ejecutado por el proceso hijo
            signal(SIGINT, manejar_senal);
            printf("Proceso hijo %d con PID %d\n", i + 1, getpid());
            while (1)
            {
                // Esperar señales continuamente
                pause();
            }
        }
        // Código ejecutado por el proceso padre
        hijos[i] = pid; // Almacenar el PID del hijo en el arreglo
    }

    // Proceso padre envía señales aleatorias a los hijos
    for (int i = 0; i < numseñales; i++)
    {
        int hijo_indice = rand() % num_hijos;                                 // Índice aleatorio del hijo
        int signal_aleatoria = rand() % (SIGRTMAX - SIGRTMIN + 1) + SIGRTMIN; // Señal aleatoria

        printf("Padre envía la señal %d al hijo %d (PID: %d).\n", signal_aleatoria, hijo_indice, hijos[hijo_indice]);
        kill(hijos[hijo_indice], signal_aleatoria);

        // Esperar un poco antes de enviar la siguiente señal
        sleep(1);
    }

    // Esperar a que todos los hijos terminen
    for (int i = 0; i < num_hijos; i++)
    {
        kill(hijos[i], SIGTERM);
        waitpid(hijos[i], NULL, 0);
    }

    free(hijos); // Liberar la memoria asignada para el arreglo de PID de hijos

    printf("Todos los procesos hijos han terminado.\n");

    return 0;
}
