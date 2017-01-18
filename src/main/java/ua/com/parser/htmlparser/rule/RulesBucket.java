package ua.com.parser.htmlparser.rule;


import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RulesBucket {

    private List<Rule> rules = new LinkedList<>();

    public void createRule(List<String> requests) {

        Pattern pattern = Pattern.compile("[><=!]+");

        for (String str : requests) {

            Matcher matcher = pattern.matcher(str);

            if(matcher.find()) {

                String key = str.substring(0, matcher.start());
                String condition = str.substring(matcher.start(), matcher.end());
                int value = 0;
                try {
                    value = Integer.parseInt(str.substring(matcher.end()).replace("+",""));
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }

                rules.add(new RuleImpl(key, condition, value));
            }
        }
    }

    public List<Rule> getRules() {
        return rules;
    }
}
