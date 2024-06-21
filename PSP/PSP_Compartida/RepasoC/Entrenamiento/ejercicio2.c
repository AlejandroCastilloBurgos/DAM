#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>

#define NUM_HIJOS 2
#define NUM_NUMEROS 20

void imprimir_mensaje(int num, int id_hijo)
{
    printf("Soy el hijo %d, he recibido el número: %d\n", id_hijo, num);
}

int main()
{
    pid_t hijos[NUM_HIJOS];
    int pipe_fd[NUM_HIJOS][2];

    // Crear dos pipes
    for (int i = 0; i < NUM_HIJOS; i++)
    {
        if (pipe(pipe_fd[i]) == -1)
        {
            perror("Error al crear el pipe");
            exit(EXIT_FAILURE);
        }
    }

    // Crear dos procesos hijos
    for (int i = 0; i < NUM_HIJOS; i++)
    {
        hijos[i] = fork();

        if (hijos[i] < 0)
        {
            perror("Error al crear el proceso hijo");
            exit(EXIT_FAILURE);
        }

        if (hijos[i] == 0)
        {
            // Este es el proceso hijo
            close(pipe_fd[i][1]); // Cerrar el extremo de escritura del pipe en el hijo

            int num;
            while (read(pipe_fd[i][0], &num, sizeof(num)) > 0)
            {
                // Imprimir mensaje por cada número recibido
                imprimir_mensaje(num, i + 1);
            }

            close(pipe_fd[i][0]); // Cerrar el extremo de lectura del pipe en el hijo
            exit(EXIT_SUCCESS);
        }
    }

    // Este es el proceso padre
    for (int i = 0; i < NUM_HIJOS; i++)
    {
        close(pipe_fd[i][0]); // Cerrar el extremo de lectura del pipe en el padre
    }

    // Generar 20 números aleatorios y enviarlos a los hijos
    srand(time(NULL));
    for (int i = 0; i < NUM_NUMEROS; i++)
    {
        int num = rand() % 100; // Números aleatorios entre 0 y 99

        if (num % 2 == 0)
        {
            // Enviar números pares al primer hijo
            write(pipe_fd[0][1], &num, sizeof(num));
        }
        else
        {
            // Enviar números impares al segundo hijo
            write(pipe_fd[1][1], &num, sizeof(num));
        }
    }

    // Cerrar extremos de escritura de los pipes en el padre
    close(pipe_fd[0][1]);
    close(pipe_fd[1][1]);

    // Esperar a que ambos hijos terminen
    for (int i = 0; i < NUM_HIJOS; i++)
    {
        wait(NULL);
    }

    printf("Proceso padre ha terminado\n");

    return 0;
}
