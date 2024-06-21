#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/md5.h>

#define MAX_BUFFER_SIZE 1024

void calcularMD5(const char *cadena, char *resultado)
{
    MD5_CTX md5Context;
    MD5_Init(&md5Context);
    MD5_Update(&md5Context, cadena, strlen(cadena));
    MD5_Final((unsigned char *)resultado, &md5Context);
}

int main()
{
    // Variables para almacenar las cadenas y el hash MD5 proporcionado
    char cadenaOriginal[MAX_BUFFER_SIZE];
    char hashMD5Proporcionado[MD5_DIGEST_LENGTH * 2 + 1];
    char hashMD5Calculado[MD5_DIGEST_LENGTH * 2 + 1];

    // Leer la cadena original y el hash MD5 proporcionado desde la entrada estándar
    printf("Ingrese la cadena original: ");
    fgets(cadenaOriginal, MAX_BUFFER_SIZE, stdin);
    cadenaOriginal[strcspn(cadenaOriginal, "\n")] = '\0'; // Eliminar el salto de línea al final

    printf("Ingrese el hash MD5 proporcionado: ");
    fgets(hashMD5Proporcionado, sizeof(hashMD5Proporcionado), stdin);
    hashMD5Proporcionado[strcspn(hashMD5Proporcionado, "\n")] = '\0'; // Eliminar el salto de línea al final

    // Calcular el hash MD5 de la cadena original
    calcularMD5(cadenaOriginal, hashMD5Calculado);

    // Crear un proceso para ejecutar el comando md5sum
    FILE *pipe = popen("md5sum -", "w");
    if (pipe == NULL)
    {
        perror("Error al abrir la tubería");
        exit(EXIT_FAILURE);
    }

    // Escribir la cadena original en la tubería
    fprintf(pipe, "%s", cadenaOriginal);

    // Cerrar la tubería y esperar a que el comando md5sum termine
    if (pclose(pipe) == -1)
    {
        perror("Error al cerrar la tubería");
        exit(EXIT_FAILURE);
    }

    // Imprimir los resultados
    printf("\nResultados:\n");
    printf("Cadena original: %s\n", cadenaOriginal);
    printf("MD5 calculado: %s\n", hashMD5Calculado);
    printf("MD5 proporcionado: %s\n", hashMD5Proporcionado);

    // Comparar los hashes MD5
    int coinciden = strcmp(hashMD5Calculado, hashMD5Proporcionado) == 0;
    printf("Los hashes MD5 %scoinciden.\n", coinciden ? "" : "no ");

    return 0;
}
