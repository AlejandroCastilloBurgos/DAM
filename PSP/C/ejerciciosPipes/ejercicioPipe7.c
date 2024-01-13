#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    int pipe_envio[2];  // Para enviar la operación y los números al hijo
    int pipe_resultado[2];  // Para recibir el resultado del hijo

    // Crear el primer tubo para la comunicación entre el padre y el hijo
    if (pipe(pipe_envio) == -1) {
        perror("Error al crear el tubo de envío");
        exit(EXIT_FAILURE);
    }

    // Crear el segundo tubo para recibir el resultado del hijo
    if (pipe(pipe_resultado) == -1) {
        perror("Error al crear el tubo de resultado");
        exit(EXIT_FAILURE);
    }

    // Crear un proceso hijo
    pid_t pid = fork();

    if (pid < 0) {
        perror("Error al crear el hijo");
        exit(EXIT_FAILURE);
    } else if (pid == 0) {
        // Código ejecutado por el proceso hijo

        // Cerrar el extremo de escritura del tubo de envío en el proceso hijo
        close(pipe_envio[1]);

        // Cerrar el extremo de lectura del tubo de resultado en el proceso hijo
        close(pipe_resultado[0]);

        char operacion;
        int num1, num2, resultado;

        // Leer la operación y los números desde el tubo de envío
        read(pipe_envio[0], &operacion, sizeof(operacion));
        read(pipe_envio[0], &num1, sizeof(num1));
        read(pipe_envio[0], &num2, sizeof(num2));

        // Realizar la operación
        if (operacion == '+') {
            resultado = num1 + num2;
        } else if (operacion == '-') {
            resultado = num1 - num2;
        } else {
            fprintf(stderr, "Operación no válida: %c\n", operacion);
            exit(EXIT_FAILURE);
        }

        // Enviar el resultado al padre a través del tubo de resultado
        write(pipe_resultado[1], &resultado, sizeof(resultado));

        // Cerrar los extremos de los tubos en el proceso hijo
        close(pipe_envio[0]);
        close(pipe_resultado[1]);

        exit(EXIT_SUCCESS);
    } else {
        // Código ejecutado por el proceso padre

        // Cerrar el extremo de lectura del tubo de envío en el proceso padre
        close(pipe_envio[0]);

        // Cerrar el extremo de escritura del tubo de resultado en el proceso padre
        close(pipe_resultado[1]);

        char operacion = '+';
        int num1 = 10, num2 = 5, resultado;

        // Escribir la operación y los números en el tubo de envío
        write(pipe_envio[1], &operacion, sizeof(operacion));
        write(pipe_envio[1], &num1, sizeof(num1));
        write(pipe_envio[1], &num2, sizeof(num2));

        // Cerrar el extremo de escritura del tubo de envío en el proceso padre
        close(pipe_envio[1]);

        // Leer el resultado del hijo desde el tubo de resultado
        read(pipe_resultado[0], &resultado, sizeof(resultado));

        // Cerrar el extremo de lectura del tubo de resultado en el proceso padre
        close(pipe_resultado[0]);

        printf("El resultado de la operación es: %d\n", resultado);

        // Esperar a que el hijo termine
        wait(NULL);
    }

    return 0; // Salir con éxito
}
