#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    // Crear un tubo para la comunicación entre el padre y el hijo
    int pipe_fd[2];
    if (pipe(pipe_fd) == -1) {
        perror("Error al crear el tubo");
        exit(EXIT_FAILURE);
    }

    // Crear un proceso hijo
    pid_t pid = fork();

    // Verificar si se creó el proceso hijo correctamente
    if (pid < 0) {
        perror("Error al crear el proceso hijo");
        exit(EXIT_FAILURE);
    } else if (pid == 0) {
        // Código ejecutado por el proceso hijo

        // Cerrar el extremo de escritura del tubo en el proceso hijo
        close(pipe_fd[1]);

        // Leer los números del tubo
        int numeros[3];
        read(pipe_fd[0], numeros, sizeof(numeros));

        // Ordenar los números
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2 - i; ++j) {
                if (numeros[j] > numeros[j + 1]) {
                    // Intercambiar los números si no están ordenados
                    int temp = numeros[j];
                    numeros[j] = numeros[j + 1];
                    numeros[j + 1] = temp;
                }
            }
        }

        // Abrir el archivo salida.txt para escritura
        FILE *archivo = fopen("salida.txt", "w");
        if (archivo == NULL) {
            perror("Error al abrir el archivo salida.txt");
            exit(EXIT_FAILURE);
        }

        // Escribir los números ordenados en el archivo
        fprintf(archivo, "%d %d %d\n", numeros[0], numeros[1], numeros[2]);

        // Cerrar el archivo
        fclose(archivo);

        // Cerrar el extremo de lectura del tubo en el proceso hijo
        close(pipe_fd[0]);

        exit(EXIT_SUCCESS);
    } else {
        // Código ejecutado por el proceso padre

        // Cerrar el extremo de lectura del tubo en el proceso padre
        close(pipe_fd[0]);

        // Generar tres números aleatorios
        int numeros[3];
        for (int i = 0; i < 3; ++i) {
            numeros[i] = rand() % 100; // Números aleatorios entre 0 y 99
        }

        // Escribir los números en el tubo
        write(pipe_fd[1], numeros, sizeof(numeros));

        // Cerrar el extremo de escritura del tubo en el proceso padre
        close(pipe_fd[1]);

        // Esperar a que el proceso hijo termine antes de finalizar el proceso padre
        wait(NULL);
    }

    return 0; // Salir con éxito
}
