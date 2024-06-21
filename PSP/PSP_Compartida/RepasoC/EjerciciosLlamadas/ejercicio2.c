#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

void fecha();

int main()
{
    pid_t pid;

    // Crear un nuevo proceso
    pid = fork();

    // Verificar si la creación del proceso fue exitosa
    if (pid < 0)
    {
        fprintf(stderr, "Error al crear el proceso hijo\n");
        return 1;
    }

    if (pid == 0)
    {
        // Este es el proceso hijo
        printf("Soy el proceso Hijo\n");
        fecha();
    }
    else
    {
        // Este es el proceso padre
        printf("Soy el proceso padre\n");
        wait(NULL); // Esperar a que el proceso hijo termine
    }

    return 0;
}

void fecha()
{
    // El nombre del programa a ejecutar
    char *program = "/bin/date"; // Ruta completa al comando date

    // Argumentos para el programa: el nombre del programa y NULL al final
    char *arguments[] = {"date", NULL};

    // Llamar a execvp para ejecutar el comando date
    execvp(program, arguments);

    // Si execvp falla, imprimirá un error
    perror("execvp");
    exit(1);
}
