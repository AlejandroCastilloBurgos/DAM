
public class GenerarFiguras {

    public static String generarPiramide(int alto, char caracter) {
        StringBuilder piramide = new StringBuilder();

        for (int i = 0; i < alto; i++) {
            // Espacios en blanco a la izquierda
            for (int j = 0; j < alto - i - 1; j++) {
                piramide.append(" ");
            }
            // Caracteres del piso actual de la pirámide
            for (int k = 0; k < (2 * i + 1); k++) {
                piramide.append(caracter);
            }
            // Saltar a la siguiente línea si no es el último piso
            if (i < alto - 1) {
                piramide.append("\n");
            }
        }

        return piramide.toString();
    }
}
