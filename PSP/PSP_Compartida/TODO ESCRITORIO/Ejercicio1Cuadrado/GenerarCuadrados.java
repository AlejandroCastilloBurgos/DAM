public class GenerarCuadrados {

    public static String GeneradorCuadrados(int alto, int ancho, char caracter) {
        StringBuilder cuadrado = new StringBuilder(); // Usamos StringBuilder para construir el cuadrado

        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                if (i == 0 || i == alto - 1 || j == 0 || j == ancho - 1) {
                    cuadrado.append(caracter);
                } else {
                    cuadrado.append(" ");
                }
            }
            if (i < alto - 1) {
                cuadrado.append("\n");
            }
        }

        return cuadrado.toString(); // Convertir el StringBuilder a String y retornarlo
    }
}
