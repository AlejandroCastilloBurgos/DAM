#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

#define READ_END 0
#define WRITE_END 1

int main()
{
    // Crear pipes
    int pipe_to_child[2];
    int pipe_from_child[2];

    if (pipe(pipe_to_child) == -1 || pipe(pipe_from_child) == -1)
    {
        perror("Error al crear los pipes");
        exit(EXIT_FAILURE);
    }

    // Crear un proceso hijo
    pid_t pid = fork();

    if (pid == -1)
    {
        perror("Error al crear el proceso hijo");
        exit(EXIT_FAILURE);
    }

    if (pid == 0)
    {
        // Este es el proceso hijo

        // Cerrar el extremo de escritura del pipe_to_child, ya que el hijo solo va a leer
        close(pipe_to_child[WRITE_END]);
        // Cerrar el extremo de lectura del pipe_from_child, ya que el hijo solo va a escribir
        close(pipe_from_child[READ_END]);

        char operacion;
        int num1, num2, resultado;

        // Leer los datos del pipe_to_child
        read(pipe_to_child[READ_END], &operacion, sizeof(char));
        read(pipe_to_child[READ_END], &num1, sizeof(int));
        read(pipe_to_child[READ_END], &num2, sizeof(int));

        // Realizar la operación
        if (operacion == '+')
        {
            resultado = num1 + num2;
        }
        else if (operacion == '-')
        {
            resultado = num1 - num2;
        }
        else
        {
            fprintf(stderr, "Operación no válida\n");
            exit(EXIT_FAILURE);
        }

        // Enviar el resultado al proceso padre a través del pipe_from_child
        write(pipe_from_child[WRITE_END], &resultado, sizeof(int));

        // Cerrar los extremos de los pipes después de enviar los datos
        close(pipe_to_child[READ_END]);
        close(pipe_from_child[WRITE_END]);

        // El hijo puede terminar después de enviar el resultado
        exit(EXIT_SUCCESS);
    }
    else
    {
        // Este es el proceso padre

        // Cerrar el extremo de lectura del pipe_to_child, ya que el padre solo va a escribir
        close(pipe_to_child[READ_END]);
        // Cerrar el extremo de escritura del pipe_from_child, ya que el padre solo va a leer
        close(pipe_from_child[WRITE_END]);

        char operacion = '+';
        int num1 = 10, num2 = 5;

        // Enviar datos al proceso hijo a través del pipe_to_child
        write(pipe_to_child[WRITE_END], &operacion, sizeof(char));
        write(pipe_to_child[WRITE_END], &num1, sizeof(int));
        write(pipe_to_child[WRITE_END], &num2, sizeof(int));

        // Cerrar el extremo de escritura del pipe_to_child después de enviar los datos
        close(pipe_to_child[WRITE_END]);

        // Leer el resultado del hijo a través del pipe_from_child
        int resultado;
        read(pipe_from_child[READ_END], &resultado, sizeof(int));

        // Cerrar el extremo de lectura del pipe_from_child después de leer el resultado
        close(pipe_from_child[READ_END]);

        // Imprimir el resultado
        printf("El resultado de la operación es: %d\n", resultado);
    }

    return 0;
}
