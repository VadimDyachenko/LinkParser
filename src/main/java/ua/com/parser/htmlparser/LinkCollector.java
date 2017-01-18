package ua.com.parser.htmlparser;

import ua.com.parser.htmlparser.fileworker.FileWorker;
import ua.com.parser.htmlparser.parser.LinkParser;
import ua.com.parser.htmlparser.rule.Rule;
import ua.com.parser.htmlparser.parser.HubsParser;
import ua.com.parser.htmlparser.rule.RulesBucket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by vadim on 15.01.17.
 */
public class LinkCollector {
    private final String URL = "https://habrahabr.ru/hubs/";
    private HubsParser hubsParser;
    private List<Rule> rules;
    private FileWorker fileWorker;

    public LinkCollector(FileWorker fileWorker) {
        this.fileWorker = fileWorker;
    }

    public void run() {
        double start = System.currentTimeMillis();
        System.out.println("Program running...");
        hubsParser = new HubsParser();
        RulesBucket rulesBucket = new RulesBucket();
        rulesBucket.createRule(fileWorker.read());
        rules = rulesBucket.getRules();
        fileWorker.write(getLinks());
        double end = System.currentTimeMillis();
        System.out.println("Run time = " + (end - start));

    }

    private List<String> getLinks() {

        Map<Integer, String> links = new HashMap<>();
        List<String> hubs = hubsParser.getHubs();
        ConcurrentMap<Integer, String> checkedPosts = new ConcurrentHashMap<>();

        if (hubs != null && !hubs.isEmpty()) {
            ExecutorService executor = Executors.newFixedThreadPool(7);
            List<Future<Map<Integer, String>>> list = new ArrayList<>();

            for (String hub : hubs) {
                Callable<Map<Integer, String>> linkParser = new LinkParser(hub, rules, checkedPosts);
                Future<Map<Integer, String>> future = executor.submit(linkParser);
                list.add(future);
            }

            for (Future<Map<Integer, String>> future : list) {
                try {
                    links.putAll(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            executor.shutdown();
        }

        return new ArrayList<>(links.values());
    }
}
