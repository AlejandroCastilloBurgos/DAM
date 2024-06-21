// md6

#include <stdio.h>
#include <string.h>
#include <openssl/md5.h>
#include <openssl/evp.h>

#define PRINCIPIOABCEDARIO 'a'
#define FINALABCEDARIO 'z'

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

void functionMD5(const char *target_hash)
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
                    for (arrayLetras[4] = PRINCIPIOABCEDARIO; arrayLetras[4] <= FINALABCEDARIO; arrayLetras[4]++)
                    {
                        for (arrayLetras[5] = PRINCIPIOABCEDARIO; arrayLetras[5] <= FINALABCEDARIO; arrayLetras[5]++)
                        {
                            arrayLetras[6] = '\0'; // Asegúrate de que la cadena esté terminada en nulo
                            md5_hash(arrayLetras, hash);

                            char hash_str[33];
                            for (int i = 0; i < 16; i++)
                            {
                                sprintf(&hash_str[i * 2], "%02x", (unsigned int)hash[i]);
                            }
                            if (strcmp(hash_str, target_hash) == 0)
                            {
                                printf("Texto encontrado: %s\n", arrayLetras);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
    printf("Texto no encontrado.\n");
}

int main()
{
    char target_hash[] = "2a6bf9f73841746e4f6b1f636d0412a2"; // Cambia esto al hash que estás buscando
    functionMD5(target_hash);

    return 0;
}
// gcc -o mio3 mio3.c -lssl -lcrypto