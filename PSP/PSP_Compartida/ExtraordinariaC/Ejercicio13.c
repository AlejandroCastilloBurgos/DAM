/*Ejercicio 8: Simulación de un Sistema de Mensajes
Desarrolla un programa en C que implemente un sistema simple de intercambio de mensajes entre procesos usando señales.

El programa debe aceptar un número M como parámetro de línea de comandos, indicando la cantidad de procesos hijo a crear.
Cada hijo debe enviar una señal al padre cada cierto intervalo de tiempo (por ejemplo, cada 2 segundos).
El padre debe imprimir un mensaje que indique el PID del hijo que envió la señal cada vez que reciba una señal.
El programa debe manejar señales SIGINT para permitir la terminación limpia del programa al presionar Ctrl+C.
Estos ejercicios cubren una variedad de conceptos fundamentales en programación en C, como la creación y manejo de procesos,
comunicación entre procesos, manejo de señales, y uso de tuberías para la comunicación*/

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
void sigint_handler(int signum)
{
    printf("\nSe recibió la señal SIGINT (Ctrl+C)\n");
    // Aquí puedes realizar cualquier acción necesaria antes de terminar el programa
    exit(signum);
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
    signal(SIGINT, sigint_handler);

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
            while (1)
            {
                // Dormir un tiempo aleatorio entre 1 y 5 segundos
                int tiempo_aleatorio = 2;

                sleep(tiempo_aleatorio);

                // Enviar una señal SIGUSR1 al proceso padre
                printf("Hijo (%d) temperatura perfecta \n", getpid());

                kill(getpid(), SIGUSR1);
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
