package ua.com.parser.htmlparser.fileworker;

import java.io.File;
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

    private String input;
    private String output;

    public FileWorkerImpl(String input, String output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public List<String> read() {
        File file = new File(input);
        try {
            return Files.readAllLines(Paths.get(input), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void write(List<String> links) {
        File file = new File(output);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (PrintWriter out = new PrintWriter(file.getAbsoluteFile())) {
                for (String link : links) {
                    out.print(link + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
