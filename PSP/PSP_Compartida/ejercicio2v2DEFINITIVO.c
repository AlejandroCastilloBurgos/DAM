/* Enunciado en c: Solo habrá dos hijos, el hijo que reciba un número primo pierde. Si ninguno recibe un número primo
se salvan.

El padre recibirá por parámetro 2 números: el primero será la longitud de los números (si es 2 los números serán entre 10-99,ç
y si 3 100-999, etc) el segundo será la cantidad de números a enviar.

Cada hijo recibirá por un pipe los números primos, indicará por pantalla el número recibido y si es primo o no.

Tras procesar todos los números enviados por el padre el hijo devuelve en su estado si ha encontrado un primo o no.
El padre espera a que sus hijos finalicen, después indica qué hijos han sobrevivido. Deberá esperar por PID en concreto
para saber si se han salvado el hijo 1 o el 2.*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <signal.h>
#include <time.h>
#include <sys/wait.h>

#define MIN_HIJOS 0
#define NUM_HIJO 0
#define NUM_ERROR_HIJO -1
#define NUM_ERROR_PIPE -1
#define READ 0
#define WRITE 1

// Función que verifica si un número es primo o no
int esPrimo(int num)
{
    if (num < 2)
    {
        return 0; // No es primo
    }
    for (int i = 2; i * i <= num; i++)
    {
        if (num % i == 0)
        {
            return 0; // No es primo
        }
    }
    return 1; // Es primo
}

int main(int argc, char *argv[])
{
    if (argc < 3)
    {
        fprintf(stderr, "ERROR. Introduce: %s num1 num2\n", argv[0]);
        return 1;
    }

    int longitud = atoi(argv[1]);
    int cantNum = atoi(argv[2]);
    int MIN_NUM = 1;
    int MAX_NUM = 9;

    for (int i = 1; i < longitud; i++)
    {
        MIN_NUM *= 10;
        MAX_NUM = (MAX_NUM * 10) + 9;
    }

    int num_generado;
    int pipe1[2], pipe2[2];

    if (pipe(pipe1) == NUM_ERROR_PIPE || pipe(pipe2) == NUM_ERROR_PIPE)
    {
        perror("Error al crear el pipe");
        exit(EXIT_FAILURE);
    }

    pid_t hijo1, hijo2;
    int estado1, estado2;

    hijo1 = fork();
    if (hijo1 == NUM_ERROR_HIJO)
    {
        perror("Error al crear el hijo");
        exit(EXIT_FAILURE);
    }
    else if (hijo1 == NUM_HIJO)
    {
        // Primer hijo
        close(pipe1[WRITE]);
        close(pipe2[READ]);
        close(pipe2[WRITE]);
        while (read(pipe1[READ], &num_generado, sizeof(num_generado)))
        {
            if (esPrimo(num_generado))
            {
                printf("Soy el hijo 1, he recibido el %d, ¡¡¡ES PRIMO!!! \n", num_generado);
                exit(1);
            }
            else
            {
                printf("Soy el hijo 1, he recibido el %d, NO ES PRIMO. \n", num_generado);
            }
        }
        close(pipe1[READ]);
        exit(0);
    }
    else
    {
        hijo2 = fork();
        if (hijo2 == NUM_ERROR_HIJO)
        {
            perror("Error al crear el segundo hijo");
            exit(EXIT_FAILURE);
        }
        else if (hijo2 == NUM_HIJO)
        {
            // Segundo hijo
            close(pipe1[READ]);
            close(pipe2[WRITE]);
            close(pipe1[WRITE]);
            while (read(pipe2[READ], &num_generado, sizeof(num_generado)))
            {
                if (esPrimo(num_generado))
                {
                    printf("Soy el hijo 2, he recibido el %d, ¡¡¡ES PRIMO!!! \n", num_generado);
                    exit(1);
                }
                else
                {
                    printf("Soy el hijo 2, he recibido el %d, NO ES PRIMO. \n", num_generado);
                }
            }
            close(pipe2[READ]);
            exit(0);
        }
    }

    // Proceso padre
    close(pipe1[READ]);
    close(pipe2[READ]);

    srand(time(NULL));
    for (int i = 1; i <= cantNum; i++)
    {
        num_generado = rand() % (MAX_NUM - MIN_NUM + 1) + MIN_NUM;
        if (i % 2 == 0)
        {
            write(pipe1[WRITE], &num_generado, sizeof(num_generado));
        }
        else
        {
            write(pipe2[WRITE], &num_generado, sizeof(num_generado));
        }
    }
    close(pipe1[WRITE]);
    close(pipe2[WRITE]);

    // Esperar a que ambos hijos terminen
    waitpid(hijo1, &estado1, 0);
    waitpid(hijo2, &estado2, 0);

    if (WIFEXITED(estado1) && WEXITSTATUS(estado1) == 1)
    {
        printf("Hijo 1 ha encontrado un primo y no ha sobrevivido\n");
    }
    else
    {
        printf("Hijo 1 NO ha encontrado un primo y ha sobrevivido\n");
    }

    if (WIFEXITED(estado2) && WEXITSTATUS(estado2) == 1)
    {
        printf("Hijo 2 ha encontrado un primo y no ha sobrevivido\n");
    }
    else
    {
        printf("Hijo 2 NO ha encontrado un primo y ha sobrevivido\n");
    }

    return 0;
}
