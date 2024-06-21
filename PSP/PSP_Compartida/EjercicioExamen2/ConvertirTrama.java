public class ConvertirTrama {
    public static String contarCaracteres(char caracter1, char caracter2, char caracter3, char caracter4,
            char caracter5) {
        StringBuilder trama = new StringBuilder(); // Usamos StringBuilder para construir el cuadrado

        if (caracter1 == 'b' || caracter1 == 'B') {
            trama.append("   alerta");
        } else if (caracter1 == 'n' || caracter1 == 'N') {
            trama.append("  nada");
        } else if (caracter1 == 'o' || caracter1 == 'O') {
            trama.append("   alerta");
        }

        if (caracter2 == 'b' || caracter2 == 'B') {
            trama.append("   alerta");
        } else if (caracter2 == 'n' || caracter2 == 'N') {
            trama.append("   nada");
        } else if (caracter2 == 'o' || caracter2 == 'O') {
            trama.append("     alerta");
        }

        if (caracter3 == 'b' || caracter3 == 'B') {
            trama.append("   alerta");
        } else if (caracter3 == 'n' || caracter3 == 'N') {
            trama.append("  nada");
        } else if (caracter3 == 'o' || caracter3 == 'O') {
            trama.append("  alerta");
        }

        if (caracter4 == 'b' || caracter4 == 'B') {
            trama.append("   alerta");
        } else if (caracter4 == 'n' || caracter4 == 'N') {
            trama.append("   nada");
        } else if (caracter4 == 'o' || caracter4 == 'O') {
            trama.append("  alerta");
        }

        if (caracter5 == 'b' || caracter5 == 'B') {
            trama.append("   alerta");
        } else if (caracter5 == 'n' || caracter5 == 'N') {
            trama.append("   nada");
        } else if (caracter5 == 'o' || caracter5 == 'O') {
            trama.append("  alerta");
        }

        return trama.toString();

    }
}
