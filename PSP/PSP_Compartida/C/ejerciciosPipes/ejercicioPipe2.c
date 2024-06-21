#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    // Crear tubos para la comunicación entre el padre y los hijos
    int pipe_par[2]; // Para enviar números pares al hijo 1
    int pipe_impar[2]; // Para enviar números impares al hijo 2

    if (pipe(pipe_par) == -1 || pipe(pipe_impar) == -1) {
        perror("Error al crear los tubos");
        exit(EXIT_FAILURE);
    }

    // Crear dos procesos hijos
    pid_t hijo1, hijo2;

    // Primer hijo
    hijo1 = fork();

    // Verificar si se creó el primer hijo correctamente
    if (hijo1 < 0) {
        perror("Error al crear el primer hijo");
        exit(EXIT_FAILURE);
    } else if (hijo1 == 0) {
        // Código ejecutado por el primer hijo

        // Cerrar el extremo de escritura del tubo de números pares
        close(pipe_par[1]);

        int numero;

        while (read(pipe_par[0], &numero, sizeof(numero)) > 0) {
            printf("Soy el hijo 1, he recibido un número par: %d\n", numero);
        }

        // Cerrar el extremo de lectura del tubo de números pares
        close(pipe_par[0]);

        exit(EXIT_SUCCESS);
    }

    // Segundo hijo
    hijo2 = fork();

    // Verificar si se creó el segundo hijo correctamente
    if (hijo2 < 0) {
        perror("Error al crear el segundo hijo");
        exit(EXIT_FAILURE);
    } else if (hijo2 == 0) {
        // Código ejecutado por el segundo hijo

        // Cerrar el extremo de escritura del tubo de números impares
        close(pipe_impar[1]);

        int numero;

        while (read(pipe_impar[0], &numero, sizeof(numero)) > 0) {
            printf("Soy el hijo 2, he recibido un número impar: %d\n", numero);
        }

        // Cerrar el extremo de lectura del tubo de números impares
        close(pipe_impar[0]);

        exit(EXIT_SUCCESS);
    }

    // Código ejecutado por el proceso padre

    // Cerrar los extremos de lectura de los tubos en el proceso padre
    close(pipe_par[0]);
    close(pipe_impar[0]);

    // Generar 20 números aleatorios y enviarlos a los hijos
    for (int i = 0; i < 20; ++i) {
        int numero = rand() % 100; // Números aleatorios entre 0 y 99

        // Enviar números pares al primer hijo
        if (numero % 2 == 0) {
            write(pipe_par[1], &numero, sizeof(numero));
        }
        // Enviar números impares al segundo hijo
        else {
            write(pipe_impar[1], &numero, sizeof(numero));
        }
    }

    // Cerrar los extremos de escritura de los tubos en el proceso padre
    close(pipe_par[1]);
    close(pipe_impar[1]);

    // Esperar a que ambos hijos terminen antes de finalizar el proceso padre
    wait(NULL);
    wait(NULL);

    return 0; // Salir con éxito
}
