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
            
            // El proceso hijo devuelve un valor de salida diferente
            exit(i + 1);
        }
    }

    // Código ejecutado por el proceso padre
    // Esperar a que todos los procesos hijos terminen y recoger sus valores de salida
    for (int i = 0; i < numHijos; ++i) {
        int estado;
        pid_t hijo = wait(&estado);

        if (WIFEXITED(estado)) {
            int valorSalida = WEXITSTATUS(estado);
            printf("Proceso hijo %d con PID %d ha terminado con valor de salida: %d\n", i + 1, hijo, valorSalida);
        }
    }

    printf("Soy el proceso padre. Todos los procesos hijos han terminado.\n");

    return 0; // Salir con éxito
}
