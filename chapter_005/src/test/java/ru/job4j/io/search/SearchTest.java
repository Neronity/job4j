package ru.job4j.io.search;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SearchTest {
    private String tmpDir = "src/test/java/ru/job4j/io/search";
    private Search s = new Search();

    @Test
    public void whenThen() {
        new File(tmpDir + "/dir1").mkdirs();
        new File(tmpDir + "/dir1/dir2").mkdirs();
        try {
            new File(tmpDir + "/dir1/file1.scv").createNewFile();
            new File(tmpDir + "/dir1/file1.txt").createNewFile();
            new File(tmpDir + "/dir1/dir2/file2.txt").createNewFile();
            new File(tmpDir + "/dir1/dir2/file2.cs").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<File> result = s.files(tmpDir + "/dir1", Arrays.asList("txt", "cs"));
        assertThat(result.size(), is(3));
        assertThat(result.stream().map(File::getName).collect(Collectors.toList()),
                is(Arrays.asList("file1.txt", "file2.txt", "file2.cs")));
    }

}