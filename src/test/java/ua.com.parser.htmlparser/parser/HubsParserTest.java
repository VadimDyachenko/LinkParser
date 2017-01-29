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

import java.io.File;
import java.io.IOException;


import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Jsoup.class)

public class HubsParserTest {
    private Document doc_hubs;
    private Document doc_page2;

    @Before
    public void setUP() {
        File file_hubs = new File("src/test/resources/html/hubs_page1.html");
        File file_page2 = new File("src/test/resources/html/hubs_page2.html");
        try {
            doc_hubs = Jsoup.parse(file_hubs, "UTF-8");
            doc_page2 = Jsoup.parse(file_page2, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetLinks() {
        //given
        PowerMockito.mockStatic(Jsoup.class);
        Connection connection_hubs = Mockito.mock(Connection.class);
        Connection connection_page2 = Mockito.mock(Connection.class);

        try {
            PowerMockito.when(Jsoup.connect("https://habrahabr.ru/hubs")).thenReturn(connection_hubs);
            PowerMockito.when(Jsoup.connect("https://habrahabr.ru/hubs/page2")).thenReturn(connection_page2);
            Mockito.when(connection_hubs.get()).thenReturn(doc_hubs);
            Mockito.when(connection_page2.get()).thenReturn(doc_page2);

        } catch (IOException e) {
            e.printStackTrace();
        }
        HubsParser hubsParser = new HubsParser();
        //when

        //then
        assertEquals("[https://habrahabr.ru/hub/infosecurity/, " +
                "https://habrahabr.ru/hub/programming/, " +
                "https://habrahabr.ru/hub/gamedev/, " +
                "https://habrahabr.ru/hub/ui/, " +
                "https://habrahabr.ru/hub/unity3d/]", hubsParser.getHubs().toString());
    }
}
