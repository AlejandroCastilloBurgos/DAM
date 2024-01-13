#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>

void manejarSenal(int senal) {
    // No es necesario realizar ninguna acción en la señal
    // Puedes personalizar esto según tus necesidades
    printf("Hijo %d recibió la señal %d y terminó.\n", getpid(), senal);
    exit(EXIT_SUCCESS);
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        fprintf(stderr, "Uso: %s <numero>\n", argv[0]);
        return 1;
    }

    int n = atoi(argv[1]);

    if (n <= 0) {
        fprintf(stderr, "El número debe ser positivo.\n");
        return 1;
    }

    // Instalar el manejador de señales
    signal(SIGTERM, manejarSenal);

    // Crear n hijos
    for (int i = 0; i < n; ++i) {
        pid_t pid = fork();

        if (pid < 0) {
            perror("Error al crear el hijo");
            return 1;
        } else if (pid == 0) {
            // Código ejecutado por el hijo
            printf("Hijo %d creado.\n", getpid());

            // Esperar la señal para terminar
            while (1) {
                sleep(1);
            }
        }
    }

    // Esperar un momento antes de enviar las señales
    sleep(2);

    // Enviar señales para matar a los hijos
    for (int i = 0; i < n; ++i) {
        kill(0, SIGTERM);
    }

    // Esperar a que todos los hijos terminen
    while (wait(NULL) > 0) {
        // Esperar a que todos los hijos terminen
    }

    printf("Todos los hijos han terminado.\n");

    return 0; // Salir con éxito
}
