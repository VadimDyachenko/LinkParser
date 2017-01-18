package ua.com.parser.htmlparser.rule;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RuleBucketTest {

    @Test
    public void testCreateRule() {
        //given
        RulesBucket bucket = new RulesBucket();
        List<String> strings = new ArrayList<>();
        strings.add("vote>+100");
        strings.add("vote>-1");
        //when
        bucket.createRule(strings);

        //then
        assertEquals(100, bucket.getRules().get(0).getValue() );
        assertEquals(-1, bucket.getRules().get(1).getValue() );
    }

}
