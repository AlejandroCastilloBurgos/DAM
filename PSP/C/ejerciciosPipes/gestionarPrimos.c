#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int esPrimo(long long num) {
    if (num < 2) {
        return 0; // No es primo
    }

    for (long long i = 2; i * i <= num; i++) {
        if (num % i == 0) {
            return 0; // No es primo
        }
    }

    return 1; // Es primo
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        fprintf(stderr, "Uso: %s <num1> <num2>\n", argv[0]);
        return EXIT_FAILURE;
    }

    long long num1 = atoll(argv[1]);
    long long num2 = atoll(argv[2]);

    pid_t pid1, pid2;
    int estado1, estado2;

    // Crear el primer proceso hijo
    pid1 = fork();

    if (pid1 < 0) {
        perror("Error al crear el primer proceso hijo");
        return EXIT_FAILURE;
    } else if (pid1 == 0) {
        // Código ejecutado por el primer proceso hijo
        printf("Hijo 1: Verificando si %lld es primo...\n", num1);
        if (esPrimo(num1)) {
            printf("Hijo 1: %lld es primo.\n", num1);
            exit(EXIT_SUCCESS);
        } else {
            printf("Hijo 1: %lld no es primo.\n", num1);
            exit(EXIT_FAILURE);
        }
    }

    // Crear el segundo proceso hijo
    pid2 = fork();

    if (pid2 < 0) {
        perror("Error al crear el segundo proceso hijo");
        return EXIT_FAILURE;
    } else if (pid2 == 0) {
        // Código ejecutado por el segundo proceso hijo
        printf("Hijo 2: Verificando si %lld es primo...\n", num2);
        if (esPrimo(num2)) {
            printf("Hijo 2: %lld es primo.\n", num2);
            exit(EXIT_SUCCESS);
        } else {
            printf("Hijo 2: %lld no es primo.\n", num2);
            exit(EXIT_FAILURE);
        }
    }

    // Código ejecutado por el proceso padre
    waitpid(pid1, &estado1, 0);
    waitpid(pid2, &estado2, 0);

    // Contar cuántos hijos indicaron que su número era primo
    int primos = (WIFEXITED(estado1) && WEXITSTATUS(estado1) == EXIT_SUCCESS) +
                 (WIFEXITED(estado2) && WEXITSTATUS(estado2) == EXIT_SUCCESS);

    printf("El total de números primos es: %d\n", primos);

    return EXIT_SUCCESS;
}
