package ua.com.parser.htmlparser.rule;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RuleImplTest {

    @Test
    public void RuleImpl() {
        //given
        Rule rule = new RuleImpl(Key.FAVORITE, "==", 100);

        //then
        assertEquals(Key.FAVORITE, rule.getKey());
        assertEquals("==", rule.getCondition());
        assertEquals(100, rule.getValue());
    }
}
