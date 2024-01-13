// Crea un programa que cree dos pipes, después haga fork. El padre enviará 3 números aleatorios al proceso hijo a través de uno de los pipes. El proceso hijo los ordenará
// y los escribirá en un pipe de vuelta al padre ordenados.
//  El padre muestra la información y espera a que finalice el hijo.
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>

#define READ 0
#define WRITE 1

int main()
{
    // Crear un pipe
    int pipe_uno[2];
    if (pipe(pipe_uno) == -1)
    {
        perror("Error al crear el tubo");
        exit(EXIT_FAILURE);
    }

    // Crear otro pipe
    int pipe_dos[2];
    if (pipe(pipe_dos) == -1)
    {
        perror("Error al crear el tubo");
        exit(EXIT_FAILURE);
    }

    // Hacer fork
    pid_t pid = fork();
    if (pid < 0)
    {
        fprintf(stderr, "Error al crear el proceso hijo.\n");
        return 1; // Salir con un código de error
    }
    else if (pid == 0)
    {
        // Código ejecutado por el proceso hijo
        int nRecibido1;
        int nRecibido2;
        int nRecibido3;

        // Leer el número del pipe_uno
        read(pipe_uno[READ], &nRecibido1, sizeof(nRecibido1));
        read(pipe_uno[READ], &nRecibido2, sizeof(nRecibido2));
        read(pipe_uno[READ], &nRecibido3, sizeof(nRecibido3));

        close(pipe_uno[READ]); // Cerrar el descriptor de lectura después de leer

        // Imprimir número recibido
        printf("Proceso hijo recibió los números: %d, %d, %d\n", nRecibido1, nRecibido2, nRecibido3);

        // Ordenar los números
        if (nRecibido1 > nRecibido2)
        {
            int temp = nRecibido1;
            nRecibido1 = nRecibido2;
            nRecibido2 = temp;
        }

        if (nRecibido2 > nRecibido3)
        {
            int temp = nRecibido2;
            nRecibido2 = nRecibido3;
            nRecibido3 = temp;
        }

        if (nRecibido1 > nRecibido2)
        {
            int temp = nRecibido1;
            nRecibido1 = nRecibido2;
            nRecibido2 = temp;
        }

        // Código del proceso hijo
        printf("Proceso hijo terminó\n");
    }
    else
    {
        // Código del proceso padre
        int n1; // Este es el número que el padre enviará al hijo
        int n2;
        int n3;

        srand(time(NULL)); // Inicializar la semilla para números aleatorios
        n1 = rand() % 100;
        n2 = rand() % 100;
        n3 = rand() % 100;

        close(pipe_uno[READ]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura

        // Escribir los números en el pipe_uno
        write(pipe_uno[WRITE], &n1, sizeof(n1));
        write(pipe_uno[WRITE], &n2, sizeof(n2));
        write(pipe_uno[WRITE], &n3, sizeof(n3));
        close(pipe_uno[WRITE]); // Cerrar el descriptor de escritura después de escribir

        // Esperar a que el proceso hijo termine
        wait(NULL);
        printf("Proceso padre terminó\n");
    }

    return 0;
}
