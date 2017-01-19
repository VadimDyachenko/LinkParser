package ua.com.parser.htmlparser.fileworker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by aleksandr
 * on 17.01.17.
 */
public class FileWorkerTest {

    @Test
    public void testRead() {
        //given
        FileWorker fileWorker = new FileWorkerImpl("src/test/resources/input.txt", null);
        //when
        List<String> requests = fileWorker.read();
        //then
        assertEquals("[favorite>+100, vote>=100]", requests.toString());
    }

    @Test
    public void testWrite() {
        //given
        FileWorker fileWorker = new FileWorkerImpl("src/test/resources/output.txt",
                "src/test/resources/output.txt");
        List<String> links = new ArrayList<>();
        links.add("https://habrahabr.ru/post/852/");
        //when
        fileWorker.write(links);
        List<String> actual = fileWorker.read();

        //then
        assertEquals("[https://habrahabr.ru/post/852/]", actual.toString());

    }
}
