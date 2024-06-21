#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main()
{
    int pipe_fd[2];
    pid_t pid;

    // Crear un pipe
    if (pipe(pipe_fd) == -1)
    {
        perror("pipe");
        exit(EXIT_FAILURE);
    }

    // Crear un nuevo proceso
    pid = fork();

    // Verificar si la creación del proceso fue exitosa
    if (pid < 0)
    {
        perror("fork");
        exit(EXIT_FAILURE);
    }

    if (pid == 0)
    {
        // Este es el proceso hijo

        // Cerrar el descriptor de escritura del pipe en el proceso hijo
        close(pipe_fd[1]);

        // Leer el número del pipe
        int numero;
        read(pipe_fd[0], &numero, sizeof(numero));

        // Elevar al cubo
        int resultado = numero * numero * numero;

        // Abrir el archivo salida.txt para escritura
        FILE *archivo = fopen("salida.txt", "w");
        if (archivo == NULL)
        {
            perror("fopen");
            exit(EXIT_FAILURE);
        }

        // Escribir el resultado en salida.txt
        fprintf(archivo, "%d\n", resultado);

        // Cerrar el archivo
        fclose(archivo);

        // Cerrar el descriptor de lectura del pipe en el proceso hijo
        close(pipe_fd[0]);
    }
    else
    {
        // Este es el proceso padre

        // Cerrar el descriptor de lectura del pipe en el proceso padre
        close(pipe_fd[0]);

        // Leer un número del usuario
        int numero;
        printf("Ingrese un número: ");
        scanf("%d", &numero);

        // Enviar el número al proceso hijo a través del pipe
        write(pipe_fd[1], &numero, sizeof(numero));

        // Cerrar el descriptor de escritura del pipe en el proceso padre
        close(pipe_fd[1]);

        // Esperar a que el proceso hijo termine
        wait(NULL);
    }

    return 0;
}
