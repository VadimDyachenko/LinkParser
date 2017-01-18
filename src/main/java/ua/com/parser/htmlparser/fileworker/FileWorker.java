package ua.com.parser.htmlparser.fileworker;

import java.util.List;

/**
 * Created by vadim on 13.01.17.
 */
public interface FileWorker {
    List<String> read();

    void write(List<String> links);
}
