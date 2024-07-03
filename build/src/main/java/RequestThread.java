
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestThread extends Thread {

    private final Socket client;

    public RequestThread(Socket client) {
        this.client = client;
    }

    public static byte[] readFile(String filename) throws IOException {
        Path path = Paths.get(filename);
        return Files.readAllBytes(path);
        
    }

    public static String getFilename(String requestLine) {
        Pattern pattern = Pattern.compile("GET /(.*) HTTP/1.1");
        Matcher matcher = pattern.matcher(requestLine);
        if (matcher.matches()) {
            String filename = matcher.group(1);
            return filename;
        } else {
            throw new IllegalArgumentException("HTTP request was not in a supported format.");
        }
    }

    @Override
    public void run() {

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream())); client;
                BufferedOutputStream writer = new BufferedOutputStream(client.getOutputStream());) {
            try {
                String requestLine = reader.readLine();
                System.out.println(requestLine);
                String filename = getFilename(requestLine);
                byte[] body = readFile(filename);
                String type = Files.probeContentType(Path.of(filename));
                writer.write("HTTP/1.1 200 OK\n".getBytes());
                writer.write(("Content-Type: " + type + "\n").getBytes());
                writer.write("\n".getBytes());

                writer.write(body);
            } catch (NoSuchFileException ex) {
                writer.write("HTTP/1.1 404 Not Found\n".getBytes());
                writer.write("Content-Type: text/plain \n".getBytes());
                writer.write("\n".getBytes());
                writer.write("That file does not exist. \n".getBytes());
            }

        } catch (IOException ex) {
            Logger.getLogger(RequestThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
