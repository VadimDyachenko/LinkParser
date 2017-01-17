package ua.com.parser.htmlparser.rule;

import org.junit.Test;
import ua.com.parser.htmlparser.fileworker.FileWorker;
import ua.com.parser.htmlparser.fileworker.FileWorkerImpl;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by vadim on 16.01.17.
 */
public class RuleBucketTest {

//    @Test
//    public void testCreateRule() {
//        //given
//        RulesBucket bucket = new RulesBucket();
//        //when
//        bucket.createRule("vote>=100");
//        //then
//        assertEquals("key='vote', condition='>=', value='100'", bucket.getRules().get(0).toString());
//    }

    @Test
    public void test_read() {
        FileWorker fileWorker = new FileWorkerImpl();

        List<String> requests = fileWorker.read("/home/aleksandr/java/workspace/LinkParser/Input.txt");
        for (String str : requests) {
            System.out.println(str);
        }

        fileWorker.write(requests, "/home/aleksandr/java/workspace/LinkParser/output.txt");
    }
}
