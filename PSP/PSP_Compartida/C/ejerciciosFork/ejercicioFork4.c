#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    // Número de hijos a crear
    int numHijos = 3;

    // Bucle para crear varios procesos hijos
    for (int i = 0; i < numHijos; ++i) {
        pid_t pid = fork();

        // Verificar si se creó el proceso hijo correctamente
        if (pid < 0) {
            fprintf(stderr, "Error al crear el proceso hijo %d.\n", i + 1);
            return 1; // Salir con un código de error
        } else if (pid == 0) {
            // Código ejecutado por el proceso hijo
            printf("Soy el proceso hijo %d con PID %d\n", i + 1, getpid());
            sleep(2); // Simular una tarea que tarda un tiempo en el proceso hijo
            exit(0); // Salir del proceso hijo
        }
    }

    // Código ejecutado por el proceso padre
    // Esperar a que todos los procesos hijos terminen antes de imprimir el mensaje final
    for (int i = 0; i < numHijos; ++i) {
        int estado;
        wait(NULL);
        printf("Proceso hijo %d ha terminado.\n", i + 1);
    }

    printf("Soy el proceso padre. Todos los procesos hijos han terminado.\n");

    return 0; // Salir con éxito
}
