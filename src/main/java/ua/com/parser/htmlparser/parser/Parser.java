package ua.com.parser.htmlparser.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

abstract class Parser {

    int getMaxPageNumber(String url) throws IOException {

        Document doc = Jsoup.connect(url).get();
        Elements links = doc.getElementById("nav-pages").getElementsByTag("li");
        String lastLink = links.last().child(0).attr("href");

        return lastLink.isEmpty() ? 0 : parseNumber(lastLink);
    }

    private int parseNumber(String lastLink) {
        return Integer.parseInt(lastLink.substring(lastLink.indexOf("page") + 4, lastLink.lastIndexOf('/')));
    }
}
