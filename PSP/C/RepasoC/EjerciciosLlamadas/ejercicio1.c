#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <dirent.h>
#include <sys/types.h>
#include <sys/wait.h>

#define READ 0
#define WRITE 1

#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

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
    }
    else
    {
        // Este es el proceso padre
        printf("Soy el proceso padre\n");
    }

    return 0;
}
