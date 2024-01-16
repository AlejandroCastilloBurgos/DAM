#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#define BUFFER_SIZE 1024 // Definimos una constante para el tamaño del buffer

int main(int argc, char *argv[])
{
    // Comprobamos si se ha pasado un comando como argumento
    if (argc < 2)
    {
        fprintf(stderr, "Uso: %s <comando>\n", argv[0]);
        exit(1);
    }

    int pipefd[2];
    // Creamos un pipe (canal de comunicación unidireccional)
    // pipefd[0] es el extremo de lectura, pipefd[1] es el extremo de escritura
    if (pipe(pipefd) == -1)
    {
        perror("pipe"); // Imprime un mensaje de error si no se pudo crear el pipe
        exit(1);
    }

    // Creamos un proceso hijo duplicando el proceso actual
    pid_t pid = fork();
    if (pid == -1)
    {
        perror("fork"); // Imprime un mensaje de error si no se pudo crear el proceso hijo
        exit(1);
    }

    if (pid == 0)
    {                     // Proceso hijo
        close(pipefd[0]); // Cerramos el extremo de lectura, ya que no lo vamos a usar

        // Redirigimos la salida estándar (STDOUT_FILENO) al extremo de escritura del pipe
        dup2(pipefd[1], STDOUT_FILENO);
        close(pipefd[1]); // Cerramos el extremo de escritura original, ya que ya lo hemos duplicado

        // Ejecutamos el comando pasado como argumento
        execvp(argv[1], &argv[1]);
        perror("execvp"); // Si execvp falla, imprimimos un mensaje de error
        exit(1);
    }
    else
    {                     // Proceso padre
        close(pipefd[1]); // Cerramos el extremo de escritura, ya que no lo vamos a usar

        char buffer[1024];
        ssize_t bytes;
        // Leemos la salida del comando desde el extremo de lectura del pipe
        while ((bytes = read(pipefd[0], buffer, sizeof(buffer) - 1)) > 0)
        {
            buffer[bytes] = '\0'; // Añadimos un carácter nulo al final para que sea una cadena válida
            printf("%s", buffer); // Imprimimos la salida
        }
        close(pipefd[0]); // Cerramos el extremo de lectura
    }

    return 0;
}