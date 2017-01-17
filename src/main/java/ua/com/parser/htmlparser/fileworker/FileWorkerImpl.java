package ua.com.parser.htmlparser.fileworker;

import ua.com.parser.htmlparser.rule.Rule;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileWorkerImpl implements FileWorker {

    public List<String> read(String fileName) {

        try {

            return Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(String.format("File %s is not read: %s", fileName, e.getMessage()));
        }
    }

    public void write(List<String> links, String path) {
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (PrintWriter out = new PrintWriter(file.getAbsoluteFile())) {
                for (String link : links) {
                    out.print(link);
                }
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
