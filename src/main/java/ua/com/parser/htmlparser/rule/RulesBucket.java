package ua.com.parser.htmlparser.rule;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vadim on 16.01.17.
 */
public class RulesBucket {

    List<Rule> rules = new LinkedList<>();

    public void createRule(List<String> requests) {
        Pattern pattern = Pattern.compile("[><=!]+");
        for (String query : requests) {
            Matcher matcher = pattern.matcher(query);
            if(matcher.find()) {
                String key = query.substring(0, matcher.start());
                String condition = query.substring(matcher.start(), matcher.end());
                String value = query.substring(matcher.end());
                rules.add(new RuleImpl(key, condition, value));
            }
        }
    }

    public List<Rule> getRules() {
        return rules;
    }
}
