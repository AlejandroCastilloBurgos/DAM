/*Crea un programa que acepte un número como parámetro de línea de comandos. El número recibido indicará la cantidad
 de números aleatorios generados.

El programa creará otro proceso con la llamada al sistema fork. El proceso padre generará n números aleatorios y se los enviará
al proceso hijo a través de un pipe. El padre esperará a que el hijo termine (padre usa wait).

El hijo recibirá los números por el pipe e irá procesando el menor y el mayor. Cuando termine de recibir
 números el hijo escribirá por pantalla el número menor y el número mayor.*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
#define NUMERO_MAXIMO 1000
#define NUMERO_MINIMO 1

int main(int argc, char *argv[])
{
    // Verificar si el número de argumentos es correcto
    if (argc != 2)
    {
        printf("Uso: %s <cantidad_numeros_aleatorios>\n", argv[0]);
        return 1;
    }

    int CANTIDAD_NUMEROS_ALEATORIOS = atoi(argv[1]);

    if (CANTIDAD_NUMEROS_ALEATORIOS <= 0)
    {
        printf("Por favor, introduce un número entero positivo.\n");
        return 1;
    }

    int pipefd[2];
    pid_t pid;

    // Crear el pipe
    if (pipe(pipefd) == -1)
    {
        perror("pipe failed");
        exit(EXIT_FAILURE);
    }

    // Crear el proceso hijo
    pid = fork();

    if (pid < 0)
    {
        // Error al crear el proceso
        perror("fork failed");
        exit(EXIT_FAILURE);
    }
    else if (pid == 0)
    {
        // Código del proceso hijo
        close(pipefd[1]); // Cerrar el extremo de escritura del pipe

        int num;
        long long suma = 0;
        int menor, mayor;
        read(pipefd[0], &num, sizeof(int));
        menor = mayor = num;
        suma += num;

        for (int i = 1; i < CANTIDAD_NUMEROS_ALEATORIOS; i++)
        {
            read(pipefd[0], &num, sizeof(int));
            if (num < menor)
                menor = num;
            suma += num;
            if (num > mayor)
                mayor = num;
        }

        double media = (double)suma / CANTIDAD_NUMEROS_ALEATORIOS;
        printf("La media es: %.2f\n", media);
        printf("Proceso hijo (%d) recibió los números.\n", getpid());
        printf("El número menor es: %d\n", menor);
        printf("El número mayor es: %d\n", mayor);

        close(pipefd[0]); // Cerrar el extremo de lectura del pipe
        exit(EXIT_SUCCESS);
    }
    else
    {
        // Código del proceso padre
        close(pipefd[0]); // Cerrar el extremo de lectura del pipe

        srand(time(NULL)); // Inicializar el generador de números aleatorios

        for (int i = 0; i < CANTIDAD_NUMEROS_ALEATORIOS; i++)
        {
            int num_aleatorio = rand() % NUMERO_MAXIMO + NUMERO_MINIMO;
            write(pipefd[1], &num_aleatorio, sizeof(int));
            printf("Número aleatorio %d: %d\n", i + 1, num_aleatorio);
        }

        close(pipefd[1]); // Cerrar el extremo de escritura del pipe
        wait(NULL);       // Esperar a que el hijo termine

        printf("Proceso padre (%d) ha terminado.\n", getpid());
    }

    return 0;
}
