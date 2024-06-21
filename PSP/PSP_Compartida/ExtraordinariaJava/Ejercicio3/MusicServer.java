package Ejercicio3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MusicServer {
    private final Path musicDirectory;
    private final int port;

    public MusicServer(Path musicDirectory, int port) {
        this.musicDirectory = musicDirectory;
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Music server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket, musicDirectory)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java MusicServer <directory> <port>");
            System.exit(1);
        }

        Path musicDirectory = Paths.get(args[0]);
        int port = Integer.parseInt(args[1]);

        if (!musicDirectory.toFile().exists() || !musicDirectory.toFile().isDirectory()) {
            System.err.println("Invalid directory: " + musicDirectory);
            System.exit(1);
        }

        MusicServer server = new MusicServer(musicDirectory, port);
        server.start();
    }
}
