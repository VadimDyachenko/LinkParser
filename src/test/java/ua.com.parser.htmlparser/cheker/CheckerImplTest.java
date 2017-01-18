package ua.com.parser.htmlparser.cheker;

import org.junit.Test;
import ua.com.parser.htmlparser.checker.Checker;
import ua.com.parser.htmlparser.checker.CheckerImpl;
import ua.com.parser.htmlparser.rule.Rule;
import ua.com.parser.htmlparser.rule.RuleImpl;

import static org.junit.Assert.assertTrue;

public class CheckerImplTest {

    @Test
    public void testConvertFirst(){
        //given
        Checker checker = new CheckerImpl();
        Rule rule = new RuleImpl("favorite", ">", 100);

        //then
        assertTrue(checker.check(rule,"9k"));
    }

    @Test
    public void testConvertSecond(){
        //given
        Checker checker = new CheckerImpl();
        Rule rule = new RuleImpl("vote", "==", 1500);

        //then
        assertTrue(checker.check(rule,"1,5k"));
    }
}
