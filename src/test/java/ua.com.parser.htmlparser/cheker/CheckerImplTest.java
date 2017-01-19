package ua.com.parser.htmlparser.cheker;

import org.junit.Before;
import org.junit.Test;
import ua.com.parser.htmlparser.checker.Checker;
import ua.com.parser.htmlparser.checker.CheckerImpl;
import ua.com.parser.htmlparser.rule.Key;
import ua.com.parser.htmlparser.rule.Rule;
import ua.com.parser.htmlparser.rule.RuleImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckerImplTest {

    @Test
    public void testConvertFirst(){
        //given
        Checker checker = new CheckerImpl();
        Rule ruleA = new RuleImpl("favorite", "<", 100);
        Rule ruleB = new RuleImpl("vote", ">", 1200);
        Rule ruleC = new RuleImpl("view", "<=", 2000);
        Rule ruleD = new RuleImpl("vote", ">=", 5);
        Rule ruleE = new RuleImpl("view", "==", 1);
        Rule ruleF = new RuleImpl("favorite", "!=", 1);

        //then
        assertTrue(checker.check(ruleA,"1"));
        assertTrue(checker.check(ruleB,"1,5k"));
        assertTrue(checker.check(ruleC,"2k"));
        assertTrue(checker.check(ruleD,"5"));
        assertFalse(checker.check(ruleE,"10"));
        assertFalse(checker.check(ruleF,"1"));
    }

    @Test
    public void testConvertSecond(){
        //given
        Checker checker = new CheckerImpl();
        Rule rule = new RuleImpl("vote", "==", 1500);

        //then
        assertTrue(checker.check(rule,"1,5k"));
    }

    @Test
    public void testGetParseValue(){
        //given
        Checker checker = new CheckerImpl();

        //then
        assertEquals("favorite-wjt__counter js-favs_count", checker.getParseValue(Key.FAVORITE));
        assertEquals("voting-wjt__counter-score js-score", checker.getParseValue(Key.VOTE));
        assertEquals("views-count_post", checker.getParseValue(Key.VIEW));
    }
}
