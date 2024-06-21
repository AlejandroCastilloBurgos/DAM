#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Función para generar las combinaciones de caracteres
void generarCombinaciones(int longitud, char* prefijo, FILE* archivo) {
    if (longitud == 0) {
        // Imprimir el prefijo en el archivo
        fprintf(archivo, "%s\n", prefijo);
        return;
    }

    for (char letra = 'a'; letra <= 'z'; ++letra) {
        // Concatenar la letra al prefijo
        char nuevoPrefijo[longitud + 2]; // +2 para la letra y el carácter nulo '\0'
        sprintf(nuevoPrefijo, "%s%c", prefijo, letra);

        // Llamar recursivamente con la longitud reducida
        generarCombinaciones(longitud - 1, nuevoPrefijo, archivo);
    }
}

int main(int argc, char* argv[]) {
    if (argc != 2) {
        fprintf(stderr, "Uso: %s <numero>\n", argv[0]);
        return 1;
    }

    int n = atoi(argv[1]);

    if (n <= 0) {
        fprintf(stderr, "El número debe ser positivo.\n");
        return 1;
    }

    // Crear archivos con las combinaciones de caracteres
    for (int i = 1; i <= n; ++i) {
        // Construir el nombre del archivo
        char nombreArchivo[15]; // Suficientemente grande para "datos" + 2 dígitos + ".txt" + '\0'
        sprintf(nombreArchivo, "datos%d.txt", i);

        // Abrir el archivo para escritura
        FILE* archivo = fopen(nombreArchivo, "w");

        if (archivo == NULL) {
            perror("Error al abrir el archivo");
            return 1;
        }

        // Generar las combinaciones de caracteres y escribir en el archivo
        generarCombinaciones(i, "", archivo);

        // Cerrar el archivo
        fclose(archivo);

        printf("Se ha creado %s con todas las combinaciones de longitud %d.\n", nombreArchivo, i);
    }

    return 0; // Salir con éxito
}
