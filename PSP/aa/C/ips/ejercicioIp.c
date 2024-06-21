#include <stdio.h>
#include <stdlib.h>

int main() {
    // Ejecutar el comando "ip a"
    FILE *fp = popen("ip a", "r");

    if (fp == NULL) {
        perror("Error al ejecutar el comando");
        return EXIT_FAILURE;
    }

    printf("Direcciones IP:\n");

    // Leer y mostrar la salida del comando
    char buffer[128];
    while (fgets(buffer, sizeof(buffer), fp) != NULL) {
        printf("%s", buffer);
    }

    // Cerrar el flujo de archivo
    pclose(fp);

    return EXIT_SUCCESS;
}
