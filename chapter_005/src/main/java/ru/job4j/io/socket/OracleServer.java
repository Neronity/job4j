package ru.job4j.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class OracleServer {
    private Socket socket;

    public OracleServer(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        String ask;
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            do {
                System.out.println("wait command ...");
                ask = in.readLine();
                if ("hello".equals(ask)) {
                    out.println("Hello, dear friend, I'm an oracle.");
                    out.println();
                } else if (!"exit".equals(ask)){
                    out.println("Some wisely put phrase.");
                    out.println();
                }
            } while (!"exit".equals(ask));
        }
    }

    public static void main(String[] args) throws IOException {
        try (Socket socket = new ServerSocket(5000).accept()) {
            new OracleServer(socket).start();
        }
    }
}
