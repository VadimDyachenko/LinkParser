package ua.com.parser.htmlparser.fileworker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by vadim on 15.01.17.
 */
public class FileWorkerImpl implements FileWorker {

    @Override
    public List<String> read(String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                throw new FileNotFoundException("File not found!");
            }
            return Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
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
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
