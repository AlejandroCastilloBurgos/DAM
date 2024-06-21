import java.io.FileWriter;

public class Logger implements PrimosHTTP.PrimosListener {

    private static final String RUTA_LOG = "/var/log/primos.txt";

    @Override
    public void primoEncontrado(int primo) {

        try (FileWriter writer = new FileWriter(RUTA_LOG, true)) {
            writer.write(Integer.toString(primo));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}