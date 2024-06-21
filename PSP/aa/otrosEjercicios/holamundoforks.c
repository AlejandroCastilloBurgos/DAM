#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main()
{
    pid_t pid = fork();

    if (pid < 0)
    {
        // Error al crear el proceso hijo
        fprintf(stderr, "Error al crear el proceso hijo.\n");
        return 1;
    }
    else if (pid == 0)
    {
        // Código ejecutado por el proceso hijo
        printf("Hola mundo desde el proceso hijo\n");
        exit(0);
    }
    else
    {
        // Código ejecutado por el proceso padre
        // Esperar a que el proceso hijo termine antes de imprimir el mensaje
        wait(NULL);
        printf("Hola mundo desde el proceso padre\n");
    }

    return 0;
}
