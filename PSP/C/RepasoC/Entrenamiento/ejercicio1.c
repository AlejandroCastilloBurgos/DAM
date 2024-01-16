#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>

#define NUM_NUMEROS 3

void ordenar_y_escribir(int numeros[])
{
    // Ordenar los números
    for (int i = 0; i < NUM_NUMEROS - 1; i++)
    {
        for (int j = 0; j < NUM_NUMEROS - i - 1; j++)
        {
            if (numeros[j] > numeros[j + 1])
            {
                // Intercambiar los números si no están en orden ascendente
                int temp = numeros[j];
                numeros[j] = numeros[j + 1];
                numeros[j + 1] = temp;
            }
        }
    }

    // Escribir los números ordenados en el archivo salida.txt
    FILE *archivo = fopen("salida.txt", "w");
    if (archivo == NULL)
    {
        perror("Error al abrir el archivo de salida");
        exit(EXIT_FAILURE);
    }

    fprintf(archivo, "Números ordenados: %d, %d, %d\n", numeros[0], numeros[1], numeros[2]);
    fclose(archivo);
}

int main()
{
    pid_t pid;
    int pipe_fd[2];

    // Crear un pipe
    if (pipe(pipe_fd) == -1)
    {
        perror("Error al crear el pipe");
        exit(EXIT_FAILURE);
    }

    // Crear un proceso hijo
    pid = fork();

    if (pid < 0)
    {
        perror("Error al crear el proceso hijo");
        exit(EXIT_FAILURE);
    }

    if (pid == 0)
    {
        // Este es el proceso hijo
        close(pipe_fd[1]); // Cerrar el extremo de escritura del pipe en el hijo

        // Leer los números del pipe
        int numeros[NUM_NUMEROS];
        read(pipe_fd[0], numeros, sizeof(numeros));

        // Ordenar y escribir los números en salida.txt
        ordenar_y_escribir(numeros);

        close(pipe_fd[0]); // Cerrar el extremo de lectura del pipe en el hijo
    }
    else
    {
        // Este es el proceso padre
        close(pipe_fd[0]); // Cerrar el extremo de lectura del pipe en el padre

        // Generar tres números aleatorios
        srand(time(NULL));
        int numeros[NUM_NUMEROS];
        for (int i = 0; i < NUM_NUMEROS; i++)
        {
            numeros[i] = rand() % 100; // Números aleatorios entre 0 y 99
        }

        // Escribir los números en el pipe para que el hijo los lea
        write(pipe_fd[1], numeros, sizeof(numeros));

        close(pipe_fd[1]); // Cerrar el extremo de escritura del pipe en el padre

        // Esperar a que el proceso hijo termine
        wait(NULL);

        printf("Proceso padre ha terminado\n");
    }

    return 0;
}
