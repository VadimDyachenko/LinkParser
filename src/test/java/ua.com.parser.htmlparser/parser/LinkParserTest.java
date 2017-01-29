package ua.com.parser.htmlparser.parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ua.com.parser.htmlparser.rule.Key;
import ua.com.parser.htmlparser.rule.Rule;
import ua.com.parser.htmlparser.rule.RuleImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Jsoup.class)

public class LinkParserTest {
    private Document post_page1;
    private Document post_page2;
    private Connection connectionPage1;
    private Connection connectionPage2;
    ;

    @Before
    public void setUP() {
        File file_hubs = new File("src/test/resources/html/post_page1.html");
        File file_page2 = new File("src/test/resources/html/post_page2.html");
        try {
            post_page1 = Jsoup.parse(file_hubs, "UTF-8");
            post_page2 = Jsoup.parse(file_page2, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        PowerMockito.mockStatic(Jsoup.class);
        connectionPage1 = Mockito.mock(Connection.class);
        connectionPage2 = Mockito.mock(Connection.class);
    }

    @Test
    public void callTest() throws IOException {
        //given
        List<Rule> rules = new ArrayList<>();
        rules.add(new RuleImpl(Key.VIEW, ">", 29000));
        ConcurrentMap<Integer, String> checkedPosts = new ConcurrentHashMap<>();

        PowerMockito.when(Jsoup.connect("https://posts")).thenReturn(connectionPage1);
        PowerMockito.when(Jsoup.connect("https://posts/page2")).thenReturn(connectionPage2);
        Mockito.when(connectionPage1.get()).thenReturn(post_page1);
        Mockito.when(connectionPage2.get()).thenReturn(post_page2);

        Map<Integer, String> expected = new HashMap<>();
        expected.put(319360, "https://habrahabr.ru/post/319360/");

        //when
        LinkParser parser = new LinkParser("https://posts", rules, checkedPosts);

        //then
        assertEquals(expected.toString(), parser.call().toString());
    }
}
