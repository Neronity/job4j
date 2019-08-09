package ru.job4j.io.unavailable;

import java.io.*;
import java.util.StringJoiner;

public class Analize {

    public void unavailable(String source, String target) {
        final StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
        PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            reader.lines().map(line -> line.split(" ")).forEach(e -> {
                if (sb.length() == 0) {
                    if (e[0].equals("400") || e[0].equals("500")) {
                        sb.append(e[1]).append(";");
                    }
                } else {
                    if (e[0].equals("200") || e[0].equals("300")) {
                        sb.append(e[1]);
                        writer.println(sb);
                        sb.delete(0, sb.length());
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
