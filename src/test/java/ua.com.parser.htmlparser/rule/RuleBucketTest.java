package ua.com.parser.htmlparser.rule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RuleBucketTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void testCreateRule() {
        //given
        RulesBucket bucket = new RulesBucket();
        List<String> strings = new ArrayList<>();
        strings.add("vote>+100");
        strings.add("view>-1");
        strings.add("favorite==10");

        //when
        bucket.createRule(strings);

        //then
        assertEquals(100, bucket.getRules().get(0).getValue() );
        assertEquals(-1, bucket.getRules().get(1).getValue() );
        assertEquals(10, bucket.getRules().get(2).getValue() );
    }

    @Test
    public void testGetValueNumberFormatException() {
        //given
        RulesBucket bucket = new RulesBucket();
        List<String> strings = new ArrayList<>();
        strings.add("vote>abcd");

        //when
        bucket.createRule(strings);

        //then
        assertEquals("Rule: \"vote>abcd\" ignored, illegal argument: For input string: \"abcd\"\n",
                outContent.toString());
    }

    @Test
    public void tetsGetUnsupportedKey() {
        //given
        RulesBucket bucket = new RulesBucket();
        List<String> strings = new ArrayList<>();
        strings.add("wrongKey!=20");

        //when
        bucket.createRule(strings);

        //then
        assertEquals("Rule: \"wrongKey!=20\" ignored, illegal argument: Unsupported Key\n",
                outContent.toString());
    }

}
