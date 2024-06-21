package Ejercicio3;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Path musicDirectory;

    public ClientHandler(Socket clientSocket, Path musicDirectory) {
        this.clientSocket = clientSocket;
        this.musicDirectory = musicDirectory;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String requestLine = in.readLine();
            if (requestLine == null || requestLine.isEmpty()) {
                return;
            }

            String[] requestParts = requestLine.split(" ");
            if (requestParts.length < 2) {
                sendErrorResponse(out, 400, "Bad Request");
                return;
            }

            String method = requestParts[0];
            String path = requestParts[1];

            if (method.equals("GET")) {
                if (path.startsWith("/search")) {
                    handleSearchRequest(out, path);
                } else if (path.startsWith("/download")) {
                    handleDownloadRequest(out, path);
                } else {
                    sendErrorResponse(out, 404, "Not Found");
                }
            } else {
                sendErrorResponse(out, 405, "Method Not Allowed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleSearchRequest(PrintWriter out, String path) throws IOException {
        String query = path.substring(path.indexOf('?') + 1);
        if (query.startsWith("q=")) {
            String searchTerm = query.substring(2).toLowerCase();

            try (Stream<Path> files = Files.list(musicDirectory)) {
                List<String> results = files
                        .filter(file -> file.getFileName().toString().toLowerCase().contains(searchTerm))
                        .map(file -> file.getFileName().toString())
                        .collect(Collectors.toList());

                sendResponse(out, 200, String.join("\n", results));
            }
        } else {
            sendErrorResponse(out, 400, "Bad Request");
        }
    }

    private void handleDownloadRequest(PrintWriter out, String path) throws IOException {
        String query = path.substring(path.indexOf('?') + 1);
        if (query.startsWith("file=")) {
            String fileName = query.substring(5);
            Path filePath = musicDirectory.resolve(fileName);

            if (Files.exists(filePath) && !Files.isDirectory(filePath)) {
                byte[] fileBytes = Files.readAllBytes(filePath);
                sendFileResponse(out, 200, "application/octet-stream", fileBytes);
            } else {
                sendErrorResponse(out, 404, "Not Found");
            }
        } else {
            sendErrorResponse(out, 400, "Bad Request");
        }
    }

    private void sendResponse(PrintWriter out, int statusCode, String body) {
        out.println("HTTP/1.1 " + statusCode + " OK");
        out.println("Content-Type: text/plain");
        out.println("Content-Length: " + body.length());
        out.println();
        out.println(body);
    }

    private void sendErrorResponse(PrintWriter out, int statusCode, String message) {
        out.println("HTTP/1.1 " + statusCode + " " + message);
        out.println("Content-Type: text/plain");
        out.println("Content-Length: " + message.length());
        out.println();
        out.println(message);
    }

    private void sendFileResponse(PrintWriter out, int statusCode, String contentType, byte[] fileBytes)
            throws IOException {
        out.println("HTTP/1.1 " + statusCode + " OK");
        out.println("Content-Type: " + contentType);
        out.println("Content-Length: " + fileBytes.length);
        out.println();
        out.flush();

        OutputStream os = clientSocket.getOutputStream();
        os.write(fileBytes);
        os.flush();
    }
}
