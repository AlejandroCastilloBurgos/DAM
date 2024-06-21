/*Escribe un programa que reciba por parámetro un número, este número indicará el número de procesos a crear.
Los hijos buscarán entre los números del 10000 al 99999 los números capicúas. Cada hijo buscará más o menos
un número parecido de números.*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>

#define RANGO_MAXIMO 99999
#define RANGO_MINIMO 10000
#define UNO 1

void invertir_cadena(char *cadena, char *cadena_invertida)
{
    int longitud = strlen(cadena);
    for (int i = 0; i < longitud; i++)
    {
        cadena_invertida[i] = cadena[longitud - i - 1];
    }
    cadena_invertida[longitud] = '\0'; // Añadir el terminador null al final
}

int comprueba_capicua(int numero)
{
    char buf[1024];
    sprintf(buf, "%d", numero); // Convertir el número a cadena

    char buf_invertido[1024];
    invertir_cadena(buf, buf_invertido); // Invertir la cadena

    // Comparar la cadena original con la cadena invertida
    return strcmp(buf, buf_invertido) == 0;
}

void procesar_rango(int inicio, int fin, int hijo_numero)
{
    printf("Hijo %d (PID %d) procesando rango: %d - %d\n", hijo_numero, getpid(), inicio, fin);
    for (int i = inicio; i <= fin; i++)
    {
        if (comprueba_capicua(i))
        {
            printf("Hijo %d (PID %d) encontró capicua: %d\n", hijo_numero, getpid(), i);
        }
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
                FIN += RESTANTE; // REVISAR ESTA FUNCION!!!!
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
