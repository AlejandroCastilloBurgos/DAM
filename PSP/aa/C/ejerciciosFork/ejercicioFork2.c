#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    // Crear un proceso hijo
    pid_t pid = fork();

    // Verificar si se creó el proceso hijo correctamente
    if (pid < 0) {
        fprintf(stderr, "Error al crear el proceso hijo.\n");
        return 1; // Salir con un código de error
    } else if (pid == 0) {
        // Código ejecutado por el proceso hijo

        // Utilizar execlp para ejecutar el comando date
        execlp("date", "date", NULL);

        // Si execlp() retorna, ha habido un error
        perror("Error al ejecutar execlp");
        exit(1); // Salir con un código de error
    } else {
        // Código ejecutado por el proceso padre
        // Esperar a que el proceso hijo termine antes de imprimir el mensaje
        wait(NULL);
        printf("Soy el proceso padre\n");
    }

    return 0; // Salir con éxito
}
