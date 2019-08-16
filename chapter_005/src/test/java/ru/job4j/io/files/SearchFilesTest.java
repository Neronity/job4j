package ru.job4j.io.files;

import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.*;

public class SearchFilesTest {
    private String tmpDir = "src/test/java/ru/job4j/io/files/";

    {
        try {
            new File(tmpDir + "SearchFilesTesting").mkdirs();
            new File(tmpDir + "SearchFilesTesting/test.java").createNewFile();
            new File(tmpDir + "SearchFilesTesting/test.txt").createNewFile();
            new File(tmpDir + "SearchFilesTesting/SubSearchFilesTesting").mkdirs();
            new File(tmpDir + "SearchFilesTesting/SubSearchFilesTesting/test2.java").createNewFile();
            new File(tmpDir + "SearchFilesTesting/SubSearchFilesTesting/test3.csv").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> searchTest(String[] args) {
        List<String> result = null;
        SearchFiles sf = new SearchFiles(
                new Args(args), System.out);
        sf.init();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/java/ru/job4j/io/files/log.txt"))) {
            result = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Test
    public void whenSearchByMaskAsteriskThenSuccess() {
        assertThat(searchTest(new String[]
                        {
                                "-d",
                                tmpDir + "SearchFilesTesting",
                                "-n",
                                "*.java",
                                "-m",
                                "-o",
                                "src/test/java/ru/job4j/io/files/log.txt"
                        }),
                is(Arrays.asList(
                        tmpDir + "SearchFilesTesting/test.java",
                        tmpDir + "SearchFilesTesting/SubSearchFilesTesting/test2.java")));
    }

    @Test
    public void whenSearchByMaskQuestionMarkThenSuccess() {
        assertThat(searchTest(new String[]
                        {
                                "-d",
                                tmpDir + "SearchFilesTesting",
                                "-n",
                                "????.txt",
                                "-m",
                                "-o",
                                "src/test/java/ru/job4j/io/files/log.txt"
                        }),
                is(Collections.singletonList(tmpDir + "SearchFilesTesting/test.txt")));
    }

    @Test
    public void whenSearchByRegexThenSuccess() {
        assertThat(searchTest(new String[]
                        {
                                "-d",
                                tmpDir + "SearchFilesTesting",
                                "-n",
                                ".+\\.[jc]ava",
                                "-r",
                                "-o",
                                "src/test/java/ru/job4j/io/files/log.txt"
                        }),
                is(Arrays.asList(
                        tmpDir + "SearchFilesTesting/test.java",
                        tmpDir + "SearchFilesTesting/SubSearchFilesTesting/test2.java")));
    }

    @Test
    public void whenSearchByFullMatchThenSuccess() {
        assertThat(searchTest(new String[]
                        {
                                "-d",
                                tmpDir + "SearchFilesTesting",
                                "-n",
                                "test.java",
                                "-f",
                                "-o",
                                "src/test/java/ru/job4j/io/files/log.txt"
                        }),
                is(Collections.singletonList(
                        tmpDir + "SearchFilesTesting/test.java")));
    }

    @Test
    public void whenSearchWithNoOutputThenConsoleOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream outStream = new PrintStream(out);
        SearchFiles sf = new SearchFiles(
                new Args(new String[]{
                        "-d",
                        tmpDir + "SearchFilesTesting",
                        "-n",
                        "test.java",
                        "-f"
                }), outStream);
        sf.init();
        assertThat(out.toString(), is(tmpDir + "SearchFilesTesting/test.java" + System.lineSeparator()));
    }


    @Test
    public void whenHelpThenDisplayHelp() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream outStream = new PrintStream(out);
        SearchFiles sf = new SearchFiles(
                new Args(new String[]{
                        "-help"
                }), outStream);
        sf.init();
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
                .add("Example input: java -jar find.jar -d c:/ -n *.txt -m -o log.txt")
                .add("");
        assertThat(out.toString(), is(sj.toString()));
    }
}