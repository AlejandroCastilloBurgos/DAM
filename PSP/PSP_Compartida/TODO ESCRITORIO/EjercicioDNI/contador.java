public class contador {

    public static void contarCaracteres(String texto) {
        int vocales = 0, consonantes = 0, espaciosBlanco = 0, simbolosEspeciales = 0;

        // Convertir todo el texto a minúsculas para simplificar las comparaciones
        texto = texto.toLowerCase();

        for (int i = 0; i < texto.length(); i++) {
            char ch = texto.charAt(i);

            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                // Es vocal
                vocales++;
            } else if ((ch >= 'a' && ch <= 'z')) {
                // Es consonante (cualquier letra que no sea vocal)
                consonantes++;
            } else if (ch == ' ') {
                // Es espacio en blanco
                espaciosBlanco++;
            } else {
                // Es símbolo especial
                simbolosEspeciales++;
            }
        }

        System.out.println("Vocales: " + vocales);
        System.out.println("Consonantes: " + consonantes);
        System.out.println("Espacios en blanco: " + espaciosBlanco);
        System.out.println("Símbolos especiales: " + simbolosEspeciales);
    }

    public static void main(String[] args) {
        String texto = "espero que el exmen no sea muy dificil porque si no %&/())(/&)"; // Ejemplo de texto
        contarCaracteres(texto);
    }
}
