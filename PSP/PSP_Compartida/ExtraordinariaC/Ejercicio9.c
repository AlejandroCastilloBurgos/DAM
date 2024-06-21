/*Ejercicio 3: Cálculo de Factoriales
Escribe un programa en C que acepte un número N como parámetro de línea de comandos. Este número N representará la cantidad de procesos hijo que el proceso padre debe crear utilizando fork.

Cada proceso hijo debe calcular el factorial de un rango específico de números, dividiendo el espacio total (desde 1 hasta N!) en N partes iguales.
Cada hijo escribirá el resultado de su cálculo en un archivo único en el formato "Número - Factorial".
El proceso padre debe esperar a que todos los hijos terminen su ejecución antes de imprimir en la consola "Procesado completado para todos los hijos".*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>

#define RANGO_MAXIMO 10
#define RANGO_MINIMO 0
#define UNO 1

unsigned long long factorial(int n)
{
    if (n == 0 || n == 1)
        return 1;
    else
        return n * factorial(n - 1);
}

void procesar_rango(int inicio, int fin, int hijo_numero)
{
    printf("Hijo %d (PID %d) procesando rango: %d - %d\n", hijo_numero, getpid(), inicio, fin);
    for (int i = inicio; i <= fin; i++)
    {
        if (factorial(i))
        {
            // Calcular la letra del DNI
            unsigned long long calculo_factorial = factorial(i);

            // Abrir el archivo para escritura
            FILE *archivo = fopen("factorial_output_.txt", "a"); // usamos a de adition, si usamos w se sobreescribe
            if (archivo == NULL)
            {
                perror("Error al abrir el archivo");
            }

            // Construir el string a escribir en el archivo utilizando sprintf
            char resultado[100];
            sprintf(resultado, "Hijo %d (PID %d) encontro factorial. el factorial %d es: %llu\n", hijo_numero, getpid(), i, calculo_factorial);

            // Escribir en el archivo utilizando fprintf
            fprintf(archivo, "%s", resultado);

            // Cerrar el archivo
            fclose(archivo);
        }
    }
    printf("Hijo %d (PID %d) terminó.\n", hijo_numero, getpid());
    exit(0); // El proceso hijo termina aquí
}

// comienzo programa, pide por parametro un numero
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