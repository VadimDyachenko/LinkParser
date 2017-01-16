package ua.com.parser.htmlparser;

import java.util.List;

/**
 * Created by vadim on 15.01.17.
 */
public class LinkCollector {
    private final String URL = "https://habrahabr.ru/hubs/";

    private List<String> conditions;
    private List<String> links;

    public LinkCollector(List<String> conditions) {
        this.conditions = conditions;
    }

    public LinkCollector() {

    }


    public List<String> getLinks() {
        return links;
    }
}
