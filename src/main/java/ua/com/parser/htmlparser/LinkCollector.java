package ua.com.parser.htmlparser;

import ua.com.parser.htmlparser.rule.Rule;
import ua.com.parser.htmlparser.parser.HubsParser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by vadim on 15.01.17.
 */
public class LinkCollector {
    private final String URL = "https://habrahabr.ru/hubs/";
    private HubsParser hubsParser;
    private List<Rule> rules;
    private List<String> links;

    public LinkCollector(List<Rule> conditions) {
        this.rules = conditions;
    }

    public LinkCollector() {
        hubsParser = new HubsParser();
    }


    public List<String> getLinks() {

        List<String> hubs = hubsParser.getHubs();

        if (hubs != null && !hubs.isEmpty()) {
            ExecutorService executor = Executors.newFixedThreadPool(10);
            List<Future<String>> list = new ArrayList<Future<String>>();
            Callable<List<String>> callable;

            for (int i = 0; i < hubs.size(); i++) {

            }
        }

        return links;
    }
}
