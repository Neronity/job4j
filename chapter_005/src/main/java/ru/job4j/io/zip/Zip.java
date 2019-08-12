package ru.job4j.io.zip;

import ru.job4j.io.search.Search;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void pack(List<File> source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File f : source) {
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(f))) {
                    zip.putNextEntry(new ZipEntry(f.getPath()));
                    zip.write(out.readAllBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<File> seekBy(String root, List<String> exc) {
        Search search = new Search();
        return search.filterExtensions(search.searchAll(root), s -> !exc.contains(s));
    }

    public static void main(String[] args) {
        Args a = new Args(args);
        Zip z = new Zip();
        z.pack(z.seekBy(a.getDirectory(), a.getExclude()), a.getOutput());
    }
}
