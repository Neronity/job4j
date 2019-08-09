package ru.job4j.io.zip;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Args {
    private String directory;
    private List<String> exclude = new ArrayList<>();
    private File output;

    public Args(String[] args) {
        String lastKey = "";
        for (int i = 0; i < args.length; i++) {
            String current = args[i];
            if (current.matches("^(-.)$")) {
                lastKey = current;
                continue;
            }
            switch (lastKey) {
                case "-d":
                    directory = current;
                    break;
                case "-e":
                    String[] tmp = current.split("\\.");
                    exclude.add(tmp[tmp.length - 1]);
                    break;
                case "-o":
                    output = new File(current);
                    break;
            }
        }
    }

    public String getDirectory() {
        return directory;
    }

    public List<String> getExclude() {
        return exclude;
    }

    public File getOutput() {
        return output;
    }
}
