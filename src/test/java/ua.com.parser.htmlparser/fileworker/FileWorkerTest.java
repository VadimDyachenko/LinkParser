package ua.com.parser.htmlparser.fileworker;

import org.junit.Test;

import java.util.List;

/**
 * Created by aleksandr
 * on 17.01.17.
 */
public class FileWorkerTest {

    @Test
    public void test_read() {

        String pathInput = "src/test/resources/input.txt";

        FileWorker fileWorker = new FileWorkerImpl();

        List<String> requests = fileWorker.read(pathInput);
        for (String str : requests) {
            System.out.println(str);
        }

    }
}
