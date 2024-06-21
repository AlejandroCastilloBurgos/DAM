#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#define RANGO_DNI 23 // Define el rango de letras del DNI
#define RANGO_MAXIMO 99999
#define RANGO_MINIMO 10000
#define UNO 1

// Declaración de la función calcular_letra_DNI
char calcular_letra_DNI(int numero)
{
    // Array con las letras del DNI en orden
    char letras[RANGO_DNI] = "TRWAGMYFPDXBNJZSQVHLCKE";

    // Calcular el resto de dividir el número por 23
    int resto = numero % 23;

    // Devolver la letra correspondiente al resto obtenido
    return letras[resto];
}

// Definición de la función procesar_rango
void procesar_rango(int inicio, int fin, int hijo_numero)
{
    printf("Hijo %d (PID %d) procesando rango: %d - %d\n", hijo_numero, getpid(), inicio, fin);
    for (int i = inicio; i <= fin; i++)
    {
        // Calcular la letra del DNI
        char letra_DNI = calcular_letra_DNI(i);

        // Abrir el archivo para escritura
        FILE *archivo = fopen("dni_output_.txt", "a");
        if (archivo == NULL)
        {
            perror("Error al abrir el archivo");
            exit(EXIT_FAILURE);
        }

        // Escribir en el archivo utilizando fprintf
        fprintf(archivo, "Hijo %d (PID %d) encontró letra. La letra del DNI %d es: %c\n", hijo_numero, getpid(), i, letra_DNI);

        // Cerrar el archivo
        fclose(archivo);
    }
    printf("Hijo %d (PID %d) terminó.\n", hijo_numero, getpid());
    exit(0); // El proceso hijo termina aquí
}

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

    int RANGO_TOTAL = RANGO_MAXIMO - RANGO_MINIMO + UNO;
    int RANGO_POR_HIJO = RANGO_TOTAL / CANTIDAD_PROCESOS_A_CREAR;
    int RESTANTE = RANGO_TOTAL % CANTIDAD_PROCESOS_A_CREAR;

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
            int INICIO = RANGO_MINIMO + i * RANGO_POR_HIJO;
            int FIN = INICIO + RANGO_POR_HIJO - UNO;

            if (i == CANTIDAD_PROCESOS_A_CREAR - UNO)
            {
                FIN += RESTANTE;
            }

            procesar_rango(INICIO, FIN, i + 1);
        }
    }

    // El proceso padre espera a que todos los hijos terminen
    for (int i = 0; i < CANTIDAD_PROCESOS_A_CREAR; i++)
    {
        wait(NULL);
    }

    printf("Proceso padre (%d) ha terminado.\n", getpid());

    return 0;
}
