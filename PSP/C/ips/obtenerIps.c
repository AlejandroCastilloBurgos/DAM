#include <stdio.h>
#include <winsock2.h>
#include <iphlpapi.h>

#pragma comment(lib, "iphlpapi.lib")

int main() {
    // Inicializar la biblioteca de sockets de Windows
    WSADATA wsaData;
    if (WSAStartup(MAKEWORD(2, 2), &wsaData) != 0) {
        fprintf(stderr, "Error al inicializar la biblioteca de sockets\n");
        return 1;
    }

    // Obtener información sobre las interfaces de red
    ULONG outBufLen = 0;
    if (GetAdaptersAddresses(AF_UNSPEC, GAA_FLAG_INCLUDE_PREFIX, NULL, NULL, &outBufLen) != ERROR_BUFFER_OVERFLOW) {
        fprintf(stderr, "Error al obtener el tamaño del búfer necesario\n");
        WSACleanup();
        return 1;
    }

    PIP_ADAPTER_ADDRESSES pAddresses = (IP_ADAPTER_ADDRESSES *)malloc(outBufLen);
    if (pAddresses == NULL) {
        fprintf(stderr, "Error al asignar memoria para las direcciones de adaptadores\n");
        WSACleanup();
        return 1;
    }

    if (GetAdaptersAddresses(AF_UNSPEC, GAA_FLAG_INCLUDE_PREFIX, NULL, pAddresses, &outBufLen) != NO_ERROR) {
        fprintf(stderr, "Error al obtener las direcciones de adaptadores\n");
        free(pAddresses);
        WSACleanup();
        return 1;
    }

    // Mostrar información sobre las direcciones IP
    printf("Direcciones IP:\n");
    PIP_ADAPTER_ADDRESSES pCurrAddresses = pAddresses;
    while (pCurrAddresses != NULL) {
        for (PIP_ADAPTER_UNICAST_ADDRESS pUnicast = pCurrAddresses->FirstUnicastAddress; pUnicast != NULL; pUnicast = pUnicast->Next) {
            struct sockaddr *sa = pUnicast->Address.lpSockaddr;
            if (sa->sa_family == AF_INET || sa->sa_family == AF_INET6) {
                char buffer[INET6_ADDRSTRLEN];
                getnameinfo(sa, sa->sa_family == AF_INET ? sizeof(struct sockaddr_in) : sizeof(struct sockaddr_in6), buffer, sizeof(buffer), NULL, 0, NI_NUMERICHOST);
                printf("  %s\n", buffer);
            }
        }
        pCurrAddresses = pCurrAddresses->Next;
    }

    // Liberar la memoria asignada
    free(pAddresses);

    // Cerrar la biblioteca de sockets de Windows
    WSACleanup();

    return 0;
}
