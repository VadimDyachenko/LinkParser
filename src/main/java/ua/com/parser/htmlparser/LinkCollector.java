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

public class LinkCollector {
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
        setupRules();

        fileWorker.write(getLinks());
        double end = System.currentTimeMillis();

        System.out.println("Run time = " + (end - start));

    }

    private void setupRules() {
        RulesBucket rulesBucket = new RulesBucket();
        rulesBucket.createRule(fileWorker.read());
        rules = rulesBucket.getRules();
    }

    private List<String> getLinks() {

        Map<Integer, String> links = new HashMap<>();
        List<String> hubs = hubsParser.getHubs();
        ConcurrentMap<Integer, String> checkedPosts = new ConcurrentHashMap<>();

        if (hubs != null && !hubs.isEmpty()) {
            ExecutorService executor = Executors.newFixedThreadPool(6);
            List<Future<Map<Integer, String>>> list = new ArrayList<>();

            for (String hub : hubs) {
                Callable<Map<Integer, String>> linkParser = new LinkParser(hub, rules, checkedPosts);
                Future<Map<Integer, String>> future = executor.submit(linkParser);
                list.add(future);
            }

            appendResults(links, list);
            executor.shutdown();
        }

        return new ArrayList<>(links.values());
    }

    private void appendResults(Map<Integer, String> links, List<Future<Map<Integer, String>>> list) {

        for (Future<Map<Integer, String>> future : list) {
            try {
                links.putAll(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
