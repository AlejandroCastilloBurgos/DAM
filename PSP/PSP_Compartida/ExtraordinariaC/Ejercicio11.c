/*Ejercicio 6: Validación de Palíndromos
Desarrolla un programa en C que cree varios procesos hijo para validar si una serie de cadenas de texto son palíndromos o no,
 usando el estado de finalización para controlar el éxito o fallo de cada proceso hijo.

El programa debe aceptar un número N como parámetro de línea de comandos, que especificará el número de procesos hijo a crear.
Cada proceso hijo recibirá una cadena de texto y determinará si es un palíndromo.
Un proceso hijo que determine que su cadena es un palíndromo terminará con exit(1), y si no es un palíndromo, con exit(0).
El proceso padre debe esperar a que todos los hijos finalicen y
luego debe revisar los códigos de estado de cada uno para determinar cuántos procesos encontraron palíndromos y cuántos no.
El padre debe imprimir cuántos procesos resultaron en cadenas palíndromas y cuántos no.*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>

void invertir_cadena(char *cadena, char *cadena_invertida)
{
    int longitud = strlen(cadena);
    for (int i = 0; i < longitud; i++)
    {
        cadena_invertida[i] = cadena[longitud - i - 1];
    }
    cadena_invertida[longitud] = '\0'; // Añadir el terminador null al final
}

int comprueba_capicua(char *frase)
{

    char buf_invertido[1024];
    invertir_cadena(frase, buf_invertido); // Invertir la cadena

    // Comparar la cadena original con la cadena invertida
    return strcmp(frase, buf_invertido) == 0;
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

    int cantidad_palindromos = 0;
    int no_palindromos = 0;

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

            char resultado[100];
            printf("Introduce una frase \n");
            scanf("%s", resultado);
            if (comprueba_capicua(resultado))
            {
                printf("Hijo %d (PID %d) encontró palindromo: %s\n", i + 1, getpid(), resultado);
                exit(1);
            }
            else
            {
                printf("Hijo %d (PID %d) no encontró palindromo\n", i + 1, getpid());
                exit(0);
            }
        }
    }

    // El proceso padre espera a que todos los hijos terminen y verifica su estado de salida
    for (int i = 0; i < CANTIDAD_PROCESOS_A_CREAR; i++)
    {
        int status;
        pid_t pid = wait(&status);
        if (pid == -1)
        {
            perror("wait failed");
            exit(EXIT_FAILURE);
        }
        if (WIFEXITED(status))
        {
            int exit_status = WEXITSTATUS(status);
            if (exit_status == 1)
            {

                no_palindromos++;
            }
            else
            {
                cantidad_palindromos++;
            }
        }
    }

    printf("Cantidad de hijos que NO encontraron palindromo: %d\n", cantidad_palindromos);
    printf("Cantidad de hijos que SI encontraron palindromo: %d\n", no_palindromos);
    printf("Proceso padre (%d) ha terminado.\n", getpid());

    return 0;
}
