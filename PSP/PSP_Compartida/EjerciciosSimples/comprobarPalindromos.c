#include <stdio.h>
#include <ctype.h>
#include <string.h>

// Función para eliminar espacios y convertir a minúsculas
void prepararCadena(char *cadena)
{
    int j = 0;

    for (int i = 0; cadena[i] != '\0'; i++)
    {
        if (isalpha(cadena[i]))
        {
            cadena[j++] = tolower(cadena[i]);
        }
    }

    cadena[j] = '\0'; // Establecer el nuevo final de la cadena
}

// Función para verificar si una cadena es un palíndromo
int esPalindromo(const char *cadena)
{
    int longitud = strlen(cadena);

    for (int i = 0; i < longitud / 2; i++)
    {
        if (cadena[i] != cadena[longitud - i - 1])
        {
            return 0; // No es un palíndromo
        }
    }

    return 1; // Es un palíndromo
}

int main()
{
    char cadena[1000];

    // Solicitar al usuario ingresar una palabra o frase
    printf("Ingrese una palabra o frase: ");
    fgets(cadena, sizeof(cadena), stdin);

    // Eliminar espacios y convertir a minúsculas
    prepararCadena(cadena);

    // Verificar si la cadena es un palíndromo
    if (esPalindromo(cadena))
    {
        printf("La palabra o frase es un palíndromo.\n");
    }
    else
    {
        printf("La palabra o frase no es un palíndromo.\n");
    }

    return 0;
}
