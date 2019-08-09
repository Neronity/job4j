package ru.job4j.io.bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput implements Input {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String getInput() throws IOException {
        return reader.readLine();
    }
}