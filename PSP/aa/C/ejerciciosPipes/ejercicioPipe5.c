#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>

// Función para manejar la señal definida por el usuario
void manejarSenal(int senal) {
    printf("Recibido. Terminando el proceso hijo %d.\n", getpid());
    exit(EXIT_SUCCESS);
}

int main() {
    // Instalar el manejador de señales
    signal(SIGUSR1, manejarSenal);

    // Crear un proceso hijo
    pid_t pid = fork();

    if (pid < 0) {
        perror("Error al crear el hijo");
        return 1;
    } else if (pid == 0) {
        // Código ejecutado por el proceso hijo

        // Esperar la señal del padre
        while (1) {
            sleep(1);
        }
    } else {
        // Código ejecutado por el proceso padre

        // Esperar un momento antes de enviar la señal
        sleep(2);

        // Enviar la señal SIGUSR1 al proceso hijo
        kill(pid, SIGUSR1);

        // Esperar a que el hijo termine
        wait(NULL);

        printf("Proceso padre ha terminado.\n");
    }

    return 0; // Salir con éxito
}
