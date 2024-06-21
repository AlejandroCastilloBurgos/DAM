
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <signal.h>
#include <sys/wait.h>
#include <time.h>
#include <sys/wait.h>

#define MIN_HIJOS 0
#define NUM_HIJO 0
#define NUM_ERROR_HIJO -1
#define MODULO 23
#define cantNum 500
#define MIN_NUM 10000000
#define MAX_NUM 99999999

int main(int argc, char *argv[])
{

    if (argc < 3)
    {
        fprintf(stderr, "ERROR. Introduce: %s numHijos caracter\n", argv[0]);
        return 1;
    }
    int numHijos = atoi(argv[1]);  //  Leer numero introducido por parametros
    char letraDNI = atoi(argv[2]); // Leer letra dni
    if (numHijos <= MIN_HIJOS)
    {
        printf("El número de hijos debe ser un entero positivo.\n");
        return 1;
    }
    for (int i = 1; i <= numHijos; i++)
    { // Crear hijos
        pid_t hijo = fork();

        if (hijo == NUM_ERROR_HIJO)
        { // Comprobar si se ha creado hijo correctamente
            perror("Error al crear el hijo");
            exit(EXIT_FAILURE);
        }
        else if (hijo == NUM_HIJO)
        { // Proceso hijo
            buscaDNI(letraDNI);
        }
    }

    // Proceso padre
    for (int i = 0; i < numHijos; i++)
    {
        int estado;
        wait(&estado);
        // El padre espera a que el hijo termine, y cada hijo que termina guarda el estado de exit
        estado += WEXITSTATUS(estado);
    }

    printf("Los DNI posibles son: ");

    return 0;
}

int esPrimo(int numero)
{
    if (numero < 2)
        return 0;
    for (int i = 2; i * i <= numero; i++)
    {
        if (numero % i == 0)
            return 0;
    }
    return 1;
}

int buscaDNI(int numeroDNI)
{
    int num_generado;
    srand(time(NULL));
    for (int i = 1; i <= cantNum; i++)
    {
        num_generado = rand() % (MAX_NUM - MIN_NUM + 1) + MIN_NUM;
    }

    char *letras["T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"];
    if (letraDNI == letras[0])
    {
        if (num_generado % MODULO == 0)
        {
            if (esPrimo(num_generado) == 1)
            {
                return numeroDNI;
            }
        }
        else
        {
        }
    }
    if (letraDNI == letras[1])
    {
        if (num_generado % MODULO == 1)
        {
            if (esPrimo(num_generado) == 1)
            {
                return numeroDNI;
            }
        }
        else
        {
        }
    }
    if (letraDNI == letras[3])
    {
        if (num_generado % MODULO == 3)
        {
            if (esPrimo(num_generado) == 1)
            {
                return numeroDNI;
            }
        }
        else
        {
        }
    }
    if (letraDNI == letras[4])
    {
        if (num_generado % MODULO == 4)
        {
            if (esPrimo(num_generado) == 1)
            {
                return numeroDNI;
            }
            else
            {
                return numeroDNI;
            }
        }
    }
    else
    {
    }
}
if (letraDNI == letras[5])
{
    if (num_generado % MODULO == 5)
    {
        if (esPrimo(num_generado) == 1)
        {
            return numeroDNI + "*";
        }
        else
        {
            return numeroDNI;
        }
    }
    else
    {
    }
}
if (letraDNI == letras[5])
{
    if (num_generado % MODULO == 5)
    {
        if (esPrimo(num_generado) == 1)
        {
            return numeroDNI;
        }
    }
    else
    {
    }
}
if (letraDNI == letras[6])
{
    if (num_generado % MODULO == 6)
    {
        if (esPrimo(num_generado) == 1)
        {
            return numeroDNI;
        }
    }
    else
    {
    }
}
if (letraDNI == letras[7])
{
    if (num_generado % MODULO == 7)
    {
        if (esPrimo(num_generado) == 1)
        {
            return numeroDNI;
        }
    }
    else
    {
    }
}
if (letraDNI == letras[8])
{
    if (num_generado % MODULO == 8)
    {
        if (esPrimo(num_generado) == 1)
        {
            return numeroDNI;
        }
    }
    else
    {
    }
}
if (letraDNI == letras[9])
{
    if (num_generado % MODULO == 9)
    {
        if (esPrimo(num_generado) == 1)
        {
            return numeroDNI;
        }
    }
    else
    {
    }
}
if (letraDNI == letras[10])
{
    if (num_generado % MODULO == 10)
    {
        if (esPrimo(num_generado) == 1)
        {
            return numeroDNI;
        }
    }
    else
    {
    }
}
if (letraDNI == letras[11])
{
    if (num_generado % MODULO == 11)
    {
        if (esPrimo(num_generado) == 1)
        {
            return numeroDNI;
        }
    }
    else
    {
    }
}
if (letraDNI == letras[12])
{
    if (num_generado % MODULO == 12)
    {
        if (esPrimo(num_generado) == 1)
        {
            return numeroDNI;
        }
    }
    else
    {
    }
}
if (letraDNI == letras[13])
{
    if (num_generado % MODULO == 13)
    {
        if (esPrimo(num_generado) == 1)
        {
            return numeroDNI;
        }
    }
    else
    {
    }
}
if (letraDNI == letras[14])
{
    if (num_generado % MODULO == 14)
    {
        if (esPrimo(num_generado) == 1)
        {
            return numeroDNI;
        }
    }
    else
    {
    }
}
if (letraDNI == letras[15])
{
    if (num_generado % MODULO == 15)
    {
        if (esPrimo(num_generado) == 1)
        {
            return numeroDNI;
        }
    }
    else
    {
    }
}
if (letraDNI == letras[16])
{
    if (num_generado % MODULO == 16)
    {
        if (esPrimo(num_generado) == 1)
        {
            return numeroDNI;
        }
    }
    else
    {
    }
}
if (letraDNI == letras[17])
{
    if (num_generado % MODULO == 17)
    {
        if (esPrimo(num_generado) == 1)
        {
            return numeroDNI;
        }
    }
    else
    {
    }
}
if (letraDNI == letras[18])
{
    if (num_generado % MODULO == 18)
    {
        if (esPrimo(num_generado) == 1)
        {
            return numeroDNI;
        }
    }
    else
    {
    }
}
if (letraDNI == letras[19])
{
    if (num_generado % MODULO == 19)
    {
        if (esPrimo(num_generado) == 1)
        {
            return numeroDNI;
        }
    }
    else
    {
    }
}
if (letraDNI == letras[20])
{
    if (num_generado % MODULO == 20)
    {
        if (esPrimo(num_generado) == 1)
        {
            return numeroDNI;
        }
    }
    else
    {
    }
}
if (letraDNI == letras[21])
{
    if (num_generado % MODULO == 21)
    {
        if (esPrimo(num_generado) == 1)
        {
            return numeroDNI;
        }
    }
    else
    {
    }
}
if (letraDNI == letras[22])
{
    if (num_generado % MODULO == 22)
    {
        if (esPrimo(num_generado) == 1)
        {
            return numeroDNI + "*";
        }
        else
        {
            return numeroDNI;
        }
    }
    else
    {
    }
}
