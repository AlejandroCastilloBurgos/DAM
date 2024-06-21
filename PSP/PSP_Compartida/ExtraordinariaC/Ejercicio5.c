/* Crea un programa en C que simule un simple sistema de cálculo con un proceso
 padre que interactúa con el usuario y un proceso hijo que realiza los cálculos.
  El padre mostrará un menú para que el usuario seleccione entre sumar,
 restar o salir, y enviará los detalles de la operación al hijo a través de una tubería.
 El hijo procesará estos datos y enviará el resultado de vuelta al padre a través de otra tubería.*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <time.h>
#include <stdio.h>

#define READ_END 0
#define WRITE_END 1

int main()
{
    int pipefd_parent_to_child[2];
    int pipefd_child_to_parent[2];
    pid_t pid;

    // Crear los pipes
    if (pipe(pipefd_parent_to_child) == -1 || pipe(pipefd_child_to_parent) == -1)
    {
        perror("pipe");
        exit(EXIT_FAILURE);
    }

    // Crear el proceso hijo
    pid = fork();

    if (pid == -1)
    {
        perror("fork");
        exit(EXIT_FAILURE);
    }
    else if (pid == 0)
    {                                     // Código del proceso hijo
        close(pipefd_parent_to_child[1]); // Cerrar el extremo de escritura del pipe del padre al hijo
        close(pipefd_child_to_parent[0]); // Cerrar el extremo de lectura del pipe del hijo al padre

        int numero;
        int operando1;
        int operando2;

        while (1)
        { // Bucle infinito para procesar múltiples solicitudes del padre
            // Leer el número enviado por el padre
            read(pipefd_parent_to_child[0], &numero, sizeof(int));

            if (numero == 0)
            {
                break; // Salir del bucle si se recibe 0
            }

            // Leer los operandos
            read(pipefd_parent_to_child[0], &operando1, sizeof(int));
            read(pipefd_parent_to_child[0], &operando2, sizeof(int));

            // Realizar la operación solicitada
            int resultado;
            switch (numero)
            {
            case 1:
                resultado = operando1 + operando2;
                break;
            case 2:
                resultado = operando1 - operando2;
                break;
            case 3:
                resultado = operando1 * operando2;
                break;
            case 4:
                resultado = operando1 / operando2;
                break;
            default:
                printf("Número no válido\n");
                exit(EXIT_FAILURE);
            }

            // Enviar el resultado al padre
            write(pipefd_child_to_parent[1], &resultado, sizeof(int));
        }

        // Cerrar los pipes que no se usan en el proceso hijo
        close(pipefd_parent_to_child[0]);
        close(pipefd_child_to_parent[1]);

        exit(EXIT_SUCCESS);
    }
    else
    {                                     // Código del proceso padre
        close(pipefd_parent_to_child[0]); // Cerrar el extremo de lectura del pipe del padre al hijo
        close(pipefd_child_to_parent[1]); // Cerrar el extremo de escritura del pipe del hijo al padre

        int numero, operando1, operando2;

        // Bucle para seguir pidiendo número, operador1 y operador2 hasta que se reciba el 0 en número
        do
        {
            // Solicitar al usuario la operación deseada
            printf("Elige 0 para salir, 1 para suma, 2 para resta, 3 para multiplicación, 4 para división:\n");
            scanf("%d", &numero);

            if (numero != 0)
            {
                printf("Introduce el primer operando:\n");
                scanf("%d", &operando1);
                printf("Introduce el segundo operando:\n");
                scanf("%d", &operando2);

                // Enviar el número al hijo
                write(pipefd_parent_to_child[1], &numero, sizeof(int));

                // Enviar los operandos al hijo
                write(pipefd_parent_to_child[1], &operando1, sizeof(int));
                write(pipefd_parent_to_child[1], &operando2, sizeof(int));

                // Esperar a que el hijo termine y leer el resultado
                int resultado;
                read(pipefd_child_to_parent[0], &resultado, sizeof(int));
                printf("Resultado: %d\n", resultado);
            }
        } while (numero != 0);

        // aqui habria que mandar un kill al ppid
        pid_t pid_hijo = getpid(); // Obtiene el PID del proceso hijo
        printf("Proceso padre (%d): Enviando señal SIGKILL al proceso hijo (%d).\n", getpid(), pid_hijo);

        // Envia la señal SIGKILL al proceso padre
        if (kill(pid_hijo, SIGKILL) == -1)
        {
            perror("Error al enviar la señal SIGKILL al proceso padre");
            exit(EXIT_FAILURE);
        }

        // Cerrar los pipes que no se usan en el proceso padre
        close(pipefd_parent_to_child[1]);
        close(pipefd_child_to_parent[0]);

        wait(NULL); // Esperar a que el hijo termine

        exit(EXIT_SUCCESS);
    }
}
