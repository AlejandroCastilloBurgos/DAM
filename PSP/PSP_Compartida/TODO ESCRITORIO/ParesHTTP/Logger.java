import java.io.FileWriter;

public class Logger implements ParesHTTP.ParesListener {

    private static final String RUTA_LOG = "/var/log/pares.txt";

    @Override
    public void parEncontrado(int par) {
        try (FileWriter writer = new FileWriter(RUTA_LOG, true)) {
            writer.write(Integer.toString(par));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
