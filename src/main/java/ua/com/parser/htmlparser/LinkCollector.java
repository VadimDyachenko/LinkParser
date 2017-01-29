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

    private FileWorker fileWorker;

    public LinkCollector(FileWorker fileWorker) {
        this.fileWorker = fileWorker;
    }

    public void run() {
        double start = System.currentTimeMillis();
        System.out.println("Program running...");

        List<Rule> rules = setupRules();
        fileWorker.write(getLinks(rules));

        double end = System.currentTimeMillis();
        System.out.println("Run time = " + (end - start) + "ms");
    }

    private List<Rule> setupRules() {
        RulesBucket rulesBucket = new RulesBucket();
        rulesBucket.createRule(fileWorker.read());
        return rulesBucket.getRules();
    }

    private List<String> getLinks(List<Rule> rules) {
        List<String> hubs = new HubsParser().getHubs();
        ConcurrentMap<Integer, String> checkedPosts = new ConcurrentHashMap<>();

        List<Future<Map<Integer, String>>> list = new ArrayList<>();
        if (hubs != null && !hubs.isEmpty()) {
            ExecutorService executor = Executors.newFixedThreadPool(6);

            for (String hub : hubs) {
                Callable<Map<Integer, String>> linkParser = new LinkParser(hub, rules, checkedPosts);
                Future<Map<Integer, String>> future = executor.submit(linkParser);
                list.add(future);
            }

            executor.shutdown();
        }

        return new ArrayList<>(appendResults(list).values());
    }

    private Map<Integer, String> appendResults(List<Future<Map<Integer, String>>> list) {
        Map<Integer, String> result = new HashMap<>();
        for (Future<Map<Integer, String>> future : list) {
            try {
                if (future != null && !future.get().isEmpty()) {
                    result.putAll(future.get());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
