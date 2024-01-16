#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <dirent.h>

int main()
{
    // Abrir un archivo para escritura
    int file = open("contenido_directorio.txt", O_WRONLY | O_CREAT | O_TRUNC, 0644);
    if (file < 0)
    {
        perror("open");
        return 1;
    }

    // Redirigir la salida estándar al archivo
    dup2(file, STDOUT_FILENO); // STDOUT_FILENO es una constante que representa la salida estándar

    // Abrir el directorio "/"
    DIR *dir = opendir("/");
    if (dir == NULL)
    {
        perror("opendir");
        close(file);
        return 1;
    }

    // Leer el contenido del directorio y escribirlo en el archivo
    struct dirent *entrada;
    while ((entrada = readdir(dir)) != NULL)
    {
        printf("%s\n", entrada->d_name);
    }

    // Cerrar el directorio y el archivo
    closedir(dir);
    close(file);

    return 0;
}
