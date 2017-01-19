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

    @Test
    public void testCreateRule() {
        //given
        FileWorker file = new FileWorkerImpl("src/test/resources/input.txt", null);
        RulesBucket bucket = new RulesBucket();
        List<String> requests = file.read();
        //when
        bucket.createRule(requests);

        //then
        assertEquals(100, bucket.getRules().get(0).getValue() );
    }
}
