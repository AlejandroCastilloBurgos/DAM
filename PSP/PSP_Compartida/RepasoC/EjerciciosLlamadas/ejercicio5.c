#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

void proceso_hijo(int numero)
{
    printf("Soy el proceso hijo %d\n", numero);
    // Realizar las acciones específicas del proceso hijo
    sleep(2);
    // Devolver un valor de salida diferente para cada hijo
    exit(numero);
}

int main()
{
    pid_t pid;
    int status;

    // Crear varios procesos hijos
    for (int i = 1; i <= 3; i++)
    {
        pid = fork();

        if (pid < 0)
        {
            fprintf(stderr, "Error al crear el proceso hijo\n");
            return 1;
        }

        if (pid == 0)
        {
            // Este es el proceso hijo
            proceso_hijo(i);
        }
    }

    // Este es el proceso padre
    for (int i = 1; i <= 3; i++)
    {
        // Esperar a que cada proceso hijo termine y recoger su valor de salida
        pid = wait(&status);

        if (WIFEXITED(status))
        {
            // Si el hijo terminó normalmente, mostrar su valor de salida
            printf("El proceso hijo %d terminó con valor: %d\n", pid, WEXITSTATUS(status));
        }
        else
        {
            // Si el hijo no terminó normalmente, mostrar un mensaje de error
            printf("El proceso hijo %d no terminó normalmente\n", pid);
        }
    }

    // El proceso padre ha terminado
    printf("El proceso padre ha terminado\n");

    return 0;
}
