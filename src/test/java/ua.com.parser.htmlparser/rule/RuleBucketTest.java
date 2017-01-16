package ua.com.parser.htmlparser.rule;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by vadim on 16.01.17.
 */
public class RuleBucketTest {

    @Test
    public void testCreateRule() {
        //given
        RulesBucket bucket = new RulesBucket();
        //when
        bucket.createRule("vote>=100");
        //then
        assertEquals("key='vote', condition='>=', value='100'", bucket.getRules().get(0).toString());
    }
}
