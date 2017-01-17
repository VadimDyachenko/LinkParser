package ua.com.parser.htmlparser.fileworker;

import ua.com.parser.htmlparser.rule.Rule;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by vadim on 13.01.17.
 */
public interface FileWorker {
    List<String> read(String fileName) throws FileNotFoundException;

    void write(List<String> links, String path);
}
