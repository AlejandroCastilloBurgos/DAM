#include <stdio.h>

#define NUM_EXAMENES 5

// Función para calcular la nota media
float calcularMedia(const float notas[])
{
    float suma = 0;

    for (int i = 0; i < NUM_EXAMENES; i++)
    {
        suma += notas[i];
    }

    return suma / NUM_EXAMENES;
}

// Función para determinar si ha aprobado o no
void determinarAprobacion(const float notas[])
{
    printf("\nNotas y Aprobación:\n");

    for (int i = 0; i < NUM_EXAMENES; i++)
    {
        printf("Examen %d: %.2f - %s\n", i + 1, notas[i], (notas[i] >= 5.0) ? "Aprobado" : "No Aprobado");
    }
}

// Función para encontrar la nota más alta
float encontrarNotaMaxima(const float notas[])
{
    float maxima = notas[0];

    for (int i = 1; i < NUM_EXAMENES; i++)
    {
        if (notas[i] > maxima)
        {
            maxima = notas[i];
        }
    }

    return maxima;
}

// Función para encontrar la nota más baja
float encontrarNotaMinima(const float notas[])
{
    float minima = notas[0];

    for (int i = 1; i < NUM_EXAMENES; i++)
    {
        if (notas[i] < minima)
        {
            minima = notas[i];
        }
    }

    return minima;
}

int main()
{
    float notas[NUM_EXAMENES];

    // Solicitar al usuario ingresar las notas de los exámenes
    printf("Ingrese las notas de los 5 exámenes:\n");

    for (int i = 0; i < NUM_EXAMENES; i++)
    {
        printf("Examen %d: ", i + 1);
        scanf("%f", &notas[i]);
    }

    // Mostrar la lista de notas y aprobación
    determinarAprobacion(notas);

    // Calcular y mostrar la nota media
    float media = calcularMedia(notas);
    printf("\nNota Media: %.2f\n", media);

    // Encontrar y mostrar la nota más alta y más baja
    float maxima = encontrarNotaMaxima(notas);
    float minima = encontrarNotaMinima(notas);
    printf("Nota Más Alta: %.2f\n", maxima);
    printf("Nota Más Baja: %.2f\n", minima);

    return 0;
}
