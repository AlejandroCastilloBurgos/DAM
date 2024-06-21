#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

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
        printf("Soy el proceso hijo\n");
        // Realizar las acciones del proceso hijo
        sleep(2);
        printf("El proceso hijo ha terminado\n");
    }
    else
    {
        // Este es el proceso padre
        printf("Soy el proceso padre\n");

        // Esperar a que el proceso hijo termine
        int status;
        wait(&status);

        if (WIFEXITED(status))
        {
            printf("El proceso hijo terminó con estado: %d\n", WEXITSTATUS(status));
        }
        else
        {
            printf("El proceso hijo terminó de manera anormal\n");
        }

        printf("El proceso padre ha terminado\n");
    }

    return 0;
}
