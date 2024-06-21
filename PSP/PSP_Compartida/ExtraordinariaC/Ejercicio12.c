/*
Ejercicio 7: Manejo de Archivos con Procesos
Crea un programa en C que acepte un número N como parámetro de línea de comandos. Este número N representará la cantidad de archivos que el proceso padre debe crear utilizando fork.

Cada proceso hijo debe crear un archivo con un nombre único y escribir en él un número aleatorio generado por ese proceso.
Cada archivo debe ser nombrado como output_<PID>.txt, donde <PID> es el PID del proceso hijo.
El proceso padre debe esperar a que todos los hijos terminen su ejecución antes de imprimir en la consola "Archivos creados correctamente".*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
int main(int argc, char *argv[])
{
    if (argc != 2)
    {
        printf("Uso: %s <Cantidad_procesos_a_crear>\n", argv[0]);
        return 1;
    }

    int CANTIDAD_PROCESOS_A_CREAR = atoi(argv[1]);

    if (CANTIDAD_PROCESOS_A_CREAR <= 0)
    {
        printf("Por favor, introduce un número entero positivo.\n");
        return 1;
    }

    for (int i = 0; i < CANTIDAD_PROCESOS_A_CREAR; i++)
    {
        pid_t pid = fork();

        if (pid < 0)
        {
            perror("fork failed");
            exit(EXIT_FAILURE);
        }
        else if (pid == 0)
        {
            int proceso = getpid();
            char nombre_archivo[50];
            // Construir el nombre del archivo con el número de proceso
            sprintf(nombre_archivo, "Output<%d>.txt", proceso);
            // Abrir el archivo para escritura
            FILE *archivo = fopen(nombre_archivo, "a");
            if (archivo == NULL)
            {
                perror("Error al abrir el archivo");
                exit(EXIT_FAILURE);
            }

            // Cerrar el archivo
            fclose(archivo);
            exit(EXIT_SUCCESS);
        }
    }

    // El proceso padre espera a que todos los hijos terminen
    for (int i = 0; i < CANTIDAD_PROCESOS_A_CREAR; i++)
    {
        wait(NULL);
    }

    printf("Proceso padre (%d) ha terminado.\n", getppid());

    return 0;
}
