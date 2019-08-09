package ru.job4j.io.bot;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BotTest {
    private String phrasesPath = "./src/test/java/ru/job4j/io/bot/phrase1.txt";

    @Test
    public void whenChattingThenSuccessfulLogging() {
        Bot b = new Bot(phrasesPath, new TestInput(Arrays.asList("1", "2", "stop", "1", "continue", "2")));
        b.startChatting();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(phrasesPath.replaceAll("/\\w+\\.txt$", "/chatLog.txt")))) {
            assertThat(reader.lines().collect(Collectors.toList()),
                    is(Arrays.asList(
                            "response",
                            "1",
                            "response",
                            "2",
                            "response",
                            "stop",
                            "1",
                            "continue",
                            "response",
                            "2",
                            "response",
                            "exit")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}