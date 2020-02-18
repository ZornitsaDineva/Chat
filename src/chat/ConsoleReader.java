/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author HP-OMEN 17-AN014NU
 */
class ConsoleReader implements Runnable {

    private PrintWriter pw;

    public ConsoleReader(PrintWriter pw) {
        this.pw = pw;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            pw.println(line);
        }
    }

}
