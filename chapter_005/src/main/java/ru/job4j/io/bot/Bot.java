package ru.job4j.io.bot;

import java.io.*;
import java.nio.Buffer;
import java.util.*;
import java.util.stream.Collectors;

public class Bot {
    private List<String> phrases = new ArrayList<>();
    private PrintWriter writer;
    private Input input;
    private boolean isStopped = false;
    private Map<String, Action> actionsList = new HashMap<>();

    public Bot(String fileName, Input input) {
        this.input = input;
        this.actionsList.put("continue", new ContinueAction());
        this.actionsList.put("other", new OtherAction());
        this.actionsList.put("stop", new StopAction());
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            phrases = reader.lines().collect(Collectors.toList());
            writer = new PrintWriter(new FileOutputStream(fileName.replaceAll("/\\w+\\.txt$", "/chatLog.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startChatting() {
        String userInput = "";
        while (!userInput.equals("exit")) {
            Optional<Action> a = Optional.ofNullable(this.actionsList.get(userInput));
            a.orElse(this.actionsList.get("other")).execute();
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

    private class StopAction implements Action {

        public void execute() {
            isStopped = true;
        }
    }

    private class ContinueAction implements Action {

        public void execute() {
            isStopped = false;
            respond();
        }
    }

    private class OtherAction implements Action {

        public void execute() {
            if (!isStopped) respond();
        }
    }

    public static void main(String[] args) {
        new Bot("./chapter_005/src/main/java/ru/job4j/io/bot/phrases.txt", new UserInput()).startChatting();
    }
}
