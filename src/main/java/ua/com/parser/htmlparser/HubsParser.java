package ua.com.parser.htmlparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vadim on 16.01.17.
 */

public class HubsParser {

    public List<String> getHubs() throws IOException {
        List<String> result = new ArrayList<>();

        String url = "https://habrahabr.ru/hubs";
        String urlNextPages = url + "/page%s";

        result.addAll(getHubLinks(url));

        int start = 2; // start index to parse next page;
        for (int i = start; i <= getMaxPageNumber(url) ; i++) {
            String nextUrl =String.format(urlNextPages, i);
            result.addAll(getHubLinks(nextUrl));
        }

        return result;
    }

    private List<String> getHubLinks(String url) throws IOException {
        List<String> result = new ArrayList<>();

        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.getElementsByAttributeValue("class", "info");
        elements.forEach(element -> {
            Element aElement = element.child(0).child(0);
            result.add(aElement.attr("href"));
        });

        return result;
    }

    private int getMaxPageNumber(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.getElementById("nav-pages").getElementsByTag("li");
        String lastLink = links.last().child(0).attr("href");

        return parseNumber(lastLink);
    }

    private int parseNumber(String lastLink) {

        return Integer.parseInt(lastLink.substring(lastLink.indexOf("page") + 4, lastLink.lastIndexOf('/')));
    }
}
