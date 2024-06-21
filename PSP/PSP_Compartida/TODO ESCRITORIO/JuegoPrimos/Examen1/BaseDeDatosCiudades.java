package Examen1;

import java.util.HashMap;
import java.util.Map;

public class BaseDeDatosCiudades {
    private static Map<String, Boolean> ciudades = new HashMap<>();

    static {
        // Inicializa el mapa de ciudades
        inicializarCiudades();
    }

    private static void inicializarCiudades() {
        // Aquí se añaden las ciudades a la "base de datos"
        ciudades.put("MADRID", true);
        ciudades.put("Barcelona", true);
        ciudades.put("LISBOA", true);
        ciudades.put("PARIS", true);
        ciudades.put("LONDRES", true);
        ciudades.put("NuevaYork", false);
        ciudades.put("BERLIN", true);
        ciudades.put("ROMA", true);
        ciudades.put("AMSTERDAM", true);
        ciudades.put("DUBLIN", true);
        ciudades.put("PRAGA", true);
        ciudades.put("BRUSELAS", true);
        ciudades.put("VIENA", true);
        ciudades.put("BUDAPEST", true);
        ciudades.put("ESTOCOLMO", true);
        ciudades.put("TOKIO", true);
        ciudades.put("BEIJING", true);
        ciudades.put("Sidney", false);
        ciudades.put("CIUDADDEMEXICO", true);
        ciudades.put("BUENOSAIRES", true);
        ciudades.put("Valencia", false);
        ciudades.put("Malaga", false);
        ciudades.put("OSAKA", false);
        ciudades.put("Liverpool", false);
        ciudades.put("Marsella", false);
        ciudades.put("Florencia", false);
        ciudades.put("SanFrancisco", false);
        ciudades.put("Melbourne", false);
        ciudades.put("LasVegas", false);
        ciudades.put("Boston", false);
    }

    // Método para consultar el estado de pacificación de una ciudad
    public static String consultarCiudad(String ciudad) {
        Boolean pacificada = ciudades.get(ciudad.toUpperCase());
        System.out.print(pacificada);
        if (pacificada == null) {
            return "404 - No encontrado";
        } else {
            return pacificada ? "200 - Pacificada" : "200 - Salvaje";
        }
    }

}
