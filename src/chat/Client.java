package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class Client {

    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());
    private static final int PORT = 123;
    private static final String HOST = "127.0.0.1";

    public void start() {
        try (Socket socket = new Socket(HOST, PORT);
                InputStream is = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                PrintWriter pw = new PrintWriter(out, true)) {

            ConsoleReader consoleReader = new ConsoleReader(pw);
            Thread consoleThread = new Thread(consoleReader);
            consoleThread.start();

            while (true) {
                String read = br.readLine();
                if (read == null) {
                    break;
                }
                System.out.println("Server: " + read);
            }
            consoleThread.interrupt();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static void main(String... arg) {
        Client client = new Client();
        client.start();
    }
}
