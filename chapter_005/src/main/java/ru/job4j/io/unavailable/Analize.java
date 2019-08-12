package ru.job4j.io.unavailable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Analize {

    private static final StringBuilder SB = new StringBuilder();
    private static final String LN = System.lineSeparator();

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            reader.lines().map(line -> line.split(" ")).forEach(this::parseLogs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeParsedLogs(target);
    }

    private void parseLogs(String[] s) {
        if (SB.length() == 0 || SB.charAt(SB.length() - 1) != ';') {
            if (s[0].equals("400") || s[0].equals("500")) {
                SB.append(s[1]).append(";");
            }
        } else {
            if (s[0].equals("200") || s[0].equals("300")) {
                SB.append(s[1]).append(LN);
            }
        }
    }

    private void writeParsedLogs(String target) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(target))) {
            writer.write(SB.toString());
        } catch (FileNotFoundException e) {
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
