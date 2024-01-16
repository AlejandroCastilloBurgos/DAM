#include <stdio.h>
#include <ctype.h> // Necesario para las funciones isalpha y isupper/islower

void cifrarCesar(char *cadena, int desplazamiento)
{
    for (int i = 0; cadena[i] != '\0'; i++)
    {
        // Verificar si el carácter es una letra
        if (isalpha(cadena[i]))
        {
            // Verificar si el carácter es mayúscula o minúscula
            if (isupper(cadena[i]))
            {
                // Cifrar letra mayúscula
                cadena[i] = (cadena[i] - 'A' + desplazamiento) % 26 + 'A';
            }
            else
            {
                // Cifrar letra minúscula
                cadena[i] = (cadena[i] - 'a' + desplazamiento) % 26 + 'a';
            }
        }
        // Si no es una letra, mantener el carácter sin cambios
    }
}

int main()
{
    char cadena[1000];
    int desplazamiento;

    // Solicitar al usuario ingresar la cadena de texto
    printf("Ingrese una cadena de texto: ");
    fgets(cadena, sizeof(cadena), stdin);

    // Solicitar al usuario ingresar el valor de desplazamiento
    printf("Ingrese un valor de desplazamiento: ");
    scanf("%d", &desplazamiento);

    // Cifrar la cadena utilizando el cifrado César
    cifrarCesar(cadena, desplazamiento);

    // Mostrar el resultado cifrado
    printf("Cadena cifrada: %s", cadena);

    return 0;
}

// para compilar cifrado usar

// gcc -o cifradoCesar cifradoCesar.c -lm