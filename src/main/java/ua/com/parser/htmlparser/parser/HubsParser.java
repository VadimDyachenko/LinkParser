package ua.com.parser.htmlparser.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HubsParser extends Parser {

    public List<String> getHubs() {
        List<String> result = new ArrayList<>();

        String url = "https://habrahabr.ru/hubs";

        try {
            result.addAll(getHubLinks(url));

// Do not uncomment this if you do not want to banned of habrahabr ! :)

//            String urlNextPages = url + "/page%s";
//            int start = 2; // start index to parse next page;
//            for (int i = start; i <= getMaxPageNumber(url); i++) {
//                String nextUrl = String.format(urlNextPages, i);
//                result.addAll(getHubLinks(nextUrl));
//            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to get a list of hubs: " + e.getMessage());
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
}
