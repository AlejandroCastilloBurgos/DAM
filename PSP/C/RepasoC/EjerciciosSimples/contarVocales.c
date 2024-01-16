#include <stdio.h>

int contarVocales(const char *cadena)
{
    int contadorVocales = 0;

    // Recorrer la cadena de caracteres
    for (int i = 0; cadena[i] != '\0'; i++)
    {
        // Verificar si el carácter es una vocal (mayúscula o minúscula)
        if (cadena[i] == 'a' || cadena[i] == 'e' || cadena[i] == 'i' || cadena[i] == 'o' || cadena[i] == 'u' ||
            cadena[i] == 'A' || cadena[i] == 'E' || cadena[i] == 'I' || cadena[i] == 'O' || cadena[i] == 'U')
        {
            contadorVocales++;
        }
    }

    return contadorVocales;
}

int main()
{
    char cadena[1000];

    // Solicitar al usuario ingresar una cadena de caracteres
    printf("Ingrese una cadena de caracteres: ");
    fgets(cadena, sizeof(cadena), stdin);

    // Contar el número de vocales en la cadena
    int numeroVocales = contarVocales(cadena);

    // Mostrar el resultado
    printf("Número de vocales en la cadena: %d\n", numeroVocales);

    return 0;
}
