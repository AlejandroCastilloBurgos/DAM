#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

void generar_combinaciones(int longitud, FILE *archivo)
{
    char combinacion[longitud + 1];
    combinacion[longitud] = '\0';

    for (int i = 0; i < longitud; i++)
    {
        for (char letra = 'a'; letra <= 'z'; letra++)
        {
            combinacion[i] = letra;
            fprintf(archivo, "%s\n", combinacion);
        }
    }
}

int main(int argc, char *argv[])
{
    if (argc != 2)
    {
        fprintf(stderr, "Uso: %s <numero de hijos>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    int n = atoi(argv[1]);

    if (n <= 0)
    {
        fprintf(stderr, "El número de hijos debe ser un entero positivo\n");
        exit(EXIT_FAILURE);
    }

    for (int i = 1; i <= n; i++)
    {
        pid_t pid = fork();

        if (pid < 0)
        {
            perror("Error al crear el proceso hijo");
            exit(EXIT_FAILURE);
        }

        if (pid == 0)
        {
            // Este es el proceso hijo
            char nombre_archivo[15];
            snprintf(nombre_archivo, sizeof(nombre_archivo), "datos%d.txt", i);

            FILE *archivo = fopen(nombre_archivo, "w");
            if (archivo == NULL)
            {
                perror("Error al abrir el archivo");
                exit(EXIT_FAILURE);
            }

            generar_combinaciones(i, archivo);

            fclose(archivo);
            exit(EXIT_SUCCESS);
        }
    }

    // Esperar a que todos los hijos terminen
    for (int i = 0; i < n; i++)
    {
        wait(NULL);
    }

    printf("Proceso padre ha terminado\n");

    return 0;
}
