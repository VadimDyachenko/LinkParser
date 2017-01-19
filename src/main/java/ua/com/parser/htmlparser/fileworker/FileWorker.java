package ua.com.parser.htmlparser.fileworker;

import java.util.List;

public interface FileWorker {
    List<String> read();

    void write(List<String> links);
}
