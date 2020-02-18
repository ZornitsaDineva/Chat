/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ClientProcessor implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(ClientProcessor.class.getName());
    private Socket socket;

    ClientProcessor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        LOGGER.info("Client conected!");
        try (Socket socket = this.socket;
                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                OutputStream out = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(out, true)) {

            ConsoleReader consoleReader = new ConsoleReader(pw);
            Thread consoleThread = new Thread(consoleReader);
            consoleThread.start();
            
            while (true) {
                String read = br.readLine();
                if (read == null) {
                    break;
                }
                System.out.println("Client: " + read);

                if (read.equalsIgnoreCase("bye")) {
                    break;
                }
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        LOGGER.info("Client disconected!");
    }

}
