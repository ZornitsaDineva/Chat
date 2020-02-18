package chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 123;
    private static final String HOST = "127.0.0.1";

    public void start() {
        try (ServerSocket ss = new ServerSocket()) {
            ss.bind(new InetSocketAddress(HOST, PORT));
            while (true) {
                Socket socket = ss.accept();
                ClientProcessor clientProcessor = new ClientProcessor(socket);
                Thread clientThread = new Thread(clientProcessor, "Client");
                clientThread.start();
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();        
    }

}
