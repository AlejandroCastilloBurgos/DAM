// md5 con papa e hijos

#include <stdio.h>
#include <string.h>
#include <openssl/md5.h>
#include <openssl/evp.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#define PRINCIPIOABCEDARIO 'a'
#define FINALABCEDARIO 'z'
#define NUM_HIJOS 5

void md5_hash(const char *str, unsigned char *digest)
{
    EVP_MD_CTX *mdctx;
    const EVP_MD *md;
    unsigned int md_len;

    OpenSSL_add_all_digests();

    md = EVP_get_digestbyname("md5");
    mdctx = EVP_MD_CTX_new();
    EVP_DigestInit_ex(mdctx, md, NULL);
    EVP_DigestUpdate(mdctx, str, strlen(str));
    EVP_DigestFinal_ex(mdctx, digest, &md_len);

    EVP_MD_CTX_free(mdctx);
}

void functionMD5(const char *target_hash, int numero_hijo)
{
    char arrayLetras[6] = {0}; // Espacio para 5 letras más el carácter nulo
    unsigned char hash[MD5_DIGEST_LENGTH];

    for (arrayLetras[0] = PRINCIPIOABCEDARIO; arrayLetras[0] <= FINALABCEDARIO; arrayLetras[0]++)
    {
        for (arrayLetras[1] = PRINCIPIOABCEDARIO; arrayLetras[1] <= FINALABCEDARIO; arrayLetras[1]++)
        {
            for (arrayLetras[2] = PRINCIPIOABCEDARIO; arrayLetras[2] <= FINALABCEDARIO; arrayLetras[2]++)
            {
                for (arrayLetras[3] = PRINCIPIOABCEDARIO; arrayLetras[3] <= FINALABCEDARIO; arrayLetras[3]++)
                {
                    arrayLetras[4] = '\0'; // Asegúrate de que la cadena esté terminada en nulo
                    md5_hash(arrayLetras, hash);

                    char hash_str[33];
                    for (int i = 0; i < 16; i++)
                    {
                        sprintf(&hash_str[i * 2], "%02x", (unsigned int)hash[i]);
                    }
                    if (strcmp(hash_str, target_hash) == 0)
                    {
                        printf("Proceso hijo %d encontró la cadena: %s\n",
                               numero_hijo, arrayLetras);
                        exit(0); // Salir del hijo después de encontrar la coincidencia
                    }
                }
            }
        }
    }

    // Si llegamos aquí, el hijo no encontró la coincidencia
    printf("Proceso hijo %d no encontró la cadena.\n", numero_hijo);
    exit(1); // Salir del hijo sin coincidencia
}

int main()
{
    pid_t pid[NUM_HIJOS];

    // Cadena hash objetivo (cámbiala según tu necesidad)
    const char *target_hash = "pepe";

    for (int i = 0; i < NUM_HIJOS; i++)
    {
        pid[i] = fork();

        if (pid[i] < 0)
        {
            perror("Error al crear el proceso hijo");
            return 1;
        }
        else if (pid[i] == 0)
        {
            // Código del proceso hijo
            functionMD5(target_hash, i + 1);
        }
        // Código del proceso padre
    }

    // Esperar a que todos los hijos terminen
    int estado_hijo;
    pid_t hijo_terminado;
    for (int i = 0; i < NUM_HIJOS; i++)
    {
        hijo_terminado = wait(&estado_hijo);
        if (WIFEXITED(estado_hijo) && WEXITSTATUS(estado_hijo) == 0)
        {
            printf("Proceso hijo %d terminó con éxito.\n", hijo_terminado);
        }
        else
        {
            printf("Proceso hijo %d terminó sin éxito.\n", hijo_terminado);
        }
    }

    return 0;
}
