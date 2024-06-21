import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GestorEntradas {
    private Map<String, Boolean> entradas;

    public GestorEntradas(String archivo) {
        entradas = new HashMap<>();
        cargarEntradas(archivo);
    }

    private void cargarEntradas(String archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                entradas.put(partes[0], partes[1].equals("0"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Entradas cargadas: " + entradas);

    }

    public String procesarEntrada(String codigo) {
        // Asume que `entradas` es tu HashMap que almacena el código y un booleano para
        // el estado.
        codigo = codigo.trim().toUpperCase(); // Ajusta según sea necesario
        if (entradas.containsKey(codigo)) {
            boolean estaConsumida = entradas.get(codigo);
            if (estaConsumida) {
                return "CANJEADA";
            } else {
                entradas.put(codigo, true); // Marcar como consumida
                return "VÁLIDA";
            }
        } else {
            return "NOENCONTRADA";
        }
    }

    public Map<String, Boolean> getEntradas() {
        return entradas;
    }

}
