#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

int main()
{
    // Abrir un archivo para escritura
    int file = open("output.txt", O_WRONLY | O_CREAT | O_TRUNC, 0644);
    if (file < 0)
    {
        perror("open");
        return 1;
    }

    // Redirigir la salida estándar al archivo
    dup2(file, STDOUT_FILENO); // STDOUT_FILENO es una constante que representa la salida estándar

    // Ahora, cualquier cosa que escribamos usando printf irá al archivo "output.txt"
    printf("Keloke con kelowa");

    // Cerrar el archivo
    close(file);

    return 0;
}