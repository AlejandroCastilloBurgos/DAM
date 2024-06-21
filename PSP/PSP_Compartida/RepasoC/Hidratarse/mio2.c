// mio1 pero en vez de ls ps -aj

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/wait.h>

int main()
{
    // Crear el pipe
    int pipefd[2];
    if (pipe(pipefd) < 0)
    {
        perror("Error al crear el pipe");
        return 1;
    }

    // Crear los procesos hijos
    pid_t pid1, pid2;
    pid1 = fork();
    if (pid1 == 0)
    {
        // Proceso emisor
        // Cerrar el extremo de lectura del pipe
        close(pipefd[0]);

        // Redirigir la salida estándar al extremo de escritura del pipe
        dup2(pipefd[1], STDOUT_FILENO);
        close(pipefd[1]);

        // Ejecutar el comando ps -aj
        char *argv[] = {"ps", "-aj", NULL};
        execvp("ps", argv);
        perror("Error en execvp");
        return 1;
    }
    else
    {
        // Proceso receptor
        // Cerrar el extremo de escritura del pipe
        close(pipefd[1]);

        // Leer los mensajes enviados por el emisor
        char buffer[1024];
        int bytes_read;

        while ((bytes_read = read(pipefd[0], buffer, sizeof(buffer))) > 0)
        {
            // Escribir los mensajes en un archivo de texto
            FILE *fp = fopen("salida.txt", "a");
            fwrite(buffer, bytes_read, 1, fp);
            fclose(fp);
        }

        // Esperar a que el emisor termine
        waitpid(pid1, NULL, 0);

        return 0;
    }
}
