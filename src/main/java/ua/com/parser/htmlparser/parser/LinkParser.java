package ua.com.parser.htmlparser.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ua.com.parser.htmlparser.checker.Checker;
import ua.com.parser.htmlparser.checker.CheckerImpl;
import ua.com.parser.htmlparser.rule.Rule;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;

public class LinkParser extends Parser implements Callable<Map<Integer, String>> {
    private String url;
    private List<Rule> rules;
    private ConcurrentMap checkedPost;
    private Checker checker;

    public LinkParser(String url, List<Rule> rules, ConcurrentMap checkedPost) {
        this.url = url;
        this.rules = rules;
        this.checkedPost = checkedPost;
        checker = new CheckerImpl();
    }

    @Override
    public Map<Integer, String> call() {
        System.out.println("Start parsing url: " + url);
        Map<Integer, String> result = new HashMap<>();
        String nextUrl = "";
        try {
            result.putAll(getLinks(url));

            int start = 2; // start index to parse next page;
            for (int i = start; i <= getMaxPageNumber(url); i++) {
                nextUrl = String.format(url + "/page%s", i);
                result.putAll(getLinks(nextUrl));
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to get a list of pages: " + nextUrl + " : " + e.getMessage());
        }

        System.out.println("hub:" + url + " parsed successful");
        return result;
    }

    private Map<Integer, String> getLinks(String url) throws IOException {
        Map<Integer, String> result = new HashMap<>();
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.getElementsByAttributeValue("class", "post post_teaser shortcuts_item");

        elements.forEach(element -> {

            Integer id = Integer.parseInt(element.attr("id").replace("post_", ""));
            if (!checkedPost.containsKey(id)) {

                checkedPost.put(id, "");

                Element aElement = element.child(0).child(1).child(0);
                String link = aElement.attr("href");

                for (Rule rule : rules) {
                    if (checkRule(rule, element)) {
                        result.put(id, link);
                        break;
                    }
                }
            }
        });

        return result;
    }

    private boolean checkRule(Rule rule, Element element) {
        boolean result = false;

        Elements elements = element.getElementsByAttributeValue("class", checker.getParseValue(rule.getKey()));

        if (!elements.isEmpty()) {
            for (Element innerElement: elements) {
                result = checker.check(rule, innerElement.text());
            }
        }
        return result;
    }
}