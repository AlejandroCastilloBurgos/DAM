// Ejercicio el juego del calamar
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
#include <math.h>

#define NUMHIJOS 2 // Número de hijos que se van a crear
#define READ 0
#define WRITE 1

// FUNCION COMPRUEBA NUMEROS PRIMOS
int esPrimo(int numero)
{
    if (numero < 2)
    {
        return 0; // No es primo
    }

    for (long long i = 2; i * i <= numero; i++)
    {
        if (numero % i == 0)
        {
            return 0; // No es primo
        }
    }

    return 1; // Es primo
}
int esPrimo2(int numero2)
{
    if (numero2 < 2)
    {
        return 0; // No es primo
    }

    for (long long i = 2; i * i <= numero2; i++)
    {
        if (numero2 % i == 0)
        {
            return 0; // No es primo
        }
    }

    return 1; // Es primo
}
int main()
{
    // Crear tubos para la comunicación entre el padre y los hijos
    int pipe_hijo1[2]; // Para enviar números aleatorios al hijo 1
    int pipe_hijo2[2]; // Para enviar números aleatorios al hijo 2
    int estado1;
    int estado2;

    if (pipe(pipe_hijo1) == -1 || pipe(pipe_hijo2) == -1)
    {
        perror("Error al crear los tubos");
        exit(EXIT_FAILURE);
    }

    // Crear dos procesos hijos
    pid_t hijo1, hijo2;

    // Primer hijo
    hijo1 = fork();

    // Verificar si se creó el primer hijo correctamente
    if (hijo1 < 0)
    {
        perror("Error al crear el primer hijo");
        exit(EXIT_FAILURE);
    }
    else if (hijo1 == 0)
    {
        // Código ejecutado por el primer hijo

        // Cerrar el extremo de escritura del tubo de números pares
        close(pipe_hijo1[1]);

        int numero;

        while (read(pipe_hijo1[0], &numero, sizeof(numero)) > 0)
        {
            printf("Hijo 1: Verificando si %d es primo...\n", numero);
            if (esPrimo(numero))
            {
                printf("Hijo 1: %d es primo.\n", numero);
                exit(EXIT_SUCCESS);
            }
            else
            {
                printf("Hijo 1: %d no es primo.\n", numero);
            }
        }

        // Cerrar el extremo de lectura del tubo de números pares
        close(pipe_hijo1[0]);

        exit(EXIT_SUCCESS);
    }

    // Segundo hijo
    hijo2 = fork();

    // Verificar si se creó el segundo hijo correctamente
    if (hijo2 < 0)
    {
        perror("Error al crear el segundo hijo");
        exit(EXIT_FAILURE);
    }
    else if (hijo2 == 0)
    {
        // Código ejecutado por el segundo hijo

        // Cerrar el extremo de escritura del tubo de números impares
        close(pipe_hijo2[1]);
        int numero2;

        while (read(pipe_hijo2[0], &numero2, sizeof(numero2)) > 0)
        {
            printf("Hijo 2: Verificando si %d es primo...\n", numero2);
            if (esPrimo(numero2))
            {
                printf("Hijo 2: %d es primo.\n", numero2);
                exit(EXIT_SUCCESS);
            }
            else
            {
                printf("Hijo 2: %d no es primo.\n", numero2);
            }
        }

        // Cerrar el extremo de lectura del tubo de números impares
        close(pipe_hijo2[0]);

        exit(EXIT_SUCCESS);
    }

    // Código ejecutado por el proceso padre

    // Cerrar los extremos de lectura de los tubos en el proceso padre
    close(pipe_hijo1[0]);
    close(pipe_hijo2[0]);

    // Generar 3 números aleatorios y enviarlos a los hijos 1
    for (int i = 0; i < 3; ++i)
    {
        int numero = rand() % 100; // Números aleatorios entre 0 y 99

        // Enviar números pares al primer hijo
        if (numero % 2 == 0)
        {
            write(pipe_hijo1[1], &numero, sizeof(numero));
        }
        // Enviar números impares al segundo hijo
        else
        {
            write(pipe_hijo1[1], &numero, sizeof(numero));
        }
    }
    // Generar 3 números aleatorios y enviarlo al hijo 2
    for (int i = 0; i < 3; ++i)
    {
        int numero2 = rand() % 100; // Números aleatorios entre 0 y 99

        // Enviar números pares al primer hijo
        if (numero2 >= 0)
        {
            write(pipe_hijo2[1], &numero2, sizeof(numero2));
        }
    }

    // Cerrar los extremos de escritura de los tubos en el proceso padre
    close(pipe_hijo1[1]);
    close(pipe_hijo2[1]);

    // Esperar a que ambos hijos terminen antes de finalizar el proceso padre
    wait(NULL);
    wait(NULL);
    // Código ejecutado por el proceso padre
    waitpid(hijo1, &estado1, 0);
    waitpid(hijo2, &estado2, 0);

    // Contar cuántos hijos indicaron que su número era primo
    if (WIFEXITED(estado1) && WEXITSTATUS(estado1) == EXIT_SUCCESS)
    {
        printf("El hijo 1 ha muerto");
    }
    else
    {
        printf("El hijo 1 ha sobrevivido");
    }
    if (WIFEXITED(estado2) && WEXITSTATUS(estado2) == EXIT_SUCCESS)
    {
        printf("El hijo 2 ha muerto");
    }
    else
    {
        printf("  El hijo 2 ha sobrevivido");
    }

    return EXIT_SUCCESS;
    return 0; // Salir con éxito
}
