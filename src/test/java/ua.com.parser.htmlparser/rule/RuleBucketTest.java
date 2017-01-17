package ua.com.parser.htmlparser.rule;

import org.junit.Test;
import ua.com.parser.htmlparser.fileworker.FileWorker;
import ua.com.parser.htmlparser.fileworker.FileWorkerImpl;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by vadim
 * on 16.01.17.
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
    public void testCreateRule2() {

        FileWorker file = new FileWorkerImpl();
        RulesBucket bucket = new RulesBucket();
        String inputPath = "src/test/resources/input.txt";

        List<String> requests = file.read(inputPath);
        bucket.createRule(requests);
    }
}
