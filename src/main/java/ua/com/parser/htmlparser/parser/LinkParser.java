package ua.com.parser.htmlparser.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.com.parser.htmlparser.rule.Rule;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by vadim on 16.01.17.
 */
public class LinkParser extends Parser implements Callable<Map<Integer,String>> {
    private String url;
    private List<Rule> rules;

    public LinkParser(String url, List<Rule> rules) {

        this.url = url;
        this.rules = rules;
    }

    @Override
    public Map<Integer, String> call() throws Exception {
        Map<Integer, String> result = new HashMap<>();

        String urlNextPage = url + "/page%s";

        try {
            result.putAll(getLinks(url));

            int start = 2; // start index to parse next page;
            for (int i = start; i <= getMaxPageNumber(url) ; i++) {
                String nextUrl = String.format(urlNextPage, i);
                result.putAll(getLinks(nextUrl));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to get a list of hubs: " + e.getMessage());
        }

        return result;
    }

    private Map<Integer, String> getLinks(String url) throws IOException {
        Map<Integer, String> result = new HashMap<>();

        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.getElementsByAttributeValue("class", "post post_teaser shortcuts_item");
        elements.forEach(element -> {
            String id = element.attr("id");
            Element aElement = element.child(2).child(0).child(0).child(1);
        });

        return result;
    }
}
