#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    // Crear un proceso hijo
    pid_t pid = fork();

    // Verificar si se creó el proceso hijo correctamente
    if (pid < 0) {
        fprintf(stderr, "Error al crear el proceso hijo.\n");
        return 1; // Salir con un código de error
    } else if (pid == 0) {
        // Código ejecutado por el proceso hijo
        printf("Soy el proceso hijo\n");
        sleep(2); // Simular una tarea que tarda un tiempo en el proceso hijo
    } else {
        // Código ejecutado por el proceso padre
        // Esperar a que el proceso hijo termine antes de imprimir el mensaje final
        int estado;
        waitpid(pid, &estado, 0);
        printf("Soy el proceso padre. Proceso hijo terminado.\n");
    }

    return 0; // Salir con éxito
}
