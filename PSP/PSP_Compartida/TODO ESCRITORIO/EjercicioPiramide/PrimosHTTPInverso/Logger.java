import java.io.FileWriter;



public class Logger implements PrimosHTTPInverso.PrimosListener {

    private static final String RUTA_LOG = "/var/log/primos.txt";

    @Override
    public void primoEncontrado(int primo) {
        try (FileWriter writer = new FileWriter(RUTA_LOG, true)) {
            writer.write(primo + "\n");
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
