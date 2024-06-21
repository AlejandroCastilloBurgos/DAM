package Ejercicio3;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger implements MensajesHTTP.MensajeListener {

    private static final String RUTA_LOG = "/home/casti/Desktop/CastilloBurgosAlejandroExamenOrdinaria/Ejercicio3/peticiones.txt";

    @Override
    public void mensajeEncontrado(String mensaje) {

        try (FileWriter writer = new FileWriter(RUTA_LOG, true)) {
            writer.write((mensaje));
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss").format(new Date());
            writer.write((timeStamp));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}