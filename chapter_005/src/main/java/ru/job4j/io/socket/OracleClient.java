package ru.job4j.io.socket;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class OracleClient {
    private Socket socket;
    private BufferedReader input;
    private PrintStream output;

    public OracleClient(Socket socket, BufferedReader input, PrintStream output) {
        this.output = output;
        this.socket = socket;
        this.input = input;
    }

    public void start() {
        String userInput;
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            do {
                userInput = input.readLine();
                out.println(userInput);
                String str;
                while ((str = in.readLine()) != null && !str.isEmpty()) {
                    output.println(str);
                }
            } while (!userInput.equals("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new OracleClient(new Socket("127.0.0.1", 5000),
                new BufferedReader(new InputStreamReader(System.in)),
                System.out)
                .start();
    }
}
