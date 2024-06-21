import java.util.Random;

public class prueba {
    public static void main(String[] args) {
        String input = "ejemploTexto"; // Cadena de entrada para generar contraseñas
        int numPasswords = 10; // Número de contraseñas a generar

        for (int i = 0; i < numPasswords; i++) {
            System.out.println(generatePassword(input));
        }
    }

    public static String generatePassword(String input) {
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        String specialChars = "*$%&()-_";

        for (char c : input.toCharArray()) {
            // Transformación de caracteres con probabilidad del 50%
            if (random.nextBoolean()) {
                switch (c) {
                    case 'o':
                        c = '0';
                        break;
                    case 'i':
                        c = '1';
                        break;
                    case 'a':
                        c = '4';
                        break;
                    case 'e':
                        c = '3';
                        break;
                    case 't':
                        c = '7';
                        break;
                    default:
                        break;
                }
            }

            // Mayúsculas y minúsculas aleatorias con probabilidad del 50%
            if (random.nextBoolean()) {
                c = Character.toUpperCase(c);
            } else {
                c = Character.toLowerCase(c);
            }

            password.append(c);
        }

        // Añadir 2 caracteres especiales al final de cada contraseña
        for (int i = 0; i < 2; i++) {
            int index = random.nextInt(specialChars.length());
            char specialChar = specialChars.charAt(index);
            password.append(specialChar);
        }

        return password.toString();
    }
}
