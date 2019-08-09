package ru.job4j.io.zip;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ZipTest {

    private String tmpDir = System.getProperty("java.io.tmpdir");
    private File f1;
    private File f2;
    private File f3;
    private File f4;
    private File f5;
    private File f6;
    {
        try {
            f1 = new File(tmpDir + "ZipTest");
            f1.mkdirs();
            f2 = new File(tmpDir + "ZipTest/test.java");
            f2.createNewFile();
            f3 = new File(tmpDir + "ZipTest/test.txt");
            f3.createNewFile();
            f4 = new File(tmpDir + "ZipTest/SubZipTest");
            f4.mkdirs();
            f5 = new File(tmpDir + "ZipTest/SubZipTest/test2.java");
            f5.createNewFile();
            f6 = new File(tmpDir + "ZipTest/SubZipTest/test3.csv");
            f6.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenSearchThenSuccess() {
        Zip z = new Zip();
        List<File> fList = z.seekBy(tmpDir + "ZipTest", Arrays.asList("txt", "csv"));
        assertThat(fList
                .stream()
                .map(File::getName)
                .collect(Collectors.toList()), is(Arrays.asList("test.java", "test2.java")));
    }

    @Test
    public void whenCreateNewZipThenSuccess() {
        Args a = new Args(new String[] {"-d",
                tmpDir + "ZipTest",
                "-e",
                "txt",
                "csv",
                "-o",
                tmpDir + "ZipTestOutput.zip"});
        Zip z = new Zip();
        List<String> result = new ArrayList<>();
        z.pack(z.seekBy(a.getDirectory(), a.getExclude()), a.getOutput());
        try (ZipInputStream zip = new ZipInputStream(new FileInputStream(a.getOutput()))) {
            ZipEntry entry = zip.getNextEntry();
            while (entry != null) {
                result.add(entry.getName());
                entry = zip.getNextEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(result.size(), is(2));
        assertThat(result.contains(tmpDir + "ZipTest/SubZipTest/test2.java"), is(true));
        assertThat(result.contains(tmpDir + "ZipTest/test.java"), is(true));
        assertThat(result.contains(tmpDir + "ZipTest/test.txt"), is(false));
        assertThat(result.contains(tmpDir + "ZipTest/SubZipTest/test3.csv"), is(false));
    }
}