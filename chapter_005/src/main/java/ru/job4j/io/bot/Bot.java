package ru.job4j.io.bot;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bot {
    private List<String> phrases = new ArrayList<>();
    private PrintWriter writer;
    private Input input;

    public Bot(String fileName, Input input) {
        this.input = input;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            phrases = reader.lines().collect(Collectors.toList());
            writer = new PrintWriter(new FileOutputStream(fileName.replaceAll("/\\w+\\.txt$", "/chatLog.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startChatting() {
        String userInput = "";
        boolean isStopped = false;

        while (!userInput.equals("exit")) {
            if (isStopped) {
                if (userInput.equals("continue")) {
                    isStopped = false;
                    respond();
                }
            } else {
                if (userInput.equals("stop")) {
                    isStopped = true;
                } else {
                    respond();
                }
            }
            try {
                userInput = this.input.getInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
            addToLog(userInput);
        }
        writer.close();
    }

    private void addToLog(String line) {
        writer.println(line);
    }

    private void respond() {
        String response = phrases.get((int) (Math.random() * phrases.size() - 1));
        addToLog(response);
        if (this.input instanceof UserInput) {
            System.out.println(response);
        }
    }

    public static void main(String[] args) {
        new Bot("./chapter_005/src/main/java/ru/job4j/io/bot/phrases.txt", new UserInput()).startChatting();
    }
}
