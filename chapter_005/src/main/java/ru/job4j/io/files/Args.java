package ru.job4j.io.files;

import ru.job4j.io.search.Search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Args {

    private String directory;
    private String filter;
    private SearchType searchType;
    private File output = null;
    private boolean isHelp = false;

    public Args(String[] args) {
        parse(args);
    }

    private void parse(String[] args) {
        String lastKey = "";
        for (int i = 0; i < args.length; i++) {
            String current = args[i];
            if (current.matches("^(-.)$")) {
                lastKey = current;
                if (!current.matches("^(-[mrf])$")) continue;
            }
            if (current.equals("-help")) {
                isHelp = true;
                break;
            }
            switch (lastKey) {
                case "-d":
                    this.directory = current;
                    break;
                case "-n":
                    this.filter = current;
                    break;
                case "-m":
                    this.searchType = new MaskSearch();
                    break;
                case "-r":
                    this.searchType = new RegexSearch();
                    break;
                case "-f":
                    this.searchType = new FullMatchSearch();
                    break;
                case "-o":
                    this.output = new File(current);
                    break;
                default:
            }
        }
    }

    public String getFilter() {
        return this.filter;
    }

    public SearchType getSearchType() {
        return this.searchType;
    }

    public String getDirectory() {
        return this.directory;
    }

    public File getOutput() {
        return this.output;
    }

    public boolean isHelp() {
        return this.isHelp;
    }

    class MaskSearch extends SearchType {
        private String regex = getFilter()
                .replaceAll("\\*", ".+")
                .replaceAll("\\?", ".");

        @Override
        boolean filter(File f) {
            return f.getName().matches(this.regex);
        }
    }

    class FullMatchSearch extends SearchType {

        @Override
        boolean filter(File f) {
            return f.getName().equals(getFilter());
        }
    }

    class RegexSearch extends SearchType {

        @Override
        boolean filter(File f) {
            return f.getName().matches(getFilter());
        }
    }
}
