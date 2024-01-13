#include <stdio.h>

// Función para calcular la nota media
float calcularMedia(const float notas[], int cantidad) {
    float suma = 0.0;

    // Sumar todas las notas
    for (int i = 0; i < cantidad; ++i) {
        suma += notas[i];
    }

    // Calcular la media
    return suma / cantidad;
}

// Función para encontrar la nota más alta
float encontrarNotaMaxima(const float notas[], int cantidad) {
    float maxima = notas[0];

    // Buscar la nota más alta
    for (int i = 1; i < cantidad; ++i) {
        if (notas[i] > maxima) {
            maxima = notas[i];
        }
    }

    return maxima;
}

// Función para encontrar la nota más baja
float encontrarNotaMinima(const float notas[], int cantidad) {
    float minima = notas[0];

    // Buscar la nota más baja
    for (int i = 1; i < cantidad; ++i) {
        if (notas[i] < minima) {
            minima = notas[i];
        }
    }

    return minima;
}

int main() {
    // Declaración de variables
    float notas[5]; // Array para almacenar las notas
    const int cantidadExamenes = 5;

    // Solicitar al usuario ingresar las notas de los exámenes
    for (int i = 0; i < cantidadExamenes; ++i) {
        printf("Ingrese la nota del examen %d: ", i + 1);
        scanf("%f", &notas[i]);
    }

    // Mostrar la lista de notas
    printf("\nLista de notas:\n");
    for (int i = 0; i < cantidadExamenes; ++i) {
        printf("Examen %d: %.2f\n", i + 1, notas[i]);
    }

    // Calcular y mostrar la nota media
    float media = calcularMedia(notas, cantidadExamenes);
    printf("\nNota media: %.2f\n", media);

    // Encontrar y mostrar la nota más alta
    float notaMaxima = encontrarNotaMaxima(notas, cantidadExamenes);
    printf("Nota más alta: %.2f\n", notaMaxima);

    // Encontrar y mostrar la nota más baja
    float notaMinima = encontrarNotaMinima(notas, cantidadExamenes);
    printf("Nota más baja: %.2f\n", notaMinima);

    // Determinar si ha aprobado o no
    if (media >= 5.0) {
        printf("\n¡Felicidades! Has aprobado.\n");
    } else {
        printf("\nLo siento, no has aprobado.\n");
    }

    return 0; // Salir con éxito
}
