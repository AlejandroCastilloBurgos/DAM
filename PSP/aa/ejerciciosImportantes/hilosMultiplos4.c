#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>

int main(void)
{
    int hijo1[2];
    int hijo2[2];

    pipe(hijo1); // Tubería del padre al hijo1
    pipe(hijo2); // Tubería del padre al hijo2

    srand(time(NULL));

    for (int i = 0; i < 2; i++)
    {
        if (fork() == 0)
        {
            int entero;
            close(hijo1[1]); // Cierra el descriptor de escritura de hijo1
            close(hijo2[1]); // Cierra el descriptor de escritura de hijo2

            do
            {
                if (read(hijo1[0], &entero, sizeof(entero)) > 0)
                {
                    printf("\tEl HIJO %d recibe MULTIPLOS DE 4: %d\n", i, entero);
                }
                else
                {
                    read(hijo2[0], &entero, sizeof(entero));
                    printf("\tEl HIJO %d recibe normales: %d\n", i, entero);
                }
            } while (read(hijo1[0], &entero, sizeof(entero)) > 0 && read(hijo2[0], &entero, sizeof(entero)) > 0);

            exit(0);
        }
    }

    for (int i = 0; i <= 20; i++)
    {
        int entero = rand() % 100;

        if (entero % 4 == 0)
        {
            close(hijo1[0]);
            write(hijo1[1], &entero, sizeof(entero)); // Escribe en la tubería hijo1
            printf("El PADRE ENVIA MENSAJE AL HIJO 1 MULTIPLO DE 4 ...%d\n", entero);
        }
        else
        {
            close(hijo2[0]);
            write(hijo2[1], &entero, sizeof(entero)); // Escribe en la tubería hijo2
            printf("El PADRE ENVIA MENSAJE AL HIJO 2 normales ...%d\n", entero);
        }
    }

    close(hijo1[0]);
    close(hijo1[1]);
    close(hijo2[0]);
    close(hijo2[1]);

    for (int i = 0; i < 2; i++)
    {
        wait(NULL); // Espera a que ambos hijos terminen
    }

    return 0;
}
