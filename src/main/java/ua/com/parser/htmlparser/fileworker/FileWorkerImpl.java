package ua.com.parser.htmlparser.fileworker;

import ua.com.parser.htmlparser.rule.Rule;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FileWorkerImpl implements FileWorker {
    public List<Rule> read(String fileName) {
        String query = ""; // TODO реализовать разбор запроса
        try {
            FileInputStream input = new FileInputStream(fileName);
            int data = input.read();
            boolean notYetReadCompletely = data != -1;
            while (notYetReadCompletely) {
                query += String.valueOf((char) data);
                data = input.read();
            }
            input.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return null; // TODO разобраться что возвращает WTF Rule
    }

    public void write(List<String> links, String path) {
        try {
            FileOutputStream output = new FileOutputStream(path);
            for (String link : links) {
                output.write(link.getBytes());
            }
            output.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
