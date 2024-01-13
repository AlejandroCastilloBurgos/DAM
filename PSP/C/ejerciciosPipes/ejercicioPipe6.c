#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    int pipe_fd[2];

    // Crear un tubo para la comunicación entre el padre y el hijo
    if (pipe(pipe_fd) == -1) {
        perror("Error al crear el tubo");
        exit(EXIT_FAILURE);
    }

    // Crear un proceso hijo
    pid_t pid = fork();

    if (pid < 0) {
        perror("Error al crear el hijo");
        exit(EXIT_FAILURE);
    } else if (pid == 0) {
        // Código ejecutado por el proceso hijo

        // Cerrar el extremo de escritura del tubo en el proceso hijo
        close(pipe_fd[1]);

        char operacion;
        int num1, num2, resultado;

        // Leer la operación y los números desde el tubo
        read(pipe_fd[0], &operacion, sizeof(operacion));
        read(pipe_fd[0], &num1, sizeof(num1));
        read(pipe_fd[0], &num2, sizeof(num2));

        // Realizar la operación
        if (operacion == '+') {
            resultado = num1 + num2;
        } else if (operacion == '-') {
            resultado = num1 - num2;
        } else {
            fprintf(stderr, "Operación no válida: %c\n", operacion);
            exit(EXIT_FAILURE);
        }

        // Devolver el resultado en el estado del proceso hijo
        exit(resultado);
    } else {
        // Código ejecutado por el proceso padre

        // Cerrar el extremo de lectura del tubo en el proceso padre
        close(pipe_fd[0]);

        char operacion = '+';
        int num1 = 10, num2 = 5, resultado;

        // Escribir la operación y los números en el tubo
        write(pipe_fd[1], &operacion, sizeof(operacion));
        write(pipe_fd[1], &num1, sizeof(num1));
        write(pipe_fd[1], &num2, sizeof(num2));

        // Cerrar el extremo de escritura del tubo en el proceso padre
        close(pipe_fd[1]);

        // Esperar a que el hijo termine y recoger su estado
        wait(&resultado);

        printf("El resultado de la operación es: %d\n", WEXITSTATUS(resultado));
    }

    return 0; // Salir con éxito
}
