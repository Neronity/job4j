package ru.job4j.io.zip;

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
        List<File> result = new ArrayList<>();
        Queue<File> queue = new LinkedList<>();
        queue.add(new File(root));
        while (!queue.isEmpty()) {
            File file = queue.poll();
            if (file.isDirectory()) {
                File[] a = file.listFiles();
                if (a != null) {
                    queue.addAll(Arrays.asList(a));
                }
            } else {
                String[] name = file.getName().split("\\.");
                if (!exc.contains(name[name.length - 1])) {
                    result.add(file);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Args a = new Args(args);
        Zip z = new Zip();
        z.pack(z.seekBy(a.getDirectory(), a.getExclude()), a.getOutput());
    }
}
