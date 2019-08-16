package ru.job4j.io.files;

import ru.job4j.io.search.Search;

import java.io.*;
import java.util.List;
import java.util.StringJoiner;

public class SearchFiles {
    final private Args args;
    final private PrintStream userOutput;

    public SearchFiles(Args args, PrintStream userOutput) {
        this.args = args;
        this.userOutput = userOutput;
    }

    public void search() {
        Search s = new Search();
        List<File> fileList = s.searchAll(this.args.getDirectory());
        fileList = this.args.getSearchType().applyFilter(fileList, this.args.getSearchType()::filter);
        try (PrintStream writer = this.args.getOutput() != null ?
                new PrintStream(new FileOutputStream(this.args.getOutput())) :
                this.userOutput) {
            fileList.forEach(writer::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void printHelp() {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        sj.add("-d root directory")
                .add("-n file name (mask, full name or regex)")
                .add("-m find by file mask")
                .add("-r find by regex")
                .add("-f find by full match")
                .add("-o file for result output")
                .add("")
                .add("In case -o is not set output will be displayed in console")
                .add("")
                .add("Example input: java -jar find.jar -d c:/ -n *.txt -m -o log.txt");
        this.userOutput.println(sj.toString());
    }

    public void init() {
        if (this.args.isHelp()) {
            this.printHelp();
        } else {
            this.search();
        }
    }

    public static void main(String[] args) {
        Args a = new Args(args);
        new SearchFiles(a, System.out).init();
    }
}
